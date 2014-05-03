/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: TaskBean.java
 * Date		: May 3, 2014
 */
package ejb;

import java.util.List;

import util.HttpHelper;
import util.XMLParser;
import model.Task;

public class TaskBean {
private String domain = "http://59.78.3.25:8080/RMP/Entity/";
	
	public int add(List<Task> ts)
	{
		if (0 == ts.size())
			return 1;
		// reset previous task
		Task t0 = ts.get(0);
		String url = domain + "iprs/Task/?Task.Pid=" + t0.getPid() + " & Task.Status=0";
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<Task> dts = Task.parseXML(resultXML);
		for (int i = 0, j = dts.size(); i < j; i ++)
		{
			XMLParser xp = new XMLParser("put");
			xp.add("set", "this.Status", "1");
			String xmlBody = xp.getXML();
			url = domain + dts.get(i).getUri();
			HttpHelper.SendHttpRequest("put", url, xmlBody);
		}
		// add new task
		for (int i = 0, j = ts.size(); i < j; i ++)
		{
			t0 = ts.get(i);
			XMLParser xp = new XMLParser("post");
			xp.add("set", "this.Pid", t0.getPid());
			xp.add("set", "this.Uid", t0.getUid());
			xp.add("set", "this.Order", String.valueOf(t0.getOrder()));
			xp.add("set", "this.Status", "0");
			String xmlBody = xp.getXML();
			url = domain + "iprs/Task/";
			HttpHelper.SendHttpRequest("post", url, xmlBody);
		}
		return 0;
	}
}
