package xyz.nebulaquest.renderer;

import java.awt.Dimension;

import javax.swing.JPanel;

import xyz.nebulaquest.event.Event;
import xyz.nebulaquest.event.EventGetter;

public class Canvas extends JPanel {
  private int width;
  private int height;
  private int scale;

  private Event readyEvent;

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

    this.readyEvent = new Event();

    setPreferredSize(new Dimension(width * scale, height * scale));
    setFocusable(true);
    requestFocus();
  }

  public EventGetter onReady() {
    return readyEvent;
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
    readyEvent.emit();
  }
}
