import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Exception;


public class MLMPem {

	static int Answer, N;
	static int[][] map;
	static StringBuffer sb;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * 
	 */
	public static void main(String[] args) throws Exception,FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("inputMLM.txt"));
		int T = sc.nextInt();
		sb = new StringBuffer();
		for (int test_case = 0; test_case < T; test_case++) {

			// ///////////////////////////////////////////////////////////////////////////////////////////
			/*
			 * Implement your algorithm here. The answer to the case will be
			 * stored in variable Answer.
			 */
			// ///////////////////////////////////////////////////////////////////////////////////////////
			Answer = 0;
			N = sc.nextInt();
			map = new int[N][N];
			long[] bonus = new long[N];
			
			for (int i = 0; i < N; i++) {
				bonus[i]=0;
			}

			
			for (int i = 0; i < N; i++) {
				sc.nextLine();
				String line = sc.next();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			
			boolean end, noDown;
			long temp;
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					end=false;
					noDown=true;
					temp=0;
					
					for (int j = 0; j < N; j++) {
						if(map[i][j]==1){
							if(bonus[j]==0){
								end=true;
								break;
							}
							temp+=bonus[j];
							noDown=false;
						}
					}
					if(end)continue;
					
					if(noDown)bonus[i]=1;
					else bonus[i]=temp;
				}
			}

			for (int i = 0; i < N; i++) {
				Answer += bonus[i];
			}

			// Print the answer to standard output(screen).
			System.out.println("Case #" + (test_case + 1) + " : $" + Answer);
		}
	}

}
