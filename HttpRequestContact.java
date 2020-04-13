public class HttpRequestContact extends HttpRequest implements Runnable{
	protected String firstName;
	protected String lastName;
	protected String preferredName;
	protected String email;
	private String firstNameStatus="**Valid**";
	private String lastNameStatus="**Valid**";
	private String preferredNameStatus="**Null**";
    private String emailStatus="**Valid**";
	public HttpRequestContact(String urlIn){
		super(urlIn);
		firstName="";
		lastName="";
		preferredName="";
		email="";
	}
	public Boolean load(){
		Boolean returnValue=false;
		System.out.println("\nLoading: "+requestURL);
		if(readURL()){
			parse();
			returnValue=true;}
		return returnValue;
	}
	public void parse(){
		for(String s:urlContent){
			String[] rawContact=s.split("\"");
			if(rawContact.length>=4){
				if(rawContact[1].equals("firstName"))firstName=rawContact[3];
				if(rawContact[1].equals("lastName"))lastName=rawContact[3];
				if(rawContact[1].equals("preferredName"))preferredName=rawContact[3];
				if(rawContact[1].equals("email"))email=rawContact[3];
			}
		}
	}
	public void validate(){
		boolean passed=true;
		System.out.println("Validating: "+requestURL);
		if(urlContent.size()<1){
			System.out.println("JSON FILE COULD NOT BE LOADED");
			firstNameStatus="**Invalid**";
			lastNameStatus="**Invalid**";
			preferredNameStatus="**Invalid**";
			emailStatus="**Invalid**";
			passed=false;
		}else{
			if(!(firstName.length()>=2 && firstName.length()<=16)){
				firstNameStatus="**Invalid**";
				passed=false;}
			if(!(lastName.length()>=2 && lastName.length()<=16)){
				lastNameStatus="**Invalid**";
				passed=false;}
			if(!preferredName.equals("")){
				if(!(preferredName.length()>=2 && preferredName.length()<=16)){
					preferredNameStatus="**Invalid**";
					passed=false;}
				else preferredNameStatus="**Valid**";}
			if(!isValid(email)){
				emailStatus="**Invalid**";
				passed=false;}
		}
		if(passed==true)System.out.println("Validation: <PASSED>");
		else System.out.println("Validation: <FAILED>");
		System.out.println("\n"+this);
	}
	public String toString(){
		String returnString="firstName: "+firstName+"\n";
		returnString+="lastName: "+lastName+"\n";
		returnString+="preferredName: "+preferredName+"\n";
		returnString+="email: "+email+"\n";
		returnString+="\n";
		returnString+="firstNameStatus: "+firstNameStatus+"\n";
		returnString+="lastNameStatus: "+lastNameStatus+"\n";
		returnString+="preferredNameStatus: "+preferredNameStatus+"\n";
		returnString+="emailStatus: "+emailStatus+"\n";
		returnString+="\n"+super.toString()+"\n";
		return returnString;
	}
	public void run(){
		load();}
	static boolean isValid(String email){
		String regex="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
}