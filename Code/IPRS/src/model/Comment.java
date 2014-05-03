package model;

import java.util.Date;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class Comment {
	private String uri;
	private String Tid;
	private String Content;
	private int Rate;
	private int Confidence;
	private Date LMTime;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getTid() {
		return Tid;
	}
	public void setTid(String tid) {
		Tid = tid;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getRate() {
		return Rate;
	}
	public void setRate(int rate) {
		Rate = rate;
	}
	public int getConfidence() {
		return Confidence;
	}
	public void setConfidence(int confidence) {
		Confidence = confidence;
	}
	public Date getLMTime() {
		return LMTime;
	}
	public void setLMTime(Date lMTime) {
		LMTime = lMTime;
	}
	static public List<Comment> parseXML(String xml)
	{
		List<Comment> ret = new  ArrayList<Comment>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> commentList = collection.getChildren("Comment");
	        for(int i = 0, j = commentList.size();i < j; i++)  
	        {
	        	Element cE = commentList.get(i);
	        	Element cUri = cE.getChild("uri");
	        	Element cTid = cE.getChild("Tid");
	        	Element cContent = cE.getChild("Content");
	        	Element cLmtime = cE.getChild("LMTime");
	        	Element cRate = cE.getChild("Rate");
	        	Element cConfidence = cE.getChild("Confidence");
	        	Comment cM = new Comment();
	        	cM.setUri(cUri.getText());
	        	cM.setTid(cTid.getText());
	        	cM.setContent(cContent.getText());
	        	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
	        	Date lmTime = fmt.parse((cLmtime.getText()));
	        	cM.setLMTime(lmTime);
	        	cM.setRate(Integer.parseInt(cRate.getText()));
	        	cM.setConfidence(Integer.parseInt(cConfidence.getTextNormalize()));
	        	ret.add(cM);
	        }
		} catch (Exception e) {
			// do nothing
		}
        return ret;
	}

}
