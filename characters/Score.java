package characters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Score {
  int score;

  public Score(int score){
    this.score = score;
  }

  public void add(){
    this.score += 1;
  }

  public void draw(GraphicsContext gc){
    gc.setFill(Color.WHITE);
		gc.setFont(new Font("", 30));
		gc.fillText("Score: " + score, 10, 30);
  }
  
}
