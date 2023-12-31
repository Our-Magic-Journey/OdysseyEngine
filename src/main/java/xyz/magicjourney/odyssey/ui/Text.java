package xyz.magicjourney.odyssey.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import xyz.magicjourney.odyssey.renderer.Canvas;
import xyz.magicjourney.odyssey.renderer.Drawable;

/**
 * Represents a text element that can be drawn on a canvas.
 *
 * @see Drawable
 */
public class Text implements Drawable {
  protected int width;
  protected int height;
  protected Color color;
  protected Font font;
  protected int fontSize;
  protected String text;
  protected int x;
  protected int y;

   /**
   * Constructs a Text instance with the specified text content, font, and position.
   *
   * @param text The content of the text.
   * @param x The x-coordinate of the text.
   * @param y The y-coordinate of the text.
   * @param font The font used for the text.
   */
  public Text(String text, int x,  int y, Font font) {
    this(text, x, y, font, Color.BLACK, 12);
  }

  /**
   * Constructs a Text instance with the specified text content, position, font, color, and size.
   *
   * @param text The content of the text.
   * @param x The x-coordinate of the text.
   * @param y The y-coordinate of the text.
   * @param font The font used for the text.
   * @param color The color of the text.
   * @param fontSize The size of the text.
   */
  public Text(String text, int x, int y, Font font, Color color, int fontSize) {
    this.font = font.deriveFont(Font.PLAIN, fontSize);
    this.text = text;
    this.x = x;
    this.y = y;
    this.color = color;
    this.fontSize = fontSize;
    this.calculateSize();
  }

  /** Calculates the size (in pixels) of the text */
  protected void calculateSize() {
    BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    Graphics context = img.getGraphics();

    context.setFont(font);

    FontMetrics metrics = context.getFontMetrics();

    width = metrics.stringWidth(text);
    height = metrics.getHeight();
  }

  /**
   * Sets the font's size.
   *
   * @param fontSize The new size of the font
   */
  public void setFontSize(int fontSize) {
    this.fontSize = fontSize;
    this.font = this.font.deriveFont(Font.PLAIN, fontSize);
    this.calculateSize();
  }

  /**
   * Sets the font style
   *
   * @param style The new font style.
   */
  public void setFontStyle(int style) {
    this.font = this.font.deriveFont(style, fontSize);
    this.calculateSize();
  }

  /**
   * Gets the size of the font.
   *
   * @return The size of the text.
   */
  public int getFontSize() {
    return this.fontSize;
  }

  /**
   * Sets the color of the text.
   *
   * @param color The new color of the text.
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Gets the color of the text.
   *
   * @return The color of the text.
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Sets the text content.
   *
   * @param text The new content of the text.
   */
  public void setText(String text) {
    this.text = text;
    this.calculateSize();
  }

  /**
   * Gets the text content.
   *
   * @return The content of the text.
   */
  public String getText() {
    return this.text;
  }

  /**
   * Gets the font name.
   *
   * @return The font name.
   */
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
