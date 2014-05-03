package ejb;

import javax.ejb.Stateless;

import util.HttpHelper;
import util.XMLParser;

@Stateless
public class RoleBean {
	private String domain = "http://59.78.3.25:8080/RMP/Entity/";
	public int add(String uid, String cid, int type){
		int ret = 0;
		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.Uid", uid);
		xp.add("set", "this.Cid", cid);
		xp.add("set", "this.Type", String.valueOf(type));
		String xmlBody = xp.getXML();
		String url = domain + "iprs/Role/";
		String resultXML = HttpHelper.SendHttpRequest("post", url, xmlBody);
		// TODO Process resultXML
		System.out.println(resultXML);
		return ret;
	}
}
