import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class FollowTheDots {
	
	public static double Answer;
	public static double[][] map;
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner sc = new Scanner(new FileInputStream("inputFollowTheDots.txt"));
		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {
		
			int N = sc.nextInt();
			map = new double[N][N];
			for (int i = 0; i < N; i++) {
				sc.nextLine();
				for (int j = 0; j < 2; j++) {
					map[i][j]=sc.nextDouble();
				}
			}
			
//			Arrays.sort(map, new Comparator<double[]>() {
//			    public int compare(double[] a, double[] b) {
//			        return Double.compare(a[1], b[1]);
//			    }
//			});
			bubbleSortArr(map, 1);
			
			Answer=0;
			for (int i = 1; i < N; i++) {
				Answer+=countDistance(map[i-1][0], map[i-1][1], map[i][0], map[i][1]);
			}
			
//			System.out.println(String.format( "%.2f", Answer));			
			System.out.println("Case #"+(test_case+1)+" : "+String.format( "%.2f", Answer));
		}
		
	}
	
	
	public static double countDistance(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2))*100/100;
	}
	

	public static double[][] bubbleSortArr(double[][] inArr, int idx){
	    
	    double[][] temp = new double[inArr.length][inArr[0].length];
	    boolean finished = false;
	    
	    while (!finished) {
		finished=true;
		for (int i = 0; i < inArr.length-1; i++) {
		    if (inArr[i][idx]>=inArr[i+1][idx]) {
			for (int j = 0; j < inArr[i].length; j++) {
			    temp[i][j]=inArr[i][j];
			    inArr[i][j]=inArr[i+1][j];
			    inArr[i+1][j]=temp[i][j];
			}
			finished=true;
		    }
		}
	    }
	    return inArr;
	}
	
}


