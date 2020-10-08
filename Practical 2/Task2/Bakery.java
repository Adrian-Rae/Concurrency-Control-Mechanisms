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

class Bakery implements Lock {
	
	private volatile boolean[] flag;
	private volatile int[] label;
	private int n;
	private static final Logger LOGGER = Logger.getLogger("global");
	private int max;

	public Bakery(int n) {
		this.n = n;
		this.flag = new boolean[n];
		this.label = new int[n];
		for(int i=0; i<n; i++){
			this.flag[i] = false;
			this.label[i] = 0;
		}
		this.max = 0;
	}

	public void lock() {
		// @todo: COMPLETE THIS FUNCTION
		int i = ThreadID.get();
		this.flag[i] = true;
		this.label[i] = ++this.max;
		LOGGER.fine("[LOCK]: (id:"+i+",label:"+label[i]+") => ticket assigned");
		while(existsGreaterPriority(i)){}
		LOGGER.finer("[LOCK]: (id:"+i+",label:"+label[i]+") => entering CS");
	}

	public void unlock() {
		// @todo: COMPLETE THIS FUNCTION
		int i = ThreadID.get();
		LOGGER.finer("[LOCK]: (id:"+i+",label:"+label[i]+") => left CS");
		this.flag[i] = false;
		
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

	private boolean existsGreaterPriority(int i){
		boolean j = false;
		for(int p=0; p<n; p++){
			boolean defer = (this.label[p]<this.label[i])||((this.label[p]==this.label[i])&&(p<i));
			if(p!=i && defer && this.flag[p]){
				LOGGER.finest("[LOCK]: assess priority => (id:"+p+",label:"+this.label[p]+") > (id:"+i+",label:"+this.label[i]+")");
				j = true;
			}
			else if (p!=i && !defer && this.flag[p]){
				LOGGER.finest("[LOCK]: assess priority => (id:"+i+",label:"+this.label[i]+") > (id:"+p+",label:"+this.label[p]+")");
			}
		}
		return j;
	}

}
