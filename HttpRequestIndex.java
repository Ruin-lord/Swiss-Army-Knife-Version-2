import java.util.ArrayList;
public class HttpRequestIndex extends HttpRequest{
	protected ArrayList<HttpRequest> urlList;
	public HttpRequestIndex(){
		super();
		urlList=new ArrayList<HttpRequest>();}
	public Boolean readURL(String urlIn){
		Boolean returnValue=super.readURL(urlIn);
		String pageData=super.toString();
		while (pageData.indexOf("http")>-1){
			urlList.add(new HttpRequest(pageData.substring(pageData.indexOf("http"),pageData.indexOf(".json")+5)));
			pageData=pageData.substring(pageData.indexOf(".json")+5);}
		return returnValue;
	}
	public String toString(){
		String returnValue="";
		for (HttpRequest h:urlList){
			h.readURL();
            returnValue+=h.toString()+"\n";}
		return returnValue;
	}
}