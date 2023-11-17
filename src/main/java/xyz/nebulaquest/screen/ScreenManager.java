package xyz.nebulaquest.screen;

import java.util.HashMap;

import xyz.nebulaquest.event.Event;
import xyz.nebulaquest.event.EventGetter;
import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;

public class ScreenManager {
  public HashMap<String, Screen> screens;
  private String selected;
  private Event closeGameEvent;

  public ScreenManager(InputManager inputManager) {
    this.screens = new HashMap<>();
    this.closeGameEvent = new Event();
    this.selected = "menu";

    registerScreen("menu", new MenuScreen(inputManager, this));
    registerScreen("credits", new CreditsScreen(inputManager, this));
    getCurrent().load();
  }

  private void registerScreen(String name, Screen screen) {
    screens.put(name, screen);
  }

  public EventGetter onGameClose() {
    return this.closeGameEvent;
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
    this.closeGameEvent.emit();
  }

  public void update(long deltaTime) {
    getCurrent().update(deltaTime);
  }

  public void draw(Renderer renderer) {
    getCurrent().draw(renderer);
  }
}
