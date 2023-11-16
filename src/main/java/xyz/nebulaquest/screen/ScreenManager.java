package xyz.nebulaquest.screen;

import java.util.HashMap;
import java.util.Optional;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;

public class ScreenManager {
  public HashMap<String, Screen> screens;
  private String selected;
  private Optional<Runnable> closeGameHandler;

  public ScreenManager(InputManager inputManager) {
    this.screens = new HashMap<>();
    this.closeGameHandler = Optional.empty();
    this.selected = "menu";

    registerScreen("menu", new MenuScreen(inputManager, this));
    getCurrent().load();
  }

  private void registerScreen(String name, Screen screen) {
    screens.put(name, screen);
  }

  public void onGameClose(Runnable callback) {
    this.closeGameHandler = Optional.of(callback);
  }

  public Screen getCurrent() {
    return screens.get(selected);
  }
  
  public void change(String state) {
    if (!isScreenRestated(state)) {
      return;
    }

    getCurrent().unload();
    selected = state;
    getCurrent().load();
  }

  private boolean isScreenRestated(String key) {
    return screens.containsKey(key);
  }

  public void closeGame() {
    if (closeGameHandler.isPresent()) {
      closeGameHandler.get().run();
    }
  }

  public void update(long deltaTime) {
    getCurrent().update(deltaTime);
  }

  public void draw(Renderer renderer) {
    getCurrent().draw(renderer);
  }
}
