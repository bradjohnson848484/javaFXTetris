package application;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TBrick extends Brick {
	
	public TBrick() {
		color = Color.GREEN;
		points = new Point[4];

		points[0] = new Point(4, 1);
		points[1] = new Point(5, 1);
		points[2] = new Point(6, 1);
		points[3] = new Point(5, 0);
	}
	
	public void rotate(GraphicsContext gc) {
		for (int i = 0; i < points.length; i++) {
			gc.clearRect(points[i].x * 30 + 15, points[i].y * 30 + 15, 30, 30);
		}
		
		if(position == 1) {			
			points[0].x = points[0].x +1;
			points[0].y = points[0].y -1;
			points[2].x = points[2].x -1;
			points[2].y = points[2].y +1;
			points[3].x = points[3].x +1;
			points[3].y = points[3].y +1;
			position= 2;
		}
		else if(position == 2) {			
			points[0].x = points[0].x +1;
			points[0].y = points[0].y +1;
			points[2].x = points[2].x -1;
			points[2].y = points[2].y -1;
			points[3].x = points[3].x -1;
			points[3].y = points[3].y +1;
			position= 3;
		}
		else if(position == 3) {			
			points[0].x = points[0].x -1;
			points[0].y = points[0].y +1;
			points[2].x = points[2].x +1;
			points[2].y = points[2].y -1;
			points[3].x = points[3].x -1;
			points[3].y = points[3].y -1;
			position= 4;
		}
		else {			
			points[0].x = points[0].x -1;
			points[0].y = points[0].y -1;
			points[2].x = points[2].x +1;
			points[2].y = points[2].y +1;
			points[3].x = points[3].x +1;
			points[3].y = points[3].y -1;
			position= 1;
		}
		for (int i = 0; i < points.length; i++) {
			gc.fillRect(points[i].x * 30 + 15, points[i].y * 30 + 15, 30, 30);
		}
	}
}
