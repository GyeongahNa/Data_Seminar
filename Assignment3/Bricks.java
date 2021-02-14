import acm.graphics.*;
import java.awt.*;
import java.util.ArrayList;

public class Bricks extends GCompound{

	ArrayList<ArrayList<GRect>>	bricks;
	public int					brickWidth, brickHeight, brickSep;
	public int					nbricksPerRow, nbrickRows;
	public int					brickXOffset, brickYOffset;	
	public int					numBricks;
	
	/*
	 * Constructor : Bricks
	 * Input param : (int)_width, (int)_brickHeight, (int)_brickSep, (int)_nbricksPerRow, (int)_nbrickRows, (int)_brickYOffset
	 * Description : Sets the properties of the bricks and creates the set of bricks. */
	public Bricks(int _width, int _brickHeight, int _brickSep, int _nbricksPerRow, int _nbrickRows, int _brickYOffset) {
		setBrickWidth((_width - (_nbricksPerRow - 1) * _brickSep) / _nbricksPerRow);
		setBrickHeight(_brickHeight);
		setBrickSep(_brickSep);
		setBricksSize(_nbricksPerRow, _nbrickRows);
		setXOffset(_width / 2 - ((brickWidth * _nbricksPerRow) + _brickSep * (_nbricksPerRow - 1)) / 2);
		setYOffset(_brickYOffset);
		setNumBricks(_nbricksPerRow * _nbrickRows);
		bricks = new ArrayList<ArrayList<GRect>>();
		addBricks();
	}

	/*
	 * Method Name : setBrickWidth
	 * Input param : (int)_brickWidth
	 * Description : Sets the width of a brick.*/
	public void setBrickWidth(int _brickWidth) {
		brickWidth = _brickWidth;
	}
	
	/*
	 * Method Name : setBrickHeight
	 * Input param : (int)_brickHeight
	 * Description : Sets the height of a brick.*/
	public void setBrickHeight(int _brickHeight) { 
		brickHeight = _brickHeight; 
	}
	
	/*
	 * Method Name : setBrickSep
	 * Input param : (int)_brickSep
	 * Description : Sets the distance between bricks.*/
	public void setBrickSep(int _brickSep) { 
		brickSep = _brickSep; 
	}
	
	/*
	 * Method Name : setBrickSize
	 * Input param : (int)_nbricksPerRow, (int)_nbrickRows
	 * Description : Sets the size of the bricks set.*/
	public void setBricksSize(int _nbricksPerRow, int _nbrickRows) {
		nbricksPerRow = _nbricksPerRow;
		nbrickRows = _nbrickRows;
	}
	
	/*
	 * Method Name : setXOffset
	 * Input param : (int)_brickXOffset
	 * Description : Sets the distance between left wall and bricks set.*/
	public void setXOffset(int _brickXOffset) {
		brickXOffset = _brickXOffset;
	}
	
	/*
	 * Method Name : setYOffset
	 * Input param : (int)_brickYOffset
	 * Description : Sets the distance between top wall and bricks set.*/
	public void setYOffset(int _brickYOffset) { 
		brickYOffset = _brickYOffset; 
	}

	/*
	 * Method Name : setNumBricks
	 * Input param : (int)_num
	 * Description : Sets the number of bricks.*/
	public void setNumBricks(int _num) { 
		numBricks = _num; 
	}
	
	/*
	 * Method Name : decreaseNumBricks
	 * Description : Subtracts one from the numBricks.*/
	public void decreaseNumBricks() { 
		numBricks--; 
	}
	
	/*
	 * Method Name : getNumBricks
	 * Description : Returns numBricks.*/
	public int getNumBricks() { 
		return (numBricks); 
	}

	/*
	 * Method Name : addBrick
	 * Description : Draws set of bricks and stores each brick object in a two-dimensional array.*/
	public void addBricks() {
		double x, y;
		ArrayList<GRect>temp;
		GRect brick;
		
		for (int row = 0; row < nbrickRows; row++) {
			y = brickYOffset + row * (brickHeight + brickSep);
			temp = new ArrayList<GRect>();
			for (int col = 0; col < nbricksPerRow; col++) {
				x = brickXOffset + col * (brickWidth + brickSep);
				brick = new GRect(x, y, brickWidth, brickHeight);
				fillColor(brick,row);
				temp.add(brick);
			}
			bricks.add(temp);
		}
	}

	/*
	 * Method Name : fillColor
	 * Input param : (GRect)brick, (int)row
	 * Description : Colors it differently depending on which row it is located on.*/
	private void fillColor(GRect brick, int row) {
		Color[] colors = {Color.red, Color.orange, Color.yellow, Color.green, Color.cyan};
		brick.setFilled(true);
		brick.setColor(colors[(row % 10) / 2]);
		brick.setFillColor(colors[(row % 10) / 2]);
		add(brick);
	}
	
	/*
	 * Method Name : getBrick
	 * Input param : (double)x ,(double)y
	 * Description : Checks if there is a brick in the x and y coordinates of the canvas and return the brick.*/
	public GRect getBrick(double x, double y) {
		double leftX, rightX, topY, bottomY;
		int xIdx, yIdx;

		leftX = brickXOffset;
		rightX = leftX + (brickWidth + brickSep) * (nbricksPerRow - 1) + brickWidth;
		topY = brickYOffset;
		bottomY = topY + (brickHeight + brickSep) * (nbrickRows - 1) + brickHeight;
		if ((x >= leftX) && (x <= rightX) && (y >= topY) && (y <= bottomY)) {
			xIdx = (int)(x - brickXOffset) / (brickWidth +  brickSep);
			yIdx = (int)(y - brickYOffset) / (brickHeight + brickSep);
			if (bricks.get(yIdx).get(xIdx) != null)
				return (bricks.get(yIdx).get(xIdx));
		}
		return (null);
	}
	
	/*
	 * Method Name : removeBrick
	 * Input param : (double)x ,(double)y
	 * Description : Removes the brick at the x and y coordinates of the canvas.*/
	public void removeBrick(double x, double y) {
		int xIdx, yIdx;
		ArrayList<GRect> temp;
		
		xIdx = (int)(x - brickXOffset) / (brickWidth +  brickSep);
		yIdx = (int)(y - brickYOffset) / (brickHeight + brickSep);
		remove(bricks.get(yIdx).get(xIdx));
		temp = bricks.get(yIdx);
		temp.set(xIdx, null);
		bricks.set(yIdx, temp);
	}
}
