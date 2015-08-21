import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ZimbabweanLunch {

    static double Answer;
    static int N, P;

    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(
		new FileInputStream("inputZimbabweanLunch.txt"));
	int T = sc.nextInt();
	for (int test_case = 0; test_case < T; test_case++) {
	    
	    Answer=0;
	    N = sc.nextInt();
	    P = sc.nextInt();
	    
	    for (int j = 0; j < N; j++) {
		sc.nextLine();
		Answer+=sc.nextDouble();
	    }
	    System.out.println("Case #"+(test_case+1)+" : "+"Total costs "+(long)Answer+", each person must pay "+(long)(Answer/P));
	}
    }
}
