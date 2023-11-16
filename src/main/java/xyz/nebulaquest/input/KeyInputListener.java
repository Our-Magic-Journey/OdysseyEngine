package xyz.nebulaquest.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import xyz.nebulaquest.input.types.KeyInputType;
import xyz.nebulaquest.subscriber.Subscriber;

public class KeyInputListener extends Subscriber<KeyInputType, KeyEvent> implements KeyListener {
  private Subscriber<KeyInputType, KeyEvent> listener;

  public KeyInputListener(Subscriber<KeyInputType, KeyEvent> listener) {
    this.listener = listener;
  }

  @Override
  public void keyPressed(KeyEvent event) {
    listener.sendNotification(KeyInputType.PRESS, event);
  }

  @Override
  public void keyReleased(KeyEvent event) {
    listener.sendNotification(KeyInputType.RELEASE, event);
  }

  @Override
  public void keyTyped(KeyEvent event) {
    listener.sendNotification(KeyInputType.TYPE, event);
  } 
}
