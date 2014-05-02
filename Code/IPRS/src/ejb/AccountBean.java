package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import model.User;
import util.XMLParser;
import util.HttpHelper;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AccountBean {
	private String domain = "http://59.78.3.25:8080/RMP/Entity/";
	
	public boolean isExist(String username)
	{
		boolean ret = false;
		HttpHelper hhp = new HttpHelper();
		String url = domain + "iprs/User/?User.Username=" + username;
		String resultXML = hhp.SendHttpRequest("get", url, null);
		List<User> us = User.parseXML(resultXML);
		if (us.size() > 0)
			ret = true;
		return ret;
	}
	
	public boolean check(String username, String password)
	{
		boolean ret = false;
		HttpHelper hhp = new HttpHelper();
		String url = domain + "iprs/User/?User.Username=" + username;
		String resultXML = hhp.SendHttpRequest("get", url, null);
		List<User> us = User.parseXML(resultXML);
		if (us.size() == 0)
			ret = false;
		else if (us.get(0).getPassword().equals(password))
			ret = true;
		return ret;
	}
	
	public boolean add(String username, String password)
	{
		boolean ret = false;
		if (isExist(username) || password == null || password.length() < 6)
			return ret;
		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.Username", username);
		xp.add("set", "this.Password", password);
		String xmlBody = xp.getXML();
		HttpHelper hhp = new HttpHelper();
		String url = domain + "iprs/User/";
		String resultXML = hhp.SendHttpRequest("post", url, xmlBody);
		// TODO Process resultXML
		System.out.println(resultXML);
		return ret;
	}
}
