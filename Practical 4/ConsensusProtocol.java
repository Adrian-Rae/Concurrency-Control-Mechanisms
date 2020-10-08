import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;

public abstract class ConsensusProtocol<T> implements Consensus<T>
{	private static final Logger LOGGER = Logger.getLogger("global");
	protected T[] proposed;

	public ConsensusProtocol(int threadCount)	{
		proposed = (T[]) new Object[threadCount];
	}

	public void propose(T value)	{
		int i = ThreadID.get();
		proposed[i]=value;
		LOGGER.info("Participant "+i+" buys a raffle ticket with random number "+value);
	}

	abstract public void decide();
}
