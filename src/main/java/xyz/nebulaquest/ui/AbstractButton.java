package xyz.nebulaquest.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import xyz.nebulaquest.collision.BoxCollider;
import xyz.nebulaquest.event.Event;
import xyz.nebulaquest.event.EventGetter;
import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.input.types.MouseInputType;
import xyz.nebulaquest.renderer.Canvas;
import xyz.nebulaquest.renderer.Drawable;
import xyz.nebulaquest.timer.Timer;
import xyz.nebulaquest.update.Updatable;

/**
 * Base class for creating various types of buttons with built-in 
 * hover and click animations an onClick event.
 *
 * @see Updatable
 * @see Drawable
 */
public abstract class AbstractButton implements Updatable, Drawable {
  protected BoxCollider collider;
  protected boolean hover;
  protected boolean clicked;
  protected Timer clickedTimer;
  protected Event clickEvent;

  protected int width;
  protected int height;
  protected Color color;
  protected int x;
  protected int y;

  /**
   * Constructs a new instance of the AbstractButton.
   * 
   * @param x The x-coordinate of the button.
   * @param y The y-coordinate of the button.
   * @param width The width of the button.
   * @param height The height of the button.
   * @param color The color of the button.
   * @param inputManager The input manager for handling user input.
   * 
   */
  public AbstractButton(int x, int y, int width, int height, Color color, InputManager inputManager) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;

    this.clickEvent = new Event();
    this.hover = false;
    this.clicked = false;
    this.clickedTimer = new Timer(250);
    this.collider = new BoxCollider(x, y, width, height);

    inputManager.onMouseEvent().subscribe(MouseInputType.MOVE, this::handleMouseMove);
    inputManager.onMouseEvent().subscribe(MouseInputType.CLICK, this::handleMouseClick);
    clickedTimer.onTimeout().subscribe(this::unclick);
  }

  /**
   * Returns an {@link EventGetter} for the "click" event.
   *
   * <p>The "click" event is emitted when the button is clicked.</p>
   * <p>Subscribe a function to this event; when the button is clicked, the subscribed function will be called.</p>
   *
   * @return An {@link EventGetter} for the "click" event.
   */
  public EventGetter onClick(){
    return this.clickEvent;
  }

  /**
   * Unsubscribes the button from mouse events.
   * 
   * <p><b>NOTE:</b> It must be called before the destroying of the button to prevent errors.</p>
   *
   * @param inputManager The input manager to unsubscribe from.
   */
  public void dispatch(InputManager inputManager) {
    inputManager.onMouseEvent().unsubscribe(MouseInputType.MOVE, this::handleMouseMove);
    inputManager.onMouseEvent().unsubscribe(MouseInputType.CLICK, this::handleMouseClick);
  }

  /** Handles the mouse move event to check if the mouse is over the button. */
  protected void handleMouseMove(MouseEvent event) {
    this.hover = this.collider.containsPoint(event.getX() + event.getComponent().getX(), event.getY() + event.getComponent().getY());
  }

  /* Handles the mouse click event to check if the button is clicked. */
  protected void handleMouseClick(MouseEvent event) {
    if (this.hover && event.getButton() == MouseEvent.BUTTON1) {
      this.clicked = true;
      this.clickedTimer.reset();
      this.clickEvent.emit();
    }
  }


  /** Resets the clicked state */
  protected void unclick() {
    this.clicked = false;
  }

  /**
   * Sets the position of the button.
   * 
   * @param x The new x-coordinate.
   * @param y The new y-coordinate.
   */
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
    collider = new BoxCollider(x, y, width, height);
  }

  @Override
  public void update(long deltaTime) {
    clickedTimer.update(deltaTime);
  }

  @Override
  public void draw(Graphics2D context, Canvas canvas) {

    if (clicked) {
      context.setColor(color.brighter().brighter().brighter());
    }
    else if (hover) {
      context.setColor(color.brighter());
    }
    else {
      context.setColor(color);
    }
  }
}
