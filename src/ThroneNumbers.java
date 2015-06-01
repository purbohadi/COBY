import java.io.FileInputStream;
import java.util.Scanner;


public class ThroneNumbers {
	static int Answer;

	/**
	 * @param args
	 */
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
		for (int test_case = 0; test_case < T; test_case++) {

			// ///////////////////////////////////////////////////////////////////////////////////////////
			/*
			 * Implement your algorithm here. The answer to the case will be
			 * stored in variable Answer.
			 */
			// ///////////////////////////////////////////////////////////////////////////////////////////
			long min = sc.nextLong();
			long max = sc.nextLong();
			Answer=0;
			if(min==max){
				if(Math.pow(sumOfDigits(min),2)==sumOfDigits(min*max))
					Answer=1;
			}else{
				
				for(long i=min;i<=max;i++){
					if(Math.pow(sumOfDigits(i),2)==sumOfDigits(i*i))
						Answer++;
					
				}
			}
			
			// Print the answer to standard output(screen).
			System.out.println("Case #" + (test_case + 1));
			System.out.println(Answer);
		}
	}
	
	private static long sumOfDigits(long number){
		long sum = 0;
		
		for(long i=number;i>0;i/=10){
			sum+=i%10;
		}
		
		return sum;
	}

}
