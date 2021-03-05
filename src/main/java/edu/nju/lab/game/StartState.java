package edu.nju.lab.game;
import javafx.animation.AnimationTimer;

public class StartState implements State{
  AnimationTimer timer;
  Game game;

  public StartState(Game game, AnimationTimer timer){
    this.timer = timer;
    this.game = game;
  }
  
  public void spacePressed(){
    timer.start();
    this.game.setState(this.game.getRunningState());
  };
  
}
