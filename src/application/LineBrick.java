package application;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;

public class LineBrick {

	private Point[] points;
	
	private int position = 1;

	public LineBrick() {
		points = new Point[4];

		points[0] = new Point(0, 0);
		points[1] = new Point(1, 0);
		points[2] = new Point(2, 0);
		points[3] = new Point(3, 0);
	}
	public Point[] getPoints() {
		return points;
	}
	
	
	public void rotate(GraphicsContext gc) {
		for (int i = 0; i < points.length; i++) {
			gc.clearRect(points[i].x * 30 + 15, points[i].y * 30 + 15, 30, 30);
		}
		
		if(position == 1) {
			
			
			
			points[0].x = points[0].x +2;
			points[0].y = points[0].y -2;
			points[1].x = points[1].x +1;
			points[1].y = points[1].y -1;
			points[3].x = points[3].x -1;
			points[3].y = points[3].y +1;
			position= 2;
		}else {
			
			points[0].x = points[0].x -2;
			points[0].y = points[0].y +2;
			points[1].x = points[1].x -1;
			points[1].y = points[1].y +1;
			points[3].x = points[3].x +1;
			points[3].y = points[3].y -1;
			position = 1;
			
		}
		
	}
	
}
