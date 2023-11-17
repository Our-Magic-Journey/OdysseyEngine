package xyz.nebulaquest.event;

import java.util.function.Consumer;

public class ParameterizedEvent<T> extends AbstractEvent<Consumer<T>> implements ParameterizedEventGetter<T> {
  public void emit(T value) {
    this.callbacks.forEach((callback) -> callback.accept(value));
  }
}