import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrafficFlow {

    public static int N, M, U, V, C;
    public static Edge[] edges;
    public static int Answer;

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputTrafficFlow.txt"));

	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {

	    Answer = 0;

	    N = sc.nextInt();
	    M = sc.nextInt();

	    edges = new Edge[M];
	    for (int i = 0; i < M; i++) {
		int x = sc.nextInt();
		int y = sc.nextInt();
		int z = sc.nextInt();
		edges[i] = new Edge(x, y, z);
	    }
	    
	    bubbleSortArr(edges);
	    
	    KruskalMST(edges,(test_case+1));

	}
    }

    public static void KruskalMST(Edge[] edges, int test_case) {

	Edge[] result = new Edge[N];
	Subset[] subsets = new Subset[N];
	
	int e=0;
	int i=0;
	
	for (int v = 0; v < N; ++v) {
	    subsets[v]=new Subset();
	    subsets[v].parent=v;
	    subsets[v].rank=0;
	}
	
	while (e<N-1) {
	    if (i==M) {
		break;
	    }
	    Edge next_edge= edges[i++];
	    int x= find(subsets, next_edge.src);
	    int y= find(subsets, next_edge.dest);
	    
	    if (x!=y) {
		result[e++]=next_edge;
		union(subsets, x, y);
	    }
	}
	System.out.println("#"+test_case+" "+result[e-1].weight);
//	for (int a=0;a<e;a++) {
//	    System.out.println(result[a].src+"--"+result[a].dest+"=="+result[a].weight);
//	}
    }
    
    public static void union(Subset[] subsets, int x, int y){
	int xRoot=find(subsets, x);
	int yRoot=find(subsets, y);
	
	if (subsets[xRoot].rank<subsets[yRoot].rank) {
	    subsets[xRoot].parent=yRoot;
	}else if (subsets[xRoot].rank>subsets[yRoot].rank) {
	    subsets[yRoot].parent=xRoot;
	}else{
	    subsets[yRoot].parent=xRoot;
	    subsets[xRoot].rank++;
	}
    }
    
    public static int find(Subset[] subsets, int src){
	if (subsets[src].parent!=src) {
	    subsets[src].parent= find(subsets, subsets[src].parent);
	}
	return subsets[src].parent;
    }

    public static Edge[] bubbleSortArr(Edge[] inArr) {

	Edge temp;
	for (int i = 0; i < inArr.length - 1; i++) {
	    for (int j = 0; j < inArr.length - 1; j++) {
		int u1 = inArr[j].weight;
		int u2 = inArr[j + 1].weight;
		if (u1 < u2) {
		    temp = inArr[j];
		    inArr[j] = inArr[j + 1];
		    inArr[j + 1] = temp;
		}
	    }

	}
	return inArr;
    }

}


class Subset {
    int parent;
    int rank;
}

class Edge {
    int src;
    int dest;
    int weight;

    public Edge(int x, int y, int z) {
	src = x;
	dest = y;
	weight = z;
    }

}