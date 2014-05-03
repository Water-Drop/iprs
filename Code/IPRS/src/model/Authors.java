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

public class Authors {
	private String uri;
	private String Name;
	private String Pid;
	private Integer Identity;
	
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
	public String getPid() {
		return Pid;
	}
	public void setPid(String pid) {
		Pid = pid;
	}
	public Integer getIdentity() {
		return Identity;
	}
	public void setIdentity(Integer identity) {
		Identity = identity;
	}
	static public List<Authors> parseXML(String xml)
	{
		List<Authors> ret = new  ArrayList<Authors>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> authorList = collection.getChildren("Authors");
	        if (null != authorList){
	        	for(int i = 0, j = authorList.size();i < j; i++)  
		        {
		        	Element cE = authorList.get(i);
		        	Element cUri = cE.getChild("uri");
		        	Element cName = cE.getChild("Name");
		        	Element cPid = cE.getChild("Pid");
		        	Element cIdentity = cE.getChild("Identity");
		        	Authors cM = new Authors();
		        	cM.setUri(cUri.getText());
		        	cM.setName(cName.getText());
		        	cM.setPid(cPid.getText());
		        	cM.setIdentity(Integer.parseInt(cIdentity.getText()));
		        	ret.add(cM);
		        }
	        }
	        
		} catch (Exception e) {
			// do nothing
		}
        return ret;
	}
}
