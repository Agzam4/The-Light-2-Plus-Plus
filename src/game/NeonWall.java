package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class NeonWall extends Block {

	private static final long serialVersionUID = 4L;

	public NeonWall(Color color) {
		super(color, false, true);
	}

	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		g.setColor(color.darker());
		g.fillRect(x*Game.BlockSize, y*Game.BlockSize, Game.BlockSize, Game.BlockSize);
		g.setColor(color);
		if(!game.isNeonWall(x-1, y))
			g.drawLine(x*Game.BlockSize, y*Game.BlockSize, x*Game.BlockSize, (y+1)*Game.BlockSize);
		if(!game.isNeonWall(x+1, y))
			g.drawLine((x+1)*Game.BlockSize-1, y*Game.BlockSize, (x+1)*Game.BlockSize-1, (y+1)*Game.BlockSize);
		
		if(!game.isNeonWall(x, y-1))
			g.drawLine(x*Game.BlockSize, y*Game.BlockSize, (x+1)*Game.BlockSize, y*Game.BlockSize);
		if(!game.isNeonWall(x, y+1))
			g.drawLine(x*Game.BlockSize, (y+1)*Game.BlockSize-1, (x+1)*Game.BlockSize, (y+1)*Game.BlockSize-1);
	}
	
	@Override
	public boolean isNeonWall() {
		return true;
	}
}
