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

/**
 * Represents a text element designed to function as a button with hover and click animations.
 * It includes an "on click" event to respond to user interactions.
 *
 * @see AbstractButton
 * @see InputManager
 * @see Canvas
 */
public class TextButton extends AbstractButton {
  protected Font font;
  protected int fontSize;
  protected String text;

  /**
   * Constructs a TextButton instance with the specified text, font, position, color, font size, and input manager.
   *
   * @param text The text content of the button.
   * @param x The x-coordinate of the button.
   * @param y The y-coordinate of the button.
   * @param font The font used for the text.
   * @param inputManager The InputManager instance for handling user input.
   */
  public TextButton(String text, int x, int y, Font font, InputManager inputManager) {
    this(text, x, y, font, Color.BLACK, 12, inputManager);
  }

  /**
   * Constructs a TextButton instance with the specified text, position, font, color, font size, and input manager.
   *
   * @param text The text content of the button.
   * @param x The x-coordinate of the button.
   * @param y The y-coordinate of the button.
   * @param font The font used for the text.
   * @param color The color of the button text.
   * @param fontSize The font size of the button text.
   * @param inputManager The InputManager instance for handling user input.
   */
  public TextButton(String text, int x, int y, Font font, Color color, int fontSize, InputManager inputManager) {
    super(x, y, 1, 1, color, inputManager);

    this.font = font.deriveFont(Font.PLAIN, fontSize);
    this.text = text;
    this.color = color;
    this.fontSize = fontSize;
    this.calculateSize();
  }

  /** Calculates the size (in pixels) of the text */
  protected void calculateSize() {
    BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    Graphics g = img.getGraphics();
    
    g.setFont(font);
    
    FontMetrics metrics = g.getFontMetrics();

    width = metrics.stringWidth(text);
    height = metrics.getHeight();
    collider = new BoxCollider(x, y, width, height);
  }

  /**
   * Sets the font style.
   *
   * @param style The new font style.
   */
  public void setFontStyle(int style) {
    this.font = this.font.deriveFont(style, fontSize);
    this.calculateSize();
  }
  
  /**
   * Gets the font size.
   *
   * @return The font size.
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
   * Sets the text.
   *
   * @param text The new button text.
   */
  public void setText(String text) {
    this.text = text;
    this.calculateSize();
  }

  /**
   * Gets the text.
   *
   * @return text
   */
  public String getText() {
    return this.text;
  }

  /**
   * Gets the font name used for rendering the text.
   *
   * @return The font name used for rendering the text.
   */
  public String getFont() {
    return font.getFontName();
  }

  @Override
  public void draw(Graphics2D context, Canvas canvas) {
    context.setFont(font);

    super.draw(context, canvas);

    context.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    context.drawString(text, x, y + context.getFontMetrics().getAscent());
  }
}
