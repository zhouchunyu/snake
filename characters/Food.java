package characters;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class Food {
  public int x;
  public int y;
  public Color color;
  
  public Food(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }

  public void draw(GraphicsContext gc, int cornersize){
    gc.setFill(color);
		gc.fillOval(x * cornersize, y * cornersize, cornersize, cornersize);
  }

}