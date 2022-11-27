package task;

import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		
		if(args[0].equalsIgnoreCase("-i")) {
			System.out.println("Running...");
			State test = new State(args[1]);
			test.start();
			
		}
		

	}

}
