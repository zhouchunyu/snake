package edu.nju.lab.game;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class StartState implements State{
  /**
   *
   */
  private static final long serialVersionUID = -2055107123014668285L;
  Game game;

  public StartState(Game game){
    this.game = game;
  }
  
  public void spacePressed(GraphicsContext gc, AnimationTimer timer){
    timer.start();
    this.game.setState(this.game.getRunningState());
  };
  
}
