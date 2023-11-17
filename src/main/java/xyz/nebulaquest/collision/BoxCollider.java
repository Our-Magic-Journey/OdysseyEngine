package xyz.nebulaquest.collision;

public class BoxCollider {
  private int x;
  private int y;
  private int width;
  private int height;

  public BoxCollider(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public boolean containsPoint(int pX, int pY) {
    return pX >= this.x
        && pY >= this.y
        && pX <= this.x + this.width
        && pY <= this.y + this.height;
  }

  public boolean collides(BoxCollider box) {
    return this.x <= box.x + box.width 
        && this.y <= box.y + box.height 
        && this.x + this.width >= box.x
        && this.y + this.height >= box.y;
  }
}
