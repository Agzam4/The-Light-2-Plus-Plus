package wires;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Game;

public class MechanicalValve extends Wires {

	boolean isOpend;
	
	@Override
	public void update(Game game, int x, int y) {
		super.update(game, x, y);
		Color c = game.getWireColor(x, y);
		int gray = c.getRed() + c.getGreen() + c.getBlue();
		isOpend = gray > 255/2;
	}
	
	@Override
	public boolean isWall() {
		return !isOpend;
	}
	
	@Override
	public boolean isMechanicalValve() {
		return true;
	}
	
	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		super.draw(g, game, x, y);
		g.setColor(game.getWireColor(x, y));
		g.drawLine(x*Game.BlockSize, y*Game.BlockSize, (x+1)*Game.BlockSize, (y+1)*Game.BlockSize);
		g.drawLine(x*Game.BlockSize, (y+1)*Game.BlockSize, (x+1)*Game.BlockSize, y*Game.BlockSize);
		if(!isOpend) {
			g.setColor(Color.WHITE);
			g.drawRect(x*Game.BlockSize, y*Game.BlockSize, Game.BlockSize-1, Game.BlockSize-1);
		}
	}
}
