package EJB;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import Util.XMLParser;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AccountBean {
	public boolean isExist(String username)
	{
		boolean ret = false;
		// http://localhost:8080/RMP/Entity/iprs/User/?User.Username= 传入参数username
		// visit above url to check username
		return ret; 
	}
	
	public boolean check(String username, String password)
	{
		boolean ret = false;
		// http://localhost:8080/RMP/Entity/iprs/User/?User.Username= 传入参数username
		// visit above url to get username and password
		
		return ret;
	}
	
	public boolean add(String username, String password)
	{
		boolean ret = false;
		if (isExist(username) || username == null || password == null)
			return ret;
		// POST http://localhost:8080/RMP/Entity/iprs/User/
		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.Username", username);
		xp.add("set", "this.Password", password);
		String xmlBody = xp.getXML();
		// TODO
		
		return ret;
	}
}
