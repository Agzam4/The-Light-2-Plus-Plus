package wires;

import java.awt.Color;

import game.Block;

public class Wires extends Block {

	private static final long serialVersionUID = 1111111111L;

	public Wires() {
		super(Color.WHITE, Color.BLACK, false, true);
	}
	
	public Wires(Color color, Color light) {
		super(color, light, false, true);
	}
	
	@Override
	public boolean isTransparent() {
		return true;
	}
	
	@Override
	public boolean isWiretype() {
		return true;
	}
}
