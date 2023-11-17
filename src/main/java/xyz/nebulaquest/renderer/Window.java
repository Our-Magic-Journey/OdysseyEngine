package xyz.nebulaquest.renderer;

import javax.swing.JFrame;

public class Window {
  private String title;
  private JFrame window;

  public Window(String title) {
    this.title = title;
    this.window = new JFrame(title);

    this.configure();
  }

  private void configure() {
    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.window.setResizable(false);
    this.window.pack();
    this.window.setVisible(true);
  }

  public void attachCanvas(Canvas canvas) {
    this.window.setContentPane(canvas);
    this.window.pack();
  }

  public String getTitle() {
    return title;
  }

  public void close(){
    this.window.dispose();
  }
}
