package xyz.nebulaquest.renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Renderer {
  private Canvas canvas;
  private BufferedImage buffer;
  private Graphics2D graphic;

  public Renderer(Canvas canvas) {
    this.canvas = canvas;

    System.out.println(canvas.getCanvasWidth() + ", " + canvas.getCanvasHeight());
    this.buffer = new BufferedImage(canvas.getCanvasWidth(), canvas.getCanvasHeight(), BufferedImage.TYPE_INT_RGB);
    this.graphic = (Graphics2D)buffer.getGraphics();
  }

  public void draw(Drawable object) {
    object.draw(graphic, canvas);
  }

  public void drawOnScreen() {
    Graphics screen = canvas.getGraphics();
    screen.drawImage(buffer, 0, 0, canvas.getCanvasWidth() * canvas.getScale(), canvas.getHeight() * canvas.getScale(), null);
    screen.dispose();
  }
}
