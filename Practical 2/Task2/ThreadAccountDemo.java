//Practical assignment 2
//Student Number: Adrian Rae
//Student Name: 19004029

import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;


class ThreadAccountDemo {
	private static final Logger LOGGER = Logger.getLogger("global");
	
	public static void main (String args[]) {

		// set up logger properties
		configureLogger();
		// set the types of log messages to be displayed
		// options (for this practical): OFF, INFO, FINE, FINER, FINEST, ALL
		LOGGER.setLevel(Level.FINER);

		// examples of logging with different log levels
		/* 	  (make sure to remove these lines before submitting your assignment)
		LOGGER.fine("This is a fine message");
		LOGGER.finer(String.format("(sample id:%d, sample name: %s)", 3, "SomeThread"));
		*/

		// set up shared Account
		Lock lock = new Bakery(4);
		float startingBalance = 2260;
		Account acc = new Account(startingBalance, lock);

		// specify your own option values for thread runs here
		TThreadOptions.numRuns = 3;
		TThreadOptions.withdrawAmount = 400;
		TThreadOptions.depositAmount = 500;

		TThread t1 = new TThread(acc);
		t1.start();
		
		TThread t2 = new TThread(acc);
		t2.start();

		TThread t3 = new TThread(acc);
		t3.start();

		TThread t4 = new TThread(acc);
		t4.start();
	}

	private static void configureLogger() {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}