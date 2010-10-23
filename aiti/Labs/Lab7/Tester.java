/**
 * This class will compare two objects.
 * If they differ, it will output an error message and halt. 
 */
import java.lang.*;
import java.util.ArrayList;
package aiti;

class Tester {
    public static void compare(Object expected, Object received) {
	if (expected.equals(received)) return;
	else outputError(expected, received);
    }

    private void outputError(Object expected, Object received) {
	(new Error("Expected: " + expected + " Received: " 
		   + received + " Exiting now.")).printStackTrace();
	System.exit(0);
    }
}