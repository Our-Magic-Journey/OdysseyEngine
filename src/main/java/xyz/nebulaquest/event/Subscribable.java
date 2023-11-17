package xyz.nebulaquest.event;

public interface Subscribable<T> {
  void subscribe(T callback);
  void unsubscribe(T callback);
}
