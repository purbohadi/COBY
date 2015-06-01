import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TheGreatDivide {

	static int[] power,minPeople,maxPeople;
	static boolean[] minPower;
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		//Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		int n,total;
		for(int test_case = 0; test_case < T; test_case++) {

			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
			   Implement your algorithm here.
			   The answer to the case will be stored in variable Answer.
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////
			// Answer = 0;
			n=sc.nextInt();
			total=0;
			power= new int[100+10];
			for (int i = 0; i < n; i++) {
				power[i]=sc.nextInt();
				total+=power[i];
			}
			
			minPower = new boolean[100000/2+100];
			minPeople = new int[100000/2+100];
			maxPeople = new int[100000/2+100];
			
			for (int i = 0; i < 100000/2+100; i++) {
				minPeople[i]= Integer.MAX_VALUE;
				maxPeople[i]=0;
			}
			minPower[0]=true;
			minPeople[0]=0;
			
			for (int i = 0; i < n; i++) {
				for (int j = total/2+5; j >=0; j--) {
					if(minPower[j]){
						minPower[j+power[i]]=true;
						if(minPeople[j+power[i]]>minPeople[j]+1)
							minPeople[j+power[i]]=minPeople[j]+1;
						if(maxPeople[j+power[i]]<maxPeople[j]+1)
							maxPeople[j+power[i]]=maxPeople[j]+1;
					}
				}
			}

			System.out.println("Case #"+(test_case+1));
			for (int i = total/2; i >=0; i--) {
				if (minPower[i]&&(minPeople[i]<=n/2&&maxPeople[i]>=n/2)||
						(minPeople[i]<=n/2+n%2 && maxPeople[i]>=n/2+n%2)) {
					System.out.println(total-i);
					System.out.println(i);
					break;
				}
			}


			// Print the answer to standard output(screen).
			//System.out.println("Case #"+(test_case+1));
			//System.out.println(Answer);
		}		

	}

}
