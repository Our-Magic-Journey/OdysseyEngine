package xyz.nebulaquest.screen;

import java.awt.Color;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.ui.Image;
import xyz.nebulaquest.ui.Text;
import xyz.nebulaquest.ui.TextButton;

public class MenuScreen extends Screen {
  public Image background;
  public Text title;

  public TextButton startText;
  public TextButton optionsText;
  public TextButton creditsText;
  public TextButton exitText;


  public MenuScreen(InputManager inputManager, ScreenManager screenManager) {
    super(inputManager, screenManager);
  }

  @Override
  public void load() {
    background = new Image("/background.png", 0, 0);
    title = new Text("Nebula Quest", 280, 100, "/fonts/NewRocker.ttf", new Color(0xff7b00), 70);
    startText = new TextButton("Start", 280, 250, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50, inputManager);
    optionsText = new TextButton("Options", 280, 310, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50, inputManager);
    creditsText = new TextButton("Credits", 280, 370, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50, inputManager);
    exitText = new TextButton("Exit", 280, 430, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50, inputManager);

    creditsText.onClick().subscribe(()->this.screenManager.change("credits"));
    exitText.onClick().subscribe(this::handleExitButtonClick);
  }

  private void handleExitButtonClick(){
    screenManager.closeGame();
  }

  @Override
  public void unload() {
    startText.dispatch(inputManager);
    optionsText.dispatch(inputManager);
    creditsText.dispatch(inputManager);
    exitText.dispatch(inputManager);
  }

  @Override
  public void update(long deltaTime) {
    startText.update(deltaTime);
    optionsText.update(deltaTime);
    creditsText.update(deltaTime);
    exitText.update(deltaTime);
  }

  @Override
  public void draw(Renderer renderer) {
    renderer.draw(background);
    renderer.draw(title);
    renderer.draw(startText);
    renderer.draw(optionsText);
    renderer.draw(creditsText);
    renderer.draw(exitText);
  }

}
