import java.util.Random;
import java.util.Scanner;


public class StackArray {
    
    private static int[] stackArr;
    private int stackSize;
    private int topOfStack = -1;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	System.out.println("input New Stack Size");
	Scanner sc = new Scanner(System.in);
	
	int N = sc.nextInt();
	StackArray stack = new StackArray(N);
	
	Random rand = new Random();
	for (int i = 0; i < stack.stackSize; i++) {
	    stack.push(rand.nextInt(N));
	}
	
	stack.push(4);
	
	for (int i = 0; i < N; i++) {
	    System.out.println(stackArr[i]+ " ");
	}
	
	System.out.println("###");
	
	for (int i = 0; i < N; i++) {
	    System.out.println(stack.pop());
	}
	
	stack.pop();
    }
    
    private StackArray(int size){
	stackSize=size;
	stackArr = new int[stackSize];
	for (int i = 0; i < stackSize; i++) {
	    stackArr[i]=-1;
	}
    }
    
    
    private void push(int value){
	if (topOfStack+1<stackSize) {
	    topOfStack++;
	    stackArr[topOfStack]=value;
	}else{
	    System.out.println("Stack Full.");
	}
    }
    
    private int pop(){
	if (topOfStack>=0) {
	    int value=stackArr[topOfStack];
	    stackArr[topOfStack]=-1;
	    topOfStack--;
	    return value;
	}else{
	    System.out.println("Stack is empty.");
	    return -1;
	}
    }

}
