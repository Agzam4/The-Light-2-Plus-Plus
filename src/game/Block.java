package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;

import game.Game.Blocks;
import work.ObjectWork;

public class Block implements Cloneable, Serializable {

	private static final long serialVersionUID = 5L;

	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	
	protected Color light = Color.BLACK;
	int lr,lg,lb;

	Color color = Color.BLACK;
	int cr,cg,cb;

	boolean isVoid;
	boolean isFixed;

	public Block(Color color, boolean isVoid) {
		setColor(color);
		setVoid(isVoid);
	}

	public Block(Color color, boolean isVoid, boolean isFixed) {
		setColor(color);
		setVoid(isVoid);
		setFixed(isFixed);
	}

	public Block(Color color, Color light, boolean isVoid) {
		setColor(color);
		setLight(light);
		setVoid(isVoid);
	}

	public Block(Color color, Color light, boolean isVoid, boolean isFixed) {
		setColor(color);
		setLight(light);
		setVoid(isVoid);
		setFixed(isFixed);
	}
	
	public void setColor(Color color) {
		this.color = color;
		cr = color.getRed();
		cg = color.getGreen();
		cb = color.getBlue();
	}
	
	public void setLight(Color light) {
		this.light = light;
		lr = light.getRed();
		lg = light.getGreen();
		lb = light.getBlue();
	}
	
	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
		if(!isFixed) {
			setVoid(false);
		}
	}
	
	public void setVoid(boolean isVoid) {
		this.isVoid = isVoid;
	}
	
	public boolean isVoid() {
		return isVoid;
	}
	
	public boolean isWall() {
		return !isVoid;
	}

	public void update(Game game, int x, int y) {
		
	}

	public void draw(Graphics2D g, Game game, int x, int y) {
		if(isWall()) {
			g.setColor(game.getWallLightColor(x, y, cr, cg, cb));
		}else {
			g.setColor(game.getLightColor(x, y));
		}
		g.fillRect(x*Game.BlockSize, y*Game.BlockSize, Game.BlockSize, Game.BlockSize);
	}
	
	/**
	 * @return returns true only if and only if the block has "light > 0"
	 */
	public boolean hasLight() {
		return lr + lg + lb != 0;
	}

	/**
	 * @param type - type of channel:<br>
	 * RED - 0<br>
	 * GREEN - 1<br>
	 * BLUE - 2
	 * @return the value of the "type" channel
	 */
	public int getLight(int type) {
		switch (type) {
		case RED:
			return lr;
		case GREEN:
			return lg;
		case BLUE:
			return lb;
		default:
			return 0;
		}
	}
	
	/**
	 * This is work, do not delete
	 * @return returns true only if and only if the block is water-block
	 */
	public boolean isWater() {
		return false;
	}

	/**
	 * This is work, do not delete
	 * @return returns true only if and only if the block is mechanism-block
	 */
	public boolean isMechanism() {
		return false;
	}

	/**
	 * This is work, do not delete
	 * @return returns true only if and only if the block is glass-block
	 */
	public boolean isGalss() {
		return false;
	}

	/**
	 * This is work, do not delete
	 * @return returns true only if and only if the block is neonwall-block
	 */
	public boolean isNeonWall() {
		return false;
	}
	
	/**
	 * This is work, do not delete
	 * @return returns true only if and only if the block is wiretype-block
	 */
	public boolean isWiretype() {
		return false;
	}
	
	/**
	 * This is work, do not delete
	 * @return returns true only if and only if the block is wire-block
	 */
	public boolean isWire() {
		return false;
	}
	
	/**
	 * This is work, do not delete
	 * @return returns true only if and only if the block is converter-block
	 */
	public boolean isConverter() {
		return false;
	}
	
	public boolean isMechanicalValve() {
		return false;
	}
	
	
	public boolean isAirBlock() {
		return isVoid;
	}
	
	public double getGrayLight() {
		return (lr + lg + lb) / 3d;
	}
	
	
	public Color getColor() {
		return color;
	}
	
	public Color getLightColor() {
		return light;
	}

	public boolean isFixed() {
		return isFixed;
	}
	
//	public Block clone() {
//		try {
//			return (Block)ObjectWork.clone(this);
//		} catch (ClassNotFoundException | IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//		/*
//		try {
//			return super.clone();
//		} catch (CloneNotSupportedException e) {
//			return null;
//		}*/
//	}

	public Block clone() {
		try {
			return (Block) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected boolean isRainbow;
	protected float timer = 0;
	protected void updateRainbow() {
		if(isRainbow) {
			timer += 5f;
			timer %= 360;
			setLight(new Color(Color.HSBtoRGB(timer/360f, 1f, 1f)));
		}
	}
	
	
	@Override
	public String toString() {
		return super.toString() 
				+ "[color: " + getColor()
				+ ", light: " + getLightColor()
				+ ", isWall: " + isWall()
				+ ", isFixed: " + isFixed()
				+ ", isRainbow: " + isRainbow
				+ ", timer: " + timer
				
				
				+ "]";
	}
	
	public boolean isTransparent() {
		return isVoid();
	}
}
