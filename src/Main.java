import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        
//        Thread example = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                long amount = 1000000;
//                
//                for(int i = 0; i < amount; i++) {}
//                
//                System.out.println("Thread is finished");
//            }
//        });
//        
//        example.start();
//        
    	B b= new B();
        System.out.println("Thread is running "+ b.str);
        
    }
}

class A{
	static String str="A";
	void over(){
		
	}
}

class B extends A{
	static String str="B";
	void over() {
	}
}