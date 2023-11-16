package xyz.nebulaquest.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import xyz.nebulaquest.input.types.MouseInputType;
import xyz.nebulaquest.subscriber.Subscriber;

public class MouseInputListener implements MouseListener, MouseMotionListener {
  private Subscriber<MouseInputType, MouseEvent> listener;

  public MouseInputListener(Subscriber<MouseInputType, MouseEvent> listener) {
    this.listener = listener;
  }

  @Override
  public void mouseEntered(MouseEvent event) {
    listener.sendNotification(MouseInputType.ENTER, event);
  }

  @Override
  public void mouseExited(MouseEvent event) {
    listener.sendNotification(MouseInputType.EXIT, event);
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    listener.sendNotification(MouseInputType.CLICK, event);
  }

  @Override
  public void mousePressed(MouseEvent event) {
    listener.sendNotification(MouseInputType.BTN_DOWN, event);
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    listener.sendNotification(MouseInputType.BTN_UP, event);
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    listener.sendNotification(MouseInputType.DRAG, event);
  }

  @Override
  public void mouseMoved(MouseEvent event) {
    listener.sendNotification(MouseInputType.MOVE, event);
  }
}
