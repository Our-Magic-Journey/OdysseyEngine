package xyz.nebulaquest.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import xyz.nebulaquest.renderer.Drawable;
import xyz.nebulaquest.renderer.Canvas;

/**
 * Represents an image that can be drawn on a canvas.
 *
 * @see Drawable
 */
public class Image implements Drawable {
  private BufferedImage image;

  private int x;
  private int y;

  /**
   * Constructs an Image instance with the specified BufferedImage and default position (0, 0).
   * 
   * @param image The BufferedImage to be used as the image source. This image will be drawn when rendering.
   */
  public Image(BufferedImage image) {
    this(image, 0, 0);
  }

  /**
   * Constructs an Image instance with the specified BufferedImage and initial position.
   * 
   * @param image The BufferedImage to be used as the image source. This image will be drawn when rendering.
   * @param x The initial x-coordinate of the image.
   * @param y The initial y-coordinate of the image.
   */
  public Image(BufferedImage image, int x, int y) {
    this.image = image;
    this.x = x;
    this.y = y;
  }

  /**
   * Sets the position of the image.
   * 
   * @param x The x-coordinate to set.
   * @param y The y-coordinate to set.
   */
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  } 

  @Override
  public void draw(Graphics2D context, Canvas canvas) {
    context.drawImage(image, x, y, null);
  }
}
