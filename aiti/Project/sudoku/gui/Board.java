package sudoku.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import sudoku.game.BoardState;

/**
 * This is a GUI Board component. It stores a double array of squares.
 * It does not check for consistancy among the Squares.
 * That is handled by sudoku.game.BoardState.
 * 
 * @author sweis
 */
public class Board extends JPanel {

	/* ---- Public Data Member Fields -------------------- */

	/**
	 * This is the size of this board. This value can be hardcoded to any size.
	 */
	public static final int SIZE = 9;
	
	/**
	 * This is the size of the subgrid spacing. It can be set equal to Board.SIZE if 
	 * no subgrids are needed.
	 */
	public static final int SUBGRID_SPACING = 3;

	/* ---- Private Data Member Fields -------------------- */
	private static final long serialVersionUID = 3742598096504649724L;
	
	private static final Border border = BorderFactory.createRaisedBevelBorder();
	private static final GridLayout layout = new GridLayout(SIZE,SIZE,0,0);

	private BoardState boardState;
	private Square[][] squares;
	
	/* ---- Constructors -------------------- */
	
	/**
	 * Constructs a new Board object. Creates a Board.SIZE x Board.SIZE 2-dimensional 
	 * array of Squares and initializes them. Also creates and intializes a BoardState.
	 * This BoardState is intialized with a number of random valued Squares. 
	 */
	public Board() {
		super();
		squares = new Square[SIZE][SIZE];
		for (int i=0; i<SIZE; i++)
			for (int j=0; j<SIZE; j++) {
				squares[i][j] = new Square(i,j);
				add(squares[i][j]);
			}
		
		setTheme(new Theme());
		
		boardState = new BoardState(this);
				
		setLayout(layout);
		setBorder(border);
	}
	
	/**
	 * Reinitializes the board to a new game state.
	 *
	 */
	public void reinit() {
		/* TODO: Implement this method */
	}

	/* ---- Get/Set Methods -------------------- */
	
	/**
	 * Returns the Square in the ith row and jth of a Board.SIZE x Board.SIZE 2-dimensional array. 
	 * 
	 * @param i The row of the Square to return
	 * @param j The column of the Square to return
	 * @return The Square at the specified 2-dimensional array element.
	 */
	public Square getSquare(int i, int j) {
		return squares[i][j];
	}
	
	/* ---- UI Methods -------------------- */
	
	/**
	 * Changes the theme of all the Squares in this board.
	 * 
	 * @param t
	 */
	public void setTheme(Theme t) {
		Square.setTheme(t);
		for (int i=0; i< SIZE; i++)
			for (int j=0; j<SIZE; j++) {
				Square s = squares[i][j];
				s.label.setForeground(t.getTextColor());
				s.setBackground(t.getBackgroundColor());
				s.setBorder(t.getBorder());
				
				/* Set the subgrid region borders to thick */
				squares[i][j].setThick(false, false,
						(i%SUBGRID_SPACING == SUBGRID_SPACING-1)&&(i<SIZE-1),
						(j%SUBGRID_SPACING == SUBGRID_SPACING-1)&&(j<SIZE-1));
				if (!s.getModifiable())
					s.label.setForeground(t.getGivenColor());
			}
		if (Square.getHighlighted() != null)
			Square.getHighlighted().setBackground(t.getHighlightColor());
	}

	
	/**
	 * Handles a Key Event that is captured by the internal KeyListener in the sudoku.Sudoku class.
	 * The Key Listener could not be added to this type, so was added to Sudoku's JFrame parent instead.  
	 * 
	 * This method controls all keyboard-based UI for the game.
	 * 
	 * @param key The Key event to be handled. 
	 */
	public void handleKeyEvent(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if (Square.getHighlighted() != null) {
			int row = Square.getHighlighted().getRow();
			int col = Square.getHighlighted().getCol();
			if (keyCode >= KeyEvent.VK_0 && 
					keyCode <= KeyEvent.VK_9 &&
					Square.getHighlighted().getModifiable())  
				boardState.play(Square.getHighlighted(), keyCode-48);
			else if (keyCode == KeyEvent.VK_UP) 
				Square.setCursorFocused(squares[(row-1+SIZE)%SIZE][col]);
			else if (keyCode == KeyEvent.VK_DOWN)
				Square.setCursorFocused(squares[(row+1)%SIZE][col]);				
			else if (keyCode == KeyEvent.VK_LEFT) 
				Square.setCursorFocused(squares[row][(col-1+SIZE)%SIZE]);
			else if (keyCode == KeyEvent.VK_RIGHT) 
				Square.setCursorFocused(squares[row][(col+1)%SIZE]);
			else if (keyCode == KeyEvent.VK_SPACE  &&
					Square.getHighlighted().getModifiable()) 
				boardState.play(Square.getHighlighted(), -1);
			else if (key.getKeyChar() == '?') {
			/* TODO: Help the player cheat by giving the solution of the highlighted cell */			
			}
		}	
	}

}
