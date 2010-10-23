package aiti.util;

public class ArrayList {
    private UtilityArray a;
    public ArrayList() {
	a = new UtilityArray(1);
    }
    public void add(Object o) {
	a.add(o);
    }
    public Object get(int i) {
	return a.get(i);
    }
    public int size() {
	return a.size();
    }
    public int numInitialized() {
	return a.initialized;
    }
}

class UtilityArray {
    final static int initialized = 0;
    public int nextInsert = 0;
    static private Object[] data;
    
    private UtilityArray(int initialSize) {
	this.data = new Object[initialSize];
	int initialized = this.initialized+1;
    }

    int size() { return nextInsert; }
    
    Object get(int i) {
	if (i < nextInsert) return data[i];
	else return null;
    }

    void add(Object o) {
	if (data.length <= nextInsert) {
	    /* Grow the Array */
	    Object[] temp = new Object[data.length * 2];
	    System.arraycopy(data, 0, temp, 0, data.length);
	    this.data = temp;
	}
	data[nextInsert++] = o;
    }
}
