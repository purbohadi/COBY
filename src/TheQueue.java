
public class TheQueue {

    private int[] queueArray;
    private int queueSize;
    private int front, rear, numberOfItems = 0;
    
    public TheQueue(int size) {
	queueSize = size;
	queueArray = new int[size];
	for (int i = 0; i < size; i++) {
	    queueArray[i]=-1;
	}
    }
    
    public void insert(int input){
	if (numberOfItems+1<=queueSize) {
	    queueArray[rear]=input;
	    rear++;
	    numberOfItems++;
	    System.out.println("INSERT "+input+" Was added ");
	}else{
	    System.out.println("Sorry queue Full!");
	}
    }
    
    public void remove(){
	if (numberOfItems>0) {
	    System.out.println("REMOVE "+queueArray[front]+" Was removed");
	    queueArray[front]=-1;
	    front++;
	    numberOfItems--;
	}else{
	    System.out.println("Sorry empty queue");
	}
    }
    
    public void displayQueue() {
	System.out
		.println("----------------------------------------------------------------------------------------------");
	for (int i = 0; i < queueSize; i++) {
	    if (queueArray[i] != -1) {
		System.out.print(" " +queueArray[i] + "  |  ");
	    }else{
		System.out.print("   |   ");
	    }
	}
	System.out.println();
	System.out
		.println("----------------------------------------------------------------------------------------------");
    }
    
    
    public static void main(String[] args) {
	TheQueue theQueue = new TheQueue(10);
	theQueue.insert(10);
	theQueue.insert(9);
	theQueue.insert(7);
	theQueue.insert(5);
	theQueue.displayQueue();
	theQueue.remove();
	theQueue.displayQueue();
	theQueue.insert(2);
	theQueue.insert(4);
	theQueue.insert(1);
	theQueue.displayQueue();
	theQueue.remove();
	theQueue.displayQueue();
	
    }
}
