import java.util.Stack;
import java.util.LinkedList;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;

public class StackConsensus<T> extends ConsensusProtocol<T>{
	private static final Logger LOGGER = Logger.getLogger("global");
	private Stack<Integer> stack;
	
	public StackConsensus(int threadCount){
		super(threadCount);
		stack = new Stack<Integer>();
		
		LinkedList<Integer> l = new LinkedList<Integer>();
		for(int i=0; i<threadCount; i++){
			double k = Math.random();
			if(k>=0.5) l.addFirst(i);
			else l.addLast(i);
		}
		for(int j=0; j<threadCount; j++){
			int c = l.get(j);
			stack.push(c);
		}

		//LOGGER.info(stack.toString());
	}
	public void propose(Integer value){
	}
	public void decide(){
		int opt = stack.peek();
		int i = ThreadID.get();
		LOGGER.info("Participant "+i+" sees the winning raffle number is "+proposed[opt]);
	}
	public void displayStackContents(){
		String s = "";
		for (int i=0; i<stack.size(); i++){
			s+=" "+stack.get(i);
		}
		LOGGER.fine("Stack:"+s);
	}
}