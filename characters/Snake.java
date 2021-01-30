package characters;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import exception.CollapseException;

public class Snake {
  public List<Block> blocks = new ArrayList<Block>();
  public Block tail;

  public Snake(int width, int height) {
    blocks.add(new Block(width / 2, height / 2));
    blocks.add(new Block(width / 2, height / 2));
    blocks.add(new Block(width / 2, height / 2));
  }

  public void up() throws CollapseException{
    move();
    blocks.get(0).y = blocks.get(0).y - 1;
    check_collapse();
  }

  public void down() throws CollapseException{
    move();
    blocks.get(0).y = blocks.get(0).y + 1;
    check_collapse();
  }

  public void left() throws CollapseException{
    move();
    blocks.get(0).x = blocks.get(0).x - 1;
    check_collapse();
  }

  public void right() throws CollapseException{
    move();
    blocks.get(0).x = blocks.get(0).x + 1;
    check_collapse();
  }

  private void check_collapse() throws CollapseException{
    for (int i = 1; i < blocks.size(); i++) {
			if (blocks.get(0).x == blocks.get(i).x && blocks.get(0).y == blocks.get(i).y) {
				throw new CollapseException("self collapse");
			}
    }
    for (int i = 1; i < blocks.size(); i++) {
			if(blocks.get(0).x < 0 || blocks.get(0).x >= 20 || blocks.get(0).y < 0 || blocks.get(0).y >= 20) {
				throw new CollapseException("self collapse");
			}
    }
  }

  private void move(){
    tail = new Block(blocks.get(blocks.size() - 1).x, blocks.get(blocks.size() - 1).y);
    for (int i = blocks.size() - 1; i >= 1; i--) {
			blocks.get(i).x = blocks.get(i - 1).x;
			blocks.get(i).y = blocks.get(i - 1).y;
		}
  }

  public boolean eaten(Food food){
    if (food.x == blocks.get(0).x && food.y == blocks.get(0).y) {
      blocks.add(tail);
      return true;
    }
    return false;
  }

  public void draw(GraphicsContext gc, int blocksize){
    for(Block b : blocks){
			gc.setFill(Color.LIGHTGREEN);
			gc.fillRect(b.x * blocksize, b.y * blocksize, blocksize - 1, blocksize - 1);
			gc.setFill(Color.GREEN);
			gc.fillRect(b.x * blocksize, b.y * blocksize, blocksize - 2, blocksize - 2);
		}
    
  }


}
