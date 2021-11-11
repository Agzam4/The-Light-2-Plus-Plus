package game;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import game.Game.Blocks;
import work.ObjectWork;

public class JBlocksBox extends JComboBox<String> {

	private static final long serialVersionUID = 3L;

	ArrayList<String> names = new ArrayList<String>();
	ArrayList<Block> blocks = new ArrayList<Block>();
	
	public JBlocksBox() {
		for (Blocks block : Game.Blocks.values()) {
			blocks.add(block.getBlock());
			names.add(block.toString());
		}
		setModel();
		setMaximumRowCount(32);
	}
	
	public void setModel() {
		setModel(new DefaultComboBoxModel<String>(names.toArray(new String[0])));
	}
	
	public Block getSelectedBlock() {
		try {
			return blocks.get(getSelectedIndex());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addBlock(Block block) {
		names.add("Block " + (names.size()-Blocks.values().length+1));
		blocks.add(block);
		setModel();
	}
	
}
