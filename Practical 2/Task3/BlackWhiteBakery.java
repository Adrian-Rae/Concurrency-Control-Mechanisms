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

class BlackWhiteBakery implements Lock {
	
	private volatile boolean[] flag;
	private volatile boolean colour;
	private volatile int[] number;
	private volatile boolean[] mycolour;

	private int n;
	private static final Logger LOGGER = Logger.getLogger("global");

	public BlackWhiteBakery	(int n) {
		this.n = n;
		this.flag = new boolean[n];
		this.number = new int[n];
		this.mycolour = new boolean[n];
		this.colour = false;
		for(int i=0; i<n; i++){ this.flag[i] = false; this.number[i] = 0;}
	}

	public void lock() {
		// @todo: COMPLETE THIS FUNCTION
		int i = ThreadID.get();
		//doorway
		this.flag[i] = true;
		this.mycolour[i] = this.colour;
		this.number[i] = 1+max(this.mycolour[i]);
		LOGGER.fine("[LOCK]: (id:"+i+",number:"+this.number[i]+",color:"+((this.mycolour[i])?"White":"Black")+") => ticket assigned");
		this.flag[i] = false; //end doorway
		for(int p=0; p<n; p++){
			while(this.flag[p]){} //wait for flag to be assigned
			//wait until threads ahead of me are served
			if(mycolour[p]==mycolour[i]) while(this.number[p]!=0 && this.mycolour[p]==this.mycolour[i] && pairGreaterThan(this.number[p],p,this.number[i],i)){}
			else while(this.number[p]!=0 && this.mycolour[i] == this.colour && this.mycolour[p]!=this.mycolour[i]){}
		}
		LOGGER.finer("[LOCK]: (id:"+i+",number:"+this.number[i]+",color:"+((this.mycolour[i])?"White":"Black")+") => entering CS");
		//LOGGER.finer("[LOCK]: (id:"+i+",label:"+label[i]+") => entering CS");
	}

	public void unlock() {
		// @todo: COMPLETE THIS FUNCTION
		int i = ThreadID.get();
		if(this.mycolour[i]==false){
			this.colour = true;
		}
		else this.colour = false;
		number[i] = 0;
		LOGGER.finer("[LOCK]: (id:"+i+",number:"+this.number[i]+",color:"+((this.mycolour[i])?"White":"Black")+") => left CS, shared color:"+((this.colour)?"White":"Black"));
		
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

	private int max(boolean colour){
		int max = 0;
		for(int i=0; i<n; i++){
			int number = this.number[i];
			if(this.mycolour[i]==colour && max<number) max = number;
		}
		return max;
	}

	private boolean pairGreaterThan(int ax,int ay, int bx, int by){
		boolean k = (ax<bx)||(ax==bx && ay<by);	
		if(k) LOGGER.finest("[LOCK]: assess priority => (id:"+ay+",number:"+ax+",color:"+((this.mycolour[ay])?"White":"Black")+") > (id:"+by+",number:"+bx+",color:"+((this.mycolour[by])?"White":"Black")+")");
		else LOGGER.finest("[LOCK]: assess priority => (id:"+by+",number:"+bx+",color:"+((this.mycolour[by])?"White":"Black")+") > (id:"+ay+",number:"+ax+",color:"+((this.mycolour[ay])?"White":"Black")+")");
		return k;
	}

}
