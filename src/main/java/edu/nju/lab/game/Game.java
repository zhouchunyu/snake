package edu.nju.lab.game;

import edu.nju.lab.exception.CollapseException;
import java.io.Serializable;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import edu.nju.lab.characters.Snake;
import edu.nju.lab.characters.Block;
import edu.nju.lab.characters.Food;
import edu.nju.lab.characters.Score;
import edu.nju.lab.controller.Controller;

public class Game implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -8940477475263808180L;
  public int speed;

  private int width;
  private int height;
  private int blocksize;
  private Random rand;
  private Snake snake;
  private Food food;
  private Controller controller;
  private Score score;

 

  State startState;
  State runningState;
  State pausedState;
  State endState;
  State current_state;

  public Game(GraphicsContext gc, Scene scene){
    this.width = 20;
    this.height = 20;
    this.blocksize = 25;
    this.speed = 2;
    this.rand = new Random();
    this.snake = new Snake(width, height);
    this.food = newFood();
    this.controller = new Controller(snake);
    this.score = new Score(0);


    this.startState = new StartState(this);
    this.runningState = new RunningState(this, gc);
    this.pausedState = new PausedState(this);
    // this.endState = new EndState();
    this.current_state = this.startState;
  
  }


  public void spacePressed(GraphicsContext gc, AnimationTimer timer){
    this.current_state.spacePressed(gc, timer);
  }

  public void setState(State state){
    this.current_state = state;
  }

  public State getStartState(){
    return this.startState;
  }
  
  public State getPausedState(){
    return this.pausedState;
  }

  public State getRunningState(){
    return this.runningState;
  }

  public State getEndState(){
    return this.endState;
  }


  // tick
	public void tick(GraphicsContext gc) throws CollapseException{
		// fill background
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width * blocksize, height * blocksize);

		controller.refresh();

		// new Food if been eaten
		if(snake.eaten(food)){
			food = newFood();
			score.add();
		}

		// score
		score.draw(gc);

		//food
		food.draw(gc, blocksize);

		// snake
		snake.draw(gc, blocksize);
	}

	public Food newFood() {
		Food new_food;
		while (true) {
			boolean free = true;
			new_food = new Food(rand.nextInt(width), rand.nextInt(height));
			for (Block b : snake.blocks) {
				if (b.x == new_food.x && b.y == new_food.y) {
					free = false;
					break;
				}
			}
			if(free){
				speed++;
				break;
			}
		}
		return new_food;
	}



  public void up(){
    controller.up();
  }

  public void down(){
    controller.down();
  }

  public void left(){
    controller.left();
  }
  public void right(){
    controller.right();
  }
}
