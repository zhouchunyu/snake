package edu.nju.lab;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

import edu.nju.lab.exception.CollapseException;

public class App extends Application {
	// variable
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


	public void start(Stage primaryStage) {
		try {
			// 新建画布
			root.getChildren().add(c);

			new AnimationTimer() {
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

			}.start();

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

			});

			// add start snake parts

			//If you do not want to use css style, you can just delete the next line.
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("SNAKE GAME");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	
	public static void main(String[] args) {
		launch(args);
	}
}
