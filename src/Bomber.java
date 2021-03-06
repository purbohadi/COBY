import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
 
public class Bomber {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	
	static void solve()
	{
		int cas = 1;
		gouter:
		while(true){
			int n = ni(), m = ni();
			if(n == 0 && m == 0)break;
			char[][] s = nm(n,n);
			boolean[][] g = new boolean[n][n];
			out.print("Case#" + (cas++) + ": ");
			for(int i = 0;i < n;i++){
				for(int j = 0;j < n;j++){
					g[i][j] = s[i][j] == '.';
				}
			}
			
			outer:
			for(int i = 0;i < n;i++){
				for(int j = 0;j < n;j++){
					if(g[i][j])continue outer;
				}
				out.println("IMPOSSIBLE");
				continue gouter;
			}
			
			out.println(n-numMaximumMatching(g, new Random()));
		}
	}
	
	public static int numMaximumMatching(boolean[][] g, Random gen)
	{
		int n = g.length;
		final int mod = 1000000009;
		int[][] a = new int[n][n];
		for(int i = 0;i < n;i++){
			for(int j = i+1;j < n;j++){
				if(g[i][j]){
					a[i][j] = gen.nextInt(mod-1)+1;
					a[j][i] = mod-a[i][j];
				}
			}
		}
		return rank(a, mod)/2;
	}
	
	public static int rank(int[][] M, int p)
	{
		int n = M.length, m = M[0].length;
		
		// Forward Elimination
		for(int i = 0;i < n;i++){
			// select pivot
			boolean pivotFound = false;
			out:
			for(int pi = i;pi < n;pi++){
				for(int pj = i;pj < m;pj++){
					if(M[pi][pj] != 0){
						// pivot found
						if(pj != i){
							// swap columns
							for(int k = 0;k < m;k++){
								int d = M[k][pj]; M[k][pj] = M[k][i]; M[k][i] = d;
							}
						}
						if(pi != i){
							// swap rows
							int[] d = M[pi]; M[pi] = M[i]; M[i] = d;
						}
						pivotFound = true;
						break out;
					}
				}
			}
			if(!pivotFound)return i;
			
			long ID = invl(M[i][i], p);
			M[i][i] = 1;
			for(int j = i+1;j < m;j++){
				M[i][j] = (int)(M[i][j] * ID % p);
			}
			
			for(int j = i+1;j < n;j++){
				long B = p-M[j][i];
				M[j][i] = 0;
				for(int k = i+1;k < m;k++){
					M[j][k] = (int)((M[j][k] + M[i][k] * B) % p);
				}
			}
		}
		return n;
	}
	
	public static long invl(long a, long mod) {
		long b = mod;
		long p = 1, q = 0;
		while (b > 0){
			long c = a / b;
			long d;
			d = a;
			a = b;
			b = d % b;
			d = p;
			p = q;
			q = d - c * q;
		}
		return p < 0 ? p + mod : p;
	}
	
	public static void main(String[] args) throws Exception
	{
		long S = System.currentTimeMillis();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		solve();
		out.flush();
		long G = System.currentTimeMillis();
		tr(G-S+"ms");
	}
	
	private static boolean eof()
	{
		if(lenbuf == -1)return true;
		int lptr = ptrbuf;
		while(lptr < lenbuf)if(!isSpaceChar(inbuf[lptr++]))return false;
		
		try {
			is.mark(1000);
			while(true){
				int b = is.read();
				if(b == -1){
					is.reset();
					return true;
				}else if(!isSpaceChar(b)){
					is.reset();
					return false;
				}
			}
		} catch (IOException e) {
			return true;
		}
	}
	
	private static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;
	
	private static int readByte()
	{
		if(lenbuf == -1)throw new InputMismatchException();
		if(ptrbuf >= lenbuf){
			ptrbuf = 0;
			try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
			if(lenbuf <= 0)return -1;
		}
		return inbuf[ptrbuf++];
	}
	
	private static boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
	private static int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
	
	private static double nd() { return Double.parseDouble(ns()); }
	private static char nc() { return (char)skip(); }
	
	private static String ns()
	{
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	private static char[] ns(int n)
	{
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while(p < n && !(isSpaceChar(b))){
			buf[p++] = (char)b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
	
	private static char[][] nm(int n, int m)
	{
		char[][] map = new char[n][];
		for(int i = 0;i < n;i++)map[i] = ns(m);
		return map;
	}
	
	private static int[] na(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		return a;
	}
	
	private static int ni()
	{
		int num = 0, b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static long nl()
	{
		long num = 0;
		int b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}
 