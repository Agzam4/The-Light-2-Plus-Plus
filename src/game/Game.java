package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import mechanism.ValveUp;
import wires.Converter;
import wires.ElectricLamp;
import wires.MechanicalValve;
import wires.Wire;
import mechanism.Eater;
import mechanism.Generator;
import mechanism.ValveLeft;
import mechanism.ValveRight;

public class Game {
	
	public static int BlockSize = 10;
	public static double scale = 1.25;
	boolean isViewMode;

	public static final Color WHITE = new Color(255,255,255);
	public static final Color BROWN = new Color(150, 100, 75);
	public static final Color RED = new Color(255,0,0);
	public static final Color ORANGE = new Color(255,180,0);
	public static final Color YELLOW = new Color(255,255,0);
	public static final Color LIME = new Color(200,255,0);
	public static final Color GREEN = new Color(0,255,0);
	public static final Color CYAN = new Color(0,255,180);
	public static final Color LIGHT_BLUE = new Color(0,180,255);
	public static final Color BLUE = new Color(0,0,255);
	public static final Color PUPLE = new Color(180,0,255);
	public static final Color PINK = new Color(255,0,255);
	public static final Color CRIMSON = new Color(255,0,100);
	
	public enum Blocks {
		AIR (new Block(Color.BLACK, true)),
		WALL (new Block(BROWN, false)),
		
		LAMP_WHITE 		(new Lamp(WHITE)),
		LAMP_RED 		(new Lamp(RED)),
		LAMP_ORANGE 	(new Lamp(ORANGE)),
		LAMP_YELLOW 	(new Lamp(YELLOW)),
		LAMP_LIME 		(new Lamp(LIME)),
		LAMP_GREEN 		(new Lamp(GREEN)),
		LAMP_CYAN 		(new Lamp(CYAN)),
		LAMP_LIGHT_BLUE (new Lamp(LIGHT_BLUE)),
		LAMP_BLUE 		(new Lamp(BLUE)),
		LAMP_PUPLE 		(new Lamp(PUPLE)),
		LAMP_PINK 		(new Lamp(PINK)),
		LAMP_CRIMSON 	(new Lamp(CRIMSON)),
		LAMP_RAINBOW 	(new Lamp(null)),
		
		WATER_WHITE 	(new Water(WHITE)),
		WATER_RED 		(new Water(RED)),
		WATER_ORANGE 	(new Water(ORANGE)),
		WATER_YELLOW 	(new Water(YELLOW)),
		WATER_LIME 		(new Water(LIME)),
		WATER_GREEN 	(new Water(GREEN)),
		WATER_CYAN 		(new Water(CYAN)),
		WATER_LIGHT_BLUE(new Water(LIGHT_BLUE)),
		WATER_BLUE 		(new Water(BLUE)),
		WATER_PUPLE 	(new Water(PUPLE)),
		WATER_PINK 		(new Water(PINK)),
		WATER_CRIMSON 	(new Water(CRIMSON)),
		WATER_RAINBOW 	(new Water(null)),

		VALVE_UP		(new ValveUp()),
		VALVE_RIGHT		(new ValveRight()),
		VALVE_LEFT		(new ValveLeft()),
		
		GLASS				(new Glass(BROWN)),
		GLASS_WHITE 		(new Glass(WHITE)),
		GLASS_RED 			(new Glass(RED)),
		GLASS_ORANGE 		(new Glass(ORANGE)),
		GLASS_YELLOW 		(new Glass(YELLOW)),
		GLASS_LIME 			(new Glass(LIME)),
		GLASS_GREEN 		(new Glass(GREEN)),
		GLASS_CYAN 			(new Glass(CYAN)),
		GLASS_LIGHT_BLUE	(new Glass(LIGHT_BLUE)),
		GLASS_BLUE 			(new Glass(BLUE)),
		GLASS_PUPLE 		(new Glass(PUPLE)),
		GLASS_PINK 			(new Glass(PINK)),
		GLASS_CRIMSON 		(new Glass(CRIMSON)),

		NEONWALL 			(new NeonWall(BROWN)),
		NEONWALL_WHITE 		(new NeonWall(WHITE)),
		NEONWALL_RED 		(new NeonWall(RED)),
		NEONWALL_ORANGE 	(new NeonWall(ORANGE)),
		NEONWALL_YELLOW 	(new NeonWall(YELLOW)),
		NEONWALL_LIME 		(new NeonWall(LIME)),
		NEONWALL_GREEN 		(new NeonWall(GREEN)),
		NEONWALL_CYAN 		(new NeonWall(CYAN)),
		NEONWALL_LIGHT_BLUE	(new NeonWall(LIGHT_BLUE)),
		NEONWALL_BLUE 		(new NeonWall(BLUE)),
		NEONWALL_PUPLE 		(new NeonWall(PUPLE)),
		NEONWALL_PINK 		(new NeonWall(PINK)),
		NEONWALL_CRIMSON 	(new NeonWall(CRIMSON)),

