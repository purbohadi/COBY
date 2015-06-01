import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Jojoba {
	static int Answer, N;
	static char[][] map;
	static char val;

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("inputjojoba.txt"));

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
			for (int i = 0; i < N; i++) {
				sc.nextLine();				
				String line = sc.next();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
					}

				}
			}

			// Print the answer to standard output(screen).
			System.out.print("Test Case #" + (test_case + 1));
			
		}
	}

}
