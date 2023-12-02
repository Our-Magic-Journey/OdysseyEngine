package xyz.magicjourney.nebulaquest.screen;

import java.awt.Font;
import java.awt.image.BufferedImage;

import xyz.magicjourney.nebulaquest.Game;
import xyz.magicjourney.odyssey.input.InputManager;
import xyz.magicjourney.odyssey.renderer.Canvas;
import xyz.magicjourney.odyssey.resource.ResourceManager;
import xyz.magicjourney.odyssey.screen.AbstractLoadingScreen;
import xyz.magicjourney.odyssey.screen.ScreenManager;

public class LoadingScreen extends AbstractLoadingScreen {
  protected static BufferedImage backgroundImage;
  protected static Font titleFont;
  protected static Font font;

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
    ClassLoader loader = Game.class.getClassLoader();

    backgroundImage = loadImage("images/background.png", loader);
    titleFont = loadFont("fonts/MOOD MKII.ttf", loader);
    font = loadFont("fonts/MiniMOOD.ttf", loader);
  }

  @Override
  protected BufferedImage getBackgroundImage() {
    return backgroundImage;
  }

  @Override
  protected Font getTitleFont() {
    return titleFont;
  }

  @Override
  protected Font getFont() {
    return font;
  }

  @Override
  protected void goToNextScene() {
    System.out.println("go to menu");
    screenManager.change("menu");
  }

  @Override
  public void load() {
    super.load();

    title.setText("Nebula Quest");
  }
}
