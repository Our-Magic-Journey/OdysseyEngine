package xyz.nebulaquest.resource.loader;

import java.awt.Font;
import java.util.HashMap;


/**
 * A loader for handling font resources
 */
public class FontLoader extends Loader<Font> {

  /**
   * Constructs a FontLoader with the specified storage for loaded fonts.
   *
   * @param storage The storage for loaded fonts.
   */
  public FontLoader(HashMap<String, Font> storage) {
    super(storage, new String[] { ".otf", ".ttf" });
  }

  /**
   * Loads a font resource from the specified path.
   *
   * @param path The path of the font resource.
   * @return The loaded Font.
   * @throws Exception if an error occurs during loading.
   */
  @Override
  protected Font loadResource(String path) throws Exception {
    return Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(path));
  }
}