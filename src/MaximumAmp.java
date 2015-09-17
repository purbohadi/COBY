import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MaximumAmp {

    private static int Answer, N, M, K;
    private static int[][] map;
    private static int[] val;

    public static void main(String[] args) throws FileNotFoundException {

	Scanner sc = new Scanner(new FileInputStream("inputMaximumAmp.txt"));

	int T = sc.nextInt();
	for (int test_case = 1; test_case <= T; test_case++) {
	    Answer = 0;

	    N = sc.nextInt();
	    M = sc.nextInt();
	    K = sc.nextInt();

	    map = new int[N][M];
	    int value;
	    val = new int[N];
	    for (int i = 0; i < N; i++) {
		sc.nextLine();
		value = 0;
		for (int j = 0; j < M; j++) {
		    map[i][j] = sc.nextInt();
		    if (map[i][j] == 1) {
			value++;
		    }else{
			value--;
		    }
		}
		val[i] = value;
		if (value == M) {
		    Answer++;
		}
	    }
	    countMaxAmp(test_case);
	}
    }

    public static void countMaxAmp(int test_case) {
	int count = 0;
	for (int i = 0; i < N; i++) {
	    if ((K + val[i])%2==0) {
		continue;
	    } else {
		count++;
	    }
	}
	Answer = Math.max(Answer, count);
	System.out.println("#" + test_case + " " + Answer);
    }
}