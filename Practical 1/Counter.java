//Practical assignment 1
//Shared counter object
//Student Number: u19004029	
//Student Name: Adrian Rae
import java.util.concurrent.locks.ReentrantLock; 

class Counter {

	private int value;
	private ReentrantLock b;

	public Counter(int c) {
		value = c;
		b = new ReentrantLock();
	}

	public int getAndIncrement() {
		b.lock();
		try{
			//critical
			return value++;
		}
		finally{
			b.unlock();
		}
		
	}
}