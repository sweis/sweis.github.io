package sudoku.gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

/**
 * This Stores the MenuBar for the Sudoku game.
 * 
 * @author sweis
 *
 */
public class MenuBar extends JMenuBar {
	
	/* ---- Private Data Member Fields -------------------- */
	
	private static final long serialVersionUID = -7074314898075057804L;
	private JMenu fileMenu;
	private Action newAction, openAction, saveAction, saveAsAction, quitAction;

	private JMenu helpMenu;
	private Action helpAction, aboutAction;

	private JMenu themeMenu;
	
	private Board board;
	
	/* ---- Constructors -------------------- */
	
	/**
	 * Creates a new MenuBar. Uses the argument Board to access the Squares 
	 * 
	 * @param b The Board the MenuBar will use to use to access the Squares
	 */
	public MenuBar(Board b) {
		board = b;
		createActions();
		fileMenu = new JMenu("File");
		fileMenu.add(new JMenuItem(newAction));
		fileMenu.add(new JMenuItem(openAction));
		fileMenu.add(new JMenuItem(saveAction));
		fileMenu.add(new JMenuItem(saveAsAction));
		fileMenu.add(new JMenuItem(quitAction));
		add(fileMenu);
		
		themeMenu = new JMenu("Themes");
		themeMenu.add(new JMenuItem(new themeActionClass("Default", new Theme())));
		Theme ugly = new Theme();
		ugly.setBackgroundColor(Color.magenta);
		ugly.setMouseoverColor(Color.yellow);
		ugly.setHighlightColor(Color.pink);
		ugly.setTextColor(Color.orange);
		ugly.setGivenColor(Color.white);
		ugly.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.blue,Color.red,Color.green,Color.cyan));
		
		/* TODO: Add at least two more themes */
		
		themeMenu.add(new JMenuItem(new themeActionClass("Ugly", ugly)));
		add(themeMenu);
		
		helpMenu = new JMenu("Help");
		helpMenu.add(new JMenuItem(helpAction));
		helpMenu.add(new JMenuItem(aboutAction));
		add(helpMenu);
	}

	/* ---- Private Methods -------------------- */
	
	/**
	 * Initializes actions for each menu.
	 */
	private void createActions() {
		int shortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

		//Create actions that can be used by menus, buttons, toolbars, etc.
		newAction = new newActionClass( "New Game", KeyStroke.getKeyStroke(KeyEvent.VK_N, shortcutKeyMask) );
		openAction = new openActionClass( "Open Saved Game", KeyStroke.getKeyStroke(KeyEvent.VK_O, shortcutKeyMask) );
		saveAction = new saveActionClass( "Save Game", KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcutKeyMask) );
		saveAsAction = new saveAsActionClass( "Save Game as.."); 
		quitAction = new quitActionClass( "Quit" );
		helpAction = new helpActionClass( "Help" );
		aboutAction = new aboutActionClass( "About" );
	}

	/* ---- Inner Class Action Listeners -------------------- */
	
	/**
	 * This should create a new Sudoku board.
	 * @author sweis
	 */
	class newActionClass extends AbstractAction {
		private static final long serialVersionUID = -3000438048650100460L;
		public newActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}
		public void actionPerformed(ActionEvent e) {
			/* 
			 * TODO: Create a new game by reinitialize to a new random board 
			 * See Board and BoardState
			 */
		}
	}

	/**
	 * This Inner Class is used when a user selects the Open menu item.
	 * @author sweis
	 */
	class openActionClass extends AbstractAction {
		private static final long serialVersionUID = 3990346655606401972L;
		public openActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}
		public void actionPerformed(ActionEvent e) {
			/* TODO: Open a saved file. See the "Save" TODO flag below */
		}
	}
	
	/**
	 * This Inner Class is used when a user selects the Save menu item.
	 * It should save the game to a file. 
	 * @author sweis
	 */
	class saveActionClass extends AbstractAction {
		private static final long serialVersionUID = -3178753762532156182L;
		public saveActionClass(String text, KeyStroke shortcut) {
			super(text);
			putValue(ACCELERATOR_KEY, shortcut);
		}
		public void actionPerformed(ActionEvent e) {
			/* TODO: Save the current board state to a file. You will need to first
			 * design your own file format. Perhaps just nine lines of comma-seperated 
			 * integers 1-9, with -1 representing an empty space. For example:
			 *  1,  2,  3,  4,  5,  6,  7,  8,  9
			 *  4,  5,  6,  7,  8,  9,  1,  2,  3
			 * -1, -1, -1, -1, -1, -1, -1, -1, -1 
			 *  etc. (for 7 more lines)
			 *  See lab 9 for help.
			 *  
			 *  "Save" assumes that this board has already been saved to a file by "Save as...". 
			 *  You'll need to keep track of which file somewhere.
			 *  Also, if the user hasn't already saved it as a file, this should just run the same code
			 *  as "Save as..."
			 */
		}
	}
	
	/**
	 * This Inner Class is used when a user selects the Save As menu item.
	 * It should prompt the user for a file name and save the game there.
	 * @author sweis
	 */
	class saveAsActionClass extends AbstractAction {
		private static final long serialVersionUID = -6240363423141414379L;
		public saveAsActionClass(String text) {
			super(text);
		}
		public void actionPerformed(ActionEvent e) {
			/* 
			 * TODO: See notes for saveActionClass. 
			 * This should prompt the user for an input file name 
			 */
		}
	}

	/**
	 * This Inner Class is used when a user selects the Quit menu item.
	 * It will cause the program to exit.
	 * 
	 * @author sweis
	 */
	class quitActionClass extends AbstractAction {
		private static final long serialVersionUID = -8988951230169763962L;
		public quitActionClass(String text) {
			super(text);
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * This Inner Class is used when a user selects the Help menu item.
	 * It should display a Help pop-up window.
	 * 
	 * @author sweis
	 */
	class helpActionClass extends AbstractAction {
		private static final long serialVersionUID = -4606030566683540686L;
		public helpActionClass(String text) {
			super(text);
		}
		public void actionPerformed(ActionEvent e) {
			/* 
			 * TODO: Display a pop-up window listing all the game controls.
			 * Specifically, the legal keys are 1-9, the arrow keys, space bar, and ? (for cheating). 
			 */
		}
	}

	/**
	 * This Inner Class is used when a user selects the About menu item.
	 * It will display a pop-up window about the program.
	 * 
	 * @author sweis
	 */
	class aboutActionClass extends AbstractAction {
		private static final long serialVersionUID = 6801940026523298625L;
		public aboutActionClass(String text) {
			super(text);
		}
		public void actionPerformed(ActionEvent e) {
			/* TODO: Add your name where it says [YOUR NAME] in the About pop-up */
			new About();
		}
	}
	
	/**
	 * This Inner Class is used when a user selects A Theme menu item.
	 * It will set the Square class's theme. 
	 * 
	 * @author sweis
	 */
	class themeActionClass extends AbstractAction {
		private static final long serialVersionUID = 2089673912311016995L;
		private Theme theme;
		public themeActionClass(String text, Theme t) {
			super(text);
			theme = t;
		}
		public void actionPerformed(ActionEvent e) {
			/* TODO: Implement this method */
			board.setTheme(theme);
		}
	}

}
