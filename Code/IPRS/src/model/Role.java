package model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class Role {
	private String uri;
	private String Uid;
	private String Cid;
	private int Type;
	public String getUid() {
		return Uid;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getCid() {
		return Cid;
	}
	public void setCid(String cid) {
		Cid = cid;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		this.Type = type;
	}
	static public List<Role> parseXML(String xml)
	{
		List<Role> ret = new  ArrayList<Role>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> roleList = collection.getChildren("Role");
	        for(int i = 0, j = roleList.size();i < j; i++)  
	        {
	        	Element cE = roleList.get(i);
	        	Element cUri = cE.getChild("uri");
	        	Element cUid = cE.getChild("Uid");
	        	Element cCid = cE.getChild("Cid");
	        	Element cType = cE.getChild("Type");
	        	Role cM = new Role();
	        	cM.setUri(cUri.getText());
	        	cM.setUid(cUid.getText());
	        	cM.setCid(cCid.getText());
	        	cM.setType(Integer.parseInt(cType.getText()));
	        	ret.add(cM);
	        }
		} catch (Exception e) {
			// do nothing
		}
        return ret;
	}
	
}
