package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Glass extends Block {

	public Glass(Color color) {
		super(color, false, true);
	}
	
	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		super.draw(g, game, x, y);
		g.setColor(color);
		if(!game.isGlass(x-1, y))
			g.drawLine(x*Game.BlockSize, y*Game.BlockSize, x*Game.BlockSize, (y+1)*Game.BlockSize);
		if(!game.isGlass(x+1, y))
			g.drawLine((x+1)*Game.BlockSize-1, y*Game.BlockSize, (x+1)*Game.BlockSize-1, (y+1)*Game.BlockSize);
		
		if(!game.isGlass(x, y-1))
			g.drawLine(x*Game.BlockSize, y*Game.BlockSize, (x+1)*Game.BlockSize, y*Game.BlockSize);
		if(!game.isGlass(x, y+1))
			g.drawLine(x*Game.BlockSize, (y+1)*Game.BlockSize-1, (x+1)*Game.BlockSize, (y+1)*Game.BlockSize-1);
	}

	@Override
	public boolean isGalss() {
		return true;
	}
	
	@Override
	public boolean isTransparent() {
		return true;
	}
}