/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: PaperBean.java
 * Date		: May 3, 2014
 */
package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import model.Authors;
import model.Keywords;
import model.Paper;
import util.XMLParser;
import util.HttpHelper;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PaperBean {
	private String domain = "http://59.78.3.25:8080/RMP/Entity/";
	
	public int add(Paper paper, List<Authors> as, List<Keywords> ks)
	{
		String url = domain + "iprs/Papers/";
		// Paper
		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.Title", paper.getTitle());
		xp.add("set", "this.Abstract", paper.getAbstract());
		xp.add("set", "this.Cid", paper.getCid());
		xp.add("set", "this.Status", paper.getStatus().toString());
		xp.add("set", "this.LMTime", paper.getLMTime().toString());
		xp.add("set", "this.Uid", paper.getUid());
		xp.add("set", "this.Location", paper.getLocation());
		String resultXML = HttpHelper.SendHttpRequest("post", url, xp.getXML());
		String uri = xp.getPostCreated(resultXML);
		paper.setUri(uri.replaceAll(domain, ""));
		System.out.println(paper.getUri()); // console
		String pId = paper.getUri().replaceFirst("iprs/Papers/", "");
		// Authors
		url = domain + "iprs/Authors/";
		for (int i = 0, j = as.size(); i < j; i ++)
		{
			Authors a = as.get(i);
			a.setPid(pId);
			xp = new XMLParser("post");
			xp.add("set", "this.Name", a.getName());
			xp.add("set", "this.Pid", a.getPid());
			xp.add("set", "this.Identity", a.getIdentity().toString());
			HttpHelper.SendHttpRequest("post", url, xp.getXML());
		}
		// Keywords
		url = domain + "iprs/Keywords/";
		for (int i = 0, j = ks.size(); i < j; i ++)
		{
			Keywords k = ks.get(i);
			k.setPid(pId);
			xp = new XMLParser("post");
			xp.add("set", "this.Name", k.getKeyword());
			xp.add("set", "this.Pid", k.getPid());
			HttpHelper.SendHttpRequest("post", url, xp.getXML());
		}
		return 0;
	}
	
	public Paper get(String pid)
	{
		String url = domain + "iprs/User/" + pid;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<Paper>ps = Paper.parseXML(resultXML);
		Paper paper = new Paper();
		paper = ps.get(0);
		return paper;
	}

	public int edit(Paper paper)
	{
		XMLParser xp = new XMLParser("put");
		String url = domain + paper.getUri();
		xp.add("set", "this.Title", paper.getTitle());
		xp.add("set", "this.Abstract", paper.getAbstract());
		xp.add("set", "this.Cid", paper.getCid());
		int s = paper.getStatus() > 0 ? paper.getStatus() : ((-1) * paper.getStatus());
		xp.add("set", "this.Status", String.valueOf(s));
		xp.add("set", "this.LMTime", paper.getLMTime().toString());
		xp.add("set", "this.Uid", paper.getUid());
		xp.add("set", "this.Location", paper.getLocation());
		HttpHelper.SendHttpRequest("put", url, xp.getXML());
		return 0;
	}
	
	public int withdraw(String pid)
	{
		XMLParser xp = new XMLParser("put");
		xp.add("set", "this.Status", "-10");
		String url = domain + "iprs/Paper/" + pid;
		HttpHelper.SendHttpRequest("put", url, xp.getXML());
		return 0;
	}
	
	public int lock(String pid)
	{
		String url = domain + "iprs/Paper/" + pid;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<Paper> ps = Paper.parseXML(resultXML);
		XMLParser xp = new XMLParser("put");
		int s = ps.get(0).getStatus();
		s = (s > 0) ? s : -s;
		xp.add("set", "this.Status", String.valueOf(s));
		HttpHelper.SendHttpRequest("put", url, xp.getXML());
		return 0;
	}
}
