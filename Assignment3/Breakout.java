/*
 * File: Breakout.java
 * -------------------
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {
	
/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Number of levels */
	private static final int NLEVELS = 3;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;
	
/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;	
	
/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 12;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 100;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 5;

/** Random generator */
	private RandomGenerator rgen = new RandomGenerator();
	
/** Velocity of the ball */
	private final double VX = rgen.nextDouble(1.0, 3.0);
	private static final double VY = 3;
	
/** Height of a HUD */
	private static final int HUD_HEIGHT = 40;

/** Sound Clip */
	//SoundClip sound = new SoundClip();
	
/** Exit status flag */
	private static final int NOT_TERMINATED = 0;
	private static final int LOSE = 1;
	private static final int WIN = 2;
	
/** Main objects of the game */
	private GImage	startPage;
	private Bricks	bricks;
	private Paddle	paddle;
	private	Ball	ball;
	private	HUD		hud;
	private Message	msg;
	private int		numLife;
	private int		pauseTime;
	private int		score;
	private int		level;
	
	/*
	 * Method Name : run
	 * Description : Displays the start page and start the game. 
	 *               Passing through each level, if the player fails to pass the level or has passed all levels,
	 *               it comes out of the loop and outputs game results.*/
	public void run() {
		score = 0;
		level = 0;
		displayStartPage();
		while (level < NLEVELS) {
			setUp(level);
			waitForClick();
			if (play(level) == LOSE) break;
			level++;
		}
		displayResult(level);
	}

	/*
	 * Method Name : displayStartPage
	 * Description : At the beginning of the game, displays the start image and the text "Loading...".*/
	private void displayStartPage() {
		startPage = new GImage("startPage.png");
		getGCanvas().setBackground(Color.BLACK);
		startPage.setSize(WIDTH, getHeight());
		add(startPage);
		msg = new Message("Loading...", 20, getHeight() - 40);
		add(msg);
		pause(2500);
		msg.remove();
	}
	
	/*
	 * Method Name : setUp
	 * Input param : (int)level
	 * Description : Sets up the bricks, paddle, ball, HUD, number of Lives
	 *               and paddle hit counts according to the level.*/
	private void setUp(int level) {
		double paddleX, paddleY;
		
		removeAll();
		paddleX = (WIDTH - PADDLE_WIDTH) / 2;
		paddleY = HEIGHT - (PADDLE_Y_OFFSET + PADDLE_HEIGHT);
		bricks = new Bricks(WIDTH, BRICK_HEIGHT, BRICK_SEP, NBRICKS_PER_ROW, NBRICK_ROWS, BRICK_Y_OFFSET);
		paddle = new Paddle(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
		ball = new Ball(WIDTH, HEIGHT, BALL_RADIUS, VX, VY);
		hud = new HUD(0, getHeight() - HUD_HEIGHT, WIDTH, HUD_HEIGHT, level, NTURNS, score);
		numLife = NTURNS;
		pauseTime = 30 - level * 2; 
		paddle.setHitCnt(0);
		add(bricks);
		add(paddle);
		add(ball);
		add(hud);
		addMouseListeners();
	}

	/*
	 * Method Name : mouseMoved
	 * Input param : (MouseEvent)e
	 * Description : Moves the paddle so that the center of the paddle is located in where the mouse is pointing.*/
	public void mouseMoved(MouseEvent e) {
		double newX;

		newX = e.getX() - PADDLE_WIDTH / 2;
		if ((newX > 0) && (newX + PADDLE_WIDTH < WIDTH))
			paddle.move(newX - paddle.getCurrX(), 0);
	}
	
	/*
	 * Method Name  : play
	 * Input param  : (int)level
	 * Return value : (int)terminated
	 * Description  : Play the level until the exit code is NOT_TERMINATED. 
	 *                When the exit code becomes WIN or LOSE, the game is over.*/
	private int play(int level) {
		int terminated;

		terminated = NOT_TERMINATED;
		while (terminated == NOT_TERMINATED) {
			terminated = advanceOneTimeStep();
			pause(pauseTime);
		}
		return (terminated);
	}
	
	/*
	 * Method Name  : advanceOneTimeStep
	 * Return value : (int)LOSE / (int)WIN / (int)NOT_TERMINATED
	 * Description  : Checking whether the ball is colliding with something or underneath the paddle, 
	                  processes it. When the number of Lives is -1, it returns LOSE 
	                  and when the number of bricks remaining is 0, it returns WIN. 
	                  Move the ball one more step if not in two cases.*/
	private int advanceOneTimeStep() {
		processCollision();
		checkUnderPaddle();
		if (numLife == -1)
			return (LOSE);
		if (bricks.getNumBricks() == 0)
			return (WIN);
		ball.move();
		return (NOT_TERMINATED);
	}

	/*
	 * Method Name  : processCollision
	 * Description  : Checks what the ball is currently colliding with and save it in the ball.collider.
	 *                If the ball is colliding with the paddle, calls processPaddleCollition(),
	 *                colliding with the bricks, calls processBrickCollision().
	 *                processWallCollision() processes the situation when the ball is colliding with a wall.*/
	private void processCollision()  {
		ball.setCollidingObject(bricks, paddle);
		if (ball.getCollider() == paddle)
			processPaddleCollision();
		else if (ball.getCollider() == bricks)
			processBrickCollision();
		processWallCollision();
	}
	
	/*
	 * Method Name  : processPaddleCollision
	 * Description  : To prevent the ball from sticking to the paddle, it only changes the speed of the ball 
	 *                when it does not crash into the paddle continuously. 
	 *                If the ball hits the left or right part of the paddle, 
	 *                it changes both the x-direction and the y-direction speed in reverse. 
	 *                If the ball hits the middle part, it only changes the speed in the y-direction. 
	 *                Each time the ball hits a paddle, it increases the paddle's hit count, 
	 *                and speeds up every seventh time the ball hits the paddle.*/
	private void processPaddleCollision() {
		if (ball.getPreCollider() != paddle) {
			if ((ball.getCollX() < paddle.getLeftRange()) || (ball.getCollX() > paddle.getRightRange()))
				ball.multMinusX();
			ball.multMinusY();
			paddle.increaseHitCnt();
			if (paddle.getHitCnt() % 7 == 0) {
				msg = new Message("Speed up!", ball.getCurrX(), ball.getCurrY() - 10);
				add(msg);
				pause(70);
				msg.remove();
				pauseTime /= 1.1; 
			}
		}
	}
	
	/*
	 * Method Name  : processBrickCollision
	 * Description  : When the ball is colliding with the bricks, 
	 *                updates score and removes the brick that ball was colliding with.
	 *                After decreasing the number of bricks, changes the y-direction speed in reverse.*/
	private void processBrickCollision() {
		updateScore();
		bricks.removeBrick(ball.getCollX(), ball.getCollY());
		bricks.decreaseNumBricks();
		ball.multMinusY();
	}
	
	/*
	 * Method Name  : updateScore
	 * Description  : It changes the score according to the color of the brick in which the ball crashed. 
	 *                Modifies the HUD so that the changed score is properly displayed on the screen.*/
	private void updateScore() {
		GRect brick;
		Color[] color =  {Color.cyan, Color.green, Color.yellow, Color.orange, Color.red};

		for (int idx = 0; idx < 5; idx++) {
			brick = bricks.getBrick(ball.getCollX(), ball.getCollY());
			if (brick.getColor() == color[idx])
			score += (idx + 1) * 10;
		}
		hud.removeScoreBox();
		hud.setScoreBox(score);
	}

	/*
	 * Method Name  : processWallCollision
	 * Description  : If the ball is colliding with left or right walls, 
	 *                changes the sign of speed in the X direction. 
	 *                If the ball is touching top wall, 
	 *                changes the sign of speed in the Y direction.*/
	private void processWallCollision() {
		if ((ball.getCurrX()) < 0 || (ball.getCurrX() + 2 * ball.getRadius() > WIDTH))
			ball.multMinusX();
		if (ball.getCurrY() < 0)
			ball.multMinusY();
	}

	/*
	 * Method Name  : checkUnderPaddle
	 * Description  : Decreases the number of lives when the ball is under the paddle.
	 *                After subtracting one from the numLife, resets the ball properties
	 *                and updates the HUD information.*/
	private void checkUnderPaddle() {
		if (ball.getCollider() == null)  {
			if ((ball.getCurrY() + 2 * ball.getRadius()) > paddle.getCurrY()) {
				numLife--;
				ball.resetBall(WIDTH, HEIGHT, BALL_RADIUS, VX, VY);
				if (numLife > -1) {
					hud.removeLifeBox();
					hud.setLifeBox(NTURNS, numLife);
					waitForClick();
				}
			}
		}
	}
	
	/*
	 * Method Name  : displayResult
	 * Input param  : (int)level
	 * Description  : Verifies that the Player has passed all levels and prints the result.*/
	private void displayResult(int level) {
		double x, y;
		Message levelInfo, scoreInfo;
		
		x = WIDTH / 2 - 30;
		y = HEIGHT / 2 - 50;
		if (level == NLEVELS)
			msg = new Message("Win!", x, y);
		else
			msg = new Message("Lose!", x, y);
		levelInfo = new Message("Level: " + (level + 1), x, y + 15);
		scoreInfo = new Message("Score: " + score, x, y + 30);
		add(msg);
		add(levelInfo);
		add(scoreInfo);
	}
}