package xyz.nebulaquest.renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * The renderer class is responsible for drawing objects on the canvas.
 */
public class Renderer {
  private Canvas canvas;
  private BufferedImage buffer;
  private Graphics2D graphic;
  private float scale;

  /**
   * Constructs a renderer with the specified canvas.
   *
   * @param canvas The canvas to render on.
   */
  public Renderer(Canvas canvas) {
    this.canvas = canvas;
    this.buffer = new BufferedImage(canvas.getCanvasWidth(), canvas.getCanvasHeight(), BufferedImage.TYPE_INT_RGB);
    this.graphic = (Graphics2D)buffer.getGraphics();
    this.scale = 1f;
  }

  /**
   * Draws a drawable object on the buffer.
   *
   * <p><b>NOTE:</b> For performance reasons, this method draws the object on the buffer image. 
   * To see the result, you need to call the {@code drawOnScreen} method.</p>
   * 
   * @param object The object to be drawn.
   */
  public void draw(Drawable object) {
    object.draw(graphic, canvas);
  }

  /**
   * Gets the current scale factor applied to the canvas.
   *
   * @return The scale factor of the canvas.
   */
  public float getScale() {
    return scale;
  }

 /**
  * Draws the buffer on the screen.
  */
  public void drawOnScreen() {
    Graphics screen = canvas.getGraphics();
    screen.drawImage(buffer, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
    screen.dispose();
  }
  
  public int getWidth() {
    return canvas.getCanvasWidth();
  }

  public int getHeight() {
    return canvas.getCanvasHeight();
  }
}
