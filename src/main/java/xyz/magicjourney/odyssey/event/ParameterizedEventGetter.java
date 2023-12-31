package xyz.magicjourney.odyssey.event;

import java.util.function.Consumer;

/**
 * Interface for returning an instance of the {@link ParameterizedEvent} class without exposing certain methods.
 * 
 * <p><b>NOTE:</b> This interface does not provide access to the {@code emit} and {@code unsubscribeAll} methods 
 * so you can use this interface to safely expose an event in a way that allows only subscribing and unsubscribing from it.</p>
 *
 * @param <T> The type of the event's parameter.
 */
public interface ParameterizedEventGetter<T> {
  /**
   * Subscribes to an Event by setting a callback function that will be invoked later 
   * when an event happens.
   * 
   * <p><b>NOTE:</b> A single callback function can subscribe to a particular {@code Event} instance only once.</p>
   * 
   * @param callback A callback function to be invoked later.
   */
  void subscribe(Consumer<T> callback);
  
  /**
   * Unsubscribes callback function from the subscribers list, so it will not be called by this event in the future.
   *
   * <p><b>NOTE:</b> To unsubscribe from an event, the callback needs to be a reference to the same function that was used when registered.
   * Anonymous functions like {@code (int x) -> x*x} CANNOT be unsubscribed.
   * Use {@code this::functionName} instead of {@code () -> this.functionName()}.</p>
   * 
   * @param callback The callback function to be removed from subscribers.
   */
  void unsubscribe(Consumer<T> callback);
}
