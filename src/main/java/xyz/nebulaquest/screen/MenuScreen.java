package xyz.nebulaquest.screen;

import java.awt.Color;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.ui.Image;
import xyz.nebulaquest.ui.Text;

public class MenuScreen extends Screen {
  public Image background;
  public Text title;

  public Text startText;
  public Text optionsText;
  public Text exitText;

  public MenuScreen(InputManager inputManager, ScreenManager screenManager) {
    super(inputManager, screenManager);

    background = new Image("/background.png", 0, 0);
    title = new Text("Nebula Quest", 280, 100, "New Rocker", new Color(0xff7b00), 70);
  }

  @Override
  public void load() {
  }

  @Override
  public void unload() {
  }

  @Override
  public void update(long deltaTime) {
  }

  @Override
  public void draw(Renderer renderer) {
    renderer.draw(background);
    renderer.draw(title);
  }
  
}
