import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Solution {

    private boolean isBipartite; // is the graph bipartite?
    private boolean[] color; // color[v] gives vertices on one side of
			     // bipartition
    private boolean[] marked; // marked[v] = true if v has been visited in DFS
    private int[] edgeTo; // edgeTo[v] = last edge on path to v
    private Stack<Integer> cycle; // odd-length cycle
    
    
    public static void main(String[] args) {
	Graph graph = new Graph(8);
	graph.addEdge(1, 2);
	graph.addEdge(1, 4);
	graph.addEdge(1, 7);
	graph.addEdge(2, 5);
	graph.addEdge(2, 6);
	graph.addEdge(6, 3);
	graph.addEdge(3, 8);
	graph.addEdge(6, 4);
	
	int i,j,flag=0;
	
    }

    public class Bipartite {
	private boolean isBipartite; // is the graph bipartite?
	private boolean[] color; // color[v] gives vertices on one side of
				 // bipartition
	private boolean[] marked; // marked[v] = true if v has been visited in
				  // DFS
	private int[] edgeTo; // edgeTo[v] = last edge on path to v
	private Stack<Integer> cycle; // odd-length cycle

	/**
	 * Determines whether an undirected graph is bipartite and finds either
	 * a bipartition or an odd-length cycle.
	 * 
	 * @param G
	 *            the graph
	 */
	public Bipartite(Graph G) {
	    isBipartite = true;
	    color = new boolean[G.V()];
	    marked = new boolean[G.V()];
	    edgeTo = new int[G.V()];

	    for (int v = 0; v < G.V(); v++) {
		if (!marked[v]) {
		    dfs(G, v);
		}
	    }
	    assert check(G);
	}

	private void dfs(Graph G, int v) {
	    marked[v] = true;
	    for (int w : G.adj(v)) {

		// short circuit if odd-length cycle found
		if (cycle != null)
		    return;

		// found uncolored vertex, so recur
		if (!marked[w]) {
		    edgeTo[w] = v;
		    color[w] = !color[v];
		    dfs(G, w);
		}

		// if v-w create an odd-length cycle, find it
		else if (color[w] == color[v]) {
		    isBipartite = false;
		    cycle = new Stack<Integer>();
		    cycle.push(w); // don't need this unless you want to include
				   // start vertex twice
		    for (int x = v; x != w; x = edgeTo[x]) {
			cycle.push(x);
		    }
		    cycle.push(w);
		}
	    }
	}

	/**
	 * Is the graph bipartite?
	 * 
	 * @return <tt>true</tt> if the graph is bipartite, <tt>false</tt>
	 *         otherwise
	 */
	public boolean isBipartite() {
	    return isBipartite;
	}

	/**
	 * Returns the side of the bipartite that vertex <tt>v</tt> is on. param
	 * v the vertex
	 * 
	 * @return the side of the bipartition that vertex <tt>v</tt> is on; two
	 *         vertices are in the same side of the bipartition if and only
	 *         if they have the same color
	 * @throws UnsupportedOperationException
	 *             if this method is called when the graph is not bipartite
	 */
	public boolean color(int v) {
	    if (!isBipartite)
		throw new UnsupportedOperationException(
			"Graph is not bipartite");
	    return color[v];
	}

	/**
	 * Returns an odd-length cycle if the graph is not bipartite, and
	 * <tt>null</tt> otherwise.
	 * 
	 * @return an odd-length cycle (as an iterable) if the graph is not
	 *         bipartite (and hence has an odd-length cycle), and
	 *         <tt>null</tt> otherwise
	 */
	public Iterable<Integer> oddCycle() {
	    return cycle;
	}

	private boolean check(Graph G) {
	    // graph is bipartite
	    if (isBipartite) {
		for (int v = 0; v < G.V(); v++) {
		    for (int w : G.adj(v)) {
			if (color[v] == color[w]) {
			    System.err
				    .printf("edge %d-%d with %d and %d in same side of bipartition\n",
					    v, w, v, w);
			    return false;
			}
		    }
		}
	    }

	    // graph has an odd-length cycle
	    else {
		// verify cycle
		int first = -1, last = -1;
		for (int v : oddCycle()) {
		    if (first == -1)
			first = v;
		    last = v;
		}
		if (first != last) {
		    System.err.printf(
			    "cycle begins with %d and ends with %d\n", first,
			    last);
		    return false;
		}
	    }

	    return true;
	}

    }

    public static class Graph {
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

    public static class Bag<Item> implements Iterable<Item> {
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