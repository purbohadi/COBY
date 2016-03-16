import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Interstellar {

    public static int AnswerN, N, limit;
    public static WeightedGraph graph;
    public static int[][] nodes;

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputInterstellar.txt"));
	int T = sc.nextInt();

	for (int tes_case = 0; tes_case < T; tes_case++) {

	    AnswerN = 0;

	    N = sc.nextInt();

	    int x1 = sc.nextInt();
	    int y1 = sc.nextInt();

	    int x2 = sc.nextInt();
	    int y2 = sc.nextInt();

	    limit = 2 * N + 2;

	    graph = new WeightedGraph(limit);
	    
	    nodes=new int[limit][2];
	    
	    int idx=0;
	    
	    nodes[idx][0]=x1;
	    nodes[idx++][1]=y1;
	    nodes[idx][0]=x2;
	    nodes[idx++][1]=y2;

	    for (int i = 0; i < limit; i++) {
		for (int j = 0; j < limit; j++) {
		    graph.addEdge(i, j, Integer.MAX_VALUE);
		}
	    }

	    int weight = countWeight(x1, y1, x2, y2);
	    
	    graph.addEdge(0, 1, weight);
	    for (int i = 0; i < N; i++) {
		x1 = sc.nextInt();
		y1 = sc.nextInt();
		x2 = sc.nextInt();
		y2 = sc.nextInt();
		weight = sc.nextInt();
		nodes[idx][0]=x1;
		nodes[idx++][1]=y1;
		nodes[idx][0]=x2;
		nodes[idx++][1]=y2;
		graph.addEdge(idx-2, idx-1, 0);
	    }

	    for (int i = 0; i < limit; i++) {
		for (int j = 0; j < limit; j++) {
		    weight=countWeight(nodes[i][0], nodes[i][1], nodes[j][0], nodes[j][1]);
		    if (weight<graph.getWeight(i, j)) {
			graph.addEdge(i, j, weight);
		    }
		}
	    }

	    int result = countMSWithDjikstra(0, 1)-AnswerN;
	    System.out.println("#" + (tes_case + 1) + " "
		    + result);
	}

    }
    
    public static int countMSWithDjikstra(int src, int dest) {

	int[] distance = new int[limit];
	boolean[] shortPath = new boolean[limit];

	for (int i = 0; i < limit; i++) {
	    distance[i] = Integer.MAX_VALUE;
	    shortPath[i] = false;
	}

	distance[src] = 0;

	for (int count = 0; count < limit - 1; count++) {
	    int u = minDistance(distance, shortPath);
	    if (u == dest) {
		break;
	    }

	    shortPath[u] = true;
	    for (int v = 0; v < limit; v++) {
		if (!shortPath[v] //&& graph.getWeight(u, v) > 0
			&& distance[u] != Integer.MAX_VALUE
			&& (distance[u] + graph.getWeight(u, v)) < distance[v]) {

		    distance[v] = distance[u] + graph.getWeight(u, v);
//		    if(graph.getWeight(u, v)<0)
//			AnswerN+=graph.getWeight(u, v);
		}
	    }
	}

	return distance[dest];
    }

    public static int minDistance(int[] dist, boolean[] shortPath) {
	int min = Integer.MAX_VALUE;
	int min_index = 0;

	for (int v = 0; v < limit; v++) {
	    if (!shortPath[v] && dist[v] <= min) {
		min = dist[v];
		min_index = v;
	    }
	}
	return min_index;
    }

    
    public static int countWeight(int x1, int y1, int x2, int y2){
	int a=Math.abs(x2-x1);
	int b=Math.abs(y2-y1);
	return a+b;
    }


    public static int[][] floydWarshell() {
	int[][] dist = new int[limit][limit];

	for (int i = 0; i < limit; i++) {
	    for (int j = 0; j < limit; j++) {
		dist[i][j] = graph.getWeight(i, j);
	    }
	}

	for (int via = 0; via < N; via++) 
	    for (int from = 0; from < N; from++) 
		for (int to = 0; to < N; to++) 
		    if (dist[from][via] != Integer.MAX_VALUE
			    && dist[via][to] != Integer.MAX_VALUE && from != to) {
			if (dist[from][to] > dist[from][via] + dist[via][to])
			    dist[from][to] = dist[from][via] + dist[via][to];
		    }

	return dist;
    }

}
