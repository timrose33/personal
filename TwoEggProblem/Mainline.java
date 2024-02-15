//package egg.problem;

public class Mainline {

	public static void main(String[] args) {
		int HEIGHT = 1000;
		int EGGS = 10;
		int START_POSITION = 1;
		
		EggProblemTestThread [] [] array = new EggProblemTestThread [HEIGHT + 1] [EGGS + 1];
		
		for (int h = START_POSITION; h <= HEIGHT; h++) {
			for  (int e = 1; e <= EGGS; e++ ) {
				array[ h] [e] =  new EggProblemTestThread (h, e);
			}
		}
		
		for (int h = START_POSITION; h <= HEIGHT; h++) {
			for  (int e = 1; e <= EGGS; e++ ) {
				array[ h] [e]. run();
			}
		}
		
		for (int h = START_POSITION; h <= HEIGHT; h++) {
			for  (int e = 1; e <= EGGS; e++ ) {
				try {
					array[ h] [e]. join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		System.out.print("Building Height / Eggs");
		
		for  (int e = 1; e <= EGGS; e++ ) {
			System.out.printf ("%5d ", e);
		}
		System.out.println();
		
		for (int h = START_POSITION; h <= HEIGHT; h++) {
			System.out.printf("%4d %17s", h, " floors     ");
			for  (int e = 1; e <= EGGS; e++ ) {
				System.out.printf ("%5d ", array[ h] [e].getResult());
			}
			System.out.println();
		}
		

	}

}
