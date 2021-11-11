import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game.Block;
import game.JBlocksBox;
import game.Lamp;
import game.Water;

public class JBlockCreator extends JPanel {

	private static final long serialVersionUID = 10L;

	ColorCreator color;
	JCheckBox isLight, isWall, isFalling;
	
	public JBlockCreator(JBlocksBox blocksBox) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		color = new ColorCreator();
		color.setBorder(new TitledBorder("Color"));
		add(color);

		isLight = new JCheckBox("Glowing");
		isLight.setAlignmentX(JCheckBox.CENTER_ALIGNMENT);
		
//		isWall = new JCheckBox("Glowing");
//		isWall.setAlignmentX(JCheckBox.CENTER_ALIGNMENT);
		
		isFalling = new JCheckBox("Falling");
		isFalling.setAlignmentX(JCheckBox.CENTER_ALIGNMENT);

		isLight.setFocusable(false);
//		isWall.setFocusable(false);
		isFalling.setFocusable(false);
		
		isLight.setSelected(true);
//		isWall.setSelected(true);
		isFalling.setSelected(true);

		add(isLight);
		add(isFalling);
		
		
		JButton create = new JButton("Create");
		create.addActionListener(e -> {
			blocksBox.addBlock(getBlock());
		});
		add(create);
	}

	private Block getBlock() {
		if(isLight.isSelected()) {
			if(isFalling.isSelected()) {
				return new Water(color.getColor());
			}else {
				return new Lamp(color.getColor());
			}
		}else {
			return new Block(color.getColor(), false, !isFalling.isSelected());
		}
	}
	
	class ColorCreator extends JPanel {

		JPanel hue, saturation, brightness;
		
		public ColorCreator(int[] hsb) {
			init(hsb);
		}
		
		public ColorCreator() {
			init(new int[] {0, 100, 100});
		}
		
		private void init(int[] hsb) {

			this.hsb = hsb;
			hue = getJSliderPanel("Hue:", 360, 0);
			saturation = getJSliderPanel("Saturation:", 100, 1);
			brightness = getJSliderPanel("Brightness:", 100, 2);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(hue);
			add(saturation);
			add(brightness);
			updateColor();
		}
		
		int hsb[];
		
		private JPanel getJSliderPanel(String name, int max, int id) {
			JPanel sliderPanel = new JPanel();
			sliderPanel.setOpaque(false);
			JLabel nameLabel = new JLabel(name);
			nameLabel.setOpaque(true);
			nameLabel.setBackground(Color.WHITE);
			nameLabel.setForeground(Color.BLACK);
			nameLabel.setBorder(new EmptyBorder(2, 5, 2, 5));
			JSlider slider = new JSlider(0, max, hsb[id]);
			
			
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(hsb[id], null, max, 1));

			slider.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					hsb[id] = slider.getValue();
					spinner.setValue(hsb[id]);
					updateColor();
				}
			});
			
			spinner.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					hsb[id] = ((Integer)spinner.getValue()).intValue();
					slider.setValue(hsb[id]);
					updateColor();					
				}
			});
			
			sliderPanel.add(nameLabel);
			sliderPanel.add(slider);
			sliderPanel.add(spinner);
			
			return sliderPanel;
		}
		
		
		Color color = Color.BLACK;
		
		private void updateColor() {
			color = new Color(Color.HSBtoRGB(hsb[0]/360f, hsb[1]/100f, hsb[2]/100f));
			setBackground(color);
		}
		
		public Color getColor() {
			return color;
		}
	}
	
	
}
