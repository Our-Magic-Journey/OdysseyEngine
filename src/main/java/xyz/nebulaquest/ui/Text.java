package xyz.nebulaquest.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import xyz.nebulaquest.renderer.Drawable;
import xyz.nebulaquest.renderer.Canvas;

public class Text implements Drawable {
  private Color color;
  private Font font;
  private int size;
  private String text;
  private int x;
  private int y;

  public Text(String text, int x,  int y) {
    this(text, x, y, "Arial", Color.BLACK, 12);
  }

  public Text(String text, int x, int y, String font, Color color, int size) {
    try {
      this.text = text;
      this.x = x;
      this.y = y;
      this.color = color;
      this.font = new Font(font, Font.PLAIN, size);
      this.size = size;
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void setSize(int size) {
    this.size = size;
    reloadFont();
  }

  private void reloadFont() {
    this.font = new Font(this.font.getFontName(), Font.PLAIN, size);
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
  }

  public String getText() {
    return this.text;
  }

  public void setFont(String font) {
    this.font = new Font(font, Font.PLAIN, size);
  }

  public String getFont() {
    return font.getFontName();
  }

  @Override
  public void draw(Graphics2D graphic, Canvas canvas) {
    graphic.setFont(font);
    graphic.setColor(color);
    graphic.drawString(text, x, y);
  }

  
}
