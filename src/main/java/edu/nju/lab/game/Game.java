package edu.nju.lab.game;

// import edu.nju.lab.characters.Snake;
import edu.nju.lab.exception.CollapseException;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import edu.nju.lab.characters.Snake;
import edu.nju.lab.characters.Block;
import edu.nju.lab.characters.Food;
import edu.nju.lab.characters.Score;
import edu.nju.lab.controller.Controller;

public class Game {
	static int speed = 1;
	static int width = 20;
	static int height = 20;
	static int blocksize = 25;
	static Random rand = new Random();

	static Snake snake = new Snake(width, height);
	static Food food = newFood();
	static Controller controller = new Controller(snake);
	static Score score = new Score(0);

	static VBox root = new VBox();
	static Canvas c = new Canvas(width * blocksize, height * blocksize);
	static GraphicsContext gc = c.getGraphicsContext2D();
	static Scene scene = new Scene(root, width * blocksize, height * blocksize);

  State startState;
  State runningState;
  State pausedState;
  State endState;
  State current_state;
  AnimationTimer timer;

  public Game(Stage primaryStage){
    root.getChildren().add(c);
    primaryStage.setScene(scene);
    primaryStage.setTitle("SNAKE GAME");
    //before running
    // fill background
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width * blocksize, height * blocksize);
    gc.setFill(Color.RED);
    gc.setFont(new Font("", 50));
    gc.fillText("Press Space To Start", 30, 250);
    primaryStage.show();
    
    timer = new AnimationTimer() {
      long lastTick = 0;

      public void handle(long now) {
        try {
          if (lastTick == 0) {
            lastTick = now;
            tick(gc);
            return;
          }

          if (now - lastTick > 1000000000 / speed) {
            lastTick = now;
            tick(gc);
          }
        } catch (CollapseException e) {
          gc.setFill(Color.RED);
          gc.setFont(new Font("", 50));
          gc.fillText("GAME OVER", 100, 250);
          this.stop();
        }
      }
    };

    this.startState = new StartState(this, timer);
    this.runningState = new RunningState(this, timer, gc);
    this.pausedState = new PausedState(this, timer);
    this.endState = new EndState();
    this.current_state = this.startState;

    // control
    scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
      if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) {
        controller.up();
      }
      if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
        controller.left();
      }
      if (key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) {
        controller.down();
      }
      if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
        controller.right();
      }
      if (key.getCode() == KeyCode.SPACE) {
        this.spacePressed();
      }
    });
  }

  public void spacePressed(){
    this.current_state.spacePressed();
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
	public static void tick(GraphicsContext gc) throws CollapseException{
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

	public static Food newFood() {
		Food new_food;
		while (true) {
			boolean free = true;
			new_food = new Food(rand.nextInt(width), rand.nextInt(height), randomColor());
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

  public static Color randomColor(){
		// random foodcolor
		Color cc;
		switch(rand.nextInt(5)) {
			case 0:
				cc = Color.PURPLE;
				break;
			case 1:
				cc = Color.LIGHTBLUE;
				break;
			case 2:
				cc = Color.YELLOW;
				break;
			case 3:
				cc = Color.PINK;
				break;
			case 4:
				cc = Color.ORANGE;
				break;
			default:
				cc = Color.WHITE;
		}
		return cc;
	}
}
