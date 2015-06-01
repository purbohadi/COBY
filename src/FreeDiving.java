import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FreeDiving {
	static int Answer, N, M, L, G;
	static int[][] map;
	static int[] available, route;
	static int optimal;
	static int temp;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("inputFreeDiving.txt"));

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][M];
			available = new int[N + 1];
			route = new int[N + 1];
			for (int i = 0; i < N; i++) {
				sc.nextLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			route[0] = 0;
			L = sc.nextInt();
			G = sc.nextInt();
			optimal = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
				}
			}
			

//			getOptimalLength(1, 0, G);

			Answer = optimal;
			if (optimal == L)
				Answer = 0;

			System.out.println("Case #" + (test_case + 1) + " : " + Answer);
		}

	}

	public static void getOptimalLength(int num, int length, int timeLeft) {
		if (num == N) {
			if (map[route[N - 1]][0] != 0) {
				temp = length + map[route[N - 1]][0];

				if (temp < optimal) {
					optimal = temp;

					System.out.print(optimal + " : ");
					for (int i = 0; i < N; i++) {
						System.out.print(route[i] + " - ");
					}
					System.out.println(route[0]);

				}

			}
			return;
		} else {
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int distance = map[route[num - 1]][j]
							- map[route[num - 1]][j - 1];
					if (available[i] == 0 && map[route[num]][i] != 0) {
						temp = length + map[route[num - 1]][i];
						if (temp > optimal)
							continue;
						route[num] = i;
						available[i] = 1;
						L -= temp;
						int time = (int) (timeLeft - Math.pow(distance, 2));
						getOptimalLength(num + 1, temp, time);
						available[i] = 0;
					}
					
				}
			}
			return;
		}

	}

}
