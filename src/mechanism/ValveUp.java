package mechanism;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Game;

public class ValveUp extends Mechanism {

	@Override
	public void update(Game game, int x, int y) {
		game.valve(x,y+1,0,-2);
		super.update(game, x, y);
	}
	
	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		g.setColor(Color.WHITE);
		g.fillRect(x*Game.BlockSize+1, y*Game.BlockSize+1, Game.BlockSize-2, Game.BlockSize/2);
		super.draw(g, game, x, y);
	}
}
