package edu.nju.lab;


import javafx.application.Application;
import javafx.stage.Stage;

import edu.nju.lab.game.Game;

public class App extends Application {

	public void start(Stage primaryStage) {
		Game game  = new Game(primaryStage);
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
