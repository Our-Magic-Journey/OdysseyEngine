package xyz.nebulaquest;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.renderer.Window;
import xyz.nebulaquest.renderer.Canvas;
import xyz.nebulaquest.screen.ScreenManager;

/**
 * The main class of the NebulaQuest game.
 * It manages the game loop, input, rendering, and screen transitions.
 *
 * <p>The game initializes components such as the window, canvas, renderer,
 * input manager, and screen manager. It runs on a separate thread to keep
 * the UI responsive and handles the main game loop, updating screens,
 * and drawing on the canvas.</p>
 *
 * <p>The main method creates an instance of the game, triggering the setup
 * and starting the game loop when the canvas is ready.</p>
 *
 * @see Window
 * @see Canvas
 * @see Renderer
 * @see InputManager
 * @see ScreenManager
 */
public class Game implements Runnable {
  private Window window;
  private Canvas canvas;
  private Renderer renderer;
  private InputManager inputManager;
  private ScreenManager screenManager;
  private Thread thread;
  private boolean running;
  private long lastFrame;

  /**
  * The main method that creates an instance of the game.
  */
  public static void main(String[] args) {
    new Game();
  }

  public Game() {
    window = new Window("Nebula Quest");
    canvas = new Canvas(960, 540);
    renderer = new Renderer(canvas);
    inputManager = new InputManager();
    screenManager = new ScreenManager(inputManager);

    canvas.onReady().subscribe(this::start);
    screenManager.onGameClose().subscribe(this::close);

    window.attachCanvas(canvas);
  }

  /**
   * Initiates the game setup when the canvas is ready.
   * Observes the canvas for input events and starts the game loop.
   */
  private void start() {
    inputManager.observe(canvas);
    initThread();
  }

  /**
   * Initializes the game thread if it's not already running.
   */
  private void initThread() {
    if (thread == null) {
      thread = new Thread(this);
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
  @Override
  public void run() {
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
