//Practical assignment 1
//Two threads can access a shared Counter
//Student Number: u19004029
//Student Name: Adrian Rae
class ThreadCounterDemo {
	
	public static void main (String args[]) {

		// shared Counter object
		Counter C = new Counter(1);

		// create threads
		TThread t1 = new TThread(C);
		TThread t2 = new TThread(C);

		// start the threads
		t1.start();
		t2.start();

		// wait for all threads to complete
		try {
			t1.join();
			t2.join();
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}
}