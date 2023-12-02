package xyz.magicjourney.odyssey.screen;

import xyz.magicjourney.odyssey.input.InputManager;
import xyz.magicjourney.odyssey.renderer.Canvas;
import xyz.magicjourney.odyssey.resource.ResourceManager;

/**
 * Functional interface representing a constructor of the Screen class.
 */
@FunctionalInterface
public interface ScreenConstructor {
   /**
   * Creates a new instance of the Screen.
   *
   * @param inputManager    The input manager for handling user input.
   * @param screenManager   The screen manager for managing different screens in the game.
   * @param resourceManager The resource manager for managing game resources.
   * @param canvas          The canvas on which the screen will be rendered.
   * @return A new instance of the Screen.
   */
  Screen create(InputManager inputManager, ScreenManager screenManager, ResourceManager resourceManager, Canvas canvas);
}
