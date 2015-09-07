import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Commandos {

    public static int N, R, Answer;
    public static UndirectedWeightedGraph graph;

    public static void main(String[] args) throws FileNotFoundException {

	Scanner sc = new Scanner(new FileInputStream("inputCommandos.txt"));

	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {

	    N = sc.nextInt();
	    R = sc.nextInt();

	    graph = new UndirectedWeightedGraph(N);

	    for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
		    graph.addEdge(i, j, Integer.MAX_VALUE);
		}
	    }

	    for (int i = 0; i < R; i++) {
		sc.nextLine();
		graph.addEdge(sc.nextInt(), sc.nextInt(), 1);
	    }

	    sc.nextLine();
	    int S = sc.nextInt();
	    int D = sc.nextInt();

	    int[][] distances = floydWarshell();

	    Answer = distances[S][D];
	    
	    if (Answer==Integer.MAX_VALUE) {
		Answer=1;
	    }

	    for (int i = 1; i < N; i++) {
		int count = 0;

		if (distances[S][i] != Integer.MAX_VALUE) {
		    count += distances[S][i];
		}
		if (distances[i][D] != Integer.MAX_VALUE) {
		    count += distances[i][D];
		}
		Answer = Math.max(Answer, count);
	    }
	    System.out.println("#" + (test_case + 1) + " " + Answer);
	}

    }

    public static int[][] floydWarshell() {
	int[][] dist = new int[N][N];
	for (int i = 0; i < N; i++) 
	    for (int j = 0; j < N; j++)
		dist[i][j] = graph.getWeight(i, j);
	    
	for (int via = 0; via < N; via++) 
	    for (int from = 0; from < N; from++) 
		for (int to = 0; to < N; to++) 
		    if (dist[from][via] != Integer.MAX_VALUE
			    && dist[via][to] != Integer.MAX_VALUE && from != to) {
			if (dist[from][to] > dist[from][via] + dist[via][to])
			    dist[from][to] = dist[from][via] + dist[via][to];
		    }
	return dist;
    }

    public static void printAllPath(int u, int d, boolean[] visited,
	    int[] path, int idx) {

	visited[u] = true;
	path[idx] = u;
	idx++;

	if (u == d) {
	    for (int i = 0; i < idx; i++) {
		System.out.print(path[i] + " ");
	    }
	    System.out.println();
	} else {
	    for (int i = 0; i < N; ++i) {
		if (graph.isEdge(u, i) > 0 && !visited[i]) {
		    printAllPath(i, d, visited, path, idx);
		}
	    }
	}

	idx--;
	visited[u] = false;
    }

}

class UndirectedWeightedGraph {
    private int adjacencyMatrix[][];
    private int vertexCount;

    public UndirectedWeightedGraph(int vCount) {
	this.vertexCount = vCount;
	this.adjacencyMatrix = new int[vertexCount][vertexCount];
    }

    public void addEdge(int i, int j, int w) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    this.adjacencyMatrix[i][j] = w;
	    adjacencyMatrix[j][i] = w;
	}
    }

    public void removeEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    this.adjacencyMatrix[i][j] = 0;
	    adjacencyMatrix[j][i] = 0;
	}
    }

    public int getWeight(int i, int j) {
	return this.adjacencyMatrix[i][j];
    }

    public int isEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount)
	    return adjacencyMatrix[i][j];
	else
	    return 0;
    }

}