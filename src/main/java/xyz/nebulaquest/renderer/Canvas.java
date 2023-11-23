package xyz.nebulaquest.renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import xyz.nebulaquest.event.Event;
import xyz.nebulaquest.event.EventGetter;
import xyz.nebulaquest.math.Vector;

/** A Canvas component */
public class Canvas extends JPanel {
  private int targetWidth;
  private int targetHeight;
  private float scaleFactor;
  private int canvasWidth;
  private int canvasHeight;
  private Vector canvasPosition;
  private float zoom;

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
   * @param width  The target width of the canvas.
   * @param height The target height of the canvas.
   */
  public Canvas(int width, int height) {
    super();
    
    this.targetWidth = width;
    this.targetHeight = height;
    this.canvasWidth = width;
    this.canvasHeight = height;
    this.canvasPosition = new Vector();
    this.zoom = 1;

    this.readyEvent = new Event();

    setPreferredSize(new Dimension(width, height));
    setFocusable(true);
    requestFocus();
    listenCanvasResize();
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
   * Listens for canvas resize events and triggers the canvas resizing process.
   */
  private void listenCanvasResize() {
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resizeCanvas();
      }
    });
  }

  /**
   * Resizes the canvas based on the current size and zoom factor.
   * Recalculates the scale factor, dimensions, and position.
   */
  private void resizeCanvas() {
    calculateScaleFactor();
    calculateDimensions();
    calculatePosition();
  }

  /**
   * Calculates the scale factor for the canvas based on the current size and zoom factor.
   *
   * <p><b>WHY?</b> We calculate the scale factor to maintain a constant proportion of the canvas, regardless of the new window size after resizing.</p>
   */
  private void calculateScaleFactor() {
    float scaleX = (float) (this.getWidth() * zoom) / (targetWidth);
    float scaleY = (float) (this.getHeight() * zoom) / (targetHeight);

    scaleFactor = Math.min(scaleX, scaleY);
  }

  /**
   * Calculates the new canvas dimensions after resizing, based on the scale factor.
   */
  private void calculateDimensions() {
    canvasWidth = (int) (targetWidth * scaleFactor);
    canvasHeight = (int) (targetHeight * scaleFactor);
  }

  /**
   * Calculates the new canvas position, keeping it in the center of the window.
   */
  private void calculatePosition() {
    this.canvasPosition = new Vector(
      (getWidth() - canvasWidth) / 2,
      (getHeight() - canvasHeight) / 2
    );
  }

  /**
   * Sets the zoom of the canvas.
   *
   * @param zoom The new zoom factor.
   */
  public void setZoom(float zoom) {
    this.zoom = zoom;

    resizeCanvas();
  }

  /**
   * Gets the current zoom of the canvas
   *
   * @return The current zoom factor.
   */
  public float getZoom() {
    return this.zoom;
  }

  /**
   * Gets the width of the canvas.
   *
   * @return The width of the canvas.
   */
  public int getCanvasWidth() {
    return canvasWidth;
  }

  /**
   * Gets the height of the canvas.
   *
   * @return The height of the canvas.
   */
  public int getCanvasHeight() {
    return canvasHeight;
  }

  /**
   * Converts a point from window coordinates to canvas coordinates.
   *
   * <p><b>WHY?</b> After resizing the canvas, its position and size have changed,
   * significantly complicating logic such as button hover detection.
   * However, using this method, you can convert positions of elements outside the canvas
   * (e.g., cursor) to canvas coordinate system, allowing operations as if the canvas still
   * had its original position and size.</p>
   *
   * @param point The point in window coordinates.
   * @return The point in canvas coordinates.
   */
  public Vector pointInCanvas(Vector point) {
    return Vector.subtraction(point, canvasPosition).divide(scaleFactor);
  }

  /**
   * Converts a point from canvas coordinates to window coordinates.
   *
   * @param point The point in canvas coordinates.
   * @return The point in window coordinates.
   */
  public Vector canvasPointToWindow(Vector point) {
    return Vector.addition(point, canvasPosition);
  }

  /**
   * Scales a size from window size to canvas size.
   *
   * @param size The size in window coordinates.
   * @return The scaled size in canvas coordinates.
   */
  public int scaleToWindow(int size) {
    return (int)(size * scaleFactor);
  }


  /**
   * Scales a size from canvas size to window size.
   *
   * @param size The size in canvas coordinates.
   * @return The scaled size in window coordinates.
   */
  public int scaleToCanvas(int size) {
    return (int)(size / scaleFactor);
  }

  /**
   * Gets the width of the window
   */
  @Override
  public int getWidth() {
    return super.getWidth();
  }

  /**
   * Gets the height of the window
   */
  @Override
  public int getHeight() {
    return super.getHeight();
  }

  /**
   * Draws a black border around the canvas to fill empty spaces.
   *
   * @param screen The graphics context to draw on.
   */
  public void drawBorder(Graphics screen) {
    int screenWidth = this.getWidth();
    int screenHeight = this.getHeight();

    screen.setColor(Color.BLACK);

    //top
    screen.fillRect(0, 0, screenWidth, canvasPosition.getRoundY());

    //left
    screen.fillRect(0, canvasPosition.getRoundY(), canvasPosition.getRoundX(), screenHeight);

    //right
    screen.fillRect(canvasPosition.getRoundX() + canvasWidth, canvasPosition.getRoundY(), screenWidth - (canvasPosition.getRoundX() + canvasWidth), canvasHeight);

    //bottom 
    screen.fillRect(0, canvasPosition.getRoundY() + canvasHeight, screenWidth, screenHeight - (canvasPosition.getRoundY() + canvasHeight));
  }

  @Override
  public void addNotify() {
    super.addNotify();
    readyEvent.emit();
  }
}
