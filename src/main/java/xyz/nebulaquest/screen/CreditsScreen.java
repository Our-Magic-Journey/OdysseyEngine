package xyz.nebulaquest.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Canvas;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.resource.ResourceManager;
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
  private Text author1;
  private Text author2;
  private TextButton returnButton;
  private Timer returnTimer;

  public CreditsScreen(InputManager inputManager, ScreenManager screenManager, ResourceManager resourceManager, Canvas canvas) {
    super(inputManager, screenManager, resourceManager, canvas);
  }

  @Override
  public void load() {
    BufferedImage backgroundImage = resourceManager.getImage("/images/background.png");
    Font titleFont = resourceManager.getFont("/fonts/MOOD MKII.ttf");
    Font font = resourceManager.getFont("/fonts/MiniMOOD.ttf");

    background = new Image(backgroundImage, 0, 0);
    title = new Text("Nebula Quest", 50, 100, titleFont, new Color(0xff7b00), 50);
    author1 = new Text("Dominik 1", 280, 250, font, new Color(0xff7b00), 20);
    author2 = new Text("Dominik 2", 280, 310, font, new Color(0xff7b00), 20);
    returnButton = new TextButton("Return", 50, 450, font, new Color(0xff7b00), 30, inputManager, canvas);
    returnTimer = new Timer(10000);
    
    returnButton.onClick().subscribe(this::backToMenu);
    returnTimer.onTimeout().subscribe(this::backToMenu);

    returnTimer.resume();
  }

  private void backToMenu() {
    this.screenManager.change("menu");
  }

  @Override
  public void unload() {
    returnTimer.dispatch();
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
    renderer.draw(author1);
    renderer.draw(author2);
    renderer.draw(returnButton);
  }
}
