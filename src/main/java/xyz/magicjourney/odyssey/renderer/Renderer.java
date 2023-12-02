package xyz.magicjourney.odyssey.renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import xyz.magicjourney.odyssey.math.Vector;

/**
 * The renderer class is responsible for drawing objects on the canvas.
 */
public class Renderer {
  private Canvas canvas;
  private BufferedImage buffer;
  private Graphics2D graphic;

  /**
   * Constructs a renderer with the specified canvas.
   *
   * @param canvas The canvas to render on.
   */
  public Renderer(Canvas canvas) {
    this.canvas = canvas;
    this.buffer = new BufferedImage(canvas.getCanvasWidth(), canvas.getCanvasHeight(), BufferedImage.TYPE_INT_RGB);
    this.graphic = (Graphics2D)buffer.getGraphics();
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
   * Draws the buffer on the screen, maintaining the canvas aspect ratio;
   */
  public void drawOnScreen() {
    Graphics screen = canvas.getGraphics();
    Vector topLeftCorner = canvas.canvasPointToWindow(new Vector(0));

    canvas.drawBorder(screen);
    screen.drawImage(buffer, topLeftCorner.getRoundX(), topLeftCorner.getRoundY(), canvas.getCanvasWidth(), canvas.getCanvasHeight(), null);

    screen.dispose();
    graphic.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
  }
}
