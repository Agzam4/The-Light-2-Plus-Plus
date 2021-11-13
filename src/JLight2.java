import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import game.JBlocksBox;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;

public class JLight2 extends JFrame {

	private static JPanel contentPane;
	private static JMyPanel game;
	private static JBlocksBox blocksBox;
	private JSpinner spinner;
	private static JLight2 frame;
	String frametitle = "Light 2 + +";
	
	String pathString = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JLight2();
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

		JCustomButton clear = new JCustomButton("Clear", new Color(75,75,75), new Color(50,50,50), new Color(150,150,150));
		JCustomButton viewMode = new JCustomButton("View mode", new Color(75,75,75), new Color(50,50,50), new Color(150,150,150));
		JCustomButton caves = new JCustomButton("Create caves", new Color(75,75,75), new Color(50,50,50), new Color(150,150,150));
		JCustomButton stop = new JCustomButton("Stop", new Color(75,75,75), new Color(50,50,50), new Color(150,150,150));

		setTitle("Light 2 + +");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE.darker()));
		menuBar.setBackground(Color.BLACK);
		menuBar.setOpaque(true);
		menuBar.setForeground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);
		
		UIDefaults ui = UIManager.getLookAndFeel().getDefaults();
		 
		for ( Object o : ui.keySet() ){
			System.out.println(o.toString());
		}
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	        UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorForeground", Color.GRAY);
	        UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorForeground", Color.GRAY);
	        UIManager.put("Menu.background", Color.BLACK);
	        UIManager.getLookAndFeelDefaults().put("MenuItem:MenuItemAccelerator[MouseOver].textForeground",Color.yellow);
	        UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorSelectionForeground", Color.LIGHT_GRAY);
	        UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorSelectionForeground", Color.LIGHT_GRAY);
	        UIManager.getLookAndFeelDefaults().put("MenuItem.selectionBackground", Color.WHITE);
	        UIManager.getLookAndFeelDefaults().put("List.background", Color.RED);
	        UIManager.getLookAndFeelDefaults().put("List.dropCellBackground", Color.RED);
	        UIManager.put("PopupMenu.border", new LineBorder(Color.WHITE.darker()));
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

		buttons.add(clear);
		buttons.add(viewMode);
		buttons.add(caves);
		buttons.add(stop);

		clear.setForeground(Color.WHITE);
		viewMode.setForeground(Color.WHITE);
		caves.setForeground(Color.WHITE);
		stop.setForeground(Color.WHITE);
		
		clear.addActionListener(e -> {
			game.clear();
		});
		
		viewMode.addActionListener(e -> {
			game.setViewMode(!game.isViewMode());
		});
		
		caves.addActionListener(e -> {
			game.createCaves();
		});
		
		stop.addActionListener(e -> {
			stop.setText(game.isStopped() ? "Stop" : "Play");
			game.setStop(!game.isStopped());
		});
		
		
		// TODO: MENU FILE

		JMenu file = new JMenu("File");
		file.setBackground(Color.BLACK);
		file.setForeground(Color.LIGHT_GRAY);
		file.setBorder(new LineBorder(new Color(50,50,50)));
		file.setOpaque(true);
		menuBar.add(file);

		JMenuItem newFile = getJMenuItem("New", KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_DOWN_MASK));
		file.add(newFile);
		
		newFile.addActionListener(e -> {
			game.clear();
		});
		

		JMenuItem save = getJMenuItem("Save", KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_DOWN_MASK));
		file.add(save);
		
		save.addActionListener(e -> {
			save(pathString);
		});
		
		JMenuItem saveAs = getJMenuItem("Save As", KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
		file.add(saveAs);
		
		saveAs.addActionListener(e -> {
			saveAs();
		});

		JMenuItem open = getJMenuItem("Open", KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_DOWN_MASK));
		file.add(open);
		
		open.addActionListener(e -> {
			FileDialog fd = new FileDialog(new JFrame());
			fd.setVisible(true);
			File[] f = fd.getFiles();
			if(f.length > 0) {
				pathString = fd.getFiles()[0].getAbsolutePath();
				game.load(pathString);
				try {
					pathString = pathString.substring(0, pathString.lastIndexOf('.'));
				} catch (StringIndexOutOfBoundsException e2) {
				}
			}
		});
		

		file.add(getJSeparator());

		JMenuItem screen = getJMenuItem("Print screen", KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_DOWN_MASK));
		file.add(screen);
		
		screen.addActionListener(e -> {
			game.clear();
		});
		
		// ctrl + p
		
//		game.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyTyped(KeyEvent e) {
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if(e.getKeyCode() == KeyEvent.VK_F11) {
//					System.out.println("F");
//					GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
//					GraphicsDevice device = graphics.getDefaultScreenDevice();
//				    frame.dispose();
//					setUndecorated(!frame.isUndecorated());
//					setExtendedState(JFrame.MAXIMIZED_BOTH);
//				    frame.setVisible(true);
//				    frame.repaint();
//				}
//			}
//		});
		
		Thread title = new Thread(() -> {
			while(true) {
				setTitle(frametitle + " - " + getPathString() + (game.isSaved() ? "" : "*"));
			}
		});
		title.start();
	}
	
	
	private void saveAs() {
		game.pause();
		FileDialog fd = new FileDialog(new JFrame());
		fd.setFile(getPathString());
		fd.setMode(FileDialog.SAVE);
		fd.setVisible(true);
		File[] f = fd.getFiles();
		if(f.length > 0) {
			pathString = fd.getFiles()[0].getAbsolutePath();
			save(pathString);
		}
		
		game.resume();
	}
	
	
	private void save(String path) {
		if(path == null) {
			saveAs();
		}else {
			game.save(path);
		}
	}

	private JSeparator getJSeparator() {
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.BLACK);
		separator.setPreferredSize(new Dimension(-1, 1));
		separator.setOpaque(true);
		return separator;
	}
	
	private JMenuItem getJMenuItem(String text, KeyStroke keyStroke) {
		JMenuItem menuItem = new JMenuItem(text);
		menuItem.setAccelerator(keyStroke);
		menuItem.setOpaque(true);
		menuItem.setBackground(Color.BLACK);
		menuItem.setForeground(Color.LIGHT_GRAY);
		menuItem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		menuItem.setOpaque(true);
		return menuItem;
	}
	
	
	public String getPathString() {
		return pathString == null ? "Unnamed" : pathString;
	}
}
