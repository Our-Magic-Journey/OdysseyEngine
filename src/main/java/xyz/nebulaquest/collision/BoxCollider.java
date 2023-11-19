package xyz.nebulaquest.collision;

/**
 * Rectangular box collider used for collision detection.
 */
public class BoxCollider {
  private int x;
  private int y;
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
    this.x = x;
    this.y = y;
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
    return pX >= this.x
        && pY >= this.y
        && pX <= this.x + this.width
        && pY <= this.y + this.height;
  }

  /**
   * Checks if this box collider collides with another box collider.
   *
   * @param box The other box collider to check for collision.
   * @return {@code true} if there is a collision, {@code false} otherwise.
   */
  public boolean collides(BoxCollider box) {
    return this.x <= box.x + box.width 
        && this.y <= box.y + box.height 
        && this.x + this.width >= box.x
        && this.y + this.height >= box.y;
  }
}
