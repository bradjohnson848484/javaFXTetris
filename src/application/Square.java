package application;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Brick{
	
	public Square() {
		color = Color.BLUE;
		points = new Point[4];

		points[0] = new Point(4, 0);
		points[1] = new Point(5, 0);
		points[2] = new Point(4, 1);
		points[3] = new Point(5, 1);
	}
	public void rotate(GraphicsContext gc) {
	}
}
