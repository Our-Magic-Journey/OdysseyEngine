package xyz.nebulaquest.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import xyz.nebulaquest.input.InputManager;
import xyz.nebulaquest.renderer.Canvas;

public class MenuButton extends AbstractButton {

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
