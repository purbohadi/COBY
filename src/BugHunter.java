import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BugHunter {

	static int Answer, N, maxStart;
	static int[][] bugs;
	static List<Integer[]> list;
	static int[] max;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("inputBugHunter.txt"));

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {

			N = sc.nextInt();
			bugs = new int[N][2];
			int start = 0;
			maxStart = -1;

			for (int i = 0; i < N; i++) {
				sc.nextLine();
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						start = sc.nextInt();
						bugs[i][j] = start;
					} else
						bugs[i][j] = sc.nextInt();

					if (start > maxStart)
						maxStart = start;

				}
			}

			Comparator<Integer[]> comparator = new Comparator<Integer[]>() {

				public int compare(Integer[] a, Integer[] b) {
					return a[0] - b[0];
				}
			};

			list = new ArrayList<Integer[]>();

			for (int i = 0; i < N; i++) {
				list.add(toObject(bugs[i]));
			}

			Collections.sort(list, comparator);

			for (int i = 0; i < N; i++) {
				bugs[i] = toPrimitive(list.get(i));
			}

			max = new int[N];
			Arrays.fill(max, -1);

			System.out
					.println("Case #" + (test_case + 1) + " : " + countMax(0));

		}

	}

	public static int countMax(int index) {

		if (index >= N)
			return 1;
		if (max[index] != -1)
			return max[index];
		if (bugs[index][1] > maxStart)
			max[index] = (int) Math.max(1, countMax(index + 1));
		else {
			int next = binarySearch(index + 1, N - 1, bugs[index][1]);
			max[index] = (int) Math.max(1 + countMax(next), countMax(index + 1));
		}
		return max[index];
	}

	public static int binarySearch(int left, int right, int value) {
		while (true) {
			if (right - left < 2) {
				return bugs[left][0] >= value ? left : right;
			}
			int mid = (left + right) / 2;
			if (bugs[mid][0] > value)
				right = mid;
			else if (bugs[mid][0] < value)
				left = mid + 1;
			else
				right = mid;
		}
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
