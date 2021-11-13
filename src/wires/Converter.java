package wires;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Game;

public class Converter extends Wires {

	private static final long serialVersionUID = 11L;

	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		super.draw(g, game, x, y);
		g.setColor(Color.WHITE);
		g.drawRect(x*Game.BlockSize, y*Game.BlockSize, Game.BlockSize-1, Game.BlockSize-1);
	}
	
	@Override
	public boolean isTransparent() {
		return true;
	}
	
	@Override
	public boolean isConverter() {
		return true;
	}
}
