import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CoinChange {
    
    public static int Answer, x, y;
    public static int[] coins;
    public static int[][] Table;

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new FileInputStream("inputCoinChange.txt"));
	
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    
	    int N = sc.nextInt();
	    int S = sc.nextInt();
	    coins = new int[S];
	    Table = new int[N+1][S];
	    x=0;
	    y=0;
	    for (int i = 0; i < S; i++) {
		Table[0][i]=1;
	    }
	    
	    for (int i = 0; i < S; i++) {
		coins[i]=sc.nextInt();
	    }
	    
	    for (int i = 1; i < N+1; i++) {
		for (int j = 0; j < S; j++) {
		    x=(i-coins[j]>=0) ? Table[i-coins[j]][j] : 0;
		    
		    y=(j>=1) ? Table[i][j-1] : 0;
		    
		    Table[i][j]= x + y;
		}
	    }
	    
	    System.out.println("#"+(test_case+1)+" "+Table[N][S-1]);
	}
    }
}
