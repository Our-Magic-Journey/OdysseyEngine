package xyz.nebulaquest.screen;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;

public abstract class Screen {
  protected InputManager inputManager;
  protected ScreenManager screenManager;
  
  public Screen(InputManager inputManager, ScreenManager screenManager) {
    this.inputManager = inputManager;
    this.screenManager = screenManager;
  }

  public abstract void load();
  public abstract void unload();
  public abstract void update(long deltaTime);
  public abstract void draw(Renderer renderer);
}
