package xyz.nebulaquest.resource;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import xyz.nebulaquest.event.Event;
import xyz.nebulaquest.event.EventGetter;
import xyz.nebulaquest.resource.loader.FontLoader;
import xyz.nebulaquest.resource.loader.ImageLoader;
import xyz.nebulaquest.resource.loader.Loader;

/**
 * Manages the loading and storage of various resources.
 * <p>Provides methods to start the loading process, monitor progress, and retrieve loaded resources.</p>
 * <p>Each type of loaded resource is handled by a specific loader that extends the {@link Loader} class.</p>
 * <p>Emits the "onLoad" event when all resources are successfully loaded.</p>
 */
public class ResourceManager {
  protected ArrayList<Loader<?>> loaders;
  protected HashMap<String, BufferedImage> images;
  protected HashMap<String, Font> fonts;
  
  protected Thread loadingThread;
  protected Event finishEvent;

  protected long filesCount;
  protected long filesLoaded;
  protected String lastLoaded;

  public ResourceManager() {
    finishEvent = new Event();
    loaders = new ArrayList<>();
    images = new HashMap<>();
    fonts = new HashMap<>();

    filesCount = 0;
    filesLoaded = 0;
    lastLoaded = "";

    loaders.add(new ImageLoader(images));
    loaders.add(new FontLoader(fonts));
  }

  /**
   * Returns an {@link EventGetter} for the "finish" event.
   *
   * <p>This "finish" event is emitted when all resources are loaded and can be safely utilized.</p>
   * <p>Subscribe a function to this event; when all resources are loaded, the subscribed function will be called.</p>
   *
   * @return An {@link EventGetter} for the "finish" event.
   */
  public EventGetter onFinish() {
    return finishEvent;
  }

  /**
   * Gets the loaded image from the specific file path.
   *
   * @param path The file path of the image.
   * @return The loaded image, or null if not found.
   */
  public BufferedImage getImage(String path) {
    return images.get(path);
  }

  /**
   * Gets the loaded font from the specified file path.
   *
   * @param path The file path of the font.
   * @return The loaded font, or null if not found.
   */
  public Font getFont(String path) {
    return fonts.get(path);
  }

  /**
   * Initiates the resource loading process in a separate thread.
   * The loading progress can be monitored using other methods.
   * 
   * @see ResourceManager#getLastLoadedFile
   * @see ResourceManager#getLoadingProgress
   * @see ResourceManager#getLoadingProgressText
   */
  public void startLoading() {
    loadingThread = new Thread(this::loadAllResources);
    loadingThread.start();
  }

  /**
   * Loads all resources. 
   * <p>Updates the loading progress and emits the "onLoad" event when all resources are successfully loaded.</p>
   */
  private void loadAllResources() {
    List<String> files = getAllFilesFromResourceFolder();
    filesCount = files.size();

    for (String file : files) {
      loadFile(file);
      updateLoadingProgress(file);
    }

    finishEvent.emit();
  }

  /**
   * Gets a list of all files in the resource folder.
   * If running from a JAR, retrieves files from the JAR; otherwise, from the {@code src/main/resources} folder.
   *
   * @return A list of file paths.
   */
  private List<String> getAllFilesFromResourceFolder() {
    try {
        if (isRunningFromJAR()) {
          return getAllFilesFromJAR();
        }
        else {
          return getAllFilesFromFolder("src/main/resources");
        }
    } catch (Exception e) {
        System.out.println("Cannot get resource files.");
        e.printStackTrace();
    }

    return Collections.emptyList();
  }

  /**
   * Gets a list of all files from the specified folder path.
   *
   * @param folderPath The path of the folder.
   * @return A list of file paths.
   * @throws IOException if an I/O error occurs.
   */
  private List<String> getAllFilesFromFolder(String folderPath) throws IOException {
    return Files.walk(Paths.get(folderPath))
      .filter(Files::isRegularFile)
      .map(file -> file.toString().replace("\\", "/").replace(folderPath, ""))
      .collect(Collectors.toList());
  }

