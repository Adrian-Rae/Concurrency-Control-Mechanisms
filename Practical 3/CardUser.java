//Practical assignment 3
//Student Number: Adrian Rae
//Student Name: 19004029

class CardUser extends Thread {

	private ATM a;

	public static double maxDeposit = 500.00;
    public static double minDeposit = 50.00;
    public static double maxWithdrawal = 1000.00;
    public static double minWithdrawal = 200.00;
	
	public CardUser(ATM atm){this.a = atm;}
	public void setATM(ATM b){this.a = b;}
	public ATM getATM(){return this.a;}
	public void run(){
		int randomOperation = (int) Math.round(Math.random());
		
		if(randomOperation == 1){ //is a withdrawal
			double amount = randBetween(this.minWithdrawal,this.maxWithdrawal);
			try{this.sleep(TransactionOptions.withdrawalSleepTime);}catch(InterruptedException e){}	
			this.a.withdraw((float) amount,ThreadID.get());
		}
		else{ //is a deposit
			double amount = randBetween(this.minDeposit,this.maxDeposit);
			try{this.sleep(TransactionOptions.depositSleepTime);}catch(InterruptedException e){}	
			this.a.deposit((float) amount,ThreadID.get());	
		}

	}
	private double randBetween(double Min, double Max){
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}

}