//Practical assignment 2
//Student Number: Adrian Rae
//Student Name: 19004029

class TThread extends Thread {
	
	Account account;
	
	TThread(Account acc) {
		account = acc;
	}

	public void run() {
		//System.out.println("Thread running...");
		for(int i=0; i<TThreadOptions.numRuns; i++){
			this.account.withdraw(TThreadOptions.withdrawAmount,this.getName());
			try{this.sleep(TThreadOptions.sleepTime);}catch(InterruptedException e){
				//Do something, I reckon.
			}
		}
		this.account.deposit(TThreadOptions.depositAmount,this.getName());
		
	}
}