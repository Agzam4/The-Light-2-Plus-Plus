import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class JCustomButton extends JButton {

	Color backgroundColor, rolloverColor, clickColor;
	
	Color color;
	
	boolean isRollover;
	
	public JCustomButton(String txt, Color background, Color rollover, Color click) {
		super(txt);
		this.backgroundColor = background;
		this.rolloverColor = rollover;
		this.clickColor = click;
		color = background;
		
		setBorderPainted(false);
		setFocusable(false);

		Thread button = new Thread() {
			@Override
			public void run() {
				while (true) {
					if(isRollover) {
						setColor(background, getModel().isPressed(), clickColor);
					} else {
						setColor(rollover, false, clickColor);
					}
					repaint();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					clickR = (clickR-1)/1.25+1d;
				}
			}
		};
		button.start();
		
		
		setBackground(new Color(0,0,0,0));

		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				clickX = e.getX();
				clickY = e.getY();
				clickR = 0;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				clickR = 1;
				isRollover = false;
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				isRollover = true;
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
	}
	
	int clickX, clickY;
	double clickR = 1;
	
	private void setColor(Color newColor, boolean isClicked, Color backgroundBP) {
		int r = newColor.getRed();
		int g = newColor.getGreen();
		int b = newColor.getBlue();
		if(isClicked) {
			r = backgroundBP.getRed();
			g = backgroundBP.getGreen();
			b = backgroundBP.getBlue();
			color = new Color(
					(int) Math.round(((color.getRed() - r) / 1.5) + r),
					(int) Math.round(((color.getBlue() - g) / 1.5) + g),
					(int) Math.round(((color.getGreen() - b) / 1.5) + b)
					);
		}else {
			color = new Color(
					(int) Math.floor(((color.getRed() - r) / 2) + r),
					(int) Math.floor(((color.getBlue() - g) / 2) + g),
					(int) Math.floor(((color.getGreen() - b) / 2) + b)
					);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(color);
		int d = (int) (Math.hypot(getWidth(), getHeight()) * clickR * 2);
		g.fillOval(clickX-d/2, clickY-d/2, d, d);
		super.paintComponent(g);
	}
}
