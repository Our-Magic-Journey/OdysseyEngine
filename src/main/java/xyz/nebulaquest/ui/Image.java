package xyz.nebulaquest.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import xyz.nebulaquest.renderer.Drawable;
import xyz.nebulaquest.renderer.Canvas;

public class Image implements Drawable {
  private BufferedImage image;

  private int x;
  private int y;

  public Image(String source) {
    this(source, 0, 0);
  }

  public Image(String source, int x, int y) {
    try {
      this.image = ImageIO.read(getClass().getResourceAsStream(source));
      this.x = x;
      this.y = y;
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  } 


  @Override
  public void draw(Graphics2D context, Canvas canvas) {
    context.drawImage(image, x, y, null);
  }
}
