import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FollowTheDots {

    public static double Answer;
    public static double[][] map;
    public static int N;
    public static BidirectionalWeightedGraph graph;

    public static void main(String[] args) throws FileNotFoundException {

	Scanner sc = new Scanner(new FileInputStream("inputFollowTheDots.txt"));
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {

	    Answer=0;
	    N = sc.nextInt();
	    map = new double[N][N];
	    for (int i = 0; i < N; i++) {
		sc.nextLine();
		for (int j = 0; j < 2; j++) {
		    map[i][j] = sc.nextDouble();
		}
	    }

	    graph = new BidirectionalWeightedGraph(N);
	    for (int i = 0; i < N; i++) {
		for (int j = 1; j < N; j++) {
		    graph.addEdge(
			    i,
			    j,
			    countDistance(map[i][0], map[i][1], map[j][0],
				    map[j][1]));
		}
	    }

	    PrimsMST((test_case+1));
	    
	    bubbleSortArr(map, 1);

	}

    }

    public static void PrimsMST(int test_case) {
	int[] parent = new int[N];
	double[] key = new double[N];
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
    
    public static void printMST(int[] parent, int test_case){
	
	System.out.println("#"+test_case);
	for (int i = 0; i < parent.length; i++) {
	    System.out.println(parent[i]);
	}
	
//	for (int i = 1; i < N; i++) {
//	    Answer+=graph.getWeight(i, parent[i]);
//	}
//	System.out.println("#"+test_case+" "+String.format( "%.2f", Answer));
    }
    
    public static int minimumKey(double[] key, boolean[] mstSet){
	
	double min= Double.MAX_VALUE;
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

    public static double countDistance(double x1, double y1, double x2,
	    double y2) {
	return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) * 100 / 100;
    }

    public static double[][] bubbleSortArr(double[][] inArr, int idx) {

	double temp = 0;
	boolean finished = false;

	while (!finished) {
	    finished = true;
	    for (int i = 0; i < inArr.length - 1; i++) {
		if (inArr[i][idx] >= inArr[i + 1][idx]) {
		    for (int j = 0; j < inArr[i].length; j++) {
			temp = inArr[i][j];
			inArr[i][j] = inArr[i + 1][j];
			inArr[i + 1][j] = temp;
		    }
		    finished = true;
		}
	    }
	}
	return inArr;
    }

}

class BidirectionalWeightedGraph {
    private double adjacencyMatrix[][];
    private int vertexCount;

    public BidirectionalWeightedGraph(int vCount) {
	this.vertexCount = vCount;
	this.adjacencyMatrix = new double[vertexCount][vertexCount];
    }

    public void addEdge(int i, int j, double weight) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    adjacencyMatrix[i][j] = weight;
	    adjacencyMatrix[j][i] = weight;
	}
    }

    public void removeEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    // this.adjacencyMatrix[i][j] = weight;
	    // adjacencyMatrix[j][i] = false;
	}
    }
    
    public double getWeight(int i, int j){
	return adjacencyMatrix[i][j];
    }

}

