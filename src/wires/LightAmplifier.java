package wires;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Game;

public class LightAmplifier extends Wires {

	int px, py;
	
	public LightAmplifier(int px, int py) {
		this.px = px;
		this.py = py;
	}
	
	@Override
	public void update(Game game, int x, int y) {
		super.update(game, x, y);
		game.lightAmplifier(x,y,px,py);
	}
	
	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		super.draw(g, game, x, y);
		g.setColor(Color.WHITE);
		g.drawRect(x*Game.BlockSize, y*Game.BlockSize, Game.BlockSize-1, Game.BlockSize-1);
		int nx = x*Game.BlockSize+Game.BlockSize/2;
		int ny = y*Game.BlockSize+Game.BlockSize/2;
		g.drawLine(nx, ny, nx+px*Game.BlockSize/2, ny+py*Game.BlockSize/2);
	}
	
	@Override
	public boolean isTransparent() {
		return false;
	}
	
	@Override
	public boolean isWiretype() {
		return false;
	}
}
