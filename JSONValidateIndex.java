import java.util.ArrayList;
public class JSONValidateIndex extends HttpRequestIndex{
	protected ArrayList<HttpRequestContact> contactList;
	private ArrayList<Thread> threadList;
	public JSONValidateIndex(){
		super();
		contactList=new ArrayList<HttpRequestContact>();
		threadList=new ArrayList<Thread>();}
	public void validateURL(String urlIn){
		requestURL=urlIn;
		if(super.readURL(requestURL)){
			for(HttpRequest h:urlList){
				contactList.add(new HttpRequestContact(h.requestURL));}
			for(HttpRequestContact h:contactList){
				h.load();
				h.validate();}
		}else{
			System.out.println("INVALID URL");}
	}
	public void validateURLThreaded(String urlIn){
		int count=0;
		requestURL=urlIn;
		if(super.readURL(requestURL)){
			for(HttpRequest h:urlList){
				contactList.add(new HttpRequestContact(h.requestURL));}
			for(HttpRequestContact h:contactList){
				threadList.add(new Thread(h));
				threadList.get(count).start();
				count++;
			}try{
				for(Thread t:threadList){
					t.join();}
			}catch(Exception e){
				System.out.println(e);}
			for(HttpRequestContact h:contactList){
				h.validate();}
		}else{
			System.out.println("INVALID URL");
		}
	}
}