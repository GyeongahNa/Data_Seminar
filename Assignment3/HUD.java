import acm.graphics.*;
import java.awt.*;
import java.util.ArrayList;

public class HUD extends GCompound{
	
	GRect				hud;
	GLabel				levelBox, scoreBox;
	ArrayList<GImage>	lifeBox;
	private int			x, y;
	private int			hudWidth, hudHeight;
	private int			blockWidth, blockHeight, blockSep, blockOffset;
	
	/*
	 * Constructor : HUD
	 * Input param : (int)_x, (int)_y, (int)_width, (int)_height, (int)level, (int)nTurns, (int)score
	 * Description : Creates the properties of the HUD and displays it in the canvas. */
	public HUD(int _x, int _y, int _width, int _height, int level, int nTurns, int score) {
		setPos(_x, _y);
		setSize(_width, _height);
		setBlockSize(90, 30);
		setBlockSep((hudWidth - blockWidth * 3) / 4);
		setBlockOffset((hudHeight - blockHeight) / 2);
		setHUD();
		setLevelBox(level + 1);
		setLifeBox(nTurns, 3);
		setScoreBox(score);
	}
	
	/*
	 * Method Name : setPos
	 * Input param : (int)_x, (int)_y
	 * Description : Sets the position of the HUD.*/
	public void setPos(int _x, int _y) {
		x = _x;
		y = _y;
	}
	
	/*
	 * Method Name : setSize
	 * Input param : (int)_width, (int)_height
	 * Description : Sets the width and height of HUD.*/
	public void setSize(int _width, int _height) {
		hudWidth = _width;
		hudHeight = _height;
	}
	
	/*
	 * Method Name : setBlockSize
	 * Input param : (int)_blockWidth, (int)_blockHeight
	 * Description : Sets the width and height of block.*/
	public void setBlockSize(int _blockWidth, int _blockHeight) {
		blockWidth = _blockWidth;
		blockHeight = _blockHeight;
	}
	
	/*
	 * Method Name : setBlockSep
	 * Input param : (int)_blockSep
	 * Description : Sets the distance between blocks.*/
	public void setBlockSep(int _blockSep) {
		blockSep = _blockSep;
	}
	
	/*
	 * Method Name : setBlockYOffset
	 * Input param : (int)_blockYOffset
	 * Description : Sets the distance between start point of the HUD and the block.*/
	public void setBlockOffset(int _blockYOffset) {
		blockOffset = _blockYOffset;
	}
	
	/*
	 * Method Name : setHUD
	 * Description : Sets the HUD and displays it.*/
	public void setHUD() {
		hud = new GRect(x, y, hudWidth, hudHeight);
		hud.setFilled(true);
		hud.setFillColor(Color.BLACK);
		hud.setColor(Color.BLACK);
		add(hud);
	}
	
	/*
	 * Method Name : setLevelBox
	 * Input param : (int)level
	 * Description : Calculates the position of block which surrounds the level information
	 *               and displays the level info in the block.*/
	public void setLevelBox(int level) {
		double blockX, blockY, labX, labY;
		
		blockX = blockSep;
		blockY = y + blockOffset;
		levelBox = new GLabel("Level " + level);
		levelBox.setFont(new Font("Monospaced", Font.PLAIN, 13));
		labX = blockX + 0.5 * blockWidth - 0.5 * levelBox.getWidth();
		labY = blockY + 0.5 * blockHeight + 0.5 * levelBox.getAscent();
		levelBox.setLocation(labX, labY);
		levelBox.setColor(Color.WHITE);
		add(levelBox);
	}

	/*
	 * Method Name : setLifeBox
	 * Input param : (int)nTurns, (int)numLife
	 * Description : Calculates the position of block which surrounds the life information
	 *               and displays the life info in the block. 
	 *               The number of lives is expressed in star images.*/
	public void setLifeBox(int nTurns, int numLife) {
		double blockX, blockY;
		GImage star;
	
		blockX = blockSep * 2 + blockWidth;
		blockY = y + blockOffset;
		lifeBox = new ArrayList<GImage>();
		for (int i = 0; i < numLife; i++) {
			star = new GImage("star.png");
			star.setSize(blockWidth / (double)nTurns, blockHeight);
			star.setLocation(blockX + blockWidth / (double)nTurns * i, blockY);
			lifeBox.add(star);
			add(star);
		}
		lifeBox.add(null);
	}
	
	/*
	 * Method Name : setScoreBox
	 * Input param : (int)score
	 * Description : Calculates the position of block which surrounds the score information
	 *               and displays the score info in the block.*/
	public void setScoreBox(int score) {
		double blockX, blockY, labX, labY;
		
		blockX = blockSep * 3 + blockWidth * 2;
		blockY =  y + blockOffset;
		scoreBox = new GLabel("Score " + score);
		scoreBox.setFont(new Font("Monospaced", Font.PLAIN, 13));
		labX = blockX + 0.5 * blockWidth - 0.5 * scoreBox.getWidth();
		labY = blockY + 0.5 * blockHeight + 0.5 * scoreBox.getAscent();
		scoreBox.setLocation(labX, labY);
		scoreBox.setColor(Color.WHITE);
		add(scoreBox);
	}
	
	/*
	 * Method Name : removeLevelBox
	 * Description : Removes the level info.*/
	public void removeLevelBox() {
		remove(levelBox);
		levelBox = null;	
	}
	
	/*
	 * Method Name : removeLifeBox
	 * Description : Removes the life info.*/
	public void removeLifeBox() {
		int idx;
		
		idx = 0;
		while (lifeBox.get(idx) != null) {
			remove(lifeBox.get(idx));
			lifeBox.set(idx, null);
			idx++;
		}
		lifeBox = null;
	}
	
	/*
	 * Method Name : removeScoreBox
	 * Description : Removes the score info.*/
	public void removeScoreBox() {
		remove(scoreBox);
		scoreBox = null;
	}
}
