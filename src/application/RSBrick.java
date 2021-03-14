package application;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RSBrick extends Brick{

	public RSBrick() {
		color = Color.PURPLE;
		points = new Point[4];

		points[0] = new Point(4, 0);
		points[1] = new Point(5, 0);
		points[2] = new Point(5, 1);
		points[3] = new Point(6, 1);
	}
	
	
	
	
	public void rotate(GraphicsContext gc) {
		for (int i = 0; i < points.length; i++) {
			gc.clearRect(points[i].x * 30 + 15, points[i].y * 30 + 15, 30, 30);
		}
		
		if(position == 1) {			
			points[0].x = points[0].x +2;
			points[0].y = points[0].y;
			points[1].x = points[1].x +1;
			points[1].y = points[1].y +1;			
			points[3].x = points[3].x -1;
			points[3].y = points[3].y +1;
			position= 2;
		}else {
			
			points[0].x = points[0].x -2;
			points[0].y = points[0].y;
			points[1].x = points[1].x -1;
			points[1].y = points[1].y -1;			
			points[3].x = points[3].x +1;
			points[3].y = points[3].y -1;
			position = 1;
			
		}
		for (int i = 0; i < points.length; i++) {
			gc.fillRect(points[i].x * 30 + 15, points[i].y * 30 + 15, 30, 30);
		}
	}
}
