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

public class Conference {
	private String uri;
	private String Name;
	private Date Begin;
	private Date End;
	private String Field;
	private String Description;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getBegin() {
		return Begin;
	}
	public void setBegin(Date begin) {
		Begin = begin;
	}
	public Date getEnd() {
		return End;
	}
	public void setEnd(Date end) {
		End = end;
	}
	public String getField() {
		return Field;
	}
	public void setField(String field) {
		Field = field;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	static public List<Conference> parseXML(String xml)
	{
		List<Conference> ret = new  ArrayList<Conference>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> conferenceList = collection.getChildren("Conference");
	        for(int i = 0, j = conferenceList.size();i < j; i++)  
	        {
	        	Element cE = conferenceList.get(i);
	        	Element cUri = cE.getChild("uri");
	        	Element cName = cE.getChild("Name");
	        	Element cBegin = cE.getChild("Begin");
	        	Element cEnd = cE.getChild("End");
	        	Element cField = cE.getChild("Field");
	        	Element cDesc = cE.getChild("Description");
	        	Conference cM = new Conference();
	        	cM.setUri(cUri.getText());
	        	cM.setName(cName.getText());
	        	cM.setField(cField.getText());
	        	cM.setDescription(cDesc.getText());
	        	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
	        	Date beginDate = fmt.parse((cBegin.getText()));
	        	Date endDate = fmt.parse((cEnd.getText()));
	        	cM.setBegin(beginDate);
	        	cM.setEnd(endDate);
	        	ret.add(cM);
	        }
		} catch (Exception e) {
			// do nothing
		}
        return ret;
	}
}
