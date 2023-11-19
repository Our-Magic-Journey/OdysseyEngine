package xyz.nebulaquest.renderer;

import java.awt.Dimension;

import javax.swing.JPanel;

import xyz.nebulaquest.event.Event;
import xyz.nebulaquest.event.EventGetter;

/** A Canvas component */
public class Canvas extends JPanel {
  private int width;
  private int height;

  private Event readyEvent;

  /**
   * Constructs a canvas with default dimensions (320x240).
   */
  public Canvas() {
    this(320, 240);
  }

  /**
   * Constructs a canvas with the specified dimensions
   *
   * @param width  The width of the canvas.
   * @param height The height of the canvas.
   */
  public Canvas(int width, int height) {
    super();
    
    this.width = width;
    this.height = height;
    
    this.readyEvent = new Event();

    setPreferredSize(new Dimension(width, height));
    setFocusable(true);
    requestFocus();
  }

  /**
   * Returns an {@link EventGetter} for the "ready" event.
   *
   * <p>The "ready" event is emitted when the canvas is ready to draw.</p>
   * <p>Subscribe a function to this event; when the canvas is ready to draw, the subscribed function will be called.</p>
   *
   * @return An {@link EventGetter} for the "ready" event.
   */
  public EventGetter onReady() {
    return readyEvent;
  }

  /**
   * Gets the width of the canvas.
   *
   * @return The width of the canvas.
   */
  public int getCanvasWidth() {
    return width;
  }

  /**
   * Gets the height of the canvas.
   *
   * @return The height of the canvas.
   */
  public int getCanvasHeight() {
    return height;
  }

  @Override
  public void addNotify() {
    super.addNotify();
    readyEvent.emit();
  }
}
