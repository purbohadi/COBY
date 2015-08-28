import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Commandos {

    public static int N, R, Answer;
    public static UndirectedGraph graph;

    public static void main(String[] args) throws FileNotFoundException {

	Scanner sc = new Scanner(new FileInputStream("inputCommandos.txt"));

	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {

	    Answer = 0;
	    N = sc.nextInt();
	    R = sc.nextInt();

	    graph = new UndirectedGraph(N);

	    for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
		    graph.addEdge(i, j, 0);
		}
	    }

	    for (int i = 0; i < R; i++) {
		sc.nextLine();
		graph.addEdge(sc.nextInt(), sc.nextInt());
	    }

	    sc.nextLine();
	    int S = sc.nextInt();
	    int D = sc.nextInt();
	    if (S < D) {
		floydWarshell(graph, S, D, (test_case + 1));
	    }else{
		floydWarshell(graph, D, S, (test_case+1));
	    }

	}

    }

    public static void floydWarshell(UndirectedGraph graph, int S, int D, int testCase){
	int[][] dist =new int[N][N];
	
	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
		dist[i][j]=graph.getWeight(i, j);
	    }
	}
	
	for (int from = 0; from < N; from++) {
	    for (int to = 0; to < N; to++) {
		for (int via = 0; via < N; via++) {
		    if (dist[via][to]+dist[from][via] < dist[from][to]) {
			dist[from][to]=dist[from][via]+dist[via][to];
		    }
		}
	    }
	}
	
	for (int i = S; i < D; i++) {
	    Answer+=dist[i][i+1];
	}
	
	System.out.println("#"+testCase+" "+Answer);
	
	printSolution(dist);
    }

    public static void printSolution(int[][] dist) {
	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
		System.out.print(dist[i][j]);
	    }
	    System.out.println();
	}
    }
}

class UndirectedGraph {
    private int adjacencyMatrix[][];
    private int vertexCount;

    public UndirectedGraph(int vCount) {
	this.vertexCount = vCount;
	this.adjacencyMatrix = new int[vertexCount][vertexCount];
    }

    public void addEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    this.adjacencyMatrix[i][j] = 1;
	    adjacencyMatrix[j][i] = 1;
	}
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