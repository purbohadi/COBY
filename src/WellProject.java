import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class WellProject {

    private static int N, Answer;
    private static WeightedGraph graph;
    
    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputWellProject.txt"));
	
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    
	    Answer=0;
	    N=sc.nextInt();
	    graph= new WeightedGraph(N);
	    for (int i = 0; i < N; i++) {
		sc.nextLine();
		for (int j = 0; j < N; j++) {
		    graph.addEdge(i, j, sc.nextInt());
		}
	    }
	    
	    PrimsMST(test_case+1);
	}
	
    }
    
    public static void PrimsMST(int test_case) {
	int[] parent = new int[N];
	int[] key = new int[N];
	boolean[] mstSet = new boolean[N];
	
	for (int i = 0; i < N; i++) {
	    key[i]=Integer.MAX_VALUE;
	    mstSet[i]=false;
	}
	
	key[0]=0;// init key & parent
	parent[0]=-1;
	
	for (int count = 0; count < N-1; count++) {
	    int u = minimumKey(key, mstSet);
	    mstSet[u]=true;
	    
	    for (int v = 0; v < N; v++) {
		if (graph.getWeight(u, v)!=0
			&&!mstSet[v]
				&& graph.getWeight(u, v)<key[v]) {
		    parent[v]=u;
		    key[v]=graph.getWeight(u, v);
		}
	    }
	}
	printMST(parent,test_case);
	
    }
    public static int minimumKey(int[] key, boolean[] mstSet){
	
	int min= Integer.MAX_VALUE;
	int min_idx = 0;
	
	for (int v = 0; v < N; v++) {
	    if (!mstSet[v]
		    &&key[v]<=min) {
		min=key[v];
		min_idx=v;
	    }
	}
	
	return min_idx;
    }

    public static void printMST(int[] parent, int test_case){
	
//	System.out.println("#"+test_case);
//	for (int i = 0; i < parent.length; i++) {
//	    System.out.println(parent[i]);
//	}
	
	for (int i = 1; i < N; i++) {
	    Answer+=graph.getWeight(i, parent[i]);
	}
	System.out.println("#"+test_case+" "+Answer);
    }

}
