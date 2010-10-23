package sudoku.gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Pops up an "About" window.
 * @author sweis
 */
public class About extends JFrame {
	private static final long serialVersionUID = -3570703727803904477L;	
	
	/**
	 * Creates an "About" window. Disposes on close.
	 */
	public About() {
		super("About AITI Sudoku");
		Container cp = getContentPane();
		Font f = new Font("SansSerif", Font.ITALIC, 24);
		
		JLabel title = new JLabel("AITI Sudoku", JLabel.CENTER);
		title.setFont(f);
		
		JLabel by = new JLabel("Written by: AITI Team and [YOUR NAME]", JLabel.CENTER);
		JLabel copyright = new JLabel("Copyright \u00A9 2005", JLabel.CENTER);

		cp.add(title);
		cp.add(by);
		cp.add(copyright);
		cp.setLayout(new GridLayout(3,1));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(150,150);
		pack();
		setVisible(true);
	}
}
