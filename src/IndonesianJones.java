import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IndonesianJones {

	static int[][] mat;
	static int T, H, C;

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		//Scanner sc = new Scanner(System.in);
		 Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int C = sc.nextInt();
		for (int test_case = 0; test_case < C; test_case++) {

			H = sc.nextInt();
			mat = new int[101][101];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < i + 1; j++) {
					T = sc.nextInt();
					mat[i][j] = T;
				}
			}
			System.out.println("Case #" + (test_case + 1));
			System.out.println(getMaxTreasure(H));

		}

	}

	private static int getMaxTreasure(int h) {
		int i, j;

		if (h == 1)
			return mat[0][0];

		for (i = h - 2; i >= 0; i--) {
			for (j = 0; j <= i; j++) {
				mat[i][j] += Math.max(mat[i + 1][j], mat[i + 1][j + 1]);
			}
		}
		return mat[0][0];
	}
}
