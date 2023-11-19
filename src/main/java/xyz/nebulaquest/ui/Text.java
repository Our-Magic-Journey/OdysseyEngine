package xyz.nebulaquest.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import xyz.nebulaquest.renderer.Drawable;
import xyz.nebulaquest.renderer.Canvas;

public class Text implements Drawable {
  protected int width;
  protected int height;
  protected Color color;
  protected Font font;
  protected int size;
  protected String text;
  protected int x;
  protected int y;

  public Text(String text, String font, int x,  int y) {
    this(text, x, y, font, Color.BLACK, 12);
  }

  public Text(String text, int x, int y, String font, Color color, int size) {
    try {
      this.font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(font));
      this.font = this.font.deriveFont(Font.PLAIN, size);

      this.text = text;
      this.x = x;
      this.y = y;
      this.color = color;
      this.size = size;
      this.calculateSize();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  protected void calculateSize() {
    BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    Graphics context = img.getGraphics();

    context.setFont(font);

    FontMetrics metrics = context.getFontMetrics();

    width = metrics.stringWidth(text);
    height = metrics.getHeight();
  }

  public void setSize(int size) {
    this.size = size;
    this.font = this.font.deriveFont(Font.PLAIN, size);
    this.calculateSize();
  }

  public void setFontStyle(int style) {
    this.font = this.font.deriveFont(style, size);
    this.calculateSize();
  }

  public int getSize() {
    return this.size;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }

  public void setText(String text) {
    this.text = text;
    this.calculateSize();
  }

  public String getText() {
    return this.text;
  }

  public String getFont() {
    return font.getFontName();
  }

  @Override
  public void draw(Graphics2D context, Canvas canvas) {
    context.setFont(font);
    context.setColor(color);
    context.drawString(text, x, y + context.getFontMetrics().getAscent());
  }
}
