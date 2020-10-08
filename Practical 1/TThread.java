//Practical assignment 1
//Thread created by extending the Thread class
//Student Number: u19004029
//Student Name: Adrian Rae
class TThread extends Thread {
	
	static Counter counter;

	TThread(){}
	TThread(Counter c){
		counter = c;
	}

	public void run() {
		//System.out.println("Thread is running..."); /*original*/
		for(int i=0; i<6; i++){ /*new*/
			try{
				this.sleep(550);
				int j = counter.getAndIncrement();
				System.out.println(this.getName()+" "+j+" "+this.getId());
			}
			catch(InterruptedException e){
				//Do something, I reckon.
			}
		}
	}


}