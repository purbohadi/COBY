import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Knapsack {
    
    public static int W,n;
    public static int[] wt,val;
    public static int[][] K;
    

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputKnapsack.txt"));
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    
	    W=sc.nextInt();
	    n=sc.nextInt();
	    sc.nextLine();
	    wt = new int[n];
	    val= new int[n];
	    K= new int[n+1][W+1];
	    
	    for (int i = 0; i < n; i++) {
		wt[i]=sc.nextInt();
	    }
	    sc.nextLine();
	    for (int i = 0; i < n; i++) {
		val[i]=sc.nextInt();
	    }
	    
	    for (int i = 0; i <= n; i++) {
		for (int w = 0; w <= W; w++) {
		    if (i==0||w==0) {
			K[i][w]=0;
		    }else if(wt[i-1]<=w){
			K[i][w]=maximum(val[i-1]+K[i-1][w-wt[i-1]], K[i-1][w]);
		    }else{
			K[i][w]=K[i-1][w];
		    }
		}
	    }
	    
	    System.out.println("#"+(test_case+1)+" "+K[n][W]);
	    
	}
    }
    
    public static int maximum(int x, int y){
	return x>y?x:y;
    }
}
