import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DetectACycle {
    static int Answer, N, cycleCount;
    static BidirectionalGraph graph;
    static boolean[] marked;
    static boolean onStack[];

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
	    cycleCount=0;
	    while (sc.hasNextLine()) {
		N = sc.nextInt();
		graph = new BidirectionalGraph(N);
		marked= new boolean[N];
		onStack= new boolean[N];
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

	    if (hasCycle()) {
		System.out.println("Contain cycle "+cycleCount);
	    }else{
		System.out.println("No cycle");
	    }
	}
    }
    
    private static boolean hasCycle(){
	for (int i = 0; i < N; i++) {
	    cycleCount = countCycle(i);
	    if (cycleCount==1) {
		return true;
	    }
	}
	return false;
    }
    
    
    private static int countCycle(int v){
	marked[v]=true;
	onStack[v]=true;
	
	for (int i = 0; i < N; i++) {
	    if (graph.isEdge(v, i)) {
		if (!marked[i]) {
		    countCycle(i);
		}else if(onStack[i]){
		    cycleCount++;
		}
	    }
	}
	
	onStack[v]=false;
	
	return cycleCount;
    }
    
    

    public static boolean DFS(int v, boolean visited[], boolean reStack[]) {
	
	if (!visited[v]) {
	    visited[v]=true;
	    reStack[v]=true;
	    for (int i = 0; i < N; ++i) {
		if (graph.isEdge(v, i)) {
		    if ((!visited[i]&&DFS(i, visited, reStack))) {
			return true;
		    }else if(reStack[i]){
			cycleCount++;
			return true;
		    }
		}
	    }
	}
	reStack[v]=false;
	return false;
    }    
}
