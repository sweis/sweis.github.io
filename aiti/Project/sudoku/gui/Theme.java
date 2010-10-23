package sudoku.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Themes are used to store different color and border settings used to draw Squares.
 * 
 * @author sweis
 */
class Theme {

	/* ---- Private Data Member Fields -------------------- */
	
	private Border border = BorderFactory.createEtchedBorder();
	
	private Color backgroundColor = new Color(232,232,232);
	private Color mouseoverColor = Color.green;
	private Color highlightColor = Color.cyan;
	private Color textColor = Color.blue;
	private Color givenColor = Color.black;
	
	/* ---- Constructors -------------------- */
	
	/**
	 * Creates a Theme with default values. 
	 */
	Theme() { }
	
	/**
	 * Creates a new theme with the given values
	 * 
	 * @param background The Square background color.
	 * @param mouseover The mouseover highlight color
	 * @param focused The cursor highlight color
	 * @param text The text color
	 * @param given The color of given squares
	 * @param border The square border
	 */
	Theme(Color background, Color mouseover, Color focused, Color text, Color given, Border border) {
		setBackgroundColor(background);
		setMouseoverColor(mouseover);
		setFocusedColor(focused);
		setTextColor(text);
		setGivenColor(given);
		this.setBorder(border);
	}

	/* ----Get/Set Methods -------------------- */

	/**
	 * @param border The border to set.
	 */
	void setBorder(Border border) {
		this.border = border;
	}

	/**
	 * @return Returns the border.
	 */
	Border getBorder() {
		return border;
	}

	/**
	 * @param backgroundColor The backgroundColor to set.
	 */
	void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return Returns the backgroundColor.
	 */
	Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param mouseoverColor The mouseoverColor to set.
	 */
	void setMouseoverColor(Color mouseoverColor) {
		this.mouseoverColor = mouseoverColor;
	}

	/**
	 * @return Returns the mouseoverColor.
	 */
	Color getMouseoverColor() {
		return mouseoverColor;
	}

	/**
	 * @param focusedColor The focusedColor to set.
	 */
	void setFocusedColor(Color focusedColor) {
		this.setHighlightColor(focusedColor);
	}


	/**
	 * @param textColor The textColor to set.
	 */
	void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return Returns the textColor.
	 */
	Color getTextColor() {
		return textColor;
	}

	/**
	 * @param givenColor The givenColor to set.
	 */
	void setGivenColor(Color givenColor) {
		this.givenColor = givenColor;
	}

	/**
	 * @return Returns the givenColor.
	 */
	Color getGivenColor() {
		return givenColor;
	}

	/**
	 * @param highlightColor The highlightColor to set.
	 */
	void setHighlightColor(Color highlightColor) {
		this.highlightColor = highlightColor;
	}

	/**
	 * @return Returns the highlight color.
	 */
	Color getHighlightColor() {
		return highlightColor;
	}

	
}
