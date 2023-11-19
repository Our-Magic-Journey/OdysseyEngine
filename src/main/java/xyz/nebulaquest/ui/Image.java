package xyz.nebulaquest.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

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
   * Constructs an Image instance with the specified image source.
   * 
   * @param source The path to the image file. ("/" points to /src/main/resources)
   */
  public Image(String source) {
    this(source, 0, 0);
  }

  /**
   * Constructs an Image instance with the specified image source and initial position.
   * 
   * @param source The path to the image file.
   * @param x The initial x-coordinate of the image.
   * @param y The initial y-coordinate of the image.
   */
  public Image(String source, int x, int y) {
    try {
      this.image = ImageIO.read(getClass().getResourceAsStream(source));
      this.x = x;
      this.y = y;
    } catch (Exception e) {
      e.printStackTrace();
    }
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
