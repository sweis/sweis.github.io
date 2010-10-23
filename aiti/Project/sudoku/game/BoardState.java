package sudoku.game;

import java.util.ArrayList;
import java.util.Collections;

import sudoku.gui.Board;
import sudoku.gui.Square;

/**
 * BoardState stores the current state of the board by keeping 
 * track of which values still remain playable. These are kept in a
 * double array of ArrayLists of Integers. It interfaces to the GUI
 * through the Board class.
 * 
 * @author sweis
 *
 */
public class BoardState {
	
	/* ---- Private Data Member Fields -------------------- */
	private final static int NUM_VALUES = 9;
	private final static Integer negOne = new Integer(-1);
	private final static Integer[] values = new Integer[] { 
			new Integer(1), new Integer(2), new Integer(3),
			new Integer(4), new Integer(5), new Integer(6), 
			new Integer(7), new Integer(8), new Integer(9)
		};
	
	private ArrayList[][] remaining;
	private Board board;

	/* ---- Constructors -------------------- */
	
	/**
	 * Create a new BoardState object.
	 * 
	 * @param b The Board object used to access the GUI.
	 */
	public BoardState(Board b) {
		board = b;
		remaining = getAllPossible();
	}
	
	/* ---- Public Methods -------------------- */
	
	/**
	 * Iniitializes a number of squares to random values and sets them as non-modifiable.
	 * 
	 * @param numSquares The number of squares to randomly initialize
	 */
	public void randomInit(int numSquares) {
		/* Resets the board to all blanks, i.e. -1 values */
		for (int i=0; i<Board.SIZE; i++)
			for (int j=0; j<Board.SIZE; j++) {
				board.getSquare(i,j).setModifiable(true);
				board.getSquare(i,j).setValue(-1);
			}
		remaining = getAllPossible();
		
		/* TODO: This method does not guarantee that the puzzle is solvable.
		 * Rewrite this so that it first initializes the entire board to a value puzzle, 
		 * then chooses (81-numSquares) cells to reset to blank.
		 */
		while (numSquares > 0) {
			/* Pick a random cell */
			int i = (int)(Math.random() * Board.SIZE);
			int j = (int)(Math.random() * Board.SIZE);
			Square s = board.getSquare(i,j);
			if (s.getValue() == -1 && remaining[i][j].size() > 0) {
				int k = (int)(Math.random() * remaining[i][j].size());
				play(s, ((Integer)remaining[i][j].get(k)).intValue());
				s.setModifiable(false);
			}
			numSquares--;	
		}
	}
				
	/**
	 * Attempts to set the argument Square to the given value then
	 * recalculates the possible values of all other Squares. <BR> 
	 * If the Square is cleared, it's value is set to -1.
	 * Otherwise, if the value is not legal, nothing happens.
	 * 
	 * @param square The Square to try to play.
	 * @param v The value to try to set the Square to.
	 */
	public void play(Square square, int v) {
		
		ArrayList remainingSquare = remaining[square.getRow()][square.getCol()];
		
		Integer newValue = negOne;

		if (v >= 1 && v <= NUM_VALUES) 
			newValue = values[v-1];

		if (remainingSquare.contains(newValue)) 
			remaining[square.getRow()][square.getCol()].remove(newValue);
		else
			newValue = negOne;
		
		square.setValue(newValue.intValue());
			
		refreshPossible();
		checkForVictory();
		setToolTipStrings();
	}

	/* ---- Private Methods -------------------- */	

	
	/**
	 * Get the remaining playable values at the given row and column.
	 * 
	 * @param i Row to get the remaining playable values from. 
	 * @param j Column to get the remaining playable values from.
	 * @return An ArrayList of values still playable by a particular cell.
	 */
	private ArrayList getRemaining(int i, int j) {
		return remaining[i][j];
	}

	/**
	 * Produces a string representation of the remaining playable values at 
	 * the given coordiantes.
	 * 
	 * @param i The row coordinate
	 * @param j The column coordinate.
	 * @return A string representation of the remaining playable values.
	 */
	private String getStringRep(int i, int j) {
		ArrayList possible = getRemaining(i,j);
		
		String output = "Possible:";
			
		for (i = 0; i<possible.size(); i++)
			output += " " + possible.get(i).toString();
		
		if (possible.size() == 0)
			output += " None";
		return output;
	}

	
	/**
	 * This is a utility function to initialize the ArrayLists to contain eveyr value.
	 * 
	 * @return A 2-dimensional array of ArrayLists initialized to contain Integers 1-9
	 */
	private ArrayList[][] getAllPossible() {
		ArrayList[][]  allPossible = new ArrayList[Board.SIZE][Board.SIZE];
		for (int i=0; i<Board.SIZE; i++)
			for (int j=0; j<Board.SIZE; j++) {
				allPossible[i][j] = new ArrayList();
				for (int k = 0; k < NUM_VALUES; k++)
					allPossible[i][j].add(values[k]);
			}
		return allPossible;
	}

	/**
	 * Sets the toolTipStrings for all the Squares on the board.
	 */
	private void setToolTipStrings() {
		for (int i=0; i < Board.SIZE; i++)
			for (int j=0; j < Board.SIZE; j++)
				for (int k=0; k < Board.SIZE; k++)
					board.getSquare(i,j).setToolTipText(getStringRep(i,j));
	}
	
	/**
	 * Recalculates which values are still possible by iterating through the entire playing board.
	 */
	private void refreshPossible() {
		remaining = getAllPossible();
		
		for (int i=0; i<Board.SIZE; i++)
			for (int j=0; j<Board.SIZE; j++) {
				
				Square square = board.getSquare(i,j);
				int value = square.getValue();
				
				if (value >= 1 && value <= 9) {
					Integer removeValue = values[value-1];
				
					for (int k = 0; k < Board.SIZE; k++) {						
						
						ArrayList targetRowCell = remaining[square.getRow()][k];
						/* 
						 * If the user entered a legal value, the removeValue will be
						 * non-null. Try to remove it from everything in the target row.
						 */
						if (removeValue != null && targetRowCell.contains(removeValue)) {
							targetRowCell.remove(removeValue);
							Collections.sort(targetRowCell);
						}

						ArrayList targetColCell = remaining[k][square.getCol()];
						
						/* 
						 * If the user entered a legal value, the removeValue will be
						 * non-null. Try to remove it from everything in the target column.
						 */
						if (removeValue != null && targetColCell.contains(removeValue)) {
							targetColCell.remove(removeValue);
							Collections.sort(targetColCell);
						}
						
					}
					
					/* 
					 * If the user entered a legal value, the removeValue will be
					 * non-null. Try to remove it from everything in its 3x3 region.
					 */
					int rowNinth = i/3;
					int colNinth = j/3;
					for (int k=0; k<Board.SUBGRID_SPACING; k++)
						for (int r=0; r<Board.SUBGRID_SPACING; r++) {
							
							int x = k+rowNinth*Board.SUBGRID_SPACING;
							int y = r+colNinth*Board.SUBGRID_SPACING;

							ArrayList targetCell = remaining[x][y];
							if (removeValue != null && targetCell.contains(removeValue)) {
								targetCell.remove(removeValue);
								Collections.sort(targetCell);
							}
						}
				}
			}
	}
	
	/**
	 * This needs to be implemented by someone! 
	 */
	private void checkForVictory() {
		/* 
		 * TODO: This method should check the board and see if the puzzle is solved.
		 * If so, it should alter the GUI in some way to alert the user, or show a
		 * pop-up congratulations meessage.
		 */
	}
	
	
}