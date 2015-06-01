import java.io.FileInputStream;
import java.util.Random;
import java.util.Scanner;

public class Bomberman {

	static int Answer, n, m;
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
		outer:
		for (int test_case = 0; test_case < T; test_case++) {

			// ///////////////////////////////////////////////////////////////////////////////////////////
			/*
			 * Implement your algorithm here. The answer to the case will be
			 * stored in variable Answer.
			 */
			// ///////////////////////////////////////////////////////////////////////////////////////////
			m = sc.nextInt();
			n = sc.nextInt();
			map = new char[n][m];
			for (int i = 0; i < n; i++) {
				sc.nextLine();
				String line = sc.next();
				for (int j = 0; j < m; j++) {
					map[i][j] = line.charAt(j);
				}
			}

			char[][] s = map;
			boolean[][] g = new boolean[n][m];
			System.out.println("Test Case #" + (test_case + 1) + ": ");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					g[i][j] = s[i][j] == '.';
				}
			}

			inner:
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (g[i][j])
						continue inner;
				}
				System.out.println(-1);
				continue outer;
			}
			
			System.out.println(n - numMaximumMatching(g, new Random()));
		}

		// Print the answer to standard output(screen).
		// System.out.println("Test Case #" + (test_case + 1));

		// System.out.println(Answer);
	}

	public static int numMaximumMatching(boolean[][] g, Random gen) {
		//int n = g.length;
		final int mod = 1000000009;
		int[][] a = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < m; j++) {
				if (g[i][j]) {
					a[i][j] = gen.nextInt(mod - 1) + 1;
					a[j][i] = mod - a[i][j];
				}
			}
		}
		return rank(a, mod) / 2;
	}

	public static int rank(int[][] M, int p) {
		int n = M.length;//, m = M[0].length;

		// Forward Elimination
		for (int i = 0; i < n; i++) {
			// select pivot
			boolean pivotFound = false;
			out: for (int pi = i; pi < n; pi++) {
				for (int pj = i; pj < m; pj++) {
					if (M[pi][pj] !=0) {
						// pivot found
						if (pj != i) {
							// swap columns
							for (int k = 0; k < m; k++) {
								int d = M[k][pj];
								M[k][pj] = M[k][i];
								M[k][i] = d;
							}
						}
						if (pi != i) {
							// swap rows
							int[] d = M[pi];
							M[pi] = M[i];
							M[i] = d;
						}
						pivotFound = true;
						break out;
					}
				}
			}
			if (!pivotFound)
				return i;

			long ID = invl(M[i][i], p);
			M[i][i] = 1;
			for (int j = i + 1; j < m; j++) {
				M[i][j] = (int) (M[i][j] * ID % p);
			}

			for (int j = i + 1; j < n; j++) {
				long B = p - M[j][i];
				M[j][i] = 0;
				for (int k = i + 1; k < m; k++) {
					M[j][k] = (int) ((M[j][k] + M[i][k] * B) % p);
				}
			}
		}
		return n;
	}

	public static long invl(long a, long mod) {
		long b = mod;
		long p = 1, q = 0;
		while (b > 0) {
			long c = a / b;
			long d;
			d = a;
			a = b;
			b = d % b;
			d = p;
			p = q;
			q = d - c * q;
		}
		return p < 0 ? p + mod : p;
	}
	/*
	 * private static char countMines(int i, int j) { int count = 0;
	 * 
	 * if (i - 1 >= 0) {// check left if(map[i - 1][j] == '.') count++; }
	 * 
	 * if (i + 1 < N) {// check right if(map[i + 1][j] == '*') count++; }
	 * 
	 * if (j + 1 < M) {// check down if(map[i][j + 1] == '*') count++; }
	 * 
	 * if (j - 1 >= 0) {// check up if(map[i][j - 1] == '*') count++; }
	 * 
	 * return (char) ('0' + count); }
	 * 
	 * private static boolean isBlocked(int i, int j) { int count = 0;
	 * 
	 * if (i - 1 >= 0) {// check left if(map[i - 1][j] == '*') count++;
	 * if(j-1>=0){//left up if(map[i - 1][j-1] == '*') count++; }
	 * 
	 * if(j+1<M){//left down if(map[i - 1][j+1] == '*') count++; } }
	 * 
	 * if (i + 1 < N) {// check right if(map[i + 1][j] == '*') count++;
	 * if(j-1>=0){//right up if(map[i + 1][j-1] == '*') count++; }
	 * 
	 * if(j+1<M){//right down if(map[i + 1][j+1] == '*') count++; } }
	 * 
	 * if (j + 1 < M) {// check down if(map[i][j + 1] == '*') count++; }
	 * 
	 * if (j - 1 >= 0) {// check up if(map[i][j - 1] == '*') count++; }
	 * 
	 * return false;
	 * 
	 * }
	 */
}
