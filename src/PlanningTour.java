import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlanningTour {

    static int N, M, Answer;
    static BidirectionalGraph graph;
    static String[] cities;
    static int[] path;

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputPlanningTour.txt"));

	int T = sc.nextInt();

	for (int test_case = 0; test_case < T; test_case++) {

	    Answer = 0;
	    N = sc.nextInt();
	    M = sc.nextInt();

	    graph = new BidirectionalGraph(N);
	    cities = new String[N];
	    for (int i = 0; i < N; i++) {
		cities[i] = sc.next();
	    }

	    sc.nextLine();
	    for (int i = 0; i < M; i++) {
		String line = sc.nextLine();
		String[] edges = line.split(" ");
		int u = -1, v = -1;
		for (int j = 0; j < N && (u < 0 || v < 0); j++) {
		    if (u < 0 && edges[0].equals(cities[j])) {
			u = j;
		    }
		    if (v < 0 && edges[1].equals(cities[j])) {
			v = j;
		    }
		}
		// if (u < v) {
		graph.addEdge(u, v);
		// } else {
		// graph.addEdge(v, u);
		// }
	    }
	    isHC();
//	     System.out.println(Answer);
	}
    }

    public static boolean isHC() {

	path = new int[N];
	boolean[] visited = new boolean[N];
	int pos = 1;
	int source = 0;
	path[0] = source;
	visited[0] = true;

	if (isHcUtil(visited, source, path, pos)) {
	    printSolution(path);
	    return true;
	}
	path[Answer] = 0;
	printSolution(path);
	return false;
    }

    public static boolean isHcUtil(boolean[] visited, int source, int[] path,
	    int pos) {

	if (pos == N) {
	    return graph.isEdge(path[pos - 1], 0);
	}

	for (int v = 0; v < N; v++) {
	    if (!visited[v] && graph.isEdge(source, v)) {
		visited[v] = true;
		path[pos] = v;
		Answer = pos;
		if (isHcUtil(visited, v, path, pos + 1)) {
		    return true;
		}

		visited[v] = false;
	    }
	}

	return false;
    }

    public static void printSolution(int[] path) {
//	System.out.print(path[0] + " ");
	Answer=0;
	for (int i = 1; i < N; i++) {
	    if (path[i] == 0) {
		break;
	    }
	    Answer++;
//	    System.out.print(path[i] + " ");
	}
	System.out.println(Answer+1);
    }
}
