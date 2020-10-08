//Practical assignment 3
//Student Number: Adrian Rae
//Student Name: 19004029

import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;


class ThreadATMDemo {
	private static final Logger LOGGER = Logger.getLogger("global");
	
	public static void main (String args[]) {

		// set up logger properties
		configureLogger();
		// set the types of log messages to be displayed
		// options (for this practical): OFF, INFO, FINE, FINER, FINEST, ALL
		LOGGER.setLevel(Level.FINEST);

		float startingBalance = 2260;
		ATM a = new ATM(startingBalance,TransactionOptions.lines*TransactionOptions.cardUsers);
		Line[] l = new Line[TransactionOptions.lines];

		for(int x=0; x<TransactionOptions.lines; x++){
			l[x] = new Line(TransactionOptions.lines,a);
		}

		//for(int i=0; i<TransactionOptions.cardUsers; i++){
			for(int j=0; j<TransactionOptions.lines; j++){
				l[j].getLines()[0].start();	
			}
		//}

	}

	private static void configureLogger() {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}