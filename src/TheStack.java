import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class TheStack {

    private int[] stackArray;
    private int stackSize;
    private int topOfStack = -1;
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int count;      

    public TheStack(int size) {
	stackSize = size;
	stackArray = new int[stackSize];
	for (int i = 0; i < stackSize; i++) {
	    stackArray[i] = -1;
	}
    }

    public void push(int in) {
	if (topOfStack + 1 < stackSize) {
	    topOfStack++;
	    stackArray[topOfStack] = in;
	} else {
	    System.out.println("Stack Full!");
	}
	displayStack();
	System.out.println("PUSH " + in + " was added");
    }

    private int pop() {
	if (topOfStack >= 0) {
	    displayStack();
	    System.out.println("POP " + stackArray[topOfStack]
		    + " Was removed from stack");
	    stackArray[topOfStack] = -1;
	    return stackArray[topOfStack--];
	} else {
	    displayStack();
	    System.out.println("Stack is empty");
	    return -1;
	}
    }

    public void displayStack() {
	System.out
		.println("----------------------------------------------------------------------------------------------");
	for (int i = 0; i < stackSize; i++) {
	    if (stackArray[i] != -1) {
		System.out.print(" " +stackArray[i] + "  |  ");
	    }else{
		System.out.print("   |   ");
	    }
	}
	System.out.println();
	System.out
		.println("----------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
	TheStack theStack = new TheStack(10);
	theStack.push(10);
	theStack.push(8);
	theStack.push(5);
	theStack.push(3);
	theStack.pop();
	theStack.push(2);
	theStack.push(1);
	theStack.push(4);
	theStack.pop();
	theStack.push(6);
    }
    
    // depth first search from v
    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
    
    public class Graph {
 	private final int V;
 	private int E;
 	private Bag<Integer>[] adj;

 	/**
 	 * Initializes an empty graph with <tt>V</tt> vertices and 0 edges.
 	 * param V the number of vertices
 	 * 
 	 * @throws java.lang.IllegalArgumentException
 	 *             if <tt>V</tt> < 0
 	 */
 	public Graph(int V) {
 	    if (V < 0)
 		throw new IllegalArgumentException(
 			"Number of vertices must be nonnegative");
 	    this.V = V;
 	    this.E = 0;
 	    adj = (Bag<Integer>[]) new Bag[V];
 	    for (int v = 0; v < V; v++) {
 		adj[v] = new Bag<Integer>();
 	    }
 	}

 	/**
 	 * Initializes a new graph that is a deep copy of <tt>G</tt>.
 	 * 
 	 * @param G
 	 *            the graph to copy
 	 */
 	public Graph(Graph G) {
 	    this(G.V());
 	    this.E = G.E();
 	    for (int v = 0; v < G.V(); v++) {
 		// reverse so that adjacency list is in same order as original
 		Stack<Integer> reverse = new Stack<Integer>();
 		for (int w : G.adj[v]) {
 		    reverse.push(w);
 		}
 		for (int w : reverse) {
 		    adj[v].add(w);
 		}
 	    }
 	}

 	/**
 	 * Returns the number of vertices in the graph.
 	 * 
 	 * @return the number of vertices in the graph
 	 */
 	public int V() {
 	    return V;
 	}

 	/**
 	 * Returns the number of edges in the graph.
 	 * 
 	 * @return the number of edges in the graph
 	 */
 	public int E() {
 	    return E;
 	}

 	// throw an IndexOutOfBoundsException unless 0 <= v < V
 	private void validateVertex(int v) {
 	    if (v < 0 || v >= V)
 		throw new IndexOutOfBoundsException("vertex " + v
 			+ " is not between 0 and " + (V - 1));
 	}

 	/**
 	 * Adds the undirected edge v-w to the graph.
 	 * 
 	 * @param v
 	 *            one vertex in the edge
 	 * @param w
 	 *            the other vertex in the edge
 	 * @throws java.lang.IndexOutOfBoundsException
 	 *             unless both 0 <= v < V and 0 <= w < V
 	 */
 	public void addEdge(int v, int w) {
 	    validateVertex(v);
 	    validateVertex(w);
 	    E++;
 	    adj[v].add(w);
 	    adj[w].add(v);
 	}

 	/**
 	 * Returns the vertices adjacent to vertex <tt>v</tt>.
 	 * 
 	 * @return the vertices adjacent to vertex <tt>v</tt> as an Iterable
 	 * @param v
 	 *            the vertex
 	 * @throws java.lang.IndexOutOfBoundsException
 	 *             unless 0 <= v < V
 	 */
 	public Iterable<Integer> adj(int v) {
 	    validateVertex(v);
 	    return adj[v];
 	}

 	/**
 	 * Returns the degree of vertex <tt>v</tt>.
 	 * 
 	 * @return the degree of vertex <tt>v</tt>
 	 * @param v
 	 *            the vertex
 	 * @throws java.lang.IndexOutOfBoundsException
 	 *             unless 0 <= v < V
 	 */
 	public int degree(int v) {
 	    validateVertex(v);
 	    return adj[v].size();
 	}

 	/**
 	 * Returns a string representation of the graph. This method takes time
 	 * proportional to <em>E</em> + <em>V</em>.
 	 * 
 	 * @return the number of vertices <em>V</em>, followed by the number of
 	 *         edges <em>E</em>, followed by the <em>V</em> adjacency lists
 	 */
 	public String toString() {
 	    StringBuilder s = new StringBuilder();
 	    String NEWLINE = System.getProperty("line.separator");
 	    s.append(V + " vertices, " + E + " edges " + NEWLINE);
 	    for (int v = 0; v < V; v++) {
 		s.append(v + ": ");
 		for (int w : adj[v]) {
 		    s.append(w + " ");
 		}
 		s.append(NEWLINE);
 	    }
 	    return s.toString();
 	}

     }

     // helper linked list class
     public static class Node<Item> {
 	private Item item;
 	private Node<Item> next;
     }

     public class Bag<Item> implements Iterable<Item> {
 	private int N; // number of elements in bag
 	private Node<Item> first; // beginning of bag

 	/**
 	 * Initializes an empty bag.
 	 */
 	public Bag() {
 	    first = null;
 	    N = 0;
 	}

 	/**
 	 * Is this bag empty?
 	 * 
 	 * @return true if this bag is empty; false otherwise
 	 */
 	public boolean isEmpty() {
 	    return first == null;
 	}

 	/**
 	 * Returns the number of items in this bag.
 	 * 
 	 * @return the number of items in this bag
 	 */
 	public int size() {
 	    return N;
 	}

 	/**
 	 * Adds the item to this bag.
 	 * 
 	 * @param item
 	 *            the item to add to this bag
 	 */
 	public void add(Item item) {
 	    Node<Item> oldfirst = first;
 	    first = new Node<Item>();
 	    first.item = item;
 	    first.next = oldfirst;
 	    N++;
 	}

 	/**
 	 * Returns an iterator that iterates over the items in the bag in
 	 * arbitrary order.
 	 * 
 	 * @return an iterator that iterates over the items in the bag in
 	 *         arbitrary order
 	 */
 	public Iterator<Item> iterator() {
 	    return new ListIterator<Item>(first);
 	}

 	// an iterator, doesn't implement remove() since it's optional
 	private class ListIterator<Item> implements Iterator<Item> {
 	    private Node<Item> current;

 	    public ListIterator(Node<Item> first) {
 		current = first;
 	    }

 	    public boolean hasNext() {
 		return current != null;
 	    }

 	    public void remove() {
 		throw new UnsupportedOperationException();
 	    }

 	    public Item next() {
 		if (!hasNext())
 		    throw new NoSuchElementException();
 		Item item = current.item;
 		current = current.next;
 		return item;
 	    }
 	}

     }    
}
