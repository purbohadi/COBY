import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HamiltonianCycle {

    public static int N, pathCount;
    public static int[] path;
    public static BidirectionalGraph graph;

    public static void main(String[] args) throws FileNotFoundException {

	Scanner sc = new Scanner(new FileInputStream(
		"inputHamiltonianCycle.txt"));

	int T = sc.nextInt();
	sc.nextLine();

	for (int test_case = 0; test_case < T; test_case++) {

	    while (sc.hasNextLine()) {
		N = sc.nextInt();
		graph = new BidirectionalGraph(N);
		sc.nextLine();
		while (sc.hasNextLine()) {
		    int u = sc.nextInt();
		    if (u != 0) {
			int v = sc.nextInt();
			graph.addEdge(u-1, v-1);
		    } else {
			break;
		    }
		}
		break;
	    }
	    if (isHC()) {
		System.out.println("#" + (test_case + 1) + " " + "Y");
	    } else {
		System.out.println("#" + (test_case + 1) + " " + "N");
	    }
	}
    }
    
    
    public static boolean isHC(){
	
	path = new int[N];
	boolean[] visited = new boolean[N];
	int pos = 1;
	int source = 0;
	path[0]=source;
	visited[0]=true;
	
	if (isHcUtil(visited, source, path, pos)) {
	    return true;
	}
	
	return false;
    }
    
    
    public static boolean isHcUtil(boolean[] visited, int source, int[] path, int pos){
	
	if (pos==N) {
	    return graph.isEdge(path[pos-1], 0);
	}
	
	for (int v = 0; v < N; v++) {
	    if (!visited[v]&&graph.isEdge(source, v)) {
		visited[v]=true;
		path[pos]=v;
		
		if (isHcUtil(visited, v, path, pos+1)) {
		    return true;
		}
		
		visited[v]=false;
	    }
	}
	
	return false;
    }
    
    public static void printSolution(int[] path){
	for (int i = 0; i < N; i++) {
	    System.out.print(path[i]+" ");
	}
	System.out.println(path[0]);
    }
}

class BidirectionalGraph {
    private boolean adjacencyMatrix[][];
    private int vertexCount;

    public BidirectionalGraph(int vCount) {
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
