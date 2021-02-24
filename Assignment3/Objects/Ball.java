package Objects;
import acm.graphics.*;
import java.awt.*;

public class Ball extends GCompound {
	
	public GOval ball;
	private double currX, currY;
	private int radius;
	private double vx, vy;
	private GObject preCollider;
	private GObject collider;
	private double collX, collY;

	/*
	 * Constructor : Ball
	 * Input param : (int)_width, (int)_height, (int)_radius, (double)_vx, (double)_vy
	 * Description : Creates the ball in the center of canvas. 
	 *               Sets the velocity and radius of the ball.*/
	public Ball(int _width, int _height, int _radius, double _vx, double _vy) {
		setCurr((_width - _radius) / 2, (_height - _radius) / 2);
		setRadius(_radius);
		setVelocity(_vx, _vy);
		setColliders(null, null);
		ball = new GOval(currX,currY, 2 * radius, 2 * radius);
		ball.setFilled(true);
		ball.setFillColor(Color.WHITE);
		add(ball);
	}

	/*
	 * Method Name : setCurr
	 * Input param : (double)_x, (double)_y
	 * Description : Sets the current x and y position of the ball.*/
	public void setCurr(double _x, double _y) {
		currX = _x;
		currY = _y;
	}

	/*
	 * Method Name : setRadius
	 * Input param : (int)_radius
	 * Description : Sets the radius of the ball.*/
	public void setRadius(int _radius) {
		radius = _radius;
	}

	/*
	 * Method Name : setVelocity
	 * Input param : (double)_vx, (double)_vy
	 * Description : Sets the velocity of the ball.*/
	public void setVelocity(double _vx, double _vy) {
		vx = _vx;
		vy = _vy;
	}
	
	/*
	 * Method Name : multMinusX
	 * Description : Reverse the sign of the vx.*/
	public void multMinusX() {
		vx = -vx;
	}
	
	/*
	 * Method Name : multMinusY
	 * Description : Reverse the sign of the vy.*/
	public void multMinusY() {
		vy = -vy;
	}
	
	/*
	 * Method Name : setColliders
	 * Description : Sets preCollider and collider of the ball.*/
	public void setColliders(GObject _preCollider, GObject _collider) {
		preCollider = _preCollider;
		collider = _collider;
	}
	
	/*
	 * Method Name : setColl
	 * Description : Sets x, y coordinates of the collision point.*/
	public void setColl(double _x, double _y) {
		collX = _x;
		collY = _y;
	}
	
	/*
	 * Method Name : move
	 * Description : Moves the ball by vx and vy.*/
	public void move() {
		ball.move(vx, vy);
		currX += vx;
		currY += vy;
	}
	
	/*
	 * Method Name : setCollidingObject
	 * Description : Checks the four vertices of a square surrounding the ball
	 *               and finds out which point are colliding the bricks or paddle.
	 *               If the ball is colliding with something, saves it as collider.*/
	public void setCollidingObject(Bricks bricks, Paddle paddle) {
		Double[] ycoord = {currY, currY + 2 * radius};
		Double[] xcoord = {currX, currX + 2 * radius};
		
		preCollider = collider;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (bricks.getBrick(xcoord[j], ycoord[i]) != null) {
					collider = bricks;
					setColl(xcoord[j], ycoord[i]);
					return ;	
				} else if (paddle.isPaddle(xcoord[j], ycoord[i])) {
					collider = paddle;
					setColl(xcoord[j], ycoord[i]);
					return ;
				}
			}
		}
		collider = null;
	}
	
	/*
	 * Method Name : resetBall
	 * Description : Resets the properties of the ball and redraws it.*/
	public void resetBall(int _width, int _height, int _radius, double _vx, double _vy) {
		setCurr((_width - _radius) / 2, (_height - _radius) / 2);
		setRadius(_radius);
		setVelocity(_vx, _vy);
		setColliders(null, null);
		remove(ball);
		ball = new GOval(currX,currY, 2 * radius, 2 * radius);
		ball.setFilled(true);
		ball.setFillColor(Color.WHITE);
		add(ball);
	}

	/*
	 * Method Name : getCurrX
	 * Description : Returns the x position of the ball.*/
	public double getCurrX() { return (currX); }
	
	/*
	 * Method Name : getCurrY
	 * Description : Returns the y position of the ball.*/
	public double getCurrY() { return (currY); }
	
	/*
	 * Method Name : getRadius
	 * Description : Returns the radius of the ball.*/
	public int getRadius() { return (radius); }
	
	/*
	 * Method Name : getCollider
	 * Description : Returns the collider of the ball.*/
	public GObject getCollider() { return (collider); }
	
	/*
	 * Method Name : getPreCollider
	 * Description : Returns the PreCollider of the ball.*/
	public GObject getPreCollider() { return (preCollider); }
	
	/*
	 * Method Name : getCollX
	 * Description : Returns the x-coordinates of the point where the ball and collider crashed.*/
	public double getCollX() { return (collX); }
	
	/*
	 * Method Name : getCollY
	 * Description : Returns the y-coordinates of the point where the ball and collider crashed.*/
	public double getCollY() { return (collY); }
}
