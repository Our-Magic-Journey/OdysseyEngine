package xyz.nebulaquest.renderer;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.input.types.KeyInputType;
import xyz.nebulaquest.input.types.MouseWheelInputType;
import xyz.nebulaquest.math.Vector;
import xyz.nebulaquest.update.Updatable;

/**
 * Represents a game camera.
 * The camera supports movement using keyboard input and zooming with the mouse wheel.
 *  
 * <p>The main purpose of the camera is to create an illusion that the user is viewing a larger scene by rendering a portion of it onto the application canvas. 
 * This is essential for scenarios where the content, like a game map, exceeds the dimensions of the game window.</p>
 * 
 * <p>Imagine having a piece of paper (representing the entire game map) and a camera-equipped phone. Initially, the phone's screen displays only a portion of the paper.
 * However, as you move the paper to the left, the phone's screen updates to reveal the previously hidden right portion of the paper. The camera class replicates this behavior 
 * in a virtual environment.</p>
 */
public class Camera implements Drawable, Updatable {
  protected float zoom;
  protected Vector position;
  protected Canvas canvas;
  protected InputManager inputManager;
  protected BufferedImage buffer;
  protected Graphics2D cameraContext;
  protected Vector movementDirection;
  protected double cameraSpeed;
  protected float zoomSpeed;

 /**
   * Creates a new Camera instance.
   *
   * <p><b>NOTE:</b> The {@code bufferWidth} and {@code bufferHeight} parameters do not represent the size of the camera's visible area on the canvas; 
   * instead, they determine the dimensions of the internal camera buffer.
   * </p>
   * 
   * <p><b>Buffer?</b> The camera renders onto a larger image (buffer), and only a small portion of it is displayed on the canvas. </p>
   * 
   * @param canvas The canvas associated with the camera.
   * @param inputManager The input manager for handling user input.
   * @param bufferWidth The width of the camera buffer.
   * @param bufferHeight The height of the camera buffer.
   */
  public Camera(Canvas canvas, InputManager inputManager, int bufferWidth, int bufferHeight) {
    this.canvas = canvas;
    this.inputManager = inputManager;
    this.position = new Vector();
    this.buffer = new BufferedImage(bufferWidth, bufferHeight, BufferedImage.TYPE_INT_ARGB);
    this.cameraContext = (Graphics2D) buffer.getGraphics();
    this.movementDirection = new Vector();
    this.zoom = 1f;
    this.cameraSpeed = 0.1;
    this.zoomSpeed = 0.05f;

    this.inputManager.onKeyboardEvent().subscribe(KeyInputType.PRESS, this::handleKeyPress);
    this.inputManager.onKeyboardEvent().subscribe(KeyInputType.RELEASE, this::handleKeyRelease);
    this.inputManager.onMouseWheelEvent().subscribe(MouseWheelInputType.SCROLL, this::zoomCamera);
  }

  /**
   * Handles key press events to determine the movement direction of the camera.
   * 
   * @param event The key press event.
   */
  protected void handleKeyPress(KeyEvent event) {
    switch (event.getKeyChar()) {
      case 'w' -> movementDirection.add(0, -1);
      case 's' -> movementDirection.add(0, 1);
      case 'a' -> movementDirection.add(-1,0);
      case 'd' -> movementDirection.add(1, 0);
    }

    movementDirection.clamp(-1, 1);
  }

  /**
   * Handles key release events to stop camera movement in the released direction.
   * 
   * @param event The key release event.
   */
  protected void handleKeyRelease(KeyEvent event) {
    switch (event.getKeyChar()) {
      case 'w' -> movementDirection.subtract(0, -1);
      case 's' -> movementDirection.subtract(0, 1);
      case 'a' -> movementDirection.subtract(-1,0);
      case 'd' -> movementDirection.subtract(1, 0);
    }
  }

  /**
   * Handles mouse wheel events to zoom the camera in or out.
   * 
   * @param event The mouse wheel event.
   */
  protected void zoomCamera(MouseWheelEvent event) {
    this.setZoom(zoom + -event.getWheelRotation() * zoomSpeed);
  }

  /**
   * Disposes of the camera resources and unsubscribes from input events.
   */
  public void dispose() {
    this.cameraContext.dispose();
    this.inputManager.onKeyboardEvent().unsubscribe(KeyInputType.PRESS, this::handleKeyPress);
    this.inputManager.onKeyboardEvent().unsubscribe(KeyInputType.RELEASE, this::handleKeyRelease);
    this.inputManager.onMouseWheelEvent().unsubscribe(MouseWheelInputType.SCROLL, this::zoomCamera);
  }

  /**
   * Gets the current zoom level of the camera.
   * 
   * @return The zoom level.
   */
  public double getZoom() {
    return zoom;
  }

  /**
   * Sets the zoom level of the camera.
   * 
   * @param zoom The new zoom level.
   */
  public void setZoom(float zoom) {
    this.zoom = (float) Math.clamp(zoom, 0.45, 2.0);
    this.setPosition(position);
  }

  /**
   * Gets the current position of the camera.
   * 
   * @return The position vector.
   */
  public Vector getPosition() {
    return position;
  }

  /**
   * Sets the position of the camera.
   * 
   * @param position The new position vector.
   */
  public void setPosition(Vector position) {
    Vector maximalPosition = new Vector(this.buffer.getWidth(), this.buffer.getHeight())
      .multiply(zoom)
      .subtract(new Vector(this.canvas.getCanvasWidth(), this.canvas.getCanvasHeight()))
      .abs();

    this.position = Vector.clamp(position, new Vector(0), maximalPosition);
  }

  /**
   * Sets the position of the camera.
   * 
   * @param position The new position vector.
   */
  public void move(Vector position) {
    setPosition(Vector.addition(this.position, position));
  }


  /**
   * Renders the provided drawable object onto the camera buffer.
   * 
   * @param object The drawable object to render.
   */
  public void render(Drawable object) {
    object.draw(cameraContext, canvas);
  }

  /**
   * Draws the visible portion of the camera buffer onto the application canvas.
   * 
   * @param context The graphics context of the canvas.
   * @param canvas The canvas associated with the camera.
   */
  @Override
  public void draw(Graphics2D context, Canvas canvas) {
    // Calculate the position of the visible portion on the canvas
    int canvasX =  (int) (-position.getRoundX() * zoom);
    int canvasY =  (int) (-position.getRoundY() * zoom);
  
    // Calculate the dimensions of the visible portion on the canvas
    int canvasWidth = (int) (buffer.getWidth() * zoom);
    int canvasHeight = (int) (buffer.getHeight() * zoom);
  
    // Draw the visible portion of the camera buffer onto the canvas
    context.drawImage(buffer, canvasX, canvasY, canvasWidth, canvasHeight, null);
  }

  /**
   * Updates the camera position based on user input.
   * 
   * @param deltaTime The time elapsed since the last update.
   */
  @Override
  public void update(long deltaTime) {
    this.move(Vector.multiplication(movementDirection, cameraSpeed * deltaTime));
  }
}
