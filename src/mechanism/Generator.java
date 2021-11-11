package mechanism;

import java.awt.Graphics2D;

import game.Game;

public class Generator extends Mechanism {

	@Override
	public void update(Game game, int x, int y) {
		game.copyWater(x-1,y,2,0);
		game.copyWater(x+1,y,-2,0);
		
		game.copyWater(x,y+1,0,-2);
		game.copyWater(x,y-1,0,+2);
		super.update(game, x, y);
	}
	
	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		g.setColor(game.getLightColor(x, y));
		g.fillOval(x*Game.BlockSize+1, y*Game.BlockSize+1, Game.BlockSize-2, Game.BlockSize-2);
		super.draw(g, game, x, y);
	}
}
