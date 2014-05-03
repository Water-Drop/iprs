package model;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class Paper {
	private String uri;
	private String Title;
	private String Abstract;
	private String Cid;
	private Integer Status;
	private Date LMTime;
	private String Uid;
	private String Location;
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getAbstract() {
		return Abstract;
	}
	public void setAbstract(String abstract1) {
		Abstract = abstract1;
	}
	public String getCid() {
		return Cid;
	}
	public void setCid(String cid) {
		Cid = cid;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	public Date getLMTime() {
		return LMTime;
	}
	public void setLMTime(Date lMTime) {
		LMTime = lMTime;
	}
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	
	static public List<Paper> parseXML(String xml)
	{
		List<Paper> ret = new ArrayList<Paper>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> paperList = collection.getChildren("Paper");
	        for(int i = 0, j = paperList.size();i < j; i++)  
	        {
	        	Element paperE = paperList.get(i);
	        	Element paperUri = paperE.getChild("uri");
	        	Element paperTitle = paperE.getChild("Title");
	        	Element paperAbstract = paperE.getChild("Abstract");
	        	Element paperCid = paperE.getChild("Cid");
	        	Element paperStatus = paperE.getChild("Status");
                Element paperLMTime = paperE.getChild("LMTime");
                Element paperUid = paperE.getChild("Uid");
                Element paperLocation = paperE.getChild("Location");
	        	Paper paperM = new Paper();
	        	paperM.setUri(paperUri.getText());
	        	paperM.setTitle(paperTitle.getText());
	        	paperM.setAbstract(paperAbstract.getText());
	        	paperM.setCid(paperCid.getText());
	        	paperM.setStatus(Integer.valueOf(paperStatus.getText()));
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
	        	paperM.setLMTime(sdf.parse(paperLMTime.getText()));
	        	paperM.setUid(paperUid.getText());
	        	paperM.setLocation(paperLocation.getText());
	        	ret.add(paperM);
	        }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return ret;
	}
}
