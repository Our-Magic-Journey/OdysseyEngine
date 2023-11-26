package xyz.nebulaquest.input;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import xyz.nebulaquest.event.EventGroup;
import xyz.nebulaquest.input.types.MouseWheelInputType;

/**
 * Listens for mouse input events and emits corresponding events to the associated EventGroup.
 */
public class MouseWheelInputListener implements MouseWheelListener {
  private EventGroup<MouseWheelInputType, MouseWheelEvent> eventGroup;

  public MouseWheelInputListener(EventGroup<MouseWheelInputType, MouseWheelEvent> eventGroup) {
    this.eventGroup = eventGroup;
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent event) {
    eventGroup.emit(MouseWheelInputType.SCROLL, event);
  }
}
