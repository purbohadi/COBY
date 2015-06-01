import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;


public class Primadonuts {
	
	static int N,C,Answer;
	static Vector primes;

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("inputPrimadonuts.txt"));

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {
			
			sc.nextLine();
			N= sc.nextInt();
			C= sc.nextInt();
			primes = new Vector();
			for (int i = 1; i <= N; i++) {
				if(isPrime(i))
					primes.add(i);
			}
			int range = 0;
			int size= primes.size();
			if(size%2==0)
				range=C*2;
			else	
				range=C*2-1;

			System.out.print("Case #" + (test_case + 1)+ " : ");
			int index = 0;
			for (int i = 0; i < range; i++) {
				index = i+((size-range)/2);
				if(index<size)
					if(index<0){
						for (int j = 0; j < size; j++) {
							System.out.print((int)primes.get(j)+" ");
						}
						break;
					}else
						System.out.print((int)primes.get(index)+" ");
				else
					break;
			}
			System.out.println();
			
		}
	}
	
	public static boolean isPrime(long n) {
	    long end = (long)Math.sqrt(n) + 1;
	    for (int i = 2; i < end; ++i) {
	        if (n % i == 0) return false;
	    }
	    return true;
	}	

}
