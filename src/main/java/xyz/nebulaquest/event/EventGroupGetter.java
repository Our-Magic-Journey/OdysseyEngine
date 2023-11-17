package xyz.nebulaquest.event;

import java.util.function.Consumer;

public interface EventGroupGetter<T, N> {
  void subscribe(T event, Consumer<N> callback);
  void unsubscribe(T event, Consumer<N> callback);
}