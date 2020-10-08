//Practical assignment 3
//Student Number: Adrian Rae
//Student Name: 19004029


import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;
import java.text.*;

class ATM {

	private float balance;
	private Lock lock;
	private int nThreads;
	private static final Logger LOGGER = Logger.getLogger("global");
	private float format;

	public ATM(float amount, int numThreads) {
		// @todo: COMPLETE THIS FUNCTION
		this.balance = amount;
		this.nThreads = numThreads;
		this.lock = new FilterLock(numThreads);
	}

	boolean withdraw(float amount, int threadId) {
		boolean isSuccess = false;	// indicates if withdrawal transaction was successful
		float newBalance = 0;

		// @todo: COMPLETE THIS FUNCTION
		lock.lock();
		try{
			if(amount<=balance){
				isSuccess = true;
				newBalance = this.balance-amount;
				this.balance = newBalance;	
			}
		}
		finally{
			if(isSuccess) LOGGER.info("Thread-"+threadId+" withdrawing R"+String.format("%.02f", amount)+" from ATM, R"+String.format("%.02f", this.balance)+" remaining");
			else LOGGER.info("Thread-"+threadId+" withdrawal of R"+String.format("%.02f", amount)+" not possible, insufficient funds. R"+String.format("%.02f", this.balance)+" remaining");
			lock.unlock();
		}
		return isSuccess;
	}

	void deposit(float amount, int threadId) {
		// @todo: COMPLETE THIS FUNCTION
		float newBalance = 0;
		lock.lock();
		try{
			newBalance+=this.balance+amount;
			this.balance = newBalance;
		
		}
		finally{
			LOGGER.info("Thread-"+threadId+" depositing R"+String.format("%.02f", amount)+" to ATM, R"+String.format("%.02f", this.balance)+" remaining");
			lock.unlock();
		}
			
	}

	float getATMBalance()
	{
		return balance;
	}

}
