package playerStrategy;

import java.util.ArrayList;
import model.Rectangle;

public interface PlayerStrategy {
	public void init();
    public void solveGame();
    public ArrayList<Rectangle> getRectangles();
    public double getScore();
}
