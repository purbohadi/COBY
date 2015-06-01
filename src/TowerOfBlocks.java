import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TowerOfBlocks {

	static int Answer, N;
	static int[][] blocks;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new FileInputStream("inputTowerOfBlocks.txt"));

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			blocks = new int[N][3];
			for (int i = 0; i < N; i++) {
				sc.nextLine();
				for (int j = 0; j < 3; j++) {
					blocks[i][j] = sc.nextInt();
				}
			}

			System.out.println("Case #" + (test_case + 1) + " : "
					+ maxStackHeight(blocks, N));
		}

	}

	public static long maxStackHeight(int[][] blocks, int n) {

		int[][] temp = new int[3 * n][3 * n];

		int idx = 0;

		for (int i = 0; i < n; i++) {

			temp[idx] = blocks[i];
			idx++;

			// 1 rotate
			temp[idx][0] = blocks[i][2];
			temp[idx][1] = Math.max(blocks[i][0], blocks[i][1]);
			temp[idx][2] = Math.min(blocks[i][0], blocks[i][1]);
			idx++;

			// 2 rotate
			temp[idx][0] = blocks[i][1];
			temp[idx][1] = Math.max(blocks[i][0], blocks[i][2]);
			temp[idx][2] = Math.min(blocks[i][0], blocks[i][2]);
			idx++;
		}

		n = 3 * n;

		Comparator<Integer[]> comparator = new Comparator<Integer[]>() {

			@Override
			public int compare(Integer[] a, Integer[] b) {
				// TODO Auto-generated method stub
				return ((b[1] * b[2]) - (a[1] * a[2]));
			}
		};

		List<Integer[]> list = new ArrayList<Integer[]>();

		for (int i = 0; i < n; i++) {
			list.add(toObject(temp[i]));
		}

		Collections.sort(list, comparator);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				temp[i] = toPrimitive(list.get(i));
			}
		}

		// init max for all idx
		long[] max = new long[n];
		for (int i = 0; i < n; i++) {
			max[i] = temp[i][0];
		}

		// compute optim max val
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (((temp[i][2] < temp[j][1] && temp[i][1] < temp[j][2]))
						&& max[i] < (max[j] + temp[i][0]))

					max[i] = max[j] + temp[i][0];
			}
		}

		// pick max val
		long maxH = -1;
		for (int i = 0; i < n; i++) {
			if (maxH < max[i])
				maxH = max[i];
		}

		return maxH;

	}

	public static Integer[] toObject(int[] intArray) {

		Integer[] result = new Integer[intArray.length];
		for (int i = 0; i < intArray.length; i++) {
			result[i] = Integer.valueOf(intArray[i]);
		}
		return result;
	}

	public static int[] toPrimitive(Integer[] IntegerArray) {

		int[] result = new int[IntegerArray.length];
		for (int i = 0; i < IntegerArray.length; i++) {
			result[i] = IntegerArray[i].intValue();
		}
		return result;
	}
}
