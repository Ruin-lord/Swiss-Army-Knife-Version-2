import java.util.ArrayList;
public class sak{
	static float startTime;
	static float elapsedTime;
	public static void main(String[]args){
		if(args.length<1){
			runHelp();}
		else if(args[0].equalsIgnoreCase("-Help")){
			runHelp();}
		else if(args[0].equalsIgnoreCase("-HttpRequest")){
			runHttpRequest(args);}
		else if(args[0].equalsIgnoreCase("-HttpRequestIndex")){
			runHttpRequestIndex(args);}
		else if(args[0].equalsIgnoreCase("-JSONValidateIndex")){
			runJSONValidateIndex(args,false);}
		else if(args[0].equalsIgnoreCase("-JSONValidateIndexThreaded")){
			runJSONValidateIndex(args,true);}
		else if(args[0].equalsIgnoreCase("-Sleep")){
			runSleep();}
		else if(args[0].equalsIgnoreCase("-SleepFast")){
			runSleepFast();}
		else if(args[0].equalsIgnoreCase("-SleepFastImplementsRunnable")){
			runSleepFastImplementsRunnable();}
	}
	private static void runHelp(){
		startTime=System.nanoTime();
		System.out.println("\nRunning Help");
		System.out.println("\nThis program requires the use of command line functions, please see below:");
      System.out.println("\nHow to Copy/Paste:");
      System.out.println("To Select The Text : Left Click and Hold at the start of the line, then slide the mouse to the other end of the line and release.");
		System.out.println("To Copy Press [ctrl + c]");
		System.out.println("To Paste Press [ctrl + v]");
		System.out.println("\nHelp menu:");
		System.out.println("java sak -Help");
		System.out.println("\nHttpRequest examples:");
		System.out.println("java sak -HttpRequest https://www.youtube.com");
		System.out.println("java sak -HttpRequest https://www.reddit.com");
      System.out.println("java sak -HttpRequest https://store.steampowered.com");
      System.out.println("\nJSONValidateIndex examples:");
		System.out.println("java sak -JSONValidateIndex https://thunderbird-index.azurewebsites.net/w0a6zk195d.json");
		System.out.println("\nHttpRequestIndex examples:");
		System.out.println("java sak -HttpRequestIndex https://thunderbird-index.azurewebsites.net/w0a6zk195d.json");
		System.out.println("\nJSONValidateIndexThreaded examples:");
		System.out.println("java sak -JSONValidateIndexThreaded https://thunderbird-index.azurewebsites.net/w0a6zk195d.json");
		elapsedTime=(System.nanoTime()-startTime)/1000000000;
		System.out.format("\n-Help took %f seconds to run",elapsedTime);
	}
	private static void runHttpRequestIndex(String[]args){
		System.out.println("\nRunning -HttpRequestIndex");
		startTime=System.nanoTime();
		if(args.length<2){
			System.out.println("-HttpRequestIndex must be followed by a URL");
		}else{
			String URL=args[1];
			HttpRequestIndex request=new HttpRequestIndex();
			if(request.readURL(URL))System.out.println(request);
			else System.out.println("Please use a valid URL");}
		elapsedTime=(System.nanoTime()-startTime)/1000000000;
		System.out.format("\n-HttpRequestIndex took %f seconds to run",elapsedTime);
   }
   private static void runHttpRequest(String[]args){
		System.out.println("\nRunning -HttpRequest");
		startTime=System.nanoTime();
		if(args.length<2){
			System.out.println("-HttpRequest must be followed by a URL");
		}else{
			String URL=args[1];
			HttpRequest request=new HttpRequest();
			if(request.readURL(URL))System.out.println(request);
			else System.out.println("Please use a valid URL");}
		elapsedTime=(System.nanoTime()-startTime)/1000000000;
		System.out.format("\n-HttpRequest took %f seconds to run",elapsedTime);
	}
	private static void runJSONValidateIndex(String[]args,boolean isThreaded){
		if(isThreaded)System.out.println("\nRunning -JSONValidateIndexThreaded");
		else System.out.println("\nRunning -JSONValidateIndex");
		startTime=System.nanoTime();
		if(args.length<2){
			System.out.println("-JSONValidateIndex must be followed by a URL");
		}else{
			String URL=args[1];
			JSONValidateIndex request=new JSONValidateIndex();
			if(isThreaded)request.validateURLThreaded(URL);
			else request.validateURL(URL);}
		elapsedTime=(System.nanoTime()-startTime)/1000000000;
		System.out.format("\n-JSONValidateIndex took %f seconds to run",elapsedTime);
	}
	private static void runSleepFast(){
		System.out.println("Starting Sleep");
		OneSecondSleeperThread sleeper0=new OneSecondSleeperThread(0);
		OneSecondSleeperThread sleeper1=new OneSecondSleeperThread(1);
		System.out.println("\nNon-threaded Sleep");
		long start=System.currentTimeMillis();
		sleeper0.sleep();
		sleeper1.sleep();
		System.out.println("Elapsed time="+(System.currentTimeMillis()-start)+"\n");
		System.out.println("\nThreaded Sleep");
		start=System.currentTimeMillis();
		sleeper0.start();
		sleeper1.start();
		try{
			sleeper0.join();
			sleeper1.join();
		}catch(Exception e){
			System.out.println("Exception"+e);}
		System.out.println("Elapsed time="+(System.currentTimeMillis()-start)+"\n");
		ArrayList<OneSecondSleeperThread> sleeperList=new ArrayList<OneSecondSleeperThread>();
		for(int i=2;i<12;i++){
			sleeperList.add(new OneSecondSleeperThread(i));}
		System.out.println("\nNon-threaded ArrayList sleep:");
		start=System.currentTimeMillis();
		for(OneSecondSleeperThread s:sleeperList){
			s.sleep();}
		System.out.println("Elapsed time="+(System.currentTimeMillis()-start));
		System.out.println("\nThreaded ArrayList sleep:");
		start=System.currentTimeMillis();
		for(OneSecondSleeperThread s:sleeperList){
			s.start();}
		try{
			for(OneSecondSleeperThread s:sleeperList){
				s.join();}
		}catch(Exception e){
			System.out.println("Exception: "+e);}
		System.out.println("Elapsed time="+(System.currentTimeMillis()-start));
   }
   private static void runSleep(){
		System.out.println("Starting Sleep");
		OneSecondSleeper sleeper0=new OneSecondSleeper(0);
		OneSecondSleeper sleeper1=new OneSecondSleeper(1);
		System.out.println("\nSleep");
		long start=System.currentTimeMillis();
		sleeper0.sleep();
		sleeper1.sleep();
		System.out.println("Elapsed time="+(System.currentTimeMillis()-start)+"\n");
	}
	private static void runSleepFastImplementsRunnable(){
		System.out.println("Starting Sleep");
		OneSecondSleeperRunnable sleeper0=new OneSecondSleeperRunnable(0);
		OneSecondSleeperRunnable sleeper1=new OneSecondSleeperRunnable(1);
		System.out.println("\nNon-threaded Sleep");
		long start=System.currentTimeMillis();
		sleeper0.sleep();
		sleeper1.sleep();
		System.out.println("Elapsed time="+(System.currentTimeMillis()-start)+"\n");
		System.out.println("\nThreaded Sleep");
		start=System.currentTimeMillis();
		Thread t0=new Thread(sleeper0);
		Thread t1=new Thread(sleeper1);
		t0.start();
		t1.start();
		try{
			t0.join();
			t1.join();
		}catch(Exception e){
			System.out.println("Exception"+e);}
		System.out.println("Elapsed time="+(System.currentTimeMillis()-start)+"\n");
		ArrayList<OneSecondSleeperRunnable> sleeperList=new ArrayList<OneSecondSleeperRunnable>();
		for(int i=2;i<12;i++){
			sleeperList.add(new OneSecondSleeperRunnable(i));}
		System.out.println("\nNon-threaded ArrayList sleep:");
		start=System.currentTimeMillis();
		for(OneSecondSleeperRunnable s:sleeperList){
			s.sleep();}
		System.out.println("Elapsed time="+(System.currentTimeMillis()-start));
		System.out.println("\nThreaded ArrayList sleep:");
		ArrayList<Thread> threadList=new ArrayList<Thread>();
		for(OneSecondSleeperRunnable s:sleeperList){
			threadList.add(new Thread(s));}
		start=System.currentTimeMillis();
		for(Thread t:threadList){
			t.start();
		}try{
			for(Thread t:threadList){
				t.join();}
		}catch(Exception e){
			System.out.println("Exception: "+e);}
		System.out.println("Elapsed time="+(System.currentTimeMillis()-start));
	}
}