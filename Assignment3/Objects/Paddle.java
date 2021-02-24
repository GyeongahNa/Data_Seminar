package Objects;
import acm.graphics.*;
import java.awt.*;

public class Paddle extends GCompound{
	
	GRect paddle;
	private double x, y;
	private double paddleWidth, paddleHeight;
	private double leftRange, rightRange;
	private int hitCnt;
	
	/*
	 * Constructor : Paddle
	 * Input param : (double)_x, (double)_y, (double)_width, (double)_height
	 * Description : Creates the paddle in _x, _y position. */
	public Paddle(double _x, double _y, double _width, double _height) {
		setPos(_x, _y);
		setSize(_width, _height);
		setRange(x + _width / 4, x + _width * 3 / 4);
		setHitCnt(0);
		setPaddle();
	}
	
	/*
	 * Method Name : setPos
	 * Input param : (double)_x, (double)_y
	 * Description : Sets position of the paddle.*/
	public void setPos(double _x, double _y) {
		x = _x;
		y = _y;
	}
	
	/*
	 * Method Name : setSize
	 * Input param : (double)_width, (double)_height
	 * Description : Sets width and height of the paddle.*/
	public void setSize(double _width, double _height) {
		paddleWidth = _width;
		paddleHeight = _height;
	}
	
	/*
	 * Method Name : setRange
	 * Input param : (double)_leftRange, (double)_rightRange
	 * Description : Sets left and right range of the paddle.*/
	public void setRange(double _leftRange, double _rightRange) {
		leftRange = _leftRange;
		rightRange = _rightRange;
	}
	
	/*
	 * Method Name : setPaddle
	 * Description : Sets the paddle and paints it in MAGENTA color.*/
	public void setPaddle() {
		paddle = new GRect(x, y, paddleWidth, paddleHeight);
		paddle.setFilled(true);
		paddle.setFillColor(Color.MAGENTA);
		add(paddle);	
	}

	/*
	 * Method Name : setHitCnt
	 * Input param : (int)_cnt
	 * Description : Sets the paddle-hit-counts.*/
	public void setHitCnt(int _cnt) { 
		hitCnt = _cnt; 
	}

	/*
	 * Method Name : increaseHitCnt
	 * Description : Adds one to hitCnt.*/
	public void increaseHitCnt() { 
		hitCnt++; 
	}

	/*
	 * Method Name : move
	 * Input param : (double)_x, (double)_y
	 * Description : Moves the paddle horizontally by _x, vertically by _y.*/
	public void move(double _x, double _y) {
		setPos(x + _x, y + _y);
		setRange(x + paddleWidth / 4, x + paddleWidth * 3 / 4);
		paddle.move(_x, _y);
	}
	
	/*
	 * Method Name : isPaddle
	 * Input param : (double)_x, (double)_y
	 * Description : Check whether the x and y coordinates of canvas is paddle.*/
	public boolean isPaddle(double _x, double _y) {
		if ((_x >= x) && (_x <= x + paddleWidth)) {
			if ((_y >= y) && (_y <= y + paddleHeight))
				return (true);
		}
		return (false);
	}
	
	/*
	 * Method Name : getCurrX
	 * Description : Returns the current x position of the paddle.*/
	public double getCurrX() { return (x); }
	
	/*
	 * Method Name : getCurrY
	 * Description : Returns the current y position of the paddle.*/
	public double getCurrY() { return (y); }
	
	/*
	 * Method Name : getLeftRange
	 * Description : Returns the leftRange of the paddle.*/
	public double getLeftRange() { return (leftRange); }
	
	/*
	 * Method Name : getRightRange
	 * Description : Returns the rightRange of the paddle.*/
	public double getRightRange() { return (rightRange); }
	
	/*
	 * Method Name : getHitCnt
	 * Description : Returns the hitCnt of the paddle.*/
	public int getHitCnt() { return (hitCnt); }
}