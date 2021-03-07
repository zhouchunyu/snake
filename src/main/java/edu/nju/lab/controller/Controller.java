package edu.nju.lab.controller;

import java.io.Serializable;

import edu.nju.lab.characters.Snake;
import edu.nju.lab.exception.CollapseException;


public class Controller implements Serializable{
  /**
   *
   */
  private static final long serialVersionUID = -6559133862204335880L;
  State upState;
  State downState;
  State leftState;
  State rightState;
  State current_state;
  State next_state;

  public Controller(Snake snake){
    this.upState = new UpState(this, snake);
    this.downState = new DownState(this, snake);
    this.leftState = new LeftState(this, snake);
    this.rightState = new RightState(this, snake);
    this.current_state = this.upState;
    this.next_state = this.upState;
  }

  public void refresh() throws CollapseException{
    next_state.refresh();
    current_state = next_state;
  }

  public void up(){
    current_state.up();
  }

  public void down(){
    current_state.down();
  }

  public void left(){
    current_state.left();
  }

  public void right(){
    current_state.right();
  }

  public void setState(State state){
    this.next_state = state;
  }

  public State getUpState(){
    return this.upState;
  }
  
  public State getDownState(){
    return this.downState;
  }

  public State getLeftState(){
    return this.leftState;
  }

  public State getRightState(){
    return this.rightState;
  }


  

  
}
