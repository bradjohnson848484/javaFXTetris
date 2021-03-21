package application;

import java.awt.Point;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GamePad {
	private GraphicsContext brickgc = null;
	private final int width = 30;
	private final int height = 30;
	private int originalX = 15;
	private int originalY = 15;
	
	private Brick brick;
	private boolean pause = false;
	private boolean[][] gameBoard;
	private Color[][] pieceColors;
	
	//private Thread gameThread = null;
	
	public GamePad(GraphicsContext gc) {
		brickgc = gc;
		brick = generateBricks();
		//brickgc.setFill(brick.getColor());
		gameBoard = new boolean[10][20];
		pieceColors = new Color[10][20];
	}
	
	private Brick generateBricks() {
//		1.Linebricks
//		2.TBricks
//		3.SBricks
//		4.RSBricks
//		5.LBricks
//		6.RLBricks
//		 default Square
		Brick randomBrick = null;
		Random rand = new Random();
		int a = rand.nextInt(7);
		switch (a) {
		case 1:
			randomBrick = new LineBrick();
			break;
		case 2:
			randomBrick = new LBrick();
			break;
		case 3:
			randomBrick = new RLBrick();
			break;
		case 4:
			randomBrick = new TBrick();
			break;
		case 5:
			randomBrick = new SBrick();
			break;
		case 6:
			randomBrick = new RSBrick();
			break;
		default:
			randomBrick = new Square();
			break;
		}
		brickgc.setFill(randomBrick.getColor());
		return randomBrick;
	}
	
	private void recordTheCoordinate() {
		Point[] ps = brick.getPoints();
		for (int i = 0; i < ps.length; i++) {
			Point p = ps[i];
			gameBoard[p.x][p.y] = true;
			pieceColors[p.x][p.y] = brick.getColor();
		}
	}
	
	private boolean collision() {
		Point[] ps = brick.getPoints();
		for (int i = 0; i < ps.length; i++) {
			Point p = ps[i];
			if(gameBoard[p.x][p.y+1] == true) {
				return true;
			}
		}
		return false;
	}
	private void checkFullRow() {
		for (int i = 19; i > 1; i--) {
			boolean rowIsFull = true;
			for (int j = 0; j < 10; j++) {
				if(gameBoard[j][i] == false) {
					rowIsFull = false;
					break;
				}
			}
			if(!rowIsFull) {
				continue;
			}
			brickgc.clearRect(15, i*30 + 15, 30 * 10, 30);
			
			for(int k = i-1; k > 1; k--) {
				for(int l = 0; l< 10; l++) {
					if(gameBoard[l][k] == true) {
						brickgc.setFill(pieceColors[l][k]);
						brickgc.fillRect(l * 30 + 15,  (k+1) * 30 + 15, 30, 30);
						brickgc.clearRect(l * 30 + 15, k * 30 + 15, 30, 30);
					}
					gameBoard[l][k+1] = gameBoard[l][k];
				}
			}
			i++;
		}
	}
	private boolean isGameOver() {
		if (gameBoard[3][2] || gameBoard[4][2] || gameBoard[5][2] || gameBoard[6][2]) {
			System.out.println("====******** GAME OVER");
			return true;
		}
		return false;
	}
	
	public void play() {
		//LineBrick lineBricks = generateBricks();
		
		Thread	gameThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
					while(!isGameOver()) {
						
					if (pause) {
						synchronized (brick) {
							try {
								brick.wait();
								// pause = true;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
						
						if (collision()) {
							recordTheCoordinate();
							checkFullRow();
							brick = generateBricks();
							continue;
							
						}
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						boolean isDown = brick.goDown(brickgc, gameBoard);
						if(!isDown) {
							System.out.println("******* the brick got bottom now start new round");
							recordTheCoordinate();
							checkFullRow();
							brick = generateBricks();
						}
					}
				}
				
		});
		gameThread.start();
	}

	public void gameController(KeyEvent event) {
		if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
			brick.goLeft(brickgc, gameBoard);
		}
		else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
			
			brick.goRight(brickgc, gameBoard);
		}
		else if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
			brick.rotate(brickgc);
		}
		else if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
			brick.goDown(brickgc, gameBoard);
		}
		else if (event.getCode() == KeyCode.SPACE) {
//			if (pause) {
//				gameThread.resume();
//				pause = false;	
//			} else {
//				gameThread.suspend();
//				pause = true;
//			}
			
			if (pause) {
				System.out.println("======= recover");
				pause = false;
				synchronized (brick) {
					brick.notifyAll();
//					pause = false;
				}
			} else {
				System.out.println("======= pause");
				pause = true;
			}
		}
	}
}
