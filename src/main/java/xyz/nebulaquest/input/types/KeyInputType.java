package xyz.nebulaquest.input.types;

/**
 * Types of keyboard input events.
 */
public enum KeyInputType {
  /** 
   * Indicates that a keyboard key was pressed.
   * 
   * <p><b>NOTE:</b> This event is emitted every frame</p>
   */
  PRESS,

  /** 
   * Indicates that a keyboard key was released.
   */
  RELEASE,

  /** 
   * Indicates that a Unicode character representation was sent by the keyboard to the system input.
   */
  TYPE,
}