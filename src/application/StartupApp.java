package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StartupApp extends Application {
	private int originalX = 15;
	private int originalY = 15;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setResizable(false);

		Pane background = generateBackground();

		Canvas brickCanvas = new Canvas(300 + originalX, 570 + originalY);
		GraphicsContext brickgc = brickCanvas.getGraphicsContext2D();

		background.getChildren().add(brickCanvas);

		// score label
		
		// next brick box
		
		// pause/start button
		
		// new game button
		
		
		
		GamePad gamePad = new GamePad(brickgc);
		gamePad.play();

		Scene primaryScene = new Scene(background);
		primaryStage.setScene(primaryScene);
		primaryStage.setTitle("Tetris Game");
		primaryStage.show();

		primaryScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				gamePad.gameController(event);

			}
		});
		
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
	}

	private Pane generateBackground() {
		Pane demoPane = new Pane();
		demoPane.setPrefWidth(400d);
		demoPane.setPrefHeight(600d);

		Rectangle rect = new Rectangle(originalX, originalY, 300, 570);
		rect.setFill(Color.LIGHTGREEN);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(1);

		demoPane.getChildren().add(rect);

		Canvas backgroundCanvas = new Canvas(300 + originalX, 570 + originalY);

		GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
		// 20 rows 10 columns
		for (int i = 1; i < 20; i++) {
			gc.strokeLine(0 + originalX, i * 30 + originalY, 300 + originalX, i * 30 + originalY);
		}
		for (int i = 1; i < 10; i++) {
			gc.strokeLine(i * 30 + originalX, 0 + originalY, i * 30 + originalX, 570 + originalY);
		}
		demoPane.getChildren().add(backgroundCanvas);
		return demoPane;
	}

}
