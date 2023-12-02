package xyz.magicjourney.odyssey.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import xyz.magicjourney.odyssey.event.EventGroup;
import xyz.magicjourney.odyssey.input.types.KeyInputType;

/**
 * Listens for keyboard input events and emits corresponding events to the associated EventGroup.
 */
public class KeyInputListener extends EventGroup<KeyInputType, KeyEvent> implements KeyListener {
  private EventGroup<KeyInputType, KeyEvent> eventGroup;

  public KeyInputListener(EventGroup<KeyInputType, KeyEvent> eventGroup) {
    this.eventGroup = eventGroup;
  }

  @Override
  public void keyPressed(KeyEvent event) {
    eventGroup.emit(KeyInputType.PRESS, event);
  }

  @Override
  public void keyReleased(KeyEvent event) {
    eventGroup.emit(KeyInputType.RELEASE, event);
  }

  @Override
  public void keyTyped(KeyEvent event) {
    eventGroup.emit(KeyInputType.TYPE, event);
  } 
}
