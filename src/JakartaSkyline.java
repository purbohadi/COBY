import java.io.FileInputStream;
import java.util.Scanner;

public class JakartaSkyline {
	static int N, A, B, H;
	static int[] height;

	public static void main(String args[]) throws Exception {
		/*
		 * The method below means that the program will read from input.txt,
		 * instead of standard(keyboard) input. To test your program, you may
		 * save input data in input.txt file, and call below method to read from
		 * the file when using nextInt() method. You may remove the comment
		 * symbols(//) in the below statement and use it. But before submission,
		 * you must remove the freopen function or rewrite comment symbols(//).
		 */

		/*
		 * Make new scanner from standard input System.in, and read data.
		 */
		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {

			// ///////////////////////////////////////////////////////////////////////////////////////////
			/*
			 * Implement your algorithm here. The answer to the case will be
			 * stored in variable Answer.
			 */
			// ///////////////////////////////////////////////////////////////////////////////////////////
			// Answer = 0;
			N = sc.nextInt();
			height = new int[10001];
			for (int i = 0; i < N; i++) {
//				System.out.println(N +" "+i);
				sc.nextLine();
				A = sc.nextInt();
				B = sc.nextInt();
				H = sc.nextInt();
				for (int k = A; k < B; ++k) {
					if (H > height[k])
						height[k] = H;
				}
			}

			int currentH = 0;
			System.out.println("Case #" + (test_case+1));
			for (int pos = 0; pos != 10000; ++pos) {
				if (height[pos] != currentH) {
					System.out.println(pos + " " + height[pos]);
					currentH = height[pos];
				}
			}

			// Print the answer to standard output(screen).
			// System.out.println("Case #"+(test_case+1));
			// System.out.println(Answer);
		}
	}
}
