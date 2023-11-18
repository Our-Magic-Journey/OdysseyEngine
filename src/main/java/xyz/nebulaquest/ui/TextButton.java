package xyz.nebulaquest.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import xyz.nebulaquest.collision.BoxCollider;
import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Canvas;

public class TextButton extends AbstractButton {
  protected Font font;
  protected int fontSize;
  protected String text;

  public TextButton(String text, String font, int x, int y, InputManager inputManager) {
    this(text, x, y, font, Color.BLACK, 12, inputManager);
  }

  public TextButton(String text, int x, int y, String font, Color color, int fontSize, InputManager inputManager) {
    super(x, y, 1, 1, color, inputManager);

    try {
      this.font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(font));
      this.font = this.font.deriveFont(Font.PLAIN, fontSize);

      this.text = text;
      this.color = color;
      this.fontSize = fontSize;
      this.calculateSize();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  protected void calculateSize() {
    BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    Graphics g = img.getGraphics();
    
    g.setFont(font);
    
    FontMetrics metrics = g.getFontMetrics();

    width = metrics.stringWidth(text);
    height = metrics.getHeight();
    collider = new BoxCollider(x, y, width, height);
  }

  public void setFontStyle(int style) {
    this.font = this.font.deriveFont(style, fontSize);
    this.calculateSize();
  }
  public int getFontSize() {
    return this.fontSize;
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
  public void draw(Graphics2D graphic, Canvas canvas) {
    graphic.setFont(font);

    super.draw(graphic, canvas);

    graphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    graphic.drawString(text, x, y + graphic.getFontMetrics().getAscent());
  }

}
