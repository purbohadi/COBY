import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DetectACycle {
    static int Answer, N;
    static BidirectionalGraph graph;

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream(
		"inputDetectACycle.txt"));

	int T = sc.nextInt();
	sc.nextLine();
	for (int test_case = 0; test_case < T; test_case++) {

	    // ///////////////////////////////////////////////////////////////////////////////////////////
	    /*
	     * Implement your algorithm here. The answer to the case will be
	     * stored in variable Answer.
	     */
	    // ///////////////////////////////////////////////////////////////////////////////////////////
	    // Answer = 0;
	    while (sc.hasNextLine()) {
		N = sc.nextInt();
		graph = new BidirectionalGraph(N);
		sc.nextLine();
		while (sc.hasNextLine()) {
		    String line = sc.nextLine();
		    if (!line.equals("")) {
			String edge = line;
			int u = edge.charAt(0)-'0';
			int v = edge.charAt(1)-'0';
			graph.addEdge(u, v);
		    } else {
			break;
		    }
		}
		break;
	    }

	    if (isCyclic()) {
		System.out.println("Contain cycle");
	    }else{
		System.out.println("No cycle");
	    }
	}
    }

    public static boolean isCyclic() {

	boolean[] visited = new boolean[N];
	boolean[] reStack = new boolean[N];
	
	for (int i = 0; i < N; i++) {
	    visited[i]=false;
	    reStack[i]=false;
	}
	
	for (int i = 0; i < N; i++) {
	    if (DFS(i, visited, reStack)) {
		return true;
	    }
	}
	
	return false;
    }

    
    public static boolean DFS(int v, boolean visited[], boolean reStack[]) {
	
	if (!visited[v]) {
	    visited[v]=true;
	    reStack[v]=true;
	    for (int i = 0; i < N; ++i) {
		if (graph.isEdge(v, i)) {
		    if ((!visited[i]&&DFS(i, visited, reStack))||reStack[i]) {
			return true;
		    }
		}
	    }
	}
	reStack[v]=false;
	return false;
    }    
}
