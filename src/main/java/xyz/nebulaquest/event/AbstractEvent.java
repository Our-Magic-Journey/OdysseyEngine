package xyz.nebulaquest.event;

import java.util.HashSet;

public abstract class AbstractEvent<T> implements Subscribable<T> {
  protected HashSet<T> callbacks;

  public AbstractEvent() {
    this.callbacks = new HashSet<>();
  }

  public void subscribe(T callback) {
    callbacks.add(callback);
  }

  public void unsubscribe(T callback) {
    callbacks.remove(callback);
  }

  public void unsubscribeAll() {
    callbacks.clear();
  }

  public boolean hasSubscribers() {
    return this.callbacks.size() >= 1;
  }

  public int subscribersCount() {
    return this.callbacks.size();
  }
}
