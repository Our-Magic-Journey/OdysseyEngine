package xyz.magicjourney.odyssey.update;

/**
 * Represents an object that can be updated.
 */
public interface Updatable {
  /**
   * Updates the object based on the elapsed time since the last update.
   * 
   * @param deltaTime The time passed since the last update, in milliseconds.
   */
  void update(long deltaTime);
}
