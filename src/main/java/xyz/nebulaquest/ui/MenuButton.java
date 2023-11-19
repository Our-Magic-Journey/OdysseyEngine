package xyz.nebulaquest.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Canvas;

/** Hamburger like Menu Button.
 * 
 * @see AbstractButton
*/
public class MenuButton extends AbstractButton {

  /**
   * Constructs a new instance of the MenuButton.
   * 
   * @param x The x-coordinate of the button.
   * @param y The y-coordinate of the button.
   * @param inputManager The input manager for handling user input.
   * 
   */
  public MenuButton(int x, int y,InputManager inputManager) {
    super(x,y,25,40,new Color(0xff7b00),inputManager);
  }

  @Override
  public void draw(Graphics2D context, Canvas canvas) {
    super.draw(context, canvas);
    
    context.fillRect(x, y, 5, 40);  
    context.fillRect(x+10, y, 5, 40); 
    context.fillRect(x+20, y, 5, 40); 
  }
}
