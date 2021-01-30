package controller;

import exception.CollapseException;

public interface State {
  public void up();
  public void down();
  public void left();
  public void right();
  public void refresh() throws CollapseException ;
}
