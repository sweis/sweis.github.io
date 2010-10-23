import javax.swing.*;

public class UnicodeDemo extends JFrame {
    public static void main(String[] Args) {
	UnicodeDemo app = new UnicodeDemo();
	app.setSize(100,100);
	JLabel label = 
	    new JLabel("Copyright \u00A9 2005", JLabel.CENTER);
	app.getContentPane().add(label);
	app.setTitle("Unicode Demo");
	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	app.setVisible(true);
    }
}
