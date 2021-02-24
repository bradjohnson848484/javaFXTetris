package application;

import java.awt.Point;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BrickDemo extends Application {
	private int originalX = 15;
	private int originalY = 15;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setResizable(false);
		Pane demoPane = new Pane();
		demoPane.setPrefWidth(400d);
		demoPane.setPrefHeight(600d);
		/// Button btn = new Button("Start");
		// demoPane.getChildren().add(btn);

		Rectangle rect = new Rectangle(originalX, originalY, 300, 570);
		rect.setFill(Color.LIGHTGREEN);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(1);
		demoPane.getChildren().add(rect);

		Canvas backgroundCanvas = new Canvas(300 + originalX, 570 + originalY);

		GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
		gc.setFill(Color.RED);

		// 20 rows 10 columns
		for (int i = 1; i < 20; i++) {
			gc.strokeLine(0 + originalX, i * 30 + originalY, 300 + originalX, i * 30 + originalY);
		}
		for (int i = 1; i < 10; i++) {
			gc.strokeLine(i * 30 + originalX, 0 + originalY, i * 30 + originalX, 570 + originalY);
		}

		Canvas brickCanvas = new Canvas(300 + originalX, 570 + originalY);
		GraphicsContext brickgc = brickCanvas.getGraphicsContext2D();
		brickgc.setFill(Color.RED);
		// Point p = new Point(originalX, originalY);
		// Point p1 = new Point(originalX+30, originalY);
		// Point p2 = new Point(originalX+30*2, originalY);
		// Point p3 = new Point(originalX+30*3, originalY);
		final int width = 30;
		final int height = 30;
		LineBrick lineBricks = new LineBrick();
		Point[] brickPoints = lineBricks.getPoints();
		 
		for (int i = 0; i < brickPoints.length; i++) {
			Point p = brickPoints[i];
			int x = originalX + p.x * 30;
			int y = p.y + originalY;
			//System.out.println("=======x:" + x + "  y:" + y);
			brickgc.fillRect(x, y, width, height);
		}
		// brickgc.fillRect(p.x, p.y, width, height);
		// brickgc.fillRect(p1.x, p1.y, width, height);
		// brickgc.fillRect(p2.x, p2.y, width, height);
		// brickgc.fillRect(p3.x, p3.y, width, height);

		demoPane.getChildren().add(backgroundCanvas);
		demoPane.getChildren().add(brickCanvas);
		Scene primaryScene = new Scene(demoPane);
		primaryStage.setScene(primaryScene);
		primaryStage.setTitle("Tetris Game");
		primaryStage.show();

		primaryScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					goLeft(lineBricks, brickgc);
					// x axis cannot lower that 0
					// if (p.x > 15) {
					// brickgc.clearRect(p.x, p.y, width, height);
					// p.x = p.x - 30;
					// brickgc.fillRect(p.x, p.y, width, height);
					// }
				} else if (event.getCode() == KeyCode.RIGHT) {
					goRight(lineBricks, brickgc);
					// if (p.x < 270) {
					// brickgc.clearRect(p.x, p.y, width, height);
					// p.x = p.x + 30;
					// brickgc.fillRect(p.x, p.y, width, height);
					// }
				} else if (event.getCode() == KeyCode.UP) {
					//rotate(lineBricks, brickgc);
					lineBricks.rotate(brickgc);
					// brickgc.clearRect(p.x, p.y, width, height);
					// p.y = p.y - 30;
					// brickgc.fillRect(p.x, p.y, width, height);
				} else if (event.getCode() == KeyCode.DOWN) {
					goDown(lineBricks, brickgc);
					// if (p.y < 540) {
					// brickgc.clearRect(p.x, p.y, width, height);
					// p.y = p.y + 30;
					// brickgc.fillRect(p.x, p.y, width, height);
					// }
				}
			}
		});
		
		//this is the game timer while loop
		// is game over
		// is paused
		// is collision if so clear line
		// if collision prepare next brick
		// redraw after full line
		// check collision, check end of game, check line is full
		
		
		
		
	}

	 

	private void goLeft(LineBrick lineBricks, GraphicsContext gc) {
		Point[] brickPoints = lineBricks.getPoints();
		for (int i = 0; i < brickPoints.length; i++) {
			if (brickPoints[i].x * 30 -30 < 0) {
				return;
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

	}


	private void goRight(LineBrick lineBricks, GraphicsContext gc) {
		Point[] brickPoints = lineBricks.getPoints();
		for (int i = 0; i < brickPoints.length; i++) {
			if (brickPoints[i].x * 30 + 30 > 270) {
				return;
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

	}
	private void goDown(LineBrick lineBricks, GraphicsContext gc) {
		Point[] brickPoints = lineBricks.getPoints();
		for (int i = 0; i < brickPoints.length; i++) {
			if (brickPoints[i].y * 30 + 30 > 540) {
				return;
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

	}
}
