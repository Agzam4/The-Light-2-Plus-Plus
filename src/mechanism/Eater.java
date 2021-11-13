package mechanism;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Game;

public class Eater extends Mechanism {
	
	private static final long serialVersionUID = 6L;

	@Override
	public void update(Game game, int x, int y) {
		game.removeWater(x-1,y);
		game.removeWater(x+1,y);
		game.removeWater(x,y-1);
		game.removeWater(x,y+1);
		super.update(game, x, y);
	}
	
	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		g.setColor(game.getLightColor(x, y));
		g.fillRect(x*Game.BlockSize+1, y*Game.BlockSize+1, Game.BlockSize-2, Game.BlockSize-2);
		g.setColor(Color.WHITE);
		g.fillOval(x*Game.BlockSize+1, y*Game.BlockSize+1, Game.BlockSize-2, Game.BlockSize-2);
	}
}
