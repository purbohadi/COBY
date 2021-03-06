import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bicoloring {

    static int N, I;
    static BidirectionalGraph graph;

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new File("inputBicoloring.txt"));
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    N = sc.nextInt();
	    I = sc.nextInt();
	    graph = new BidirectionalGraph(N);
	    for (int j = 0; j < I; j++) {
		int u = sc.nextInt();
		int v = sc.nextInt();
		graph.addEdge(u, v);
	    }

	    if (isBicolor())
		System.out.println("#" + (test_case + 1) + " BICOLORABLE.");
	    else
		System.out.println("#" + (test_case + 1) + " NOT BICOLORABLE.");
	}
    }

    public static boolean isBicolor() {
	int[] colorArr = new int[N];
	for (int i = 0; i < N; i++) {// initialize colorArr to default value 
	    colorArr[i] = -1;
	}
	colorArr[0] = 1; // set source to true

	Queue q = new Queue(N);
	q.insert(0);

	while (!q.isEmpty()) {
	    int u = q.peek();
	    q.remove();
	    for (int v = 0; v < N; v++) {
		if (graph.isEdge(u, v)) {
		    if (colorArr[v] == -1) {
			colorArr[v] = 1 - colorArr[u];
			q.insert(v);
		    } else if (colorArr[v] == colorArr[u]) {
			return false;
		    }
		}
	    }
	}
	return true;
    }
}

class Queue {

    private int[] queueArray;
    private int queueSize;
    private int front, rear, numberOfItems = 0;

    public Queue(int size) {
	queueSize = size;
	queueArray = new int[size];
	for (int i = 0; i < size; i++) {
	    queueArray[i] = -1;
	}
    }

    public void insert(int input) {
	if (numberOfItems + 1 <= queueSize) {
	    queueArray[rear] = input;
	    rear++;
	    numberOfItems++;
	    // System.out.println("INSERT " + input + " Was added ");
	} else {
	    // System.out.println("Sorry queue Full!");
	}
    }

    public void remove() {
	if (numberOfItems > 0) {
	    // System.out.println("REMOVE " + queueArray[front] +
	    // " Was removed");
	    queueArray[front] = -1;
	    front++;
	    numberOfItems--;
	} else {
	    // System.out.println("Sorry empty queue");
	}
    }

    public boolean isEmpty() {
	if (numberOfItems > 0) {
	    return false;
	} else {
	    return true;
	}
    }

    public int peek() {
	if (numberOfItems > 0) {
	    return queueArray[front];
	} else {
	    System.out.println("empty");
	    return 0;
	}
    }
}

class UndirectedGraph {
    private boolean adjacencyMatrix[][];
    private int vertexCount;

    public UndirectedGraph(int vCount) {
	this.vertexCount = vCount;
	this.adjacencyMatrix = new boolean[vertexCount][vertexCount];
    }

    public void addEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    this.adjacencyMatrix[i][j] = true;
	    this.adjacencyMatrix[j][i] = true;
	}
    }

    public void removeEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    this.adjacencyMatrix[i][j] = false;
	    this.adjacencyMatrix[j][i] = false;
	}
    }

    public boolean isEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount)
	    return adjacencyMatrix[i][j];
	else
	    return false;
    }

}
