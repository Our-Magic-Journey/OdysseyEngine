package xyz.nebulaquest.screen;

import java.awt.Color;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.ui.Image;
import xyz.nebulaquest.ui.Text;
import xyz.nebulaquest.ui.TextButton;

/**
 * Represents the main menu screen in the game.
 * 
 * @see Screen
 */
public class MenuScreen extends Screen {
  private Image background;
  private Text title;

  private TextButton startButton;
  private TextButton optionsButton;
  private TextButton creditsButton;
  private TextButton exitButton;


  public MenuScreen(InputManager inputManager, ScreenManager screenManager) {
    super(inputManager, screenManager);
  }

  @Override
  public void load() {
    background = new Image("/images/background.png", 0, 0);
    title = new Text("Nebula Quest", 280, 100, "/fonts/NewRocker.ttf", new Color(0xff7b00), 70);
    startButton = new TextButton("Start", 280, 250, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50, inputManager);
    optionsButton = new TextButton("Options", 280, 310, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50, inputManager);
    creditsButton = new TextButton("Credits", 280, 370, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50, inputManager);
    exitButton = new TextButton("Exit", 280, 430, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50, inputManager);

    creditsButton.onClick().subscribe(()->this.screenManager.change("credits"));
    exitButton.onClick().subscribe(this::handleExitButtonClick);
  }

  private void handleExitButtonClick(){
    screenManager.closeGame();
  }

  @Override
  public void unload() {
    startButton.dispatch(inputManager);
    optionsButton.dispatch(inputManager);
    creditsButton.dispatch(inputManager);
    exitButton.dispatch(inputManager);
  }

  @Override
  public void update(long deltaTime) {
    startButton.update(deltaTime);
    optionsButton.update(deltaTime);
    creditsButton.update(deltaTime);
    exitButton.update(deltaTime);
  }

  @Override
  public void draw(Renderer renderer) {
    renderer.draw(background);
    renderer.draw(title);
    renderer.draw(startButton);
    renderer.draw(optionsButton);
    renderer.draw(creditsButton);
    renderer.draw(exitButton);
  }

}
