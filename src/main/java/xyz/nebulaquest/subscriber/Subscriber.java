package xyz.nebulaquest.subscriber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;

public class Subscriber<T, N> {
  private HashMap<T, HashSet<Consumer<N>>> listeners;

  public Subscriber() {
    this.listeners = new HashMap<>();
  }

  public void subscribe(T type, Consumer<N> callback) {
    prepareHandlerArray(type);
    listeners.get(type).add(callback);
  }

  private void prepareHandlerArray(T type) {
    if (!hasAnyListenersForType(type)) {
      listeners.put(type, new HashSet<>());
    }
  }

  public void unsubscribe(T type, Consumer<N> callback) {
    if (hasAnyListenersForType(type)) {
      listeners.get(type).remove(callback);
    }
  }

  public void sendNotification(T type, N notification) {
    if (hasAnyListenersForType(type)) {
      this.listeners.get(type).forEach((handler) -> handler.accept(notification));
    }
  }

  private boolean hasAnyListenersForType(T type) {
    return listeners.containsKey(type);
  }
}
