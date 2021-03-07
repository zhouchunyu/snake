package edu.nju.lab;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.nju.lab.exception.CollapseException;
import edu.nju.lab.game.Game;

public class App extends Application {
	private VBox root;
	private Canvas c;
  private GraphicsContext gc;
  private Scene scene;

	private int width;
  private int height;
  private int blocksize;
  private Game game;

	public void start(Stage primaryStage) {
  
		this.width = 20;
    this.height = 20;
    this.blocksize = 25;

    this.root =  new VBox();
    this.c = new Canvas(width * blocksize, height * blocksize);
    this.gc = c.getGraphicsContext2D();
    this.scene = new Scene(root, width * blocksize, height * blocksize);

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

    game  = new Game(gc, scene);
    AnimationTimer timer = new AnimationTimer() {
      long lastTick = 0;

      public void handle(long now) {
        try {
          if (lastTick == 0) {
            lastTick = now;
            game.tick(gc);
            return;
          }

          if (now - lastTick > 1000000000 / game.speed) {
            lastTick = now;
            game.tick(gc);
          }
        } catch (CollapseException e) {
          gc.setFill(Color.RED);
          gc.setFont(new Font("", 50));
          gc.fillText("GAME OVER", 100, 250);
          this.stop();
        }
      }
    };
    
    // control
    scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
      if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) {
        game.up();
      }
      if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
        game.left();
      }
      if (key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) {
        game.down();
      }
      if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
        game.right();
      }
      if (key.getCode() == KeyCode.SPACE) {
        game.spacePressed(gc, timer);
      }

      if (key.getCode() == KeyCode.C) {
        try{
          System.out.println("save");
          FileOutputStream fos = new FileOutputStream("test.obj");
          ObjectOutputStream oos = new ObjectOutputStream(fos);
          oos.writeObject(game);
          oos.close();
        }catch (IOException e) {
          e.printStackTrace();
        }
      }

      if (key.getCode() == KeyCode.L) {
        System.out.println("load");
        try {
          FileInputStream fis = new FileInputStream("test.obj");
          ObjectInputStream ois = new ObjectInputStream(fis);

          game = (Game) ois.readObject();
          ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      }  
    });
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
