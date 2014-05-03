package ejb;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import model.Conference;
import util.XMLParser;
import util.HttpHelper;

@Stateless
public class ConferenceBean {
	private String domain = "http://59.78.3.25:8080/RMP/Entity/";
	public int add(String name, String begin, String end, String field, String description){
		int ret = 0;

		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.Name", name);
		xp.add("set", "this.Begin", begin);
		xp.add("set", "this.End", end);
		xp.add("set", "this.Field", field);
		xp.add("set", "this.Description", description);
		String xmlBody = xp.getXML();
		String url = domain + "iprs/Conference/";
		String resultXML = HttpHelper.SendHttpRequest("post", url, xmlBody);
		// TODO Process resultXML
		System.out.println(resultXML);
		return ret;
	}
	public List<Conference> getConferences(){
		String url = domain + "iprs/Conference/";
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<Conference> confs = Conference.parseXML(resultXML);
		return confs;
	}
}
