import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Algorithm {
	static int N, d, min, cost;
	static int[][] A;
	static int[] B, C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < T; tc++) {
			d = 0;
			N = Integer.parseInt(br.readLine().trim());
			A = new int[N][N];
			B = new int[N + 1];
			C = new int[N];
			for (int i = 0; i < N; i++) {
				String[] ith = br.readLine().trim().split(" ");
				for (int j = 0; j < ith.length; j++) {
					A[i][j] = Integer.parseInt(ith[j]);
				}
				C[i] = 0;
			}
			B[0] = 0;
			cost = 0;
			Hamilton(1, 0);
			System.out.println(min);
		}
		br.close();
	}

	public static void Hamilton(int i, int e) {
		if (i == N) {
			if (A[B[i - 1]][e] > 0) {
				B[i] = e;
				d++;
				if (d < 2) {
					min = airFare();
				} else {
					int airFare = airFare();
					if (airFare < min) {
						min = airFare;
					}
				}
			}
			return;
		}
		for (int j = 0; j < N; j++) {
			if (j != e && A[B[i - 1]][j] > 0 && C[j] == 0) {
				if (d > 1 && cost + A[B[i - 1]][j] > min) {
					continue;
				}
				cost += A[B[i - 1]][j];
				B[i] = j;
				C[j] = 1;
				Hamilton(i + 1, e);
				C[j] = 0;
				cost -= A[B[i - 1]][j];			
			}
		}
	}

	public static int airFare() {
		int rs = 0;
		for (int i = 0; i < N; i++) {
			rs += A[B[i]][B[i + 1]];
		}
		return rs;
	}
}