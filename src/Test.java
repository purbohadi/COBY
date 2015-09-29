public class Test {
	public static void main(String[] args) {
		// Integer intObj=Integer.valueOf(args[args.length-1]);
		// int i = intObj.intValue();
		//
		// System.out.println(args.length);
		//	      
		// if(args.length > 1)
		// System.out.println(i);
		// if(args.length > 0)
		// System.out.println(i - 1);
		// else
		// System.out.println(i - 2);

		int i = 0;
		boolean t = true;
		boolean f = false, b;
		b = (t | ((i++) == 0));
		b = (f | ((i+=2) > 0));
		System.out.println(i);			}

}
