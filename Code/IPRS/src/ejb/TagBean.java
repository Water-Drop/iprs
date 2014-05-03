package ejb;

import javax.ejb.Stateless;

import util.XMLParser;
import util.HttpHelper;

@Stateless
public class TagBean {
	private String domain = "http://59.78.3.25:8080/RMP/Entity/";
	public int add(String pid, String tag){
		int ret = 0;
		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.Pid", pid);
		xp.add("set", "this.Tag", tag);
		String xmlBody = xp.getXML();
		String url = domain + "iprs/Tag/";
		String resultXML = HttpHelper.SendHttpRequest("post", url, xmlBody);
		// TODO Process resultXML
		System.out.println(resultXML);
		return ret;
	}
}
