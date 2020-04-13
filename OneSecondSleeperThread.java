public class OneSecondSleeperThread extends Thread{
	private int sleepNumber;
	OneSecondSleeperThread(int sleepNumberIn){
		sleepNumber=sleepNumberIn;}
	public void sleep(){
		System.out.println(sleepNumber+" - Going to sleep");
		try{
			Thread.sleep(1000);
		}catch(Exception e){
			System.out.println("Exception: "+e);}
		System.out.println("... "+sleepNumber+" - Done sleeping");
	}
	public void run(){
		sleep();
	}
}