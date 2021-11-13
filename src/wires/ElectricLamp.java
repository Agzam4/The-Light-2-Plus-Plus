package wires;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Game;

public class ElectricLamp extends Wires {

	private static final long serialVersionUID = 12L;

	@Override
	public void update(Game game, int x, int y) {
		super.update(game, x, y);
		setLight(game.getWireColor(x, y));
	}
	
	
	@Override
	public boolean hasLight() {
		return true;
	}
	
	public Color getLightColor() {
		return light;
	}
	
	@Override
	public boolean isVoid() {
		return true;
	}
	
	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		super.draw(g, game, x, y);
		g.setColor(light.brighter());
		g.drawRect(x*Game.BlockSize, y*Game.BlockSize, Game.BlockSize-1, Game.BlockSize-1);
	}
}
