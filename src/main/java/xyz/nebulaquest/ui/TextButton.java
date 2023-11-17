package xyz.nebulaquest.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import xyz.nebulaquest.collision.BoxCollider;
import xyz.nebulaquest.event.Event;
import xyz.nebulaquest.event.EventGetter;
import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.input.types.MouseInputType;
import xyz.nebulaquest.renderer.Canvas;
import xyz.nebulaquest.timer.Timer;
import xyz.nebulaquest.update.Updatable;

public class TextButton extends Text implements Updatable {
  private BoxCollider collider;
  private boolean hover;
  private boolean clicked;
  private Timer clickedTimer;
  private Event clickEvent;

  public TextButton(String text, String font, int x, int y, InputManager inputManager) {
    this(text, x, y, font, Color.BLACK, 12, inputManager);
  }

  public TextButton(String text, int x, int y, String font, Color color, int size, InputManager inputManager) {
    super(text, x, y, font, color, size);

    this.clickEvent = new Event();
    this.hover = false;
    this.clicked = false;
    this.clickedTimer = new Timer(250);
    this.clickedTimer.pause();

    inputManager.onMouseEvent().subscribe(MouseInputType.MOVE, this::handleMouseMove);
    inputManager.onMouseEvent().subscribe(MouseInputType.CLICK, this::handleMouseClick);
    clickedTimer.onTimeout().subscribe(this::unclick);
  }

  public EventGetter onClick(){
    return this.clickEvent;
  }


  @Override
  protected void calculateSize() {
    super.calculateSize();
    collider = new BoxCollider(x, y, width, height);
  }

  public void dispatch(InputManager inputManager) {
    inputManager.onMouseEvent().unsubscribe(MouseInputType.MOVE, this::handleMouseMove);
    inputManager.onMouseEvent().unsubscribe(MouseInputType.CLICK, this::handleMouseClick);
  }

  private void handleMouseMove(MouseEvent event) {
    this.hover = this.collider.containsPoint(event.getX() + event.getComponent().getX(), event.getY() + event.getComponent().getY());
  }

  private void handleMouseClick(MouseEvent event) {
    if (this.hover && event.getButton() == MouseEvent.BUTTON1) {
      this.clicked = true;
      this.clickedTimer.reset();
      this.clickEvent.emit();
    }
  }

  private void unclick() {
    this.clicked = false;
  }

  @Override
  public void update(long deltaTime) {
    clickedTimer.update(deltaTime);
  }

  @Override
  public void draw(Graphics2D graphic, Canvas canvas) {
    graphic.setFont(font);

    if (clicked) {
      graphic.setColor(color.brighter().brighter().brighter());
    }
    else if (hover) {
      graphic.setColor(color.brighter());
    }
    else {
      graphic.setColor(color);
    }

    graphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    graphic.drawString(text, x, y + graphic.getFontMetrics().getAscent());
  }

}
