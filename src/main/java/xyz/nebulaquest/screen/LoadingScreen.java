package xyz.nebulaquest.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Canvas;
import xyz.nebulaquest.renderer.Renderer;
import xyz.nebulaquest.resource.ResourceManager;
import xyz.nebulaquest.ui.Image;
import xyz.nebulaquest.ui.Text;

/**
 * Screen displaying the progress of asset loading via ResourceManager.
 * 
 * @see Screen
 * @see ResourceManager
 */
public class LoadingScreen extends Screen {
  private Image background;
  private Text title;
  private Text loadingDetails;
  private Text loadingProgress;

  BufferedImage backgroundImage;
  Font titleFont;
  Font font;

  public LoadingScreen(InputManager inputManager, ScreenManager screenManager, ResourceManager resourceManager, Canvas canvas) {
    super(inputManager, screenManager, resourceManager, canvas);

    loadAssets();
  }

  /**
   * This screen is displayed before ResourceManager is ready, so we load assets manually.
   */
  private void loadAssets() {
    try {
      backgroundImage = ImageIO.read(getClass().getResourceAsStream("/images/background.png"));
      titleFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/MOOD MKII.ttf"));
      font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/MiniMOOD.ttf"));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void load() {
    background = new Image(backgroundImage, 0, 0);
    title = new Text("Nebula Quest", 50, 100, titleFont, new Color(0xff7b00), 50);
    
    loadingDetails = new Text("Loading", 50, 470, font, new Color(0xffffff), 15);
    loadingProgress = new Text("File 0/0", 50, 500, font, new Color(0xffffff), 20);
    
    resourceManager.onFinish().subscribe(this::goToMenu);
  }

  private void goToMenu() {
    this.screenManager.change("menu");
  }


  @Override
  public void unload() {
    resourceManager.onFinish().unsubscribe(this::goToMenu);
  }

  @Override
  public void update(long deltaTime) {
    loadingDetails.setText(resourceManager.getLastLoadedFile());
    loadingProgress.setText(resourceManager.getLoadingProgressText());
  }

  @Override
  public void draw(Renderer renderer) {
    renderer.draw(background);
    renderer.draw(title);
    renderer.draw(loadingDetails);
    renderer.draw(loadingProgress);
  }
}
