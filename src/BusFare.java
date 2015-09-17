import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BusFare {

    private static int dist;
    private static int[] fares = new int[11];
    private static int[] MinFares;
    
    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputBusFare.txt"));
	
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    
	    for (int i = 1; i < 11; i++) {
		fares[i]=sc.nextInt();
	    }
	    sc.nextLine();
	    dist = sc.nextInt();
	    MinFares = new int[dist+1];
	    
	    countMinimumFare();
	}
    }
    
    public static void countMinimumFare(){
	
	for (int i = 1; i <= dist; i++) {
	    MinFares[i]=Integer.MAX_VALUE;
	}
	
	for (int i = 1; i < 11; i++) {
	    for (int j = i; j <= dist; j++) {
		MinFares[j]=Math.min(MinFares[j-i]+fares[i], MinFares[j]);
	    }
	}
	
	System.out.println(MinFares[dist]);
    }
}
