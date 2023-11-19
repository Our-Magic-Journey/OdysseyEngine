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

  public EventGetter onClick(){
    return this.clickEvent;
  }

  public void dispatch(InputManager inputManager) {
    inputManager.onMouseEvent().unsubscribe(MouseInputType.MOVE, this::handleMouseMove);
    inputManager.onMouseEvent().unsubscribe(MouseInputType.CLICK, this::handleMouseClick);
  }

  protected void handleMouseMove(MouseEvent event) {
    this.hover = this.collider.containsPoint(event.getX() + event.getComponent().getX(), event.getY() + event.getComponent().getY());
  }

  protected void handleMouseClick(MouseEvent event) {
    if (this.hover && event.getButton() == MouseEvent.BUTTON1) {
      this.clicked = true;
      this.clickedTimer.reset();
      this.clickEvent.emit();
    }
  }

  protected void unclick() {
    this.clicked = false;
  }

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
