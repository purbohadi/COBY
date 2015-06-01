import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CandyCrush {

	static int Answer, X, Y;
	static String[] map;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("inputCandyCrush.txt"));

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {
			X= sc.nextInt();
			Y=sc.nextInt();
			map= new String[X];
			for (int i = 0; i < X; i++) {
				sc.nextLine();
				map[i]=sc.next();
			}
			
			Answer=0;
			System.out.println("Case #"+(test_case+1)+" : "+numMoves(map));

		}
	}

	public static int numMoves(String[] board) {
		
		int result=0;
		
		char[][] b = new char[board.length][];
		for (int i = 0; i < board.length; i++)
			b[i] = board[i].toCharArray();

		boolean[][][][] m = new boolean[50][50][50][50];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length(); j++) {

				for (int y = -1; y <= 1; y++) {
					for (int x = -1; x <= 1; x++) {
						if (i + y < 0 || i + y >= board.length || j + x < 0
								|| j + x >= board[0].length())
							continue;

						if (x + y == 0 || x + y == -2 || x + y == 2)
							continue;

						if (m[i][j][i + y][j + x])
							continue;

						char t = b[i + y][j + x];
						b[i + y][j + x] = b[i][j];
						b[i][j] = t;

						if (check(b)) {
							result++;
						}

						t = b[i + y][j + x];
						b[i + y][j + x] = b[i][j];
						b[i][j] = t;

						m[i][j][i + y][j + x] = true;
						m[i + y][j + x][i][j] = true;
					}
				}
			}
		}

		return result;
	}

	public static boolean check(char[][] b) {
		boolean bv = true;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				bv = true;
				char t = b[i][j];
				for (int x = 0; x < 3; x++) {
					if (j + x >= b[0].length) {
						bv = false;
						break;
					}
					if (b[i][j + x] != t) {
						bv = false;
						break;
					}
				}
				if (bv == true)
					return true;

				bv = true;

				for (int x = 0; x < 3; x++) {
					if (i + x >= b.length) {
						bv = false;
						break;
					}
					if (b[i + x][j] != t) {
						bv = false;
						break;
					}
				}
				if (bv == true)
					return true;
			}
		}

		return false;
	}

}
