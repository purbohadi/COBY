/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */

public class Main {
    private static int time = 1;

    private static void doDFS(Graph g, int u, int[] desc, int[] low,
	    ArrayList<PairNode> bridge, int[] parent) {
	if (g == null)
	    return;
	desc[u] = low[u] = time++;
	int size = g.adj[u].size();
	int i = 0, v = -1;
	for (i = 0; i < size; i++) {
	    v = (int) g.adj[u].get(i);
	    if (desc[v] == -1) {
		parent[v] = u;
		doDFS(g, v, desc, low, bridge, parent);
		low[u] = Math.min(low[u], low[v]);
		if (low[v] > desc[u])
		    bridge.add(new PairNode(u, v));
	    } else if (v != parent[u])
		low[u] = Math.min(low[u], desc[v]);
	}
    }

    private static ArrayList<PairNode> getBridge(Graph g) {
	if (g == null)
	    return null;
	int[] parent = new int[g.V];
	int[] desc = new int[g.V];
	int[] low = new int[g.V];
	ArrayList<PairNode> bridge = new ArrayList<PairNode>();
	Arrays.fill(parent, -1);
	Arrays.fill(desc, -1);
	Arrays.fill(low, -1);
	doDFS(g, 0, desc, low, bridge, parent);
	return bridge;
    }

    public static void main(String[] args) {
	int V = 10;
	Graph g = new Graph(V);
	g.addEdge(0, 1);
	g.addEdge(1, 2);
	g.addEdge(1, 0);
	g.addEdge(1, 3);
	g.addEdge(2, 1);
	g.addEdge(2, 3);
	g.addEdge(3, 2);
	g.addEdge(3, 4);
	g.addEdge(4, 3);
	g.addEdge(7, 6);
	g.addEdge(6, 7);
	g.addEdge(5, 0);

	ArrayList<PairNode> bridge = getBridge(g);
	if (bridge == null)
	    return;
	System.out.println(bridge);
    }
}

class Graph {

    int V;
    ArrayList<Integer> adj[];

    public Graph(int V) {
	this.V = V;
	adj = new ArrayList[V];
	for (int i = 0; i < V; i++)
	    adj[i] = new ArrayList<Integer>();
    }

    public void addEdge(int u, int v) {
	adj[u].add(v);
    }

    void PrintGraph() {
	System.out.println("No of nodes: " + V);
	for (int i = 0; i < V; i++) {
	    System.out.println(i + " -- >> " + adj[i]);
	}
    }
}

class PairNode {
    int u;
    int v;

    public PairNode(int u, int v) {
	this.u = u;
	this.v = v;
    }

    public String toString() {
	return u + " -- >> " + v;
    }
}