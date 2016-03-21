import java.util.Random;


public class MergeSort {
    
    static int[] data = new int[] {50, 30, 21, 46, 46, 45, 45, 40, 38, 37, 36, 34, 34, 33, 33, 32, 32, 32, 30, 28, 28, 28, 28, 27, 26, 25, 25, 25, 25, 22, 22, 19, 19, 18, 17, 17, 15, 13, 12, 11, 11, 10, 9, 5, 4, 3, 3, 2, 1, 0};
//    static int[] data;
    static int[] helper;
    static long startTime, endTime, elapsedTime;
    static final int N = 50;
    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

////	data= new int[N];
////	
////	Random rand = new Random();
//	
//	for (int i = 0; i < data.length; i++) {
//	    data[i] = rand.nextInt(N);
//	}
	
	for (int i = 0; i < data.length; i++) {
	    System.out.print(data[i]+", ");
	}
	
	sort();
	
	System.out.println();
	
	for (int i = 0; i < data.length; i++) {
	    System.out.print(data[i]+", ");
	}
	
	System.out.println();
	
	System.out.println("Running Time: "+elapsedTime);
	
    }
    
    public static void sort(){
	int number = data.length;
	helper = new int[number];
	
	startTime = System.nanoTime();
	
	mergeSort(0, number-1);
	
	elapsedTime = System.nanoTime() - startTime;
    }
    
    
    public static void mergeSort(int low, int high){
	if (low<high) {
	    
	    int middle = low+(high-low)/2;
	    
	    mergeSort(low, middle);
	    
	    mergeSort(middle+1, high);
	    
	    merge(low, middle, high);
	    
	}
    }
    
    public static void merge(int low, int middle, int high){
	
	for (int i = low; i <= high; i++) {
	    helper[i]=data[i];
	}
	
	int i = low;
	int j = middle+1;
	int k = low;
	
	
	while (i<=middle&& j<=high) {
	    
	    if (helper[i]<=helper[j]) {
		data[k]=helper[i];
		i++;
	    }else{
		data[k]=helper[j];
		j++;
	    }
	    k++;
	}
	
	while (i<=middle) {
	    data[k]=helper[i];
	    k++;
	    i++;
	}
	
	
	
    }
    

}
