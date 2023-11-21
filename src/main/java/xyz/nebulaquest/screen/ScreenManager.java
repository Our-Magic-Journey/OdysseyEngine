package xyz.nebulaquest.screen;

import java.util.HashMap;
import java.util.Optional;

import xyz.nebulaquest.event.Event;
import xyz.nebulaquest.event.EventGetter;
import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.resource.ResourceManager;

/**
 * Manages different screens in the game, allowing screen transitions, updates, and drawing.
 * It provides methods to handle the selected screen's updates, drawing, and closing the game.
 * 
 * <p>See the constructor implementation to view all registered screens.</p>
 *
 * @see Screen
 * @see InputManager
 * @see Renderer
 */
public class ScreenManager {
  // HashMap to store registered screens with their respective names.
  public HashMap<String, Screen> screens;
  
  // The name of the currently selected screen.
  private String selected;

  // Name of the screen that will replace the current screen in the next frame.
  private Optional<String> nextScreen;

  // This event should be used to let the Game class know that one of the screens wants
  // to close the game (for example, the quit button was clicked in the main menu).
  private Event closeGameEvent;

  /** 
   * Constructs a new ScreenManager instance.
   *
   * @param inputManager The InputManager instance for handling user input.
   * @param resourceManager The ResourceManager instance for accessing game resources.
   */
  public ScreenManager(InputManager inputManager, ResourceManager resourceManager) {
    this.screens = new HashMap<>();
    this.closeGameEvent = new Event();
    this.selected = "loading";
    this.nextScreen = Optional.empty();

    // Register default screens and load the initial screen.
    registerScreen("loading", new LoadingScreen(inputManager, this, resourceManager));
    registerScreen("menu", new MenuScreen(inputManager, this, resourceManager));
    registerScreen("credits", new CreditsScreen(inputManager, this, resourceManager));
    current().load();
  }

  /**
   * Registers a new screen with a specified name.
   *
   * @param name  The name of the screen.
   * @param screen The Screen instance to be registered.
   */
  private void registerScreen(String name, Screen screen) {
    screens.put(name, screen);
  }

  /**
   * Returns an {@link EventGetter} for the "game close" event.
   * 
   * <p>The "game close" event is emitted when an action is performed that should close the game 
   * (for example, the quit button was clicked in the main menu).</p>
   * 
   * <p>Subscribe a function to this event; it will be called when selected screen uses the {@code ScreenManager.closeGame} method.</p>
   *
   * @return An {@link EventGetter} for the "game close" event.
   */
  public EventGetter onGameClose() {
    return this.closeGameEvent;
  }

  /**
   * Closes the game.
   * 
   * <p><b>NOTE:</b> It only triggers the "game close" event, indicating that the game should be closed.
   * Remember to connect the actual game closing function to the {@code onGameClose} event. </p>
   */
  public void closeGame() {
    this.closeGameEvent.emit();
  }

  /** 
   * Returns the currently selected screen.
   *
   * @return The currently selected screen.
   */
  private Screen current() {
    return screens.get(selected);
  }

  /**
   * Changes the current screen to the one with the specified name.
   * If the target screen is not registered, the method does nothing.
   *
   * <p><b>NOTE:</b> This method only informs the ScreenManager that you want to change 
   * the screen; the actual change will be done at the beginning of the next frame to ensure 
   * the screen is not being used by the engine while the screen change occurs.</p>
   * 
   * @param state The name of the screen to change to.
   */
  public void change(String state) {
    if (!isScreenRegistered(state)) {
      return;
    }

    nextScreen = Optional.of(state);
  }

  /**
   * Checks if a screen with the specified name is registered.
   *
   * @param key The name of the screen to check.
   * @return True if the screen is registered; false otherwise.
   */
  private boolean isScreenRegistered(String key) {
    return screens.containsKey(key);
  }

  /**
   * Updates the current screen and loads the next screen if specified.
   *
   * @param deltaTime The time passed since the last update (in ms).
   */
  public void update(long deltaTime) {
    loadNextScreen();    
    current().update(deltaTime);
  }

  /** Loads the next screen if there is one specified. */
  private void loadNextScreen() {
    if (!nextScreen.isPresent()) {
      return;
    }
    
    current().unload();
    selected = nextScreen.get();
    current().load();

    nextScreen = Optional.empty();
  }

  /**
   * Draws the current screen using the provided renderer.
   *
   * @param renderer The renderer used to draw the screen.
   */
  public void draw(Renderer renderer) {
    current().draw(renderer);
  }
}
