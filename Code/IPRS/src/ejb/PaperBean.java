/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: PaperBean.java
 * Date		: May 3, 2014
 */
package ejb;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import model.Authors;
import model.Keywords;
import model.Paper;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import util.HttpHelper;
import util.XMLParser;



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
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		xp.add("set", "this.LMTime", fmt.format(paper.getLMTime()));
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
		String url = domain + "iprs/Papers/" + pid;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		Paper paper = new Paper();
		try {
			StringReader read = new StringReader(resultXML);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element p = doc.getRootElement();
	        if (null != p){
	        	
	        	Element paperUri = p.getChild("uri");
	        	Element paperTitle = p.getChild("Title");
	        	Element paperAbstract = p.getChild("Abstract");
	        	Element paperCid = p.getChild("Cid");
	        	Element paperStatus = p.getChild("Status");
                Element paperLMTime = p.getChild("LMTime");
                Element paperUid = p.getChild("Uid");
                Element paperLocation = p.getChild("Location");
	        	
	        	paper.setUri(paperUri.getText());
	        	paper.setTitle(paperTitle.getText());
	        	paper.setAbstract(paperAbstract.getText());
	        	paper.setCid(paperCid.getText());
	        	paper.setStatus(Integer.valueOf(paperStatus.getText()));
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
	        	paper.setLMTime(sdf.parse(paperLMTime.getText()));
	        	paper.setUid(paperUid.getText());
	        	paper.setLocation(paperLocation.getText());
	        }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return paper;
	}
	
	public List<Paper> getAll()
	{
		String url = domain + "iprs/Papers/";
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		System.out.println(resultXML);
		List<Paper> ps = Paper.parseXML(resultXML);
		System.out.println(ps);
		return ps;
	}
	
	public List<Paper> getPaperbyCondition(String title, String abst, String author) {
		List<Paper> papers = new ArrayList<Paper>();
		PaperBean pb = new PaperBean();
		if (null != author){
			String url = domain + "iprs/Authors/?Authors.Name=(like)" + author;
			String resultXML = HttpHelper.SendHttpRequest("get", url, null);
			List<Authors> as = Authors.parseXML(resultXML);

			for (int i = 0; i < as.size(); i++){
				Paper p = pb.get(as.get(i).getPid());
				papers.add(p);
			}
		} else {
			papers = pb.getAll();
		}
		if (null != title)
			for(int i = 0; i < papers.size(); i++){
				if (papers.get(i).getTitle().contains(title) == false){
					papers.remove(i);
				}
			}
		if (null != abst){
			for(int i = 0; i < papers.size(); i++){
				if (papers.get(i).getAbstract().contains(abst) == false){
					papers.remove(i);
				}
			}
		}
		return papers;
	}
	
	public List<Paper> getByUid(String uid)
	{
		String url = domain + "iprs/Papers/?Papers.Uid=" + uid;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<Paper> ps = Paper.parseXML(resultXML);
		return ps;
	}

	public int edit(Paper paper)
	{
		XMLParser xp = new XMLParser("put");
		String url = domain + paper.getUri();
		xp.add("set", "this.Title", paper.getTitle());
		xp.add("set", "this.Abstract", paper.getAbstract());
		int s = paper.getStatus() > 0 ? paper.getStatus() : ((-1) * paper.getStatus());
		xp.add("set", "this.Status", String.valueOf(s));
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		xp.add("set", "this.LMTime", fmt.format(d));
		xp.add("set", "this.Location", paper.getLocation());
		HttpHelper.SendHttpRequest("put", url, xp.getXML());
		return 0;
	}
	
	public int withdraw(String pid)
	{
		XMLParser xp = new XMLParser("put");
		xp.add("set", "this.Status", "-10");
		String url = domain + "iprs/Papers/" + pid;
		HttpHelper.SendHttpRequest("put", url, xp.getXML());
		return 0;
	}
	
	public int lock(String pid)
	{
		String url = domain + "iprs/Papers/" + pid;
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
