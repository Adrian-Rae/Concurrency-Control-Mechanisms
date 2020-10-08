//Practical assignment 3
//Student Number: Adrian Rae
//Student Name: 19004029

class Line{

	private CardUser[] l;
	private ATM a;
	private int n;
	public static boolean[] lineArray;

	static{
		lineArray = new boolean[TransactionOptions.lines];
		for(int i=0; i<TransactionOptions.lines; i++) lineArray[i] = false;
	}
	
	public Line(int numLines, ATM atm){
		this.a = atm;
		this.n = numLines;
		l = new CardUser[TransactionOptions.cardUsers];
		for(int i=0; i<TransactionOptions.cardUsers; i++){l[i] = new CardUser(atm);}
	}
	public void setLines(CardUser[] lines){
		this.l = lines;
	}
	public CardUser[] getLines(){
		return this.l;
	}

}