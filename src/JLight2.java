import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import game.Game.Blocks;
import game.JBlocksBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class JLight2 extends JFrame {

	private static JPanel contentPane;
	private static JMyPanel game;
	private static JBlocksBox blocksBox;
	private JSpinner spinner;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLight2 frame = new JLight2();
					frame.setVisible(true);
					game.go();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JLight2() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			 UIManager.put("ComboBox.background", new ColorUIResource(new Color(15,15,15)));
//			 UIManager.put("ComboBox.foreground", new ColorUIResource(Color.WHITE));
//			 
//			 UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(50,50,50)));
//			 UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
//			 
//
//			 UIManager.put("ComboBox.buttonDarkShadow", Color.WHITE);
//			 UIManager.put("ComboBox.buttonBackground", Color.GRAY);
//			 UIManager.put("ComboBox.buttonHighlight", Color.WHITE);
//			 UIManager.put("ComboBox.buttonShadow", Color.WHITE);
			 
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		
		JPanel buttons = new JPanel();
		buttons.setBackground(Color.BLACK);
		contentPane.add(buttons, BorderLayout.NORTH);
		
		JPanel blockShooser = new JPanel();
		blockShooser.setBackground(Color.BLACK);
		blockShooser.setLayout(new BoxLayout(blockShooser, BoxLayout.Y_AXIS));
		contentPane.add(blockShooser, BorderLayout.EAST);
		
		JPanel blocksBoxPanel = new JPanel();
		blocksBoxPanel.setBackground(Color.BLACK);
		blocksBox = new JBlocksBox();
		blocksBox.setFocusable(false);
		blocksBoxPanel.add(blocksBox);
		blockShooser.add(blocksBoxPanel);

		JBlockCreator blockCreator = new JBlockCreator(blocksBox);
		blockShooser.add(blockCreator);
		blockCreator.setVisible(false); // TODO
		
		game = new JMyPanel();
		game.setJBlocksBox(blocksBox);
		contentPane.add(game, BorderLayout.CENTER);

		JButton clear = new JButton("Clear");
		buttons.add(clear);

		JButton viewMode = new JButton("View mode");
		buttons.add(viewMode);
		
		JButton caves = new JButton("Create caves");
		buttons.add(caves);
		
		
		clear.addActionListener(e -> {
			game.clear();
		});
		
		viewMode.addActionListener(e -> {
			game.setViewMode(!game.isViewMode());
		});
		
		caves.addActionListener(e -> {
			game.createCaves();
		});
	}

}
