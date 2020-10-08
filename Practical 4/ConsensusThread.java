

class ConsensusThread extends Thread{
	Consensus<Integer> o;
	ConsensusThread(Consensus<Integer> consensusObject){
		o = consensusObject;
	}
	public void run(){
		int gen = randBetween(0,49);
		o.propose(gen);
		try{this.sleep(ConsensusOptions.delay);}catch(InterruptedException e){}
		o.decide();
	}
	private int randBetween(int Min, int Max){
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}
}