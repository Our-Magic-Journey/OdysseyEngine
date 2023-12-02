package xyz.magicjourney.odyssey.resource.loader;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * A loader for handling image resources.
 */
public class ImageLoader extends Loader<BufferedImage> {

  /**
   * Constructs an ImageLoader with the specified storage for loaded images.
   *
   * @param storage The storage for loaded images.
   */
  public ImageLoader(HashMap<String, BufferedImage> storage) {
    super(storage, new String[] {".png", ".jpg"});
  }

  /**
   * Loads an image resource from the specified path.
   *
   * @param path The path of the image resource.
   * @return The loaded BufferedImage.
   * @throws Exception if an error occurs during loading.
   */
  @Override
  protected BufferedImage loadResource(String path) throws Exception {
    return ImageIO.read(getClass().getResourceAsStream(path));
  }
}