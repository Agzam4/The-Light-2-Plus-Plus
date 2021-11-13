package wires;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Game;

public class Wire extends Wires {

	private static final long serialVersionUID = 15L;

	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		g.setColor(Color.WHITE);
		g.fillRect(x*Game.BlockSize+1, y*Game.BlockSize+1, Game.BlockSize-2, Game.BlockSize-2);

		if(game.isWiretype(x-1, y))
			g.fillRect(x*Game.BlockSize, y*Game.BlockSize+1, Game.BlockSize-1, Game.BlockSize-2);
		if(game.isWiretype(x+1, y))
			g.fillRect(x*Game.BlockSize+1, y*Game.BlockSize+1, Game.BlockSize-1, Game.BlockSize-2);

		if(game.isWiretype(x, y-1))
			g.fillRect(x*Game.BlockSize+1, y*Game.BlockSize, Game.BlockSize-2, Game.BlockSize-1);
		if(game.isWiretype(x, y+1))
			g.fillRect(x*Game.BlockSize+1, y*Game.BlockSize+1, Game.BlockSize-2, Game.BlockSize-1);
		
		g.setColor(game.getWireColor(x, y));
		g.fillRect(x*Game.BlockSize+2, y*Game.BlockSize+2, Game.BlockSize-4, Game.BlockSize-4);
		
		if(game.isWiretype(x-1, y))
			g.fillRect(x*Game.BlockSize, y*Game.BlockSize+2, Game.BlockSize-2, Game.BlockSize-4);
		if(game.isWiretype(x+1, y))
			g.fillRect(x*Game.BlockSize+2, y*Game.BlockSize+2, Game.BlockSize-2, Game.BlockSize-4);

		if(game.isWiretype(x, y-1))
			g.fillRect(x*Game.BlockSize+2, y*Game.BlockSize, Game.BlockSize-4, Game.BlockSize-2);
		if(game.isWiretype(x, y+1))
			g.fillRect(x*Game.BlockSize+2, y*Game.BlockSize+2, Game.BlockSize-4, Game.BlockSize-2);
	}

	@Override
	public boolean isWire() {
		return true;
	}
}
