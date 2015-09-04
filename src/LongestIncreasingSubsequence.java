import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LongestIncreasingSubsequence {
    
    public static int[] array, lis;
    public static int maxLength;
    
    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputLongestIncreasingSubsequence.txt"));
	
	int T = sc.nextInt();
	
	for (int test_case = 0; test_case < T; test_case++) {
	    int N= sc.nextInt();
	    array= new int[N];
	    maxLength=0;
	    
	    for (int i = 0; i < N; i++) {
		array[i]=sc.nextInt();
	    }

	    lis = new int[N];
	    
	    for (int i = 0; i < N; i++) {// initialize LIS array
		lis[i]=1;
	    }
	    
	    for (int i = 1; i < N; i++) {
		for (int j = 0; j <i; j++) {
		    if (array[i]>array[j]&&
			    lis[i]<lis[j]+1) {
			lis[i]=lis[j]+1;
		    }
		}
	    }
	    
	    for (int i = 0; i < N; i++) {
		if (maxLength<lis[i]) {
		    maxLength=lis[i];
		}
	    }
	    
	    System.out.println("#"+(test_case+1)+" "+maxLength);
	    
	}
    }

}
