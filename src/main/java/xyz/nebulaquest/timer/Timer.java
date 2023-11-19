package xyz.nebulaquest.timer;

import xyz.nebulaquest.event.Event;
import xyz.nebulaquest.event.EventGetter;
import xyz.nebulaquest.update.Updatable;

/**
 * A simple countdown timer. It counts down a specified interval and emits an event on reaching 0.
 * <p>Can be set to repeat mode or be paused.</p>
 * 
 * <b>Events</b>
 * <p>{@code onTimeout} emitted when the time set in {@code waitTime} parameter elapses.</p>
 */
public class Timer implements Updatable {
  private long timeLeft;
  private long waitTime;
  private boolean paused;
  private boolean autoRepeat;
  private Event timeoutEvent;

  /**
   * Constructs a timer with the specified waiting time and disabled auto-repeat.
   * The timer is initially stopped. Use {@code resume()} to start it.   
   * 
   * @param waitTime The waiting time in milliseconds.
   *  
   */
  public Timer(long waitTime) {
    this(waitTime, false);
  }

  /**
   * Constructs a timer with the specified waiting time and optional auto-repetition.
   * The timer is initially stopped. Use {@code resume()} to start it.   
   * 
   * @param waitTime The waiting time in milliseconds.
   * @param autoRepeat If true, the timer will stop when reaching 0. If false, it will restart.
   */
  public Timer(long waitTime, boolean autoRepeat) {
    this.waitTime = waitTime;
    this.timeLeft = waitTime;
    this.autoRepeat = autoRepeat;
    this.paused = true;
    this.timeoutEvent = new Event();
  }

  /**
   * Returns an {@link EventGetter} for the "timeout" event.
   *
   * <p>The "timeout" event is emitted when the time set in {@code waitTime} parameter elapses</p>
   * <p>Subscribe a function to this event; when the {@code waitTime} elapses, the subscribed function will be called.</p>
   *
   * @return An {@link EventGetter} for the "click" event.
   */
   public EventGetter onTimeout() {
    return timeoutEvent;
  }

  /**
   * Resets the timer, setting the remaining time to the original waiting time.
   * It also unpauses the timer if it was paused.
   */
  public void reset() {
    timeLeft = waitTime;
    paused = false;
  }

  /**
   * Pauses the timer.
   */
  public void pause() {
    paused = true;
  }

  /**
   * Resumes the timer if it was paused.
   * 
   * <p><b>NOTE:</b> If the timer was paused because it has reached the specified time, using resume will not reset the timer; 
   * in such cases, use {@code reset()} to start the timer again.</p>
   */
  public void resume() {
    paused = false;
  }

  /**
   * Updates the timer's state within the game's update cycle.
   * Call this method to make the timer functional.
   * 
   * <p><b>NOTE:</b> If the timer is paused, the update will have no effect until it is resumed.</p>
   * 
   * @param deltaTime The time by which to update the timer in milliseconds.
   */
  @Override
  public void update(long deltaTime) {
    if (paused) {
      return;
    }

    timeLeft = Math.max(0, timeLeft - deltaTime);

    if (timeLeft == 0) {
      timeoutEvent.emit();

      if (autoRepeat) {
        this.reset();
      }
      else {
        this.pause();
      }
    }
  }
}
