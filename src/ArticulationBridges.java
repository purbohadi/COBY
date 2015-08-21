import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArticulationBridges {

    public static int N, time, count, max=1100;
    public static BidirectionalGraph graph;
    public static int[][] tree;
    public static int[] disc, status, low;

    public static void main(String[] args) throws FileNotFoundException {

	Scanner sc = new Scanner(new FileInputStream(
		"inputArticulationBridges.txt"));

	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    sc.nextLine();
	    N = sc.nextInt();
	    graph = new BidirectionalGraph(N);
	    tree=new int[N][N];
	    time=0;
	    count=0;
	    disc= new int[N];
	    status= new int[N];
	    low= new int[N];
	    max=N;
	    for (int i = 0; i < N; i++) {
		int j = sc.nextInt();
		int edges = sc.nextInt();
		for (int k = 0; k < edges; k++) {
		    int v = sc.nextInt();
		    graph.addEdge(j, v);
		}
	    }

	    System.out.println("#"+(test_case+1));
	    findCriticalLinks(0);
	}

    }
    
    public static int findCriticalLinks(int v) {
	
	status[v]=1;
	disc[v]=++time;
	int min=N;
	for (int i = 0; i < max; ++i) {
	    if (graph.isEdge(v, i)) {
		if (status[i]==0) {
		    if (v==0) {
			++count;
		    }
		    
		    if (v==0&&count>=2) {
			System.out.println(v+" is critical point");
		    }
		    
		    tree[v][i]=1;
		    low[i]=findCriticalLinks(i);
		    if (low[i]<min) {
			min=low[i];
		    }
		    if (low[i]>=disc[v]&&v!=0) {
			System.out.println(v+" is critical point");
		    }
		}
		
		if (status[i]==1&&tree[i][v]==0) {
		    if (disc[i]<min) {
			min=disc[i];
		    }
		}
	    }
	}
	low[v]= minimum(disc[v], min);
	return low[v];
	
    }
    
    public static int minimum(int a, int b){
	return a>b?b:a;
    }
    
    public static void display(int[] i, int[] v){
	System.out.println(i.length+" critical links");
	for (int j = 0; j < v.length; j++) {
	    System.out.println(i[j]+" - "+v[j]);
	}
    }
}
