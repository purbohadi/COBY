import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NetFlow {

    private static int S, D, N, E;
    private static UndirectedWeightedGraph graph;

    public static void main(String[] args) throws FileNotFoundException {

	Scanner sc = new Scanner(new FileInputStream("inputNetFlow.txt"));

	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {

	    N = sc.nextInt();
	    S = sc.nextInt() - 1;
	    D = sc.nextInt() - 1;
	    E = sc.nextInt();

	    graph = new UndirectedWeightedGraph(N);

	    for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
		    graph.addEdge(i, j, 0);
		}
	    }

	    for (int i = 0; i < E; i++) {

		int u = sc.nextInt() - 1;
		int v = sc.nextInt() - 1;
		int w = sc.nextInt();
		int preWeight=graph.getWeight(u, v);
		if (preWeight > 0) {
		    graph.addEdge(u, v, preWeight+w);
		}else{
		    graph.addEdge(u, v, w);
		}
	    }

	    System.out.println("#" + (test_case + 1) + " "
		    + FordFulkerson(S, D));

	}

    }

    public static int FordFulkerson(int S, int D) {
	int u, v;
	int[][] rGraph = new int[N][N];
	for (u = 0; u < N; u++)
	    for (v = 0; v < N; v++)
		rGraph[u][v] = graph.getWeight(u, v);
	int[] parent = new int[N];
	int maxFlow = 0;
	while (BFS(rGraph, S, D, parent)) {
	    int path_flow = Integer.MAX_VALUE;
	    for (v = D; v != S; v = parent[v]) {
		u = parent[v];
		path_flow = Math.min(path_flow, rGraph[u][v]);
	    }
	    for (v = D; v != S; v = parent[v]) {
		u = parent[v];
		rGraph[u][v] -= path_flow;
		rGraph[v][u] += path_flow;
	    }
	    maxFlow += path_flow;
	}
	return maxFlow;
    }

    public static boolean BFS(int[][] rGraph, int S, int D, int[] parent) {
	boolean[] visited = new boolean[N];
	Queue q = new Queue(N);
	q.insert(S);
	visited[S] = true;
	parent[S] = -1;
	while (!q.isEmpty()) {
	    int u = q.peek();
	    q.remove();
	    for (int v = 0; v < N; v++) {
		if (!visited[v] && rGraph[u][v] > 0) {
		    q.insert(v);
		    parent[v] = u;
		    visited[v] = true;
		}
	    }
	}
	return (visited[D]);
    }

}
