//Practical assignment 1
//Note the different ways in which the threads are initialised
//Student Number: u19004029
//Student Name: Adrian Rae
class ThreadDemo {
	
	public static void main (String args[]) {
		TThread t1 = new TThread(); 
		t1.start();
		
		Thread t2 = new Thread(new TRunnable()); 
		t2.start();
	}
}