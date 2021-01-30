package edu.nju.lab.controller;

import edu.nju.lab.exception.CollapseException;

public interface State {
  public void up();
  public void down();
  public void left();
  public void right();
  public void refresh() throws CollapseException ;
}
