import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SendingEmail {

    public static int n, m, S, D, Answer;
    public static WeightedGraph graph;

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputSendingEmail.txt"));
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    Answer = 0;
	    n = sc.nextInt();
	    m = sc.nextInt();
	    S = sc.nextInt();
	    D = sc.nextInt();

	    graph = new WeightedGraph(n);

	    for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
		    graph.addEdge(i, j, 0);
		}
	    }

	    for (int i = 0; i < m; i++) {
		graph.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
	    }
	    
	    if (test_case==24) {
//		break;
	    }

	    countMSWithDjikstra(graph, S, D, (test_case + 1));

	}
    }

    public static void countMSWithDjikstra(WeightedGraph graph, int src,
	    int dest, int testcase) {

	int[] distance = new int[n];
	boolean[] shortPath = new boolean[n];

	for (int i = 0; i < n; i++) {
	    distance[i] = Integer.MAX_VALUE;
	    shortPath[i] = false;
	}

	distance[src] = 0;

	for (int count = 0; count < n - 1; count++) {
	    int u = minDistance(distance, shortPath);
	    if (u == D) {
		break;
	    }

	    shortPath[u] = true;
	    for (int v = 0; v < n; v++) {
		if (!shortPath[v] && graph.getWeight(u, v) > 0
			&& distance[u] != Integer.MAX_VALUE
			&& (distance[u] + graph.getWeight(u, v)) < distance[v]) {

		    distance[v] = distance[u] + graph.getWeight(u, v);
		}
	    }
	}
	// System.out.println("Vertex         Distance");
	// for (int i = 0; i < distance.length; i++) {
	// System.out.println(i+"      "+distance[i]);
	// }

	if (distance[D] == Integer.MAX_VALUE) {
	    System.out.println("#" + testcase + " unreacheable");
	} else {
	    System.out.println("#" + testcase + " " + distance[D]);
	}

    }

    public static int minDistance(int[] dist, boolean[] shortPath) {
	int min = Integer.MAX_VALUE;
	int min_index = 0;

	for (int v = 0; v < n; v++) {
	    if (!shortPath[v] && dist[v] <= min) {
		min = dist[v];
		min_index = v;
	    }
	}
	return min_index;
    }
}

class WeightedGraph {
    private int adjacencyMatrix[][];
    private int vertexCount;

    public WeightedGraph(int vCount) {
	this.vertexCount = vCount;
	this.adjacencyMatrix = new int[vertexCount][vertexCount];
    }

    public void addEdge(int i, int j, int weight) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    adjacencyMatrix[i][j] = weight;
	    adjacencyMatrix[j][i] = weight;
	}
    }

    public void removeEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    // this.adjacencyMatrix[i][j] = weight;
	    // adjacencyMatrix[j][i] = false;
	}
    }

    public int getWeight(int i, int j) {
	return adjacencyMatrix[i][j];
    }

}
