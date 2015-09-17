import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SkyMap {
    
    private static int N;
    private static UndirectedWGraph graph;
    
    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputSkyMap.txt"));
	
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    
	    N = sc.nextInt();
	    for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
		    
		}
	    }
	    
	}
	
    }

}

class UndirectedWGraph {
    public int[][] adjacencyMatrix;
    public int verticesCount;

    public UndirectedWGraph(int vCount) {
	verticesCount = vCount;
	adjacencyMatrix = new int[vCount][vCount];
    }

    public void addEdge(int u, int v) {
	if (u >= 0 && u < verticesCount && v >= 0 && v < verticesCount) {
	    adjacencyMatrix[u][v] =1;
	    adjacencyMatrix[v][u] =1;
	}
    }

    public void removeEdge(int u, int v) {
	if (u >= 0 && u < verticesCount && v >= 0 && v < verticesCount) {
	    adjacencyMatrix[u][v] =0;
	    adjacencyMatrix[v][u] =0;
	}
    }
    
    public boolean isEdge(int u, int v){
	return (adjacencyMatrix[u][v]==1);
    }
    
    public int getWeight(int u, int v) {
	return adjacencyMatrix[u][v];
    }
}