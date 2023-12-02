package xyz.magicjourney.odyssey.input.types;

/**
 * Types of mouse input events.
 */
public enum MouseInputType {
  /** 
   * Indicates that a mouse button was pressed and released. 
   * 
   * <p><b>NOTE:</b> A mouse action will not result in a click if the user moves the mouse before releasing the button.</p>
   */
  CLICK,

  /** 
   * Indicates that the mouse cursor was moved.
   */
  MOVE,

  /** 
   * Indicates that a mouse button was pressed down.
   * 
   * <p><b>NOTE:</b> This event is emitted once per button click. </p>
   */
  BTN_DOWN,

  /** 
   * Indicates that a mouse button was released.
   */
  BTN_UP,

  /** 
   * Indicates that the mouse cursor entered a component.
   */
  ENTER,

  /** 
   * Indicates that the mouse cursor exited a component.
   */
  EXIT,

  /** 
   * Indicates that the mouse was dragged (moved while a button was held down).
   */
  DRAG,
}