		GENERATOR			(new Generator()),
		EATER				(new Eater()),
		
		WIRE				(new Wire()),
		CONVERTER			(new Converter()),
		ELECTRIC_LAMP		(new ElectricLamp()),
		MECHANICAL_VALVE	(new MechanicalValve()),
		
		/*
		 * TODO:
		 * Wire-blocks:
		 * 	Light amplifiers
		 * 	Logic Valve
		 */
		TO_DELETE (new Block(Color.BLACK, true));
		
		Block block;
		
		private Blocks(Block block) {
			this.block = block;
		}
		
		public Block getBlock() {
			return block.clone();
		}
		
		@Override
		public String toString() {
			String newStr = super.toString().toLowerCase().replaceAll("_", " ");
			newStr = Character.toUpperCase(newStr.charAt(0)) + newStr.substring(1);
			return newStr;
		}
		
		public static String[] getNames(Class<? extends Enum<?>> e) {
		    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
		}
	}
	

	Block[][] blocks;
	Block[][] newBlocks;
	
	int[][] r,g,b;
	int[][] wr,wg,wb; // wires
	
	int width, height;

	public Game() {
		init(50, 50); // FIXME: 50
	}

	public Game(int size) {
		init(size, size);
	}

	public Game(int w, int h) {
		init(w, h);
	}
	
	
	private Block createBlock(Blocks block) {
		return new Block(
				block.getBlock().getColor(),
				block.getBlock().getLightColor(),
				block.getBlock().isVoid(),
				block.getBlock().isFixed());
	}
	
	private void init(int w, int h) {
		width = w;
		height = h;
		blocks = new Block[w][h];
		newBlocks = new Block[w][h];
		r = new int[w][h];
		g = new int[w][h];
		b = new int[w][h];
		wr = new int[w][h];
		wg = new int[w][h];
		wb = new int[w][h];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				//* <-- Add "/" to comment
				blocks[x][y] = Math.random() > 0.0 ? Blocks.AIR.getBlock() :
					Blocks.WATER_RAINBOW.getBlock();
				/*/
				blocks[x][y] = Math.random() > 0.1 ? Blocks.AIR.getBlock() :
						Blocks.values()[(int) (Math.random()*Blocks.values().length)].getBlock();
				//*/
				newBlocks[x][y] = Blocks.AIR.getBlock();
				r[x][y] = 0;
				g[x][y] = 0;
				b[x][y] = 0;
				
				wr[x][y] = 0;
				wg[x][y] = 0;
				wb[x][y] = 0;
			}
		}
		game = new BufferedImage(width*Game.BlockSize+2, height*Game.BlockSize+2, BufferedImage.TYPE_INT_RGB);
	}
	
	private transient BufferedImage game;

	public void update() { // TODO: update
		
		if(isNeedCreateCaves) {
			generateCaves();
			isNeedCreateCaves = false;
		}
		
		// blocks -> newBlocks
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				newBlocks[x][y] = blocks[x][y];
			}
		}
				
		// update mechanisms (maybe move in "update-water-for")
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(blocks[x][y].isMechanism()) {
					blocks[x][y].update(this, x, y);
				}
			}
		}
				
		// update water
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(!blocks[x][y].isMechanism()) {
					blocks[x][y].update(this, x, y);
				}
				if(blocks[x][y].isWater() && newBlocks[x][y].isWater()) {
					if(checkMoveBlock(x, y, 0, 1)) {
						moveBlock(x, y, 0, 1);
					}else {
						boolean canMoveRight = checkMoveBlock(x, y, 1, 0);
						boolean canMoveLeft = checkMoveBlock(x, y, -1, 0);
						double moveRight = -1;
						double moveLeft = -1;
						if(canMoveRight && canMoveLeft) {
							if(canMoveRight) {
								moveRight = blocks[x+1][y].getGrayLight();
							}
							if(canMoveLeft) {
								moveLeft = blocks[x-1][y].getGrayLight();
							}
							if(moveRight == moveLeft) {
								moveLeft =  Math.random();
								moveRight = Math.random();
							}

							if(moveRight < moveLeft && moveRight != -1) {
								moveBlock(x, y, 1, 0);
							} else if(moveLeft < moveRight && moveLeft != -1) {
								moveBlock(x, y, -1, 0);
							}
						} else if(canMoveRight || canMoveLeft) {	
							if(canMoveRight) {
								moveBlock(x, y, 1, 0);
							}
							if(canMoveLeft) {
								moveBlock(x, y, -1, 0);
							}
						}
					}
				}
			}
		}
		
		
		// newBlocks -> blocks
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				blocks[x][y] = newBlocks[x][y];
//				newBlocks[x][y] = Blocks.AIR.getBlock();
			}
		}

		// update light
		updateLight(r, Block.RED);
		updateLight(g, Block.GREEN);
		updateLight(b, Block.BLUE);

		// update wires
		updateWires(wr, r, Block.RED);
		updateWires(wg, g, Block.GREEN);
		updateWires(wb, b, Block.BLUE);
	}
	
	private boolean checkMoveBlock(int x, int y, int px, int py) {
		if(!checkBounds(x+px, y+py)) {
			return false;
		}
		
		if(blocks[x+px][y+py].isMechanicalValve() && !blocks[x+px][y+py].isWall()) {
			return checkMoveBlock(x, y, px+getK(px), py+getK(py));
		}
		return blocks[x+px][y+py].isAirBlock() 
				&& newBlocks[x+px][y+py].isAirBlock();
	}
	
	private int getK(int i) {
		if(i > 0) return 1;
		if(i < 0) return -1;
		return 0;
	}
	
	private void moveBlock(int x, int y, int px, int py) {
		if(blocks[x+px][y+py].isMechanicalValve() && !blocks[x+px][y+py].isWall()) {
			moveBlock(x, y, px+getK(px), py+getK(py));
			return;
		}
		newBlocks[x+px][y+py] = blocks[x][y];
		blocks[x][y] = Blocks.AIR.getBlock();
		newBlocks[x][y] = Blocks.AIR.getBlock();
	}
	
	private void updateWires(int[][] l, int[][] ml, int type) { // TODO: updateWires
		int[][] newArr = new int[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(blocks[x][y].isConverter()) {
					l[x][y] = ml[x][y];//blocks[x][y].getLight(type);
				}
			}
		}
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int count = 1; // sum of light void-blocks
				int sum = l[x][y]; // sum of void blocks
				lightToWire(newArr, l, x, y, 1, 0);
				lightToWire(newArr, l, x, y, -1, 0);
				lightToWire(newArr, l, x, y, 0, 1);
				lightToWire(newArr, l, x, y, 0, -1);
