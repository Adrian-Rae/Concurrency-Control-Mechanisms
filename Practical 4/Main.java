import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("global");
    public static void main(String[] args) {
		
		configureLogger();
        LOGGER.setLevel(Level.FINEST);

        /*
        The scenario will need to run for the amount of runs
        specified in the ConsensusOptions.java file. You can overwrite
        the default ConsensusOptions values in this main method as follows:

            ConsensusOptions.delay = 50;

        This value will then reflect in all classes that access ConsensusOptions.delay.


        HINT - Make sure the thread IDs are 0 and 1 in each run.
        Take a look at the functions offered in ThreadID.java that you
        could use to enforce this.
        */

        ConsensusOptions.delay = 50;
        MemorySwapConsensus con = new MemorySwapConsensus(ConsensusOptions.threads);
        

        for(int i=0; i<ConsensusOptions.runs; i++){
            ConsensusThread[] u = new ConsensusThread[ConsensusOptions.threads]; 
            for(int o=0; o<ConsensusOptions.threads; o++){
                u[o] = new ConsensusThread(con);
                u[o].start();
            } 
		}

        
    }

    private static void configureLogger() {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
