package edu.nju.lab.game;

import javafx.animation.AnimationTimer;

public class PausedState implements State {
  AnimationTimer timer;
  Game game;

  public PausedState(Game game, AnimationTimer timer){
    this.timer = timer;
    this.game = game;
  }

  public void spacePressed(){
    this.timer.start();
    this.game.setState(this.game.getRunningState());
  };
}