//				newArr[x][y] = (int) ((sum/count)-1);
			}
		}
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(newArr[x][y] > 255){
					l[x][y] = 255;
				}else {
					l[x][y] = newArr[x][y];
				}
					
			}
		}
	}
	
	private void lightToWire(int[][] nl, int[][] l, int x, int y, int px, int py) {
		if(checkBounds(x+px, y+py)) {
			if(blocks[x+px][y+py].isWiretype()) {
				if(l[x+px][y+py] < l[x][y]) {
					l[x+px][y+py] = l[x][y]	/*TODO*/ -2; 
					nl[x+px][y+py] = l[x][y]/*TODO*/ -2; 
				}
			}
		}
	}
	
	
	private void updateLight(int[][] l, int type) {
		int[][] newArr = new int[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(blocks[x][y].hasLight()) {
					l[x][y] += blocks[x][y].getLight(type);
				}
			}
		}
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int count = 1; // sum of light void-blocks
				int sum = l[x][y]; // sum of void blocks
				if(checkBounds(x+1, y)) {
					if(blocks[x+1][y].isTransparent()) {
						sum += l[x+1][y];
						count++;
					}
				}
				if(checkBounds(x-1, y)) {
					if(blocks[x-1][y].isTransparent()) {
						sum += l[x-1][y];
						count++;
					}
				}
				if(checkBounds(x, y+1)) {
					if(blocks[x][y+1].isTransparent()) {
						sum += l[x][y+1];
						count++;
					}
				}
				if(checkBounds(x, y-1)) {
					if(blocks[x][y-1].isTransparent()) {
						sum += l[x][y-1];
						count++;
					}
				}
				newArr[x][y] = (int) ((sum/count)/1.05);
			}
		}
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(newArr[x][y] > 255){
					l[x][y] = 255;
				}else {
					l[x][y] = newArr[x][y];
				}
					
			}
		}
	}
	
	private boolean checkBounds(int x, int y) {
		return x < width && x >= 0 &&
				y < height && y >= 0;
	}

	public void draw(Graphics2D pg) { // TODO: draw
		Graphics2D g = (Graphics2D) game.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width*BlockSize, height*BlockSize);
		g.translate(1, 1);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				blocks[x][y].draw(g, this, x, y);
			}
		}

		g.setColor(Color.WHITE);
		g.drawRect(-1, -1, width*BlockSize+1, height*BlockSize+1);
		g.dispose();
		
		pg.drawImage(game, 0, 0, (int)(game.getWidth()*scale), (int) (game.getHeight()*scale), null);
	}

	public BufferedImage getImage() {
		return game;
	}

	public Color getLightColor(int x, int y) {
		if(isViewMode()) return Color.BLACK;
		return new Color(r[x][y], g[x][y], b[x][y]);
	}

	public Color getWallLightColor(int x, int y, int red, int green, int blue) {
		if(isViewMode()) return new Color(red,green,blue);
		return new Color(
				fixColor(r[x][y]/(255d/red)),
				fixColor(g[x][y]/(255d/green)),
				fixColor(b[x][y]/(255d/blue))
				);
	}
	
	public Color getWireColor(int x, int y) {
		if(isViewMode()) return Color.BLACK;
		return new Color(fixColor(wr[x][y]*3), fixColor(wg[x][y]*3), fixColor(wb[x][y]*3));
	}

	private int fixColor(double c) {
		if(c > 255) return 255;
		if(c < 0) return 0;
		return (int) c;
	}
	
	
	public int getPxWidth() {
		return (int) (width*BlockSize*scale);
	}
	
	public int getPxHeight() {
		return (int) (height*BlockSize*scale);
	}
	
	
	private int toInt(boolean b) {
		return b ? 1 : 0;
	}

	public void setBlock(int x, int y, Block block) {
		if(checkBounds(x, y)) {
			blocks[x][y] = block;
			newBlocks[x][y] = block;
		}
	}

	public void valve(int x, int y, int px, int py) {
		if(checkMoveBlock(x, y, px, py)) {
			if(blocks[x][y].isWater()) {
				moveBlock(x, y, px, py);
			}
		}
	}

	public void copyWater(int x, int y, int px, int py) {
		if(checkMoveBlock(x, y, px, py)) {
			if(blocks[x][y].isWater()) {
				newBlocks[x+px][y+py] = new Water(blocks[x][y].getLightColor());
			}
		}
	}

	public void removeWater(int x, int y) {
		if(blocks[x][y].isWater()) {
			newBlocks[x][y] = Blocks.AIR.getBlock();
		}
	}
	
	public boolean isViewMode() {
		return isViewMode;
	}
	
	public void setViewMode(boolean isViewMode) {
		this.isViewMode = isViewMode;
	}
	
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	boolean isLamp[][]; // for caves generating
	int light[][]; // for caves generating
	boolean isNeedCreateCaves;
	
	public void createCaves() {
		isNeedCreateCaves = true;
	}
	
	public void generateCaves() {
		isLamp = new boolean[width][height];
		light = new int[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				isLamp[x][y] = 1 == (int) (Math.random()*3);
				light[x][y] = 0;
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					updateLightOLD(x, y);
				}
			}
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if(light[x][y] > 75) {
					blocks[x][y] = Blocks.AIR.getBlock();
				}else {
					blocks[x][y] = Blocks.WALL.getBlock();
				}
				r[x][y] = 0;
				g[x][y] = 0;
				b[x][y] = 0;
			}
		}
	}
	
	private void updateLightOLD(int x, int y) {
		
		int count = 1; // sum of light void-blocks
		int sum = light[x][y]; // sum of void blocks
		if(isLamp[x][y]) {
			light[x][y] += 255;
			sum = 255;
		}
		if(checkBounds(x+1, y)) {
			if(!blocks[x+1][y].isWall()) {
				sum += light[x+1][y];
				count++;
			}
		}
		if(checkBounds(x-1, y)) {
			if(!blocks[x-1][y].isWall()) {
				sum += light[x-1][y];
				count++;
			}
		}
		if(checkBounds(x, y+1)) {
			if(!blocks[x][y+1].isWall()) {
				sum += light[x][y+1];
				count++;
			}
		}
		if(checkBounds(x, y-1)) {
			if(!blocks[x][y-1].isWall()) {
				sum += light[x][y-1];
				count++;
			}
		}
		light[x][y] = sum/count;
		light[x][y] = (int) (light[x][y]/1.05);
		if(light[x][y] > 255)
			light[x][y] = 255;
		if(light[x][y] < 0)
			light[x][y] = 0;
	}

	public boolean isGlass(int x, int y) {
		if(checkBounds(x, y)) {
			if(blocks[x][y].isGalss()) return true;
		}
		return false;
	}

	public boolean isNeonWall(int x, int y) {
		if(checkBounds(x, y)) {
			if(blocks[x][y].isNeonWall()) return true;
		}
		return false;
	}

	public boolean isWire(int x, int y) {
		if(checkBounds(x, y)) {
			if(blocks[x][y].isWire()) return true;
		}
		return false;
	}

	public boolean isWiretype(int x, int y) {
		if(checkBounds(x, y)) {
			if(blocks[x][y].isWiretype()) return true;
		}
		return false;
	}
	
}
