//Practical assignment 3
//Student Number: Adrian Rae	
//Student Name: 19004029
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;

class FilterLock implements Lock {

	private int n;
	private static final Logger LOGGER = Logger.getLogger("global");

	private volatile int[] level;
	private volatile int[] victim;

	public FilterLock(int levels) {
		this.n = levels;
		this.level = new int[this.n];
		this.victim = new int[this.n];
		for(int i=0; i<this.n; i++){level[i]=0; victim[i]=-1;}
		printLines();
	}

	public void lock() {
		// @todo: COMPLETE THIS FUNCTION
		int i=ThreadID.get();

		boolean lineDefer = Line.lineArray[i];


		LOGGER.fine("Thread-"+i+" waiting to perform transaction");
		try{Thread.sleep(Math.round(10*Math.random()));}catch(InterruptedException e){} //wait before attempting to acquire
		for(int L=1; L < this.n; L++){
			level[i] = L;
			victim[L] = i;
			while(this.mustDefer(i,L)){
				try{Thread.sleep(Math.round(10*Math.random()));}catch(InterruptedException e){}
			}
		}
		while(lineDefer){}//wait if line has gone
		LOGGER.fine("Thread-"+i+" performing transaction on the ATM(entering CS)");
		for(int j=1; j<this.n; j++){ if(victim[j] >= 0 && victim[j]!=i) LOGGER.fine("Thread-"+victim[j]+" is a victim"); }
		Line.lineArray[i] = true;
	}

	public void unlock() {
		// @todo: COMPLETE THIS FUNCTION	
		int i=ThreadID.get();
		LOGGER.fine("Thread-"+i+" exiting");
		printLines();
		level[i]=0;
	}

	public boolean mustDefer(int i, int L){
		for(int j=0; j<this.n; j++){
			if(j!=i && level[j]>=L && victim[L]==i) return true;
		}
		return false;
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

	public void printLines(){
		boolean allFull = true;
		LOGGER.info("Lines that used the ATM:");
		for(int i=0; i<TransactionOptions.lines; i++){
			allFull = allFull&&Line.lineArray[i];
			LOGGER.info("["+(Line.lineArray[i]?"o":"x")+"]");	
		}
		if(allFull){
			LOGGER.info("All lines used the ATM, resetting:");
			for(int i=0; i<TransactionOptions.lines; i++){
				Line.lineArray[i] = false;
				LOGGER.info("["+(Line.lineArray[i]?"o":"x")+"]");
			}
		}
	}

}
