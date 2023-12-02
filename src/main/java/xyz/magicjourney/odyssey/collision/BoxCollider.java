package xyz.magicjourney.odyssey.collision;

import xyz.magicjourney.odyssey.math.Vector;

/**
 * Rectangular box collider used for collision detection.
 */
public class BoxCollider {
  private Vector position;
  private int width;
  private int height;

  /**
   * Constructs a new BoxCollider with the specified position and dimensions.
   *
   * @param x      The x-coordinate of the top-left corner of the box.
   * @param y      The y-coordinate of the top-left corner of the box.
   * @param width  The width of the box.
   * @param height The height of the box.
   */
  public BoxCollider(int x, int y, int width, int height) {
    this(new Vector(x, y), width, height);
  }

  /**
   * Constructs a new BoxCollider with the specified position and dimensions.
   *
   * @param position The coordinates of the top-left corner of the box.
   * @param width  The width of the box.
   * @param height The height of the box.
   */
  public BoxCollider(Vector position, int width, int height) {
    this.position = position;
    this.width = width;
    this.height = height;
  }

  /**
   * Checks if the specified point is contained within the box collider.
   *
   * @param pX The x-coordinate of the point.
   * @param pY The y-coordinate of the point.
   * @return {@code true} if the point is inside the box, {@code false} otherwise.
   */
  public boolean containsPoint(int pX, int pY) {
    return containsPoint(new Vector(pX, pY));
  }

  /**
   * Checks if the specified point is contained within the box collider.
   *
   * @param point The vector representing the point to check.
   * @return {@code true} if the point is inside the box, {@code false} otherwise.
   */
  public boolean containsPoint(Vector point) {
    return point.greaterOrEqual(position) && point.lessOrEqual(getBottomRightCorner());
  }

  /**
   * Calculates the vector representing the bottom-right corner of the box.
   *
   * @return The vector representing the bottom-right corner of the box.
   */
  protected Vector getBottomRightCorner() {
    return Vector.addition(position, new Vector(width, height));
  }

  /**
   * Checks if this box collider collides with another box collider.
   *
   * @param box The other box collider to check for collision.
   * @return {@code true} if there is a collision, {@code false} otherwise.
   */
  public boolean collides(BoxCollider box) {
    return position.lessOrEqual(box.getBottomRightCorner()) && getBottomRightCorner().greaterOrEqual(box.position);
  }
}
