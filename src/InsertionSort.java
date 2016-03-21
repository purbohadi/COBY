import java.util.Arrays;
import java.util.Random;




public class InsertionSort {

    
    static int[] data = new int[] {50, 30, 21, 46, 46, 45, 45, 40, 38, 37, 36, 34, 34, 33, 33, 32, 32, 32, 30, 28, 28, 28, 28, 27, 26, 25, 25, 25, 25, 22, 22, 19, 19, 18, 17, 17, 15, 13, 12, 11, 11, 10, 9, 5, 4, 3, 3, 2, 1, 0};
//     static int[] data;
     static long startTime, endTime, elapsedTime;
     static final int N = 47;
    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	data = new int[N];

	Random rand = new Random();
	
	for (int i = 0; i < data.length; i++) {
	    data[i]=rand.nextInt(N);
	}
	
	for (int i = 0; i < data.length; i++) {
	    System.out.print(data[i]+", ");
	}
	
	System.out.println();
	
	startTime = System.nanoTime();
	Arrays.sort(data);
	endTime = System.nanoTime();
	
	elapsedTime = endTime-startTime;
	
	System.out.println("Running Time Arrays.sort : "+elapsedTime);
	
	
	data=insertionSort(data);

	System.out.println();
	
	for (int i = 0; i < data.length; i++) {
	    System.out.print(data[i]+", ");
	}
	System.out.println();
	
	System.out.println("Running Time: "+elapsedTime);
	
    }
    
    public static int[] insertionSort(int[] inArr){
	
	startTime = System.nanoTime();
	
	int val=0;
	int j=0;
	for (int i = 1; i < inArr.length; i++) {
	    val=inArr[i];
	    for (j = i-1; j>=0 && inArr[j] > val; j--) {
		inArr[j+1]=inArr[j];
	    }
	    inArr[j+1]= val;
	}
	
	endTime = System.nanoTime();
	elapsedTime = endTime-startTime;
	
	return inArr;
	
    }

}
