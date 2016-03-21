import java.util.Random;
import java.util.Scanner;


public class QueueArray {
    
    private static int[] queueArray;
    private int queueSize;
    private int front, back, numOfItems=0;

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

	System.out.println("Insert Queue Size: ");
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	int N = sc.nextInt();
	QueueArray queue = new QueueArray(N);
	for (int i = 0; i < N; i++) {
	    queue.enqueue(rand.nextInt(N));
	}
	queue.enqueue(4);
	
	for (int i = 0; i < N; i++) {
	    System.out.print(queueArray[i]+" ");
	}
	
	System.out.println();
	
	System.out.println("###");
	
	for (int i = 0; i < N; i++) {
	    System.out.print(queue.dequeue()+" ");
	}
	System.out.println();
	queue.dequeue();
	
    }
    
    private QueueArray(int size){
	queueSize=size;
	queueArray=new int[queueSize];
	front=0;
	back=size-1;
	for (int i = 0; i < queueSize; i++) {
	    queueArray[i]=-1;
	}
    }
    
    private void enqueue(int item){
	if (numOfItems+1<=queueSize) {
	    queueArray[back]=item;
	    back--;
	    numOfItems++;
	}else{
	    System.out.println("Queue Full");
	}
    }
    
    private int dequeue(){
	if (numOfItems>0) {
	    int value = queueArray[front];
	    queueArray[front]=-1;
	    front++;
	    numOfItems--;
	    return value;
	}else{
	    System.out.println("Empty queue.");
	    return -1;
	}
    }

}
