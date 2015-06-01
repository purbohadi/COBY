import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Encryption {

	static int Answer, N;

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
		Scanner sc = new Scanner(new FileInputStream("inputEncryption.txt"));

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {

			// ///////////////////////////////////////////////////////////////////////////////////////////
			/*
			 * Implement your algorithm here. The answer to the case will be
			 * stored in variable Answer.
			 */
			// ///////////////////////////////////////////////////////////////////////////////////////////
			Answer = 0;
			N = sc.nextInt();
			sc.nextLine();
			String text = sc.nextLine();
			ArrayList<String> results = new ArrayList<String>();

			int length = text.length();

			for (int i = 0; i < length; i += N) {
				if ((length - (i + N)) >= 0) {
					results.add(text.substring(i, i + N));
				} else {
					results.add(text.substring(i, length));
				}
			}

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < N; i++) {
				for (int a = 0; a < results.size(); a++) {
					if (i<results.get(a).length()) {
						sb.append(results.get(a).charAt(i));
					}else{
						sb.append(" ");
					}
				}
				if (i<N-1) {
					sb.append(" ");
				}
			}
			System.out.println("Case #" + (test_case + 1) + " : ["
					+ sb.toString() + "]");
		}
	}


}
