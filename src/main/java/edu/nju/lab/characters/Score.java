package edu.nju.lab.characters;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Score implements Serializable{
  /**
   *
   */
  private static final long serialVersionUID = -8735785971300120249L;
  int score;

  public Score(int score){
    this.score = score;
  }

  public void add(){
    this.score += 1;
  }

  public void draw(GraphicsContext gc){
    gc.setFill(Color.WHITE);
		gc.setFont(new Font("", 30));
		gc.fillText("Score: " + score, 10, 30);
  }
  
}