  /**
   * Retrieves a list of all files in the "resources" folder from the JAR archive.
   * 
   * <p>This method excludes files not originally located in the "src/main/resources" directory
   * by filtering out files from the "META-INF" and "xyz" directories.</p>
   * 
   * <p><b>Why?</b> When Java packages files into a JAR archive, it places files from the "src/main/resources" folder at the root of the archive ("/").
   * Unfortunately, there are already files related to the game code (in the "xyz/nebulaquest" folder) and the "META-INF" directory at the root.
   * To preserve the original file structure of the "src/main/resources" folder, we need to filter out these folders.</p>
   *
   * @return A list of file paths.
   * @throws IOException if an I/O error occurs.
   * @throws URISyntaxException if there is a syntax error in the URI.
   */
  private List<String> getAllFilesFromJAR() throws IOException, URISyntaxException {
    try (FileSystem fs = createFileSystemForJAR()) {
      return Files.walk(fs.getPath("/"))
        .filter(Files::isRegularFile)
        .filter(file -> !file.toString().startsWith("/xyz") && !file.toString().startsWith("/META-INF"))
        .map(file -> file.toString())
        .collect(Collectors.toList());
    }
  }

  /**
   * Checks if the game is running from a JAR file.
   *
   * @return True if running from a JAR, false otherwise.
   */
  private boolean isRunningFromJAR() {
    String className = getClass().getName().replace('.', '/');
    String classJar = getClass().getResource("/" + className + ".class").toString();
    
    return classJar.startsWith("jar:");
  }

  /**
   * Creates a new file system for accessing resources from a JAR file.
   * <p><b>Why?</b> The standard java.nio.file.Files class cannot operate directly on files packed within a JAR archive.
   * Therefore, a virtual file system for JAR files is created to enable operations on these files.</p>
   *
   * @return The created file system.
   * @throws IOException if an I/O error occurs.
   * @throws URISyntaxException if there was a problem with getting the JAR URI.
   */
  private FileSystem createFileSystemForJAR() throws IOException, URISyntaxException {
    return FileSystems.newFileSystem(getJarURI(), Collections.emptyMap());
  }

  /**
   * Gets the URI for the JAR file in which the game is running.
   *
   * @return The URI of the JAR file.
   * @throws URISyntaxException if a URI syntax error occurs.
   */
  private URI getJarURI() throws URISyntaxException {
    String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
    
    return URI.create("jar:file:" + jarPath);
  }

  /**
   * Attempts to load a specific file using available loaders.
   * Prints a message if no suitable loader is found.
   * 
   * <p><b>Note:</b> Loading stops as soon as a suitable loader successfully loads the resource.</p>
   *
   * @param file The path of the file to load.
   */
  private void loadFile(String file) {
    System.out.println("Loading " + file);

    for (Loader<?> loader : loaders) {
      if (loader.load(file)) {
        return;
      }
    }

    System.out.println("There is no file loader for: " + file);
  }

  /**
   * Updates the loading progress by incrementing the count of loaded files and setting the last loaded file.
   *
   * @param file The path of the file that was just loaded.
   */
  private void updateLoadingProgress(String file) {
    filesLoaded += 1;
    lastLoaded = "Loaded " + file;
  }

  /**
   * Gets the path of the last loaded file during the resource loading process.
   *
   * @return The path of the last loaded file.
   */
  public String getLastLoadedFile() {
    return lastLoaded;
  }

  /**
   * Gets the loading progress as a percentage.
   *
   * @return The loading progress percentage.
   */
  public int getLoadingProgress() {
    return (int) ((filesLoaded / filesCount) * 100);
  }

  /**
   * Gets a text indicating the loading progress.
   *
   * @return Text in the format "loaded/total_files".
   */
  public String getLoadingProgressText() {
    return filesLoaded + "/" + filesCount;
  }
}
