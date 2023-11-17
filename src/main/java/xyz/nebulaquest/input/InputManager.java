package xyz.nebulaquest.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import xyz.nebulaquest.event.EventGroup;
import xyz.nebulaquest.event.EventGroupGetter;
import xyz.nebulaquest.input.types.KeyInputType;
import xyz.nebulaquest.input.types.MouseInputType;

public class InputManager {
  private EventGroup<MouseInputType, MouseEvent> mouseEvents;
  private EventGroup<KeyInputType, KeyEvent> keyboardEvents;

  private KeyInputListener keyboardListener;
  private MouseInputListener mouseListener;

  public InputManager() {
    this.mouseEvents = new EventGroup<>();
    this.keyboardEvents = new EventGroup<>();
    this.keyboardListener = new KeyInputListener(keyboardEvents);
    this.mouseListener = new MouseInputListener(mouseEvents);
  }

  public EventGroupGetter<MouseInputType, MouseEvent> onMouseEvent() {
    return mouseEvents;
  }

  public EventGroupGetter<KeyInputType, KeyEvent> onKeyboardEvent() {
    return keyboardEvents;
  }

  public void observe(JPanel panel) {
    panel.addMouseListener(mouseListener);
    panel.addMouseMotionListener(mouseListener);
    panel.addKeyListener(keyboardListener);
  }
}
