import aiti.util.ArrayList;
import aiti.Tester;
public class Lab7 {
    public static void main(String[] args) {
	ArrayList al = new ArrayList();
	ArrayList al2 = new ArrayList();

	Tester.compare(new Integer(2), new Integer(ArrayList.numInitialized()));
	al.add("Hello");
	al2.add("World");
	al.add(new Integer(100));
	al2.add(new Double(-2.0e-2));
	Tester.compare(new Integer(100), al.get(1));
	Tester.compare("Hello", al.get(0));
	Tester.compare("World", al2.get(0));
	Tester.compare(new Double(-2.0e-2), al2.get(1));
	al.add("World");
	al.add(new Double(2e2));
	Tester.compare(new Double(2e2), al.get(3));

	for (int i = 0; i < al.size(); i++)
	    System.out.print(al.get(i) + " ");
	System.out.println();
	for (int i = 0; i < al2.size(); i++)
	    System.out.print(al2.get(i) + " ");
	System.out.println();

	System.out.println("This should cause an error and halt: ");
	Tester.compare("Aaaaargh!", al.get(1));
    }
}