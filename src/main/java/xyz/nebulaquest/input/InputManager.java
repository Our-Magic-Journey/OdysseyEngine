package xyz.nebulaquest.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

import javax.swing.JPanel;

import xyz.nebulaquest.input.types.KeyInputType;
import xyz.nebulaquest.input.types.MouseInputType;
import xyz.nebulaquest.subscriber.Subscriber;

public class InputManager {
  private Subscriber<MouseInputType, MouseEvent> mouseEvents;
  private Subscriber<KeyInputType, KeyEvent> keyboardEvens;

  private KeyInputListener keyboardListener;
  private MouseInputListener mouseListener;

  public InputManager() {
    this.mouseEvents = new Subscriber<>();
    this.keyboardEvens = new Subscriber<>();
    this.keyboardListener = new KeyInputListener(keyboardEvens);
    this.mouseListener = new MouseInputListener(mouseEvents);
  }

  public void addMouseEvent(MouseInputType type, Consumer<MouseEvent> callback) {
    mouseEvents.subscribe(type, callback);
  }

  public void removeMouseEvent(MouseInputType type, Consumer<MouseEvent> callback) {
    mouseEvents.unsubscribe(type, callback);
  }

  public void addKeyboardEvent(KeyInputType type, Consumer<KeyEvent> callback) {
    keyboardEvens.subscribe(type, callback);
  }

  public void removeKeyboardEvent(KeyInputType type, Consumer<KeyEvent> callback) {
    keyboardEvens.unsubscribe(type, callback);
  }

  public void observe(JPanel window) {
    window.addMouseListener(mouseListener);
    window.addKeyListener(keyboardListener);
  }
}
