//Practical assignment 2
//Student Number: Adrian Rae
//Student Name: 19004029


import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;
import java.text.*;

class Account {

	float balance;
	Lock lock;
	private static final Logger LOGGER = Logger.getLogger("global");
	private float format;

	Account(float amount, Lock mylock) {
		// @todo: COMPLETE THIS FUNCTION
		this.balance = amount;
		this.lock = mylock;
		this.format = 2.001f;
	}

	boolean withdraw(float amount, String threadName) {
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
			if(isSuccess) LOGGER.info(threadName+" R"+String.format("%.02f", amount)+" withdrawn from account, R"+String.format("%.02f", newBalance)+" remaining");
			else LOGGER.info(threadName+" Not enough money, R"+String.format("%.02f", this.balance)+" remaining");
			lock.unlock();
		}
		return isSuccess;
	}

	void deposit(float amount, String threadName) {
		// @todo: COMPLETE THIS FUNCTION
		float newBalance = 0;
		lock.lock();
		try{
			newBalance+=this.balance+amount;
			this.balance = newBalance;
		
		}
		finally{
			LOGGER.info(threadName+" R"+String.format("%.02f", amount)+" deposited into account, R"+String.format("%.02f", newBalance)+" remaining");
			lock.unlock();
		}
			
	}

	float getBalance()
	{
		return balance;
	}

}
