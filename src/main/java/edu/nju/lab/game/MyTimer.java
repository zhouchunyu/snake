package edu.nju.lab.game;

import java.io.Serializable;

import edu.nju.lab.exception.CollapseException;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MyTimer extends AnimationTimer implements Serializable{
  /**
   *
   */
  private static final long serialVersionUID = 7120834531576081410L;
  long lastTick = 0;
  Game game;
  GraphicsContext gc;
  int speed;
  

  public MyTimer(Game game, GraphicsContext gc, int speed){
    this.game = game;
    this.gc = gc;
    this.speed = speed;
  }

  @Override
  public void handle(long now) {
    // TODO Auto-generated method stub
    try {
      if (lastTick == 0) {
        lastTick = now;
        game.tick(gc);
        return;
      }

      if (now - lastTick > 1000000000 / speed) {
        lastTick = now;
        game.tick(gc);
      }
    } catch (CollapseException e) {
      gc.setFill(Color.RED);
      gc.setFont(new Font("", 50));
      gc.fillText("GAME OVER", 100, 250);
      this.stop();
    }

  }
}