package xyz.magicjourney.nebulaquest;

import xyz.magicjourney.nebulaquest.screen.CreditsScreen;
import xyz.magicjourney.nebulaquest.screen.LoadingScreen;
import xyz.magicjourney.nebulaquest.screen.MenuScreen;
import xyz.magicjourney.odyssey.Engine;

public class Game {
  public static void main(String[] args) {
    LoadingScreen.loadAssets();
    Engine engine = new Engine("Nebula Quest");

    engine.setLoadingScreen(LoadingScreen::new);
    engine.registerScreen("menu", MenuScreen::new);
    engine.registerScreen("credits", CreditsScreen::new);

    engine.start();
  }
}
