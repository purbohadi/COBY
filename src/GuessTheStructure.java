import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class GuessTheStructure {

    private static int n;
    private static String[] ds = { "stack", "queue", "priority queue",
	    "impossible", "not sure" };
    private static String Answer;
    private static int[] bag;

    public static void main(String[] args) throws FileNotFoundException {

	Scanner sc = new Scanner(new FileInputStream(
		"inputGuessTheStructure.txt"));

	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {

	    n = sc.nextInt();
	    Answer = ds[4];
	    bag = new int[n];

	    for (int i = 0; i < n; i++) {
		sc.nextLine();
		if (sc.nextInt() == 1) {
		    bag[i] = sc.nextInt();
		} else {
		    if (sc.nextInt() == bag[i - 1]) {
			Answer = ds[0];
		    }
		}
	    }

	    System.out.println("#" + (test_case + 1) + " " + Answer);

	}
    }
    
    public class FixedQueue implements Queue {

	    private Object[] array;
	    private int size = 0;
	    private int head = 0; // index of the current front item, if one exists
	    private int tail = 0; // index of next item to be added

	    public FixedQueue(int capacity) {
	        array = new Object[capacity];
	    }

	    public void enqueue(Object item) {
	        if (size == array.length) {
	            throw new IllegalStateException("Cannot add to full queue");
	        }
	        array[tail] = item;
	        tail = (tail + 1) % array.length;
	        size++;
	    }

	    public Object dequeue() {
	        if (size == 0) {
	            throw new NoSuchElementException("Cannot remove from empty queue");
	        }
	        Object item = array[head];
	        array[head] = null;
	        head = (head + 1) % array.length;
	        size--;
	        return item;
	    }

	    public Object peek() {
	        if (size == 0) {
	            throw new NoSuchElementException("Cannot peek into empty queue");
	        }
	        return array[size - 1];
	    }

	    public boolean isEmpty() {
	        return size == 0;
	    }

	    public int size() {
	        return size;
	    }

	    @Override
	    public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	    }

	    @Override
	    public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	    }

	    @Override
	    public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	    }

	    @Override
	    public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public void clear() {
		// TODO Auto-generated method stub
		
	    }

	    @Override
	    public boolean add(Object e) {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public boolean offer(Object e) {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public Object remove() {
		// TODO Auto-generated method stub
		return null;
	    }

	    @Override
	    public Object poll() {
		// TODO Auto-generated method stub
		return null;
	    }

	    @Override
	    public Object element() {
		// TODO Auto-generated method stub
		return null;
	    }
    }

}
