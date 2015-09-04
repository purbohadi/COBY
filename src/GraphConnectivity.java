import java.io.FileInputStream;
import java.util.Scanner;

/*
 * this program goal is to find max number of connected component of undirected graph
 * 
 */
public class GraphConnectivity {
    static int Answer, I;
    static String N;
    static BidirectionalGraph g;

    public static void main(String args[]) throws Exception {
	/*
	 * Make new scanner from standard input System.in, and read data.
	 */
	// Scanner sc = new Scanner(System.in);
	Scanner sc = new Scanner(new FileInputStream(
		"inputGraphConnectivity.txt"));

	int T = sc.nextInt();
	sc.nextLine();
	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	for (int test_case = 0; test_case < T; test_case++) {

	    // ///////////////////////////////////////////////////////////////////////////////////////////
	    /*
	     * Implement your algorithm here. The answer to the case will be
	     * stored in variable Answer.
	     */
	    // ///////////////////////////////////////////////////////////////////////////////////////////
	    // Answer = 0;
	    while (sc.hasNextLine()) {
		N = sc.next();
		I = alphabet.indexOf(N);
		g = new BidirectionalGraph(I + 1);
		sc.nextLine();
		while (sc.hasNextLine()) {
		    String line=sc.nextLine();
		    if (!line.equals("")) {
			String edge = line;
			int u = alphabet.indexOf(edge.charAt(0));
			int v = alphabet.indexOf(edge.charAt(1));
			g.addEdge(u, v);
		    }else{
			break;
		    }
		}
		break;
	    }
	    System.out.println("#" + (test_case + 1) + " " + (countMaxCC()));
	}
    }

    public static int DFS() {
	Stack stack = new Stack(I + 1);
	int visited[] = new int[I + 1];
	int cc = 0;
	for (int v = 0; v < I + 1; v++) {
	    if (visited[v] == 0) {
		int element = v;
		int i = v;
		visited[v] = 1;
		cc++;
		stack.push(v);
		while (!stack.isEmpty()) {
		    element = stack.peek();
		    i = element;
		    while (i < I + 1) {
			if (g.isEdge(element, i) && visited[i] == 0) {
			    stack.push(i);
			    visited[i] = 1;
			    element = i;
			    i = 1;
			    continue;
			}
			i++;
		    }
		    stack.pop();
		}
	    }
	}
	return cc;
    }

    private static int countMaxCC() {
	return DFS();
    }
}

class Stack {

    private int[] stackArray;
    private int stackSize;
    private int topOfStack = -1;

    public Stack(int size) {
	stackSize = size;
	stackArray = new int[stackSize];
	for (int i = 0; i < stackSize; i++) {
	    stackArray[i] = -1;
	}
    }

    public void push(int in) {
	if (topOfStack + 1 < stackSize) {
	    topOfStack++;
	    stackArray[topOfStack] = in;
	} else {
	    // System.out.println("Stack Full!");
	}
	// System.out.println("PUSH " + in + " was added");
    }

    public int pop() {
	if (topOfStack >= 0) {
	    // System.out.println("POP " + stackArray[topOfStack]
	    // + " Was removed from stack");
	    stackArray[topOfStack] = -1;
	    return stackArray[topOfStack--];
	} else {
	    // System.out.println("Stack is empty");
	    return -1;
	}
    }

    public int peek() {
	if (topOfStack >= 0) {
	    return stackArray[topOfStack];
	} else {
	    return -1;
	}
    }

    public boolean isEmpty() {
	return topOfStack == -1;
    }
}