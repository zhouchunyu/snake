package edu.nju.lab.characters;

import java.io.Serializable;

public class Block implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = -8382372837976760285L;
  public int x;
  public int y;

  public Block(int x, int y) {
    this.x = x;
    this.y = y;
  }

}
