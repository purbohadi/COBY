import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class BestFriendNumbers {

	static int Answer,N,K;
	static long[] numbers;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("inputBestFriendNumbers.txt"));

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {
			
			Answer=0;
			N=sc.nextInt();
			K=sc.nextInt();
			numbers = new long[N];
			sc.nextLine();
			for (int i = 0; i < N; i++) {
				numbers[i]=sc.nextLong();
			}
			
			Arrays.sort(numbers);
			
			System.out.println("Case #"+(test_case+1)+" : "+countAtKDiff(numbers, N, K));
		}
	}
	
	public static int countAtKDiff(long[] array, int arraySize, int k) {

		int count = 0;

		for (int i = 0, j = 1; i < arraySize && j < arraySize;) {
			if (array[j] - array[i] == k) { // found a pair
				count++;
				i++;
				j++;
			} else if (array[j] - array[i] < k) { // difference is less than wanted so increment right pointer
				j++;
			} else { // difference is more than wanted so increment the left pointer 
				i++;
			}
		}

		return count;
	}	

	
}
