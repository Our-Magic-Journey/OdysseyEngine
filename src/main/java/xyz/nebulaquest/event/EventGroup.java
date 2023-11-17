package xyz.nebulaquest.event;

import java.util.HashMap;
import java.util.function.Consumer;

public class EventGroup<T, N> implements EventGroupGetter<T, N> {
  private HashMap<T, ParameterizedEvent<N>> events;

  public EventGroup() {
    this.events = new HashMap<>();
  }

  public void subscribe(T event, Consumer<N> callback) {
    prepareEvent(event);
    events.get(event).subscribe(callback);
  }

  private void prepareEvent(T event) {
    if (!hasAnySubscribersForEvent(event)) {
      events.put(event, new ParameterizedEvent<>());
    }
  }

  public void unsubscribe(T event, Consumer<N> callback) {
    if (hasAnySubscribersForEvent(event)) {
      events.get(event).unsubscribe(callback);
    }
  }

  public void emit(T event, N value) {
    if (hasAnySubscribersForEvent(event)) {
      this.events.get(event).emit(value);
    }
  }

  private boolean hasAnySubscribersForEvent(T event) {
    return events.containsKey(event);
  }
}
