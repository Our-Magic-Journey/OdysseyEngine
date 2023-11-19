package xyz.nebulaquest.screen;

import java.awt.Color;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.timer.Timer;
import xyz.nebulaquest.ui.Image;
import xyz.nebulaquest.ui.Text;
import xyz.nebulaquest.ui.TextButton;

/**
 * Represents the credits screen in the game.
 * 
 * @see Screen
 */
public class CreditsScreen extends Screen {
  private Image background;
  private Text title;
  private Text autor1;
  private Text autor2;
  private TextButton returnButton;
  private Timer returnTimer;

  public CreditsScreen(InputManager inputManager, ScreenManager screenManager) {
    super(inputManager, screenManager);
  }

  @Override
  public void load() {
    background = new Image("/background.png", 0, 0);
    title = new Text("Nebula Quest", 280, 100, "/fonts/NewRocker.ttf", new Color(0xff7b00), 70);
    autor1 = new Text("Dominik1", 280, 250, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50);
    autor2 = new Text("Dominik2", 280, 310, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50);
    returnButton = new TextButton("Return", 150, 410, "/fonts/Fervojo-Regular.otf", new Color(0xff7b00), 50, inputManager);
    returnTimer = new Timer(10000);
    
    returnButton.onClick().subscribe(()->this.backToMenu());
    returnTimer.onTimeout().subscribe(this::backToMenu);

    returnTimer.resume();
  }

  private void backToMenu() {
    this.screenManager.change("menu");
  }


  @Override
  public void unload() {
    returnTimer.onTimeout().unsubscribe(this::backToMenu);
    returnButton.dispatch(inputManager);
  }

  @Override
  public void update(long deltaTime) {
    returnButton.update(deltaTime);
    returnTimer.update(deltaTime);
  }

  @Override
  public void draw(Renderer renderer) {
    renderer.draw(background);
    renderer.draw(title);
    renderer.draw(autor1);
    renderer.draw(autor2);
    renderer.draw(returnButton);

  }

}
