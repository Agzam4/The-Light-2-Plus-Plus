package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Water extends Block implements Cloneable {

	private static final long serialVersionUID = 5L;

	public Water(Color color) {
		super(Color.BLACK, color == null ? Color.BLACK : color, false, false);
		isRainbow = color == null;
		updateRainbow();
	}
	
	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		g.setColor(light);
		g.fillRect(x*Game.BlockSize, y*Game.BlockSize, Game.BlockSize, Game.BlockSize);
	}

	@Override
	public boolean isVoid() {
		return true;
	}
	
	@Override
	public boolean isWater() {
		return true;
	}
	
	@Override
	public void update(Game game, int x, int y) {
		updateRainbow();
		super.update(game,x,y);
	}
}
