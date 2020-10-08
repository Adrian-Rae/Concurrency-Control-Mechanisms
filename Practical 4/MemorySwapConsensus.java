import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;
import java.util.concurrent.atomic.AtomicInteger;

public class MemorySwapConsensus extends ConsensusProtocol{
	private static final Logger LOGGER = Logger.getLogger("global");
	private final int FIRST = -1;
	private AtomicInteger[] a;
	private AtomicInteger r;
	private AtomicInteger k;
	private int n;
	
	public MemorySwapConsensus(int threadCount){
		super(threadCount);
		a = new AtomicInteger[threadCount];
		for(int i=0; i<threadCount; i++){
			a[i] = new AtomicInteger(0);
		}
		r = new AtomicInteger(1);
		k = new AtomicInteger(-1);
		n = threadCount;
		
	}
	public void propose(Integer value){
	}
	public void decide(){
		int i = ThreadID.get();
		atomicSwap();

		if(i==k.get()){
			Object p = proposed[i];
			LOGGER.info("Participant "+i+" sees the winning raffle number is "+p);
			
		}
		else{
			Object p = proposed[k.get()];
			LOGGER.info("Participant "+i+" sees the winning raffle number is "+p);
		}
		printRegisterContents();
		
	}

	private void atomicSwap(){
		int i = ThreadID.get();
		synchronized(this){
			if(r.get()==1) k.set(i);
			int temp = r.get();
			r.set(a[i].get());
			a[i].set(temp);
		}
	}

	public void printRegisterContents(){
		int p =ThreadID.get();
		String s = " ";
		for(int i=0; i<n; i++){
			s+=a[i].get()+" ";
		}
		LOGGER.fine("Register @ Participant "+p+": ["+s+"]");
	}
}