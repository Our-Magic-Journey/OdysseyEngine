package xyz.magicjourney.odyssey.renderer;

import javax.swing.JFrame;

/**
 * Represents a game window.
 */
public class Window {
  private String title;
  private JFrame window;

  /**
   * Constructs a window with the specified title.
   *
   * @param title The title of the window.
   */
  public Window(String title) {
    this.title = title;
    this.window = new JFrame(title);

    this.configure();
  }

  /** Configures the window settings. */
  private void configure() {
    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.window.setResizable(true);
    this.window.pack();
    this.window.setVisible(true);
  }

  /**
   * Attaches a canvas to the window.
   *
   * @param canvas The canvas to be attached.
   */
  public void attachCanvas(Canvas canvas) {
    this.window.setContentPane(canvas);
    this.window.pack();
  }

  /**
   * Gets the title of the window.
   *
   * @return The title of the window.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Closes the window.
   */
  public void close(){
    this.window.dispose();
  }
}
