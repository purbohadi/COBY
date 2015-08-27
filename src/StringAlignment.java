import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringAlignment {

    static int[][] Table;
    static char[] A, B;

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(
		new FileInputStream("inputStringAlignment.txt"));

	int T = sc.nextInt();
	sc.nextLine();
	for (int test_case = 0; test_case < T; test_case++) {

	    String lineA = sc.nextLine();
	    String lineB = sc.nextLine();

	    A = lineA.toCharArray();
	    B = lineB.toCharArray();

	    int N = lineA.length();
	    int M = lineB.length();

	    Table = new int[N+1][M+1];

	    for (int i = 0; i <= N; i++) {
		for (int j = 0; j <= M; j++) {
		    if (i==0||j==0) {
			Table[i][j]=0;
		    }else if (A[i - 1]==B[j - 1]) {
			Table[i][j] = Table[i - 1][j - 1] + 2;
		    } else {
			Table[i][j] = maximum(Table[i-1][j], Table[i][j-1])-1;
		    }
		}
	    }
	    System.out.println("#" + (test_case + 1) + " " + Table[N][M]);
	}
    }

    public static int maximum(int X, int Y) {
	return (X > Y) ? X : Y;
    }
}
