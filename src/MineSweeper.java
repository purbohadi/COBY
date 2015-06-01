import java.io.FileInputStream;
import java.util.Scanner;

public class MineSweeper {

	static int Answer, M, N;
	static char[][] map;
	static char val;

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
			M = sc.nextInt();
			N = sc.nextInt();
			map = new char[N][M];
			for (int i = 0; i < N; i++) {
				sc.nextLine();				
				String line = sc.next();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '.') {
						val=countMines(i, j);
						if(val!='0')
							map[i][j]=countMines(i, j);
					}

				}
			}

			// Print the answer to standard output(screen).
			System.out.print("Test Case #" + (test_case + 1));
			
			for (int i = 0; i < N; i++) {
				System.out.println();
				for (int j = 0; j < M; j++) {
					System.out.print(map[i][j]);
				}
			}
			System.out.println();
			//System.out.println(Answer);
		}
	}

	private static char countMines(int i, int j) {
		int count = 0;

		if (i - 1 >= 0) {// check left
			if(map[i - 1][j] == '*')
				count++;
			if(j-1>=0){//left up
				if(map[i - 1][j-1] == '*')
					count++;
			}
			
			if(j+1<M){//left down
				if(map[i - 1][j+1] == '*')
					count++;
			}
		}

		if (i + 1 < N) {// check right
			if(map[i + 1][j] == '*')
				count++;
			if(j-1>=0){//right up
				if(map[i + 1][j-1] == '*')
					count++;
			}
			
			if(j+1<M){//right down
				if(map[i + 1][j+1] == '*')
					count++;
			}
		}

		if (j + 1 < M) {// check down
			if(map[i][j + 1] == '*')
				count++;
		}

		if (j - 1 >= 0) {// check up
			if(map[i][j - 1] == '*')
				count++;
		}
		
		return  (char) ('0' + count);

	}
}
