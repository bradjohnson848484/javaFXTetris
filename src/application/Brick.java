package application;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Brick {
	protected Point[] points;
	
	protected int position = 1;
	protected Color color;
	
	public Color getColor() {
		return this.color;
	}
	public Point[] getPoints() {
		return points;
	}
	
	protected boolean goLeft(GraphicsContext gc) {
		Point[] brickPoints = getPoints();
		for (int i = 0; i < brickPoints.length; i++) {
			if (brickPoints[i].x * 30 -30 < 0) {
				return false;
			}
		}

		for (int i = 0; i < brickPoints.length; i++) {
			gc.clearRect(brickPoints[i].x * 30 + 15, brickPoints[i].y * 30 + 15, 30, 30);
		}
		for (int i = 0; i < brickPoints.length; i++) {
			Point p = brickPoints[i];
			p.x = p.x-1;
			gc.fillRect(p.x*30+15, p.y*30+15, 30, 30);
		}
		return true;
	}
	
	protected boolean goRight(GraphicsContext gc) {
		Point[] brickPoints = getPoints();
		for (int i = 0; i < brickPoints.length; i++) {
			if (brickPoints[i].x * 30 + 30 > 270) {
				return false;
			}
		}

		for (int i = 0; i < brickPoints.length; i++) {
			gc.clearRect(brickPoints[i].x * 30 + 15, brickPoints[i].y * 30 + 15, 30, 30);
		}
		for (int i = 0; i < brickPoints.length; i++) {
			Point p = brickPoints[i];
			p.x = p.x+1;
			gc.fillRect(p.x*30+15, p.y*30+15, 30, 30);
		}
		return true;
	}
	
	protected boolean goDown(GraphicsContext gc) {

		Point[] brickPoints = getPoints();
		for (int i = 0; i < brickPoints.length; i++) {
			if (brickPoints[i].y * 30 + 30 > 540) {
				return false;
			}
		}

		for (int i = 0; i < brickPoints.length; i++) {
			gc.clearRect(brickPoints[i].x * 30 + 15, brickPoints[i].y * 30 + 15, 30, 30);
		}
		for (int i = 0; i < brickPoints.length; i++) {
			Point p = brickPoints[i];
			p.y = p.y+1;
			gc.fillRect(p.x*30+15, p.y*30+15, 30, 30);
		}
		return true;
	
	}
	
	protected abstract void rotate(GraphicsContext gc);
}
