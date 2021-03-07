package edu.nju.lab.game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RunningState implements State {
  /**
   *
   */
  private static final long serialVersionUID = 2667551074255975872L;

  Game game;
  
  public RunningState(Game game, GraphicsContext gc){
    this.game = game;
  }

  public void spacePressed(GraphicsContext gc, AnimationTimer timer){
    gc.setFill(Color.RED);
    gc.setFont(new Font("", 50));
    gc.fillText("Paused", 150, 250);
    timer.stop();
    this.game.setState(this.game.getPausedState());
  };
}
