package xyz.nebulaquest;

import javax.swing.JFrame;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.renderer.Canvas;
import xyz.nebulaquest.screen.ScreenManager;

public class Game implements Runnable {
  private JFrame window;
  private Canvas canvas;
  private Renderer renderer;
  private InputManager inputManager;
  private ScreenManager screenManager;
  private Thread thread;
  private boolean running;
  private long lastFrame;

  public static void main(String[] args) {
    new Game();
  }

  public Game() {
    window = new JFrame("Nebula Quest");
    canvas = new Canvas(960, 540, 1);
    renderer = new Renderer(canvas);
    inputManager = new InputManager();
    screenManager = new ScreenManager(inputManager);

    canvas.onReady(() -> start());
    screenManager.onGameClose(() -> close());

    prepareWindow();
  }

  private void prepareWindow() {
    window.setContentPane(canvas);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.pack();
    window.setVisible(true);
  }

  private void start() {
    initThread();
  }

  private void initThread() {
    if (thread == null) {
      thread = new Thread(this);
      thread.start();
    }
  }

  public void close() {
    this.running = false;
  }

  @Override
  public void run() {
    this.running = true;
    this.lastFrame = System.currentTimeMillis();
    this.gameLoop();
  }

  public void gameLoop() {
    while (running) {
      long deltaTime = calculateLastFrameDuration();
      
      screenManager.update(deltaTime);
      screenManager.draw(renderer);
      renderer.drawOnScreen();
    }
  }

  private long calculateLastFrameDuration() {
    long current = System.currentTimeMillis();
    long duration = lastFrame - current;

    lastFrame = current;

    return duration;
  }
}
