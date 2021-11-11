package mechanism;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Block;
import game.Game;

public class Mechanism extends Block {

	private static final long serialVersionUID = 111L;

	public Mechanism() {
		super(Color.BLACK, false, true);
	}
	
	@Override
	public boolean isMechanism() {
		return true;
	}
	
	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		g.setColor(Color.WHITE);
		g.drawRect(x*Game.BlockSize, y*Game.BlockSize, Game.BlockSize-1, Game.BlockSize-1);
	}
}
