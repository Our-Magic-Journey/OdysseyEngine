package xyz.magicjourney.odyssey.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import xyz.magicjourney.odyssey.event.EventGroup;
import xyz.magicjourney.odyssey.input.types.MouseInputType;

/**
 * Listens for mouse input events and emits corresponding events to the associated EventGroup.
 */
public class MouseInputListener implements MouseListener, MouseMotionListener {
  private EventGroup<MouseInputType, MouseEvent> eventGroup;

  public MouseInputListener(EventGroup<MouseInputType, MouseEvent> eventGroup) {
    this.eventGroup = eventGroup;
  }

  @Override
  public void mouseEntered(MouseEvent event) {
    eventGroup.emit(MouseInputType.ENTER, event);
  }

  @Override
  public void mouseExited(MouseEvent event) {
    eventGroup.emit(MouseInputType.EXIT, event);
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    eventGroup.emit(MouseInputType.CLICK, event);
  }

  @Override
  public void mousePressed(MouseEvent event) {
    eventGroup.emit(MouseInputType.BTN_DOWN, event);
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    eventGroup.emit(MouseInputType.BTN_UP, event);
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    eventGroup.emit(MouseInputType.DRAG, event);
  }

  @Override
  public void mouseMoved(MouseEvent event) {
    eventGroup.emit(MouseInputType.MOVE, event);
  }
}
