package edu.nju.lab.game;

import java.io.Serializable;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public interface State extends Serializable{
  public void spacePressed(GraphicsContext gc, AnimationTimer timer);
}
