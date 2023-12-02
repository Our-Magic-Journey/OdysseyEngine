package xyz.magicjourney.odyssey.renderer;

import java.awt.Graphics2D;

/**
 * Represents an object that can be drawn on a canvas.
 */
public interface Drawable {
  /**
   * Draws the object on the specified graphics context.
   *
   * @param graphic The graphics context on which to draw the object.
   * @param canvas The canvas on which the object is drawn.
   */
  void draw(Graphics2D context, Canvas canvas);
}
