package edu.nju.lab.controller;

import edu.nju.lab.characters.Snake;
import edu.nju.lab.exception.CollapseException;

public class RightState implements State{
  Controller controller;
  Snake snake;
  
  public RightState(Controller controller, Snake snake){
    this.controller = controller;
    this.snake = snake;
  }

  public void up(){
    controller.setState(controller.getUpState());
  }

  public void down(){
    controller.setState(controller.getDownState());
  }

  public void left(){
  }

  public void right(){
  }

  public void refresh()  throws CollapseException{
    snake.right();
  }
  
}
