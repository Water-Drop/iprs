package ejb;

import java.util.List;

import javax.ejb.Stateless;

import model.User;
import util.HttpHelper;

@Stateless
public class UserBean {
	private String domain = "http://59.78.3.25:8080/RMP/Entity/";
	public List<User> getAllUsers(){
		String url = domain + "iprs/User/";
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<User> users = User.parseXML(resultXML);
		return users;
	}
}
