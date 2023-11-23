package xyz.nebulaquest.screen;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Canvas;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.resource.ResourceManager;

/** Represents a screen in the game, providing methods for loading, unloading, updating, and drawing. */
public abstract class Screen {
  protected InputManager inputManager;
  protected ResourceManager resourceManager;
  protected Canvas canvas;

  // Can be used to change active screen or closing the game.
  protected ScreenManager screenManager;
  
  /**
   * Constructs a new screen with the specified input manager and screen manager.
   * 
   * @param inputManager The input manager for handling user input.
   * @param screenManager The screen manager for closing game and screen navigation.
   * @param resourceManager The resource manager for accessing game resources.
   * @param canvas The canvas for calculations based on its size and position.
   */
  public Screen(InputManager inputManager, ScreenManager screenManager, ResourceManager resourceManager, Canvas canvas) {
    this.inputManager = inputManager;
    this.screenManager = screenManager;
    this.resourceManager = resourceManager;
    this.canvas = canvas;
  }

  /** Initializes the screen and its resources. */
  public abstract void load();

  /** Cleans up the resources used by the screen. */
  public abstract void unload();

  /**
   * Updates the logic of the screen.
   * 
   * @param deltaTime The time passed since the last update (in milliseconds).
   */
  public abstract void update(long deltaTime);
  
  /**
   * Renders the screen using the provided renderer.
   * 
   * @param renderer The renderer used to draw the screen.
   */
  public abstract void draw(Renderer renderer);
}
