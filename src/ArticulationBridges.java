import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArticulationBridges {

    public static int N, countcl;
    public static UndirectionalGraph graph;
    public static int[] disc, low, parent;
    private static int time = 1;
    private static boolean[] visited;

    public static void main(String[] args) throws FileNotFoundException {

	Scanner sc = new Scanner(new FileInputStream(
		"inputArticulationBridges.txt"));

	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    sc.nextLine();
	    N = sc.nextInt();
	    disc = new int[N];
	    low = new int[N];
	    parent = new int[N];
	    visited = new boolean[N];
	    graph = new UndirectionalGraph(N);
	    countcl = 0;
	    Pair[] bridge = new Pair[N];
	    for (int i = 0; i < N; i++) {
		int j = sc.nextInt();
		int edges = sc.nextInt();
		for (int k = 0; k < edges; k++) {
		    int v = sc.nextInt();
		    graph.addEdge(j, v);
		}
	    }

	    for (int i = 0; i < N; i++) {
		parent[i] = -1;
		disc[i] = -1;
		low[i] = -1;
		visited[i] = false;
	    }

	    DFS(visited, 0, disc, low, bridge, parent);

	    for (int i = 0; i < N; i++) {
		if (!visited[i]) {
		    DFS(visited, i, disc, low, bridge, parent);
		}
	    }

	    System.out.println("#" + (test_case + 1));
	    System.out.println(countcl + " critical links");

	    Pair[] answers = new Pair[countcl];

	    int count = 0;
	    for (int i = 0; i < bridge.length; i++) {
		if (bridge[i] != null) {
		    answers[count] = bridge[i];
		    count++;
		    // System.out.println(bridge[i]);
		}
	    }

	    bubbleSortArr(answers);

	    for (int i = 0; i < answers.length; i++) {
		System.out.println(answers[i]);
	    }

	}

    }

    public static void DFS(boolean[] visited, int u, int[] disc, int[] low,
	    Pair[] bridge, int[] parent) {

	visited[u] = true;

	disc[u] = low[u] = time++;

	int v = -1;
	for (int i = 0; i < N; i++) {

	    if (graph.isEdge(u, i)) {
		v = i;
		if (disc[v] == -1) {
		    parent[v] = u;
		    DFS(visited, v, disc, low, bridge, parent);
		    low[u] = minimum(low[u], low[v]);
		    if (low[v] > disc[u]) {
			bridge[i] = new Pair(u, v);
			countcl++;
		    }

		    if (low[v] >= disc[u] && low[u] >= disc[v]) {
			bridge[i] = new Pair(u, v);
			countcl++;
		    }

		} else if (v != parent[u]) {
		    low[u] = minimum(low[u], disc[v]);
		}
	    }
	}

    }

    public static int minimum(int a, int b) {
	return a > b ? b : a;
    }

    public static Pair[] bubbleSortArr(Pair[] inArr) {

	Pair temp;
	for (int i = 0; i < inArr.length - 1; i++) {
	    for (int j = 0; j < inArr.length - 1; j++) {
		int u1 = inArr[j].getU();
		int u2 = inArr[j + 1].getU();
		if (u1 > u2) {
		    temp = inArr[j];
		    inArr[j] = inArr[j + 1];
		    inArr[j + 1] = temp;
		} else if (u1 == u2) {
		    u1 = inArr[j].getV();
		    u2 = inArr[j + 1].getV();
		    if (u1 > u2) {
			temp = inArr[j];
			inArr[j] = inArr[j + 1];
			inArr[j + 1] = temp;
		    }

		}
	    }

	}
	return inArr;
    }

}

class UndirectionalGraph {
    public boolean adjacencyMatrix[][];
    private int vertexCount;

    public UndirectionalGraph(int vCount) {
	this.vertexCount = vCount;
	this.adjacencyMatrix = new boolean[vertexCount][vertexCount];
    }

    public void addEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    this.adjacencyMatrix[i][j] = true;
	    adjacencyMatrix[j][i] = true;
	}
    }

    public void removeEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    this.adjacencyMatrix[i][j] = false;
	    adjacencyMatrix[j][i] = false;
	}
    }

    public boolean isEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount)
	    return adjacencyMatrix[i][j];
	else
	    return false;
    }

}

class Pair {
    int u;
    int v;

    public Pair(int u, int v) {
	if (u < v) {
	    this.u = u;
	    this.v = v;
	} else {
	    this.u = v;
	    this.v = u;
	}
    }

    public String toString() {
	return u + " - " + v;
    }

    public int getU() {
	return u;
    }

    public int getV() {
	return v;
    }

}