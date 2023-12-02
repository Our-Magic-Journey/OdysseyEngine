package xyz.magicjourney.odyssey;

import xyz.magicjourney.odyssey.input.InputManager;
import xyz.magicjourney.odyssey.renderer.Canvas;
import xyz.magicjourney.odyssey.renderer.Renderer;
import xyz.magicjourney.odyssey.renderer.Window;
import xyz.magicjourney.odyssey.resource.ResourceManager;
import xyz.magicjourney.odyssey.screen.AbstractLoadingScreen;
import xyz.magicjourney.odyssey.screen.Screen;
import xyz.magicjourney.odyssey.screen.ScreenConstructor;
import xyz.magicjourney.odyssey.screen.ScreenManager;

/**
 * The main class of the Odyssey engine.
 * It manages the game loop, input, rendering, and screen transitions.
 *
 * <p>The game initializes components such as the window, canvas, renderer,
 * input manager, and screen manager. It runs on a separate thread to keep
 * the UI responsive and handles the main game loop, updating screens,
 * and drawing on the canvas.</p>
 *
 * @see Window
 * @see Canvas
 * @see Renderer
 * @see InputManager
 * @see ScreenManager
 */
public class Engine {
  private Window window;
  private Canvas canvas;
  private Renderer renderer;
  private InputManager inputManager;
  private ScreenManager screenManager;
  private ResourceManager resourceManager;
  private Thread thread;
  private boolean running;
  private long lastFrame;

  /**
   * Creates an instance of the game engine with a default window size.
   *
   * @param title The title of the game window.
   */
  public Engine(String title) {
    this(title, 960, 540);
  }

  /**
   * Creates an instance of the game engine with a specified window size.
   *
   * @param title  The title of the game window.
   * @param width  The width of the game window.
   * @param height The height of the game window.
   */
  public Engine(String title, int width, int height) {
    window = new Window(title);
    canvas = new Canvas(width, height);
    renderer = new Renderer(canvas);
    inputManager = new InputManager();
    resourceManager = new ResourceManager();
    screenManager = new ScreenManager(inputManager, resourceManager, canvas);
  }
  
  /**
   * Registers a new loading screen to be displayed while resources are being loaded.
   *
   * @param loadingScreen The constructor for the loading screen.
   * 
   * @see AbstractLoadingScreen
   */
  public void setLoadingScreen(ScreenConstructor loadingScreen) {
    screenManager.setLoadingScreen(loadingScreen.create(inputManager, screenManager, resourceManager, canvas));
  }
  
  /**
   * Registers a new game screen with a specified name.
   *
   * <p>Example usage: {@code registerScreen("menu", MenuScreen::new)}</p> 
   * 
   * @param name   The name of the screen.
   * @param screen The constructor for the screen.
   * 
   * @see Screen
   * @see ScreenConstructor
   */
  public void registerScreen(String name, ScreenConstructor screen) {
    screenManager.registerScreen(name, screen.create(inputManager, screenManager, resourceManager, canvas));
  }

  /**
   * Start the game.
   * Observes the canvas for input events and starts the game loop.
   */
  public void start() {
    canvas.onReady().subscribe(() -> {
      inputManager.observe(canvas);
      initThread();
    });
    
    screenManager.onGameClose().subscribe(this::close);

    resourceManager.startLoading();
    window.attachCanvas(canvas);
  }

  /**
   * Initializes the game thread if it's not already running.
   */
  private void initThread() {
    if (thread == null) {
      thread = new Thread(this::logicThread);
      thread.start();
    }
  }

  /**
   * Closes the game by setting the running flag to false.
   */
  public void close() {
    this.running = false;
  }

  /**
   * The main game loop that runs on a separate thread.
   * It continuously updates screens and renders on the canvas.
   * Exits the loop when the game is set to close.
   */
  public void logicThread() {
    this.running = true;
    this.lastFrame = System.currentTimeMillis();
    this.gameLoop();
  }

  /**
   * The core game loop that updates screens and renders on the canvas.
   * Calculates the time elapsed between frames for smooth updates.
   */
  public void gameLoop() {
    while (running) {
      long deltaTime = calculateLastFrameDuration();

      screenManager.update(deltaTime);
      screenManager.draw(renderer);
      renderer.drawOnScreen();
    }

    window.close();
  }

  /**
   * Calculates the duration of the last frame in milliseconds.
   * Updates the last frame timestamp for the next calculation.
   *
   * @return The duration of the last frame in milliseconds.
   */
  private long calculateLastFrameDuration() {
    long current = System.currentTimeMillis();
    long duration = current - lastFrame;

    lastFrame = current;

    return duration;
  }
}
