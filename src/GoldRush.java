import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GoldRush {

	static int Answer, M, N;
	static char val;

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("inputGoldRush.txt"));

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {

			M = sc.nextInt();
			N = sc.nextInt();
			ArrayList<Gold> golds=new ArrayList<Gold>();

			for (int i = 0; i < M; i++) {
				sc.nextLine();				
				String line = sc.next();
				for (int j = 0; j < N; j++) {
					if(line.charAt(j)=='*')
						golds.add(new Gold(i, j));
				}
			}
			
			ArrayList<Gold> group = new ArrayList<Gold>();
			int numberGroup=0;
			int maxSize=0;

			while (!golds.isEmpty()) {// check each Gold until golds is full
				group.clear();
				int size = 1;
				group.add(golds.remove(0));
				numberGroup++;
				while (!group.isEmpty()) {// check one by one until group is full
					
					Gold Gold=group.remove(0);
					Gold st=new Gold(Gold.x-1, Gold.y);//check left
					if (golds.contains(st)) {
						golds.remove(st);
						group.add(st);
						size++;
					}

					st=new Gold(Gold.x-1, Gold.y-1);//check left up
					if (golds.contains(st)) {
						golds.remove(st);
						group.add(st);
						size++;
					}
					
					st=new Gold(Gold.x-1, Gold.y+1);//check left down
					if (golds.contains(st)) {
						golds.remove(st);
						group.add(st);
						size++;
					}

					st=new Gold(Gold.x+1, Gold.y);//check right
					if (golds.contains(st)) {
						golds.remove(st);
						group.add(st);
						size++;
					}

					st=new Gold(Gold.x+1, Gold.y-1);//check right up
					if (golds.contains(st)) {
						golds.remove(st);
						group.add(st);
						size++;
					}

					st=new Gold(Gold.x+1, Gold.y+1);//check right down
					if (golds.contains(st)) {
						golds.remove(st);
						group.add(st);
						size++;
					}
					
					st=new Gold(Gold.x, Gold.y-1);//check up
					if (golds.contains(st)) {
						golds.remove(st);
						group.add(st);
						size++;
					}

					st=new Gold(Gold.x, Gold.y+1);//check down
					if (golds.contains(st)) {
						golds.remove(st);
						group.add(st);
						size++;
					}
				}
				
				if (size>maxSize) {
					maxSize=size;
				}
			}
			
			System.out.println(numberGroup);
			
			
		}

	}

	static class Gold{
		
		int x,y;
		
		public Gold(int x, int y) {
			super();
			this.x=x;
			this.y=y;
		}
		
		public boolean equals(Object object){
			if (object instanceof Gold) {
				if (this.x==((Gold)object).x && this.y == ((Gold)object).y) {
					return true;
				}
			}
			
			return false;
		}
	}	
	
}
