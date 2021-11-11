package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Lamp extends Block {
	
	public Lamp(Color color) {
		super(color == null ? Color.BLACK : color.darker(), color == null ? Color.BLACK : color, false, true);
		isRainbow = color == null;
		updateRainbow();
	}
	
	@Override
	public void draw(Graphics2D g, Game game, int x, int y) {
		g.setColor(light);
		g.fillRect(x*Game.BlockSize, y*Game.BlockSize, Game.BlockSize-1, Game.BlockSize-1);
		g.setColor(Color.WHITE);
		g.drawRect(x*Game.BlockSize, y*Game.BlockSize, Game.BlockSize-1, Game.BlockSize-1);
	}
	
	@Override
	public boolean isVoid() {
		return true;
	}
	
	@Override
	public void update(Game game, int x, int y) {
		updateRainbow();
		super.update(game,x,y);
	}
}
