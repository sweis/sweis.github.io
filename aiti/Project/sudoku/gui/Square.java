package sudoku.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 * This class represents a Square on the Sudoku board and is contained in a Board object.
 * 
 * @author sweis
 *
 */
public class Square extends JPanel {
	
	/* ---- Private Data Member Fields -------------------- */
	
	private static final long serialVersionUID = 8266531025496618022L;

	private int value;

	private int row;
	private int col;
	JLabel label;
	private boolean modifiable = true;
	private static Theme theme = new Theme();
	private static Border border = BorderFactory.createEtchedBorder();
	
	
	private static Square highlighted = null;
	
	/**
	 * Creates a new Square, initialized to blank (represented by a value of -1).
	 * Coordinates are its position in the Board object.
	 * 
	 * @param x The row of this Square
	 * @param y The column of this Square
	 */
	Square(int x, int y) {	
		super();
		value = -1;
		row = x;
		col = y;
		
		label = new JLabel();
		add(this.label);
		label.setForeground(theme.getTextColor());
		setBackground(theme.getBackgroundColor());
		setBorder(border);
		addMouseListener(mouseList);
		label.addMouseListener(mouseList);
		setValue(this.value);
	}
	
	public static void setTheme(Theme t) {
		theme = t;
	}
	
	/**
	 * The value currently stored in this Square.
	 * Empty squares are represented by a -1
	 * 
	 * @return This Square's value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return This Square's row.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return This Square's column.
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * This method is used by the Board class to outline 3x3 regions in Squares with think borders.
	 * 
	 * @param top Whether to draw thick borders on top of this Square.
	 * @param left Whether to draw thick borders on top of this Square.
	 * @param bottom Whether to draw thick borders on top of this Square.
	 * @param right Whether to draw thick borders on top of this Square.
	 */
	void setThick(boolean top, boolean left, boolean bottom, boolean right) {
		if (top || left || bottom || right) {
			Border matteBorder = 	BorderFactory.createMatteBorder
				((top?2:0),(left?2:0),(bottom?2:0),(right?2:0),Color.black);
			Border compoundBorder = BorderFactory.createCompoundBorder(matteBorder,border);
			this.setBorder(compoundBorder);
		}
	}
	
	/**
	 * Sets this Square's value to a value between 1 and 9, inclusve, or -1.
	 * Any integer outside of [1,9] will default to -1.
	 * If the square is not modifiable, nothing will happen.
	 * 
	 * @param v The value to set this squre to.
	 */
	public void setValue(int v) {
		if (modifiable) {
			String text = "    ";
			
			value = -1;
			
			if (v >= 1 && v <= 9) {
				value = v;
				text = " " + v + " ";
			}
			
			label.setText(text);
		}
	}
	
	
	/**
	 * Given Squares are not modifiable.
	 * 
	 * @return Whether this square is modifiable.
	 */
	public boolean getModifiable() { return modifiable; }
	
	/**
	 * Given Squares are not modifiable and cannot be changed by the user.
	 * 
	 * @param value The value to set this Square's modifiable state to.
	 */
	public void setModifiable(boolean value) {
		modifiable = value;
		if (!modifiable)
			label.setForeground(theme.getGivenColor());
	}
	
	/**
	 * Sets this Square to be cursor focused.
	 * 
	 * @param s The Square that the cursor is on.
	 */
	public static void setCursorFocused(Square s) {
		if (getHighlighted() != null)
			getHighlighted().setBackground(theme.getBackgroundColor());
		setHighlighted(s);
		getHighlighted().setBackground(theme.getHighlightColor());
	}
	
	
	/**
	 * Squares can be highlighted by the cursor or the mouse. 
	 * There can only be one highlighted Square at a time.
	 * Subsequently, multiple Boards can only have one highlighted Square among them.
	 * This could potentially return null if the user has not done anything.
	 * 
	 * @param highlighted The square to highlight.
	 */
	static void setHighlighted(Square highlighted) {
		Square.highlighted = highlighted;
	}


	/**
	 * Squares can be highlighted by the cursor or the mouse. 
	 * There can only be one highlighted Square at a time.
	 * Subsequently, multiple Boards can only have one highlighted Square among them.
	 * This could potentially return null if the user has not done anything.
	 * 
	 * @return Returns the highlighted square.
	 */
	static Square getHighlighted() {
		return highlighted;
	}

	/**
	 * This is a private, anonymous MouseListener object. 
	 */
	private static MouseListener mouseList = new MouseListener() {
		
		private Square getSquare(MouseEvent e) {
			Square sourceSquare = null;	
    			if (e.getSource() instanceof JLabel) {
    				JLabel label = (JLabel)e.getSource();
    				sourceSquare = (Square)label.getParent();
    			} else if (e.getSource() instanceof Square) { 
    				sourceSquare = (Square)e.getSource();
    			}
    			return sourceSquare;
		}
		
	    public void mouseEntered(MouseEvent e) {	
	    		Square sourceSquare = getSquare(e);
	    		if (sourceSquare != getHighlighted())
	    			sourceSquare.setBackground(theme.getMouseoverColor());
	    }
	    public void mouseExited(MouseEvent e) {
	    		Square sourceSquare = getSquare(e);
	    		if (sourceSquare != getHighlighted())
	    			sourceSquare.setBackground(theme.getBackgroundColor());
	    }
	    
	    public void mousePressed(MouseEvent e) { }
	    
	    public void mouseClicked(MouseEvent e) {
	    		Square sourceSquare = getSquare(e);
	    		Square.setCursorFocused(sourceSquare);
	    }	
	    
	    public void mouseReleased(MouseEvent e) { }
	};
}

