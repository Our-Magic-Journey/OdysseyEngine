package xyz.nebulaquest.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import xyz.nebulaquest.Game;
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

  private static BufferedImage backgroundImage;
  private static Font titleFont;
  private static Font font;

  public LoadingScreen(InputManager inputManager, ScreenManager screenManager, ResourceManager resourceManager, Canvas canvas) {
    super(inputManager, screenManager, resourceManager, canvas);
  }

  /**
   * Loads assets to be used in the Loading screen.
   * <p>This operation cannot be performed via ResourceManager because the Loading Screen is displayed before the ResourceManager is ready.</p>
   *  
   * <p><b>NOTE:</b> This method must be called before creating the game window; otherwise, Keyboard events may be disrupted.
   * The error is likely related to the AWT event loop. Refer to https://stackoverflow.com/questions/44203744/program-blocks-when-calling-imageio-readfile </p>
   */
  public static void loadAssets() {
    try {
      ClassLoader classLoader = Game.class.getClassLoader();

      backgroundImage = ImageIO.read(classLoader.getResourceAsStream("images/background.png"));
      titleFont = Font.createFont(Font.TRUETYPE_FONT, classLoader.getResourceAsStream("fonts/MOOD MKII.ttf"));
      font = Font.createFont(Font.TRUETYPE_FONT, classLoader.getResourceAsStream("fonts/MiniMOOD.ttf"));
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
