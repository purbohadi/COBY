import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TopologicalSort {
    
    public static int Answer,N;
    public static String nodes[], edges[];
    public static DirectedGraph graph;
    
    
    public static void main(String[] args) throws FileNotFoundException {
	
	Scanner sc = new Scanner(new FileInputStream("inputTopologicalSort.txt"));
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    
	    sc.nextLine();
	    nodes = sc.nextLine().split(" ");
	    edges = sc.nextLine().split(" ");
	    N=nodes.length;
	    graph=new DirectedGraph(N);
	    for (int i = 0; i < edges.length/2; i++) {
//		graph.addEdge(edges[i]-"a", edges[i]-'a');
	    }
	    printOrders(nodes, N, edges);
	}
    }
    
    public static void printOrders(String[] words, int n, String[] edges){
	for (int i = 0; i < edges.length; i++) {
	    
	}
    }
    
}

class DirectedGraph {
    private boolean adjacencyMatrix[][];
    private int vertexCount;

    public DirectedGraph(int vCount) {
	this.vertexCount = vCount;
	this.adjacencyMatrix = new boolean[vertexCount][vertexCount];
    }

    public void addEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    this.adjacencyMatrix[i][j] = true;
	}
    }

    public void removeEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    this.adjacencyMatrix[i][j] = false;
	}
    }

    public boolean isEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount)
	    return adjacencyMatrix[i][j];
	else
	    return false;
    }

}