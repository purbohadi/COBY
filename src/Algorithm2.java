// In Practice, You should use the statndard input/output
// in order to receive a score properly.
// Do not use file input and output. Please be very careful. 

import java.io.FileInputStream;
import java.util.Scanner;

class Algorithm2 {
	public static int[][] cost = new int[13][13];
	public static int[] available = new int[13];
	public static int[] route = new int[13];
	public static int optimal;
	public static int T;
	public static int N, Answer;
	public static int temp;

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(new FileInputStream("travel_input.txt"));

		T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {

			N = sc.nextInt();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (sc.hasNext())
						cost[i][j] = sc.nextInt();

				}
				available[i] = 0;
			}

			available[0] = 1;
			route[0] = 0;
			optimal = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.println(cost[i][j]);
				}
			}

			getOptimalLength(1, 0);

			Answer = optimal;

			if (optimal == Integer.MAX_VALUE)
				Answer = 0;
			// Print the answer to standard output(screen).
			System.out.println(Answer);
		}
	}

	public static void getOptimalLength(int num, int length) {
		if (num >= N) {
			if (cost[route[N - 1]][0] != 0) {
				temp = length + cost[route[N - 1]][0];

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
				if (available[i] == 0 && cost[route[num - 1]][i] != 0) {
					temp = length + cost[route[num - 1]][i];
					if (temp > optimal)
						continue;
					route[num] = i;
					available[i] = 1;
					getOptimalLength(num + 1, temp);
					available[i] = 0;
				}
			}
			return;
		}

	}
}
