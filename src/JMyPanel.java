import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Block;
import game.Game;
import game.Game.Blocks;
import work.ObjectWork;
import game.JBlocksBox;

public class JMyPanel extends JPanel {

	private static final long serialVersionUID = 2L;

	public BufferedImage gameImage;
	
	public JMyPanel() {
		setFocusable(true);
		setBackground(Color.BLACK);
	}
	
	public void go() {
		Thread mainGameThread = new Thread(() -> {
			long start;
			long wait;
			isGameThreadStopped = false;
			while (true) {
				start = System.nanoTime();
				update();
				draw((Graphics2D) getGraphics());
				wait = 75-(System.nanoTime()-start)/1_000_000;
				if(wait < 1) wait = 1;
				try {
					Thread.sleep(wait);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Game.scale = 1;
				if(getHeight() < getWidth()) {
					Game.scale = (getHeight()-10) / (double) game.getPxHeight();
				}else {
					Game.scale = (getWidth()-10) / (double) game.getPxWidth();
				}
				
				if(isGameThreadStopped) {
					while (isGameThreadStopped) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}	
		});
		mainGameThread.start();
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				isMousePressed = false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				isMousePressed = true;
				setMousePosition(e);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				isMousePressed = false;
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				setMousePosition(e);
//				setBlock();
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				isMousePressed = true;
				setMousePosition(e);
			}
		});
	}
	
	Game game = new Game();
	
	int mx, my;
	boolean isMousePressed;
	
	private void setMousePosition(MouseEvent e) {
		double blockSizePx = Game.BlockSize * Game.scale;
		mx = (int) ((e.getX() - getNewX())/blockSizePx);
		my = (int) ((e.getY() - getNewY())/blockSizePx);
	}
	
	private void setBlock() {
		if(selectedBlock != null) {
			if(game.setBlock(mx, my, selectedBlock.clone())) {
				isSaved = false;
			}
		}
	}
	
	int updateCount = 0;
	
	Block selectedBlock = Blocks.WATER_WHITE.getBlock();
	
	public void update() {
		game.update();
		if(isMousePressed) {
//			if(updateCount%2 == 0) {
				setBlock();
//			}
		}else {
			updateCount = 1;
		}
		updateCount++;
	}

	public void setJBlocksBox(JBlocksBox blocksBox) {
		blocksBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				selectedBlock = blocksBox.getSelectedBlock();
			}
		});
	}
	
	private int getNewX() {
		return (getWidth()-game.getPxWidth())/2;
	}
	
	private int getNewY() {
		return (getHeight()-game.getPxHeight())/2;
	}
	
	public void draw(Graphics2D g) {
		if(g != null) {
			g.translate(getNewX(), getNewY());
//			g.setColor(Color.BLACK);
//			g.fillRect(0, 0, getWidth(), getHeight());
			game.draw(g);
			if(frame != null) {
				frame.setIconImage(game.getImage());
			}
			g.dispose();
		}
	}
	
	JFrame frame;
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public boolean isViewMode() {
		return game.isViewMode();
	}

	public void setViewMode(boolean b) {
		game.setViewMode(b);
	}

	public void clear() {
		game = new Game(game.getWidth(), game.getHeight());
	}

	public void createCaves() {
		game.createCaves();
	}

	public boolean isStopped() {
		return game.isStopped();
	}

	public void setStop(boolean stopped) {
		game.setStopped(stopped);
	}
	
	
	public Game getGame() {
		return game;
	}

	public void load(String filename) {
		try {
			System.out.println("Loading... (" + filename + ")");
			game = (Game) ObjectWork.readObject(filename);
			isSaved = true;
		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	boolean isSaved = false;
	
	public void save(String path) {
		System.out.println("Saving... (" + path + ")");
		try {
			ObjectWork.writeObject(game, path.endsWith(".game") ? path : path + ".game");
			isSaved = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	boolean isGameThreadStopped;
	
	public void pause() {
		isGameThreadStopped = true;
	}

	public void resume() {
		isGameThreadStopped = false;
	}
	
	public boolean isSaved() {
		return isSaved;
	}
}
