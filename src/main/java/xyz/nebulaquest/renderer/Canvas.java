package xyz.nebulaquest.renderer;

import java.awt.Dimension;
import java.util.Optional;

import javax.swing.JPanel;

public class Canvas extends JPanel {
  private int width;
  private int height;
  private int scale;

  private Optional<Runnable> readyHandler;

  public Canvas() {
    this(320, 240, 2);
  }

  public Canvas(int width, int height) {
    this(width, height, 2);
  }

  public Canvas(int width, int height, int scale) {
    super();
    
    this.width = width;
    this.height = height;
    this.scale = scale;

    this.readyHandler = Optional.empty();

    setPreferredSize(new Dimension(width * scale, height * scale));
    setFocusable(true);
    requestFocus();
  }

  public void onReady(Runnable callback) {
    this.readyHandler = Optional.of(callback);
  }

  public int getCanvasWidth() {
    return width;
  }

  public int getCanvasHeight() {
    return height;
  }

  public int getScale() {
    return scale;
  }

  @Override
  public void addNotify() {
    super.addNotify();

    if (readyHandler.isPresent()) {
      readyHandler.get().run();
    }
  }
}
