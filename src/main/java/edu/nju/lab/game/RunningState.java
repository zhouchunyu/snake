package edu.nju.lab.game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RunningState implements State {
  AnimationTimer timer;
  Game game;
  GraphicsContext gc;
  
  public RunningState(Game game, AnimationTimer timer, GraphicsContext gc){
    this.timer = timer;
    this.game = game;
    this.gc = gc;
  }

  public void spacePressed(){
    gc.setFill(Color.RED);
    gc.setFont(new Font("", 50));
    gc.fillText("Paused", 150, 250);
    timer.stop();
    this.game.setState(this.game.getPausedState());
  };
}
