//Practical assignment 2
//Student Number: Adrian Rae	
//Student Name: 19004029
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;

class PetersonLock implements Lock {
	
	private volatile boolean[] flag;
	private volatile int victim;
	private static final Logger LOGGER = Logger.getLogger("global");

	public PetersonLock() {
		this.flag = new boolean[2];
	}

	public void lock() {
		// @todo: COMPLETE THIS FUNCTION
		int i = ThreadID.get();
		int j = 1-i;
		this.flag[i]=true;
		victim = i;
		LOGGER.fine("[Lock] Thread "+ThreadID.get()+" is victim");
		while(flag[j] && (victim==i)){}
		LOGGER.fine("[Lock] Thread "+ThreadID.get()+" entering CS (thread "+victim+" is victim)");
	}

	public void unlock() {
		// @todo: COMPLETE THIS FUNCTION
		int i = ThreadID.get();
		this.flag[i] = false;
		LOGGER.fine("[Lock] Thread "+ThreadID.get()+" leaving CS");
	}

	// Any class implementing Lock must provide these methods
	public Condition newCondition() {
		throw new java.lang.UnsupportedOperationException();
	}

	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		throw new java.lang.UnsupportedOperationException();
	}

	public boolean tryLock() {
		throw new java.lang.UnsupportedOperationException();
	}

	public void lockInterruptibly() throws InterruptedException {
		throw new java.lang.UnsupportedOperationException();
	}
}
