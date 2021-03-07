package edu.nju.lab.game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class PausedState implements State {
  /**
   *
   */
  private static final long serialVersionUID = 1321658657731781145L;
  Game game;

  public PausedState(Game game){
    this.game = game;
  }

  public void spacePressed(GraphicsContext gc, AnimationTimer timer){
    timer.start();
    this.game.setState(this.game.getRunningState());
  };
}
