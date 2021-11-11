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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Block;
import game.Game;
import game.Game.Blocks;
import game.JBlocksBox;

public class JMyPanel extends JPanel {

	private static final long serialVersionUID = 2L;

	public BufferedImage gameImage;
	
	public JMyPanel() {
		setFocusable(true);
		setBackground(new Color(15,15,15));
	}
	
	public void go() {
		Thread mainGameThread = new Thread(() -> {
			while (true) {
				update();
				draw((Graphics2D) getGraphics());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
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
			game.setBlock(mx, my, selectedBlock.clone());
		}
	}
	
	int updateCount = 0;
	
	Block selectedBlock = Blocks.WATER_WHITE.getBlock();
	
	public void update() {
		game.update();
		if(isMousePressed && updateCount%2 == 0) {
			setBlock();
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
//		g.setColor(new Color(15,15,15));
//		g.fillRect(0, 0, getWidth(), getHeight());
		g.translate(getNewX(), getNewY());
		game.draw(g);
		if(frame != null) {
			frame.setIconImage(game.getImage());
		}
		g.dispose();
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
}
