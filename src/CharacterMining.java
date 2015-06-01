import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class CharacterMining {

	static int Answer, M, N, W;
	static char[][] map;
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("inputCharacterMining.txt"));

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {
			M = sc.nextInt();
			N = sc.nextInt();
			map = new char[M][N];
			for (int i = 0; i < M; i++) {
				sc.nextLine();
				String line = sc.next();
				for (int j = 0; j < N; j++) {
					map[i][j]=line.charAt(j);
				}
			}
			
			W=sc.nextInt();
			ArrayList<String> words = new ArrayList<String>();
			System.out.println("Case #"+(test_case+1));
			for (int i = 0; i < W; i++) {
				sc.nextLine();
				System.out.println(findWord(sc.next()));
			}
			
		}

	}
	
	public static String findWord(String word){
		StringBuilder result = new StringBuilder();		
		int count=0;
		int strLength=word.length();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (i - strLength >= 0) {// check up
					result = new StringBuilder();
					
					for (int j2 = 0; j2 < strLength; j2++) {
						result.append(""+map[i-j2][j]);
					}
					
					if(result.toString().equalsIgnoreCase(word))
						return ""+(i+1)+" "+(j+1);
					
					if(j-strLength>=0){//left up 
						result = new StringBuilder();
						for (int j2 = 0; j2 > strLength; j2++) {
							result.append(""+map[i-j2][j-j2]);
						}
						
						if(result.toString().equalsIgnoreCase(word))
							return ""+(i+1)+" "+(j+1);
					}
					
					if(j+strLength<=N){//right down
						result = new StringBuilder();
						for (int j2 = 0; j2 < strLength; j2++) {
							result.append(""+map[i-j2][j+j2]);
						}
						if(result.toString().equalsIgnoreCase(word))
							return ""+(i+1)+" "+(j+1);
						
					}
				}

				if (i + strLength <= M) {// check down
					result = new StringBuilder();
					for (int j2 = 0; j2 < strLength; j2++) {
						result.append(""+map[i+j2][j]);
					}
					
					if(result.toString().equalsIgnoreCase(word))
						return ""+(i+1)+" "+(j+1);

					if(j-strLength>=0){//left down 
						result = new StringBuilder();
						for (int j2 = 0; j2 < strLength; j2++) {
							result.append(""+map[i+j2][j-j2]);
						}
						
						if(result.toString().equalsIgnoreCase(word))
							return ""+(i+1)+" "+(j+1);
					}
					
					if(j+strLength<=N){//right down
						result = new StringBuilder();
						for (int j2 = 0; j2 < strLength; j2++) {
							result.append(""+map[i+j2][j+j2]);
						}
						if(result.toString().equalsIgnoreCase(word))
							return ""+(i+1)+" "+(j+1);
						
					}
				}

				if (j + strLength <= N) {// check right
					result = new StringBuilder();
					for (int j2 = 0; j2 < strLength; j2++) {
						result.append(""+map[i][j+j2]);
					}
					
					if(result.toString().equalsIgnoreCase(word))
						return ""+(i+1)+" "+(j+1);
				}

				if (j - strLength >= 0) {// check up
					result = new StringBuilder();
					for (int j2 = 0; j2 < strLength; j2++) {
						result.append(""+map[i][j-j2]);
					}
					if(result.toString().equalsIgnoreCase(word))
						return ""+(i+1)+" "+(j+1);
				}
				
			}
		}
		
		
		return ""+1;
	}
	

}
