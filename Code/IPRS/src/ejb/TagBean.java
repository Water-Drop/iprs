package ejb;

import java.util.List;

import javax.ejb.Stateless;

import model.Tag;
import util.HttpHelper;
import util.XMLParser;

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
	public List<Tag> getTagbyPaper(String pid){
		String url = domain + "iprs/Tag/?Tag.Pid=" + pid;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<Tag> ts = Tag.parseXML(resultXML);
		return ts;
	}
}
