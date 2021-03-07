package edu.nju.lab.characters;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;

public class Food implements Serializable{
  /**
   *
   */
  private static final long serialVersionUID = -2923756590532565122L;
  
  public static Random rand = new Random();

  public int x;
  public int y;
  public int color;
  
  public Food(int x, int y) {
    this.x = x;
    this.y = y;
    this.color = rand.nextInt(5);
  }

  public void draw(GraphicsContext gc, int blocksize){
    gc.setFill(toColor(color));
		gc.fillOval(x * blocksize, y * blocksize, blocksize, blocksize);
  }


  public Color toColor(int color){
		// random foodcolor
		Color cc;
		switch(color) {
			case 0:
				cc = Color.PURPLE;
				break;
			case 1:
				cc = Color.LIGHTBLUE;
				break;
			case 2:
				cc = Color.YELLOW;
				break;
			case 3:
				cc = Color.PINK;
				break;
			case 4:
				cc = Color.ORANGE;
				break;
			default:
				cc = Color.WHITE;
		}
		return cc;
	}

}
