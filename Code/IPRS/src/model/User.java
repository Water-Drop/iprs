package model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class User {
	private String uri;
	private String Username;
	private String Password;
	private String Email;
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	static public List<User> parseXML(String xml)
	{
		List<User> ret = new ArrayList<User>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> userList = collection.getChildren("User");
	        for(int i = 0, j = userList.size();i < j; i++)  
	        {
	        	Element userE = userList.get(i);
	        	Element userUri = userE.getChild("uri");
	        	Element userName = userE.getChild("Username");
	        	Element userPW = userE.getChild("Password");
	        	Element userMail = userE.getChild("Email");
	        	User userM = new User();
	        	userM.setUri(userUri.getText());
	        	userM.setUsername(userName.getText());
	        	userM.setPassword(userPW.getText());
	        	userM.setEmail(userMail.getText());
	        	ret.add(userM);
	        }
		} catch (Exception e) {
			// do nothing
		}
        return ret;
	}
}
