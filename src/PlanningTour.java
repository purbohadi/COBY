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

	    path = new int[N];

	    for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
		    graph.removeEdge(i, j);
		}
	    }

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
		graph.addEdge(u, v);
	    }
	    findMaximumTour();
	}
    }

    public static void findMaximumTour() {
	int[][] dp = new int[N][N];
	dp[0][0] = 1;
	for (int i = 0; i < N; i++) {
	    for (int j = i; j < N; j++) {
		int count = 0;
		for (int k = j; k >= 0; k--) {
		    if (graph.isEdge(k, j) && dp[i][k] > 0) {
			int temp = dp[i][k];
			if (i != j) {
			    temp++;
			}
			count = Math.max(temp, count);
		    } else if (i == j && dp[0][i] > count) {
			count = dp[0][i];
		    }
		}
		dp[i][j] = dp[j][i] = count;
	    }
	}
	System.out.println(dp[N - 1][N - 1]);

    }

    public static void printSolution(int[] path) {
	System.out.print(path[0] + " ");
	Answer = 0;
	for (int i = 1; i < N; i++) {
	    if (path[i] == 0) {
		break;
	    }
	    // Answer++;
	    System.out.print(path[i] + " ");
	}
	// System.out.println(Answer + 1);
    }
}
