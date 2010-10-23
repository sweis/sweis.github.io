package sudoku;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import sudoku.gui.Board;
import sudoku.gui.MenuBar;

/**
 * Sudoru: This is a stand-alone application that creates a new Sudoru game when run.
 * This class extends JFrame. 
 * 
 * @link http://www.sudoku.com
 * @author sweis
 *
 */
public class Sudoku extends JFrame {
	
	/* ---- Private Data Member Fields -------------------- */
	
	private static final long serialVersionUID = -8406169254820913518L;
	
	private Board board;
	
	private MenuBar menuBar;
	
	/* ---- Constructor -------------------- */
	
	/**
	 * Constructs a new Sudoru game with a randomly initialized playing board. 
	 */
	public Sudoku() {
		super("Sudoku");
		board = new Board();
		menuBar = new MenuBar(board);
		setJMenuBar(this.menuBar);
		
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(this.board);
		addKeyListener(new KeyForwarder());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		pack();
		setVisible(true);
	}

	
	public static void main(String[] args) {
		new Sudoku();
	}

	/* ---- UI Inner Classes -------------------- */

	/**
	 * This is an inner class that passes on Key Events to the board's handleKeyEvent() method.
	 * 
	 * @author sweis
	 *
	 */
	class KeyForwarder extends KeyAdapter {
		public void keyReleased(KeyEvent key) {
			board.handleKeyEvent(key);
		}
	};
}