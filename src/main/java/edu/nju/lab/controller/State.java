package edu.nju.lab.controller;

import java.io.Serializable;

import edu.nju.lab.exception.CollapseException;

public interface State extends Serializable{
  public void up();
  public void down();
  public void left();
  public void right();
  public void refresh() throws CollapseException ;
}
