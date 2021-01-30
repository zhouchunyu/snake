package controller;
import characters.Snake;
import exception.CollapseException;

public class UpState implements State{
  Controller controller;
  Snake snake;
  
  public UpState(Controller controller, Snake snake){
    this.controller = controller;
    this.snake = snake;
  }

  public void up(){}

  public void down(){}

  public void left(){
    controller.setState(controller.getLeftState());
  }

  public void right(){
    controller.setState(controller.getRightState());
  }

  public void refresh() throws CollapseException{
    snake.up();
  }
  
}
