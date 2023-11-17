package xyz.nebulaquest.screen;

import java.awt.Color;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.ui.Image;
import xyz.nebulaquest.ui.Text;


public class CreditsScreen extends Screen {
  public Image background;
  public Text title;
  public Text autor1;
  public Text autor2;

  public CreditsScreen(InputManager inputManager, ScreenManager screenManager) {
    super(inputManager, screenManager);
  }

  @Override
  public void load() {
    background = new Image("/background.png", 0, 0);
    title = new Text("Nebula Quest", 280, 100, "/fonts/NewRocker.ttf", new Color(0xff7b00), 70);
    autor1 = new Text("Dominik", 280, 250, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50);
    autor2 = new Text("Dominik", 280, 310, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50);
  }



  @Override
  public void unload() {}

  @Override
  public void update(long deltaTime) {}

  @Override
  public void draw(Renderer renderer) {
    renderer.draw(background);
    renderer.draw(title);
    renderer.draw(autor1);
    renderer.draw(autor2);

  }

}
