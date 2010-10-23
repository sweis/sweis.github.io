import java.util.*;
import java.text.*;

public class Available {
    static public void main(String[] args) {
	Locale list[] = DateFormat.getAvailableLocales();
	for (int i = 0; i < list.length; i++) {
	    System.out.print(list[i].toString() + ": ");	
	    System.out.println(list[i].getDisplayName());	
	}
    }
}