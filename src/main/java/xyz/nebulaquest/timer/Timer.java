package xyz.nebulaquest.timer;

import xyz.nebulaquest.event.Event;
import xyz.nebulaquest.event.EventGetter;
import xyz.nebulaquest.update.Updatable;

public class Timer implements Updatable {
  private long timeLeft;
  private long waitTime;
  private boolean paused;
  private boolean autoRepeat;
  private Event timeoutEvent;

  public Timer(long waitTime) {
    this(waitTime, false);
  }

  public Timer(long waitTime, boolean autoRepeat) {
    this.waitTime = waitTime;
    this.timeLeft = waitTime;
    this.timeoutEvent = new Event();
    this.paused = true;
  }

  public EventGetter onTimeout() {
    return timeoutEvent;
  }

  public void reset() {
    timeLeft = waitTime;
    paused = false;
  }

  public void pause() {
    paused = true;
  }

  public void resume() {
    paused = false;
  }

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
