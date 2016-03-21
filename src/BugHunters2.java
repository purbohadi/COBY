import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BugHunters2 {

	static int N;
	static int[][] bugs;
	static List<int[]> list;
	static Comparator<int[]> comparator;
	static int[] max;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("inputBugHunter.txt"));

		comparator = new Comparator<int[]>() {

			public int compare(int[] a, int[] b) {
				return a[0]>b[0]?a[0]:b[0];
			}
		};

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {

			N = sc.nextInt();
			bugs = new int[N][2];
			int start = 0;

			for (int i = 0; i < N; i++) {
				sc.nextLine();
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						start = sc.nextInt();
						bugs[i][j] = start;
					} else
						bugs[i][j] = sc.nextInt();
				}
			}

			list = new ArrayList<int[]>();

			for (int i = 0; i < N; i++) {
				list.add(bugs[i]);
			}

			Collections.sort(list, comparator);

			for (int i = 0; i < N; i++) {
				bugs[i] = list.get(i);
			}

			max = new int[N];
			max[0]=1;
			
			for (int i = 1; i < N; i++) {
				int m=0;
				for (int j = i-1; j >=0; j--) {
					if(bugs[j][1]<bugs[i][0]&&max[j]>m)
						m=max[j];
				}
				max[i]=m+1;
			}
			
			System.out
					.println("Case #" + (test_case + 1) + " : " + max[N-1]);

		}
	}


}
