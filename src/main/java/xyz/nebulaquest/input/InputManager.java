package xyz.nebulaquest.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import xyz.nebulaquest.event.EventGroup;
import xyz.nebulaquest.event.EventGroupGetter;
import xyz.nebulaquest.input.types.KeyInputType;
import xyz.nebulaquest.input.types.MouseInputType;

/**
 * Manages input events for keyboard and mouse interactions.
 */
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

  /**
   * Returns an {@link EventGroupGetter} for mouse input events.
   * 
   * <p>Use this method to access events related to mouse interactions, such as clicks, movement, etc.</p>
   * <p>Events are grouped by type (see {@link MouseInputType}). You can subscribe a function 
   * to a particular mouse event; when that event occurs, this function will be called. 
   * The function should contain an argument of type {@code MouseEvent}, to which all details 
   * (for example, cursor position) will be passed.
   * </p>
   *
   * @return An {@link EventGroupGetter} for mouse input events.
   */
  public EventGroupGetter<MouseInputType, MouseEvent> onMouseEvent() {
    return mouseEvents;
  }

  /**
   * Returns an {@link EventGroupGetter} for keyboard input events.
   * 
   * <p>Use this method to access events related to keyboard interactions, such as key presses, releases, etc.</p>
   * <p>Events are grouped by type (see {@link KeyInputType}). You can subscribe a function 
   * to a particular keyboard event; when that event occurs, this function will be called. 
   * The function should contain an argument of type {@code KeyEvent}, to which all details 
   * (for example, which key was pressed) will be passed.
   * </p>
   *
   * @return An {@link EventGroupGetter} for keyboard input events.
   */
  public EventGroupGetter<KeyInputType, KeyEvent> onKeyboardEvent() {
    return keyboardEvents;
  }

  /**
   * Observes the specified JPanel for mouse and keyboard input events.
   *
   * @param panel The JPanel to observe.
   */
  public void observe(JPanel panel) {
    panel.addMouseListener(mouseListener);
    panel.addMouseMotionListener(mouseListener);
    panel.addKeyListener(keyboardListener);
  }
}
