package xyz.nebulaquest.resource.loader;

import java.util.HashMap;
import java.util.stream.Stream;


/**
 * The abstract Loader class provides a common structure for loading resources of a specific type.
 *
 * @param <T> The type of resource to be loaded.
 */
public abstract class Loader<T> {
  protected HashMap<String, T> storage;
  protected String[] extensions;


  /**
   * Constructs a Loader with the specified storage and supported file extensions.
   *
   * @param storage The HashMap to store loaded resources.
   * @param extensions The array of file extensions supported by the loader.
   */
  public Loader(HashMap<String, T> storage, String[] extensions) {
    this.storage = storage;
    this.extensions = extensions;
  }
  
  /**
   * Attempts to load a resource from the specified path.
   *
   * @param path The path of the resource to be loaded.
   * @return {@code true} if the resource is loaded successfully, {@code false} otherwise.
   */
  public boolean load(String path) {
    if (canBeLoaded(path)) {
      try {
        storage.put(path, loadResource(path));
        return true; 
      }
      catch (Exception e){
        System.out.println("Cannot load " + path);
        e.printStackTrace();
      }
    }

    return false;
  }

  /**
   * Checks if a resource at the specified path is supported by loader based on its file extension.
   *
   * @param path The path of the resource.
   * @return {@code true} if the resource can be loaded, {@code false} otherwise.
   */
  public boolean canBeLoaded(String path) {
    String extension = getFileExtension(path);

    return Stream.of(extensions).anyMatch(ext -> extension.equals(ext));
  }

  /**
   * Retrieves the file extension from the specified path.
   *
   *  <pre>Examples:
   *    getFileExtension("/data/file.json") returns ".json"
   *    getFileExtension("./data/file.animation.json") returns ".animation.json"
   *    getFileExtension("./data/file") returns "file"
   *    getFileExtension(".gitignore") returns ".gitignore"
   *    getFileExtension("Dockerfile") returns "Dockerfile"
   *  </pre>
   * 
   * @param path The path of the resource.
   * @return The file extension.
   */
  protected String getFileExtension(String path) {
    String filename = getFileName(path);
    int firstDot = filename.indexOf(".", 0);

    if (firstDot > 0) {
      return filename.substring(firstDot);
    }

    return filename;
  }

  /**
   * Retrieves the file name from the specified path.
   *
   * @param path The path of the resource.
   * @return The file name.
   */
  protected String getFileName(String path) {
    return path.substring(Math.max(0, path.indexOf("/")));
  }
  
  /**
   * Loads a resource from the specified path.
   *
   * @param path The path of the resource to be loaded.
   * @return The loaded resource.
   * @throws Exception if an error occurs during the loading process.
   */
  protected abstract T loadResource(String path) throws Exception;
}
