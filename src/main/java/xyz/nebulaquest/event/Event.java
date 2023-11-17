package xyz.nebulaquest.event;

public class Event extends AbstractEvent<Runnable> implements EventGetter {
  public void emit() {
    this.callbacks.forEach((callback) -> callback.run());
  }
}