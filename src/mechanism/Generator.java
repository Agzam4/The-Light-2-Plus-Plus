package mechanism;

import java.awt.Graphics2D;

import game.Game;

public class Generator extends Mechanism {

	private static final long serialVersionUID = 7L;
	private int updateCount = 0;

	@Override
	public void update(Game game, int x, int y) {
		boolean b = false;
		if(updateCount%2 == 0) {
			b = game.copyWater(x-1,y,2,0) ||
				game.copyWater(x+1,y,-2,0) ||
				game.copyWater(x,y+1,0,-2) ||
				game.copyWater(x,y-1,0,+2);
			updateCount++;
		}
		if(!b) {
			updateCount = 0;
		}
		super.update(game, x, y);
	}

	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		g.setColor(game.getLightColor(x, y));
		g.fillOval(x*Game.BlockSize+1, y*Game.BlockSize+1, Game.BlockSize-2, Game.BlockSize-2);
		super.draw(g, game, x, y);
	}
}
