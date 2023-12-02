package xyz.magicjourney.odyssey.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import xyz.magicjourney.odyssey.input.InputManager;
import xyz.magicjourney.odyssey.renderer.Canvas;
import xyz.magicjourney.odyssey.renderer.Renderer;
import xyz.magicjourney.odyssey.resource.ResourceManager;
import xyz.magicjourney.odyssey.ui.Image;
import xyz.magicjourney.odyssey.ui.Text;

/**
 * Screen displaying the progress of asset loading via ResourceManager.
 * 
 * @see Screen
 * @see ResourceManager
 */
public abstract class AbstractLoadingScreen extends Screen {
  protected Image background;
  protected Text title;
  protected Text loadingDetails;
  protected Text loadingProgress;

  public AbstractLoadingScreen(InputManager inputManager, ScreenManager screenManager, ResourceManager resourceManager, Canvas canvas) {
    super(inputManager, screenManager, resourceManager, canvas);
  }

  /**
   * Background image for the loading screen.
   * @return The background image.
   */
  protected abstract BufferedImage getBackgroundImage();

  /**
   * Font used for the loading screen title.
   * @return The title font.
   */
  protected abstract Font getTitleFont();

  /**
   * Font used for the loading screen.
   * @return The general font.
   */
  protected abstract Font getFont();

  /**
   * Action to be taken when the loading screen completes.
   */
  protected abstract void goToNextScene();

  /**
   * Loads image
   * <p>This operation cannot be performed via ResourceManager because the Loading Screen is displayed before the ResourceManager is ready.</p>
   *  
   * <p><b>NOTE:</b> This method must be called before creating the game window; otherwise, Keyboard events may be disrupted.
   * The error is likely related to the AWT event loop. Refer to https://stackoverflow.com/questions/44203744/program-blocks-when-calling-imageio-readfile </p>
   */
  protected static BufferedImage loadImage(String path, ClassLoader loader) {
    try {
      return ImageIO.read(loader.getResourceAsStream(path));
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Loads font
   * <p>This operation cannot be performed via ResourceManager because the Loading Screen is displayed before the ResourceManager is ready.</p>
   *  
   * <p><b>NOTE:</b> This method must be called before creating the game window; otherwise, Keyboard events may be disrupted.
   * The error is likely related to the AWT event loop. Refer to https://stackoverflow.com/questions/44203744/program-blocks-when-calling-imageio-readfile </p>
   */
  protected static Font loadFont(String path, ClassLoader loader) {
    try {
      return Font.createFont(Font.TRUETYPE_FONT, loader.getResourceAsStream(path));
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public void load() {
    background = new Image(getBackgroundImage(), 0, 0);
    title = new Text("Odyssey Engine", 50, 100, getTitleFont(), new Color(0xff7b00), 50);
    
    loadingDetails = new Text("Loading", 50, 470, getFont(), new Color(0xffffff), 15);
    loadingProgress = new Text("File 0/0", 50, 500, getFont(), new Color(0xffffff), 20);
    
    resourceManager.onFinish().subscribe(this::goToNextScene);
  }


  @Override
  public void unload() {
    resourceManager.onFinish().unsubscribe(this::goToNextScene);
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
