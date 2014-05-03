package model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class Tag {
	private String uri;
	private String Pid;
	private String Tag;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getPid() {
		return Pid;
	}
	public void setPid(String pid) {
		Pid = pid;
	}
	public String getTag() {
		return Tag;
	}
	public void setTag(String tag) {
		Tag = tag;
	}
	static public List<Tag> parseXML(String xml)
	{
		List<Tag> ret = new ArrayList<Tag>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> tagList = collection.getChildren("Tag");
	        if (null != tagList){
		        for(int i = 0, j = tagList.size();i < j; i++)  
		        {
		        	Element tagE = tagList.get(i);
		        	Element tagUri = tagE.getChild("uri");
		        	Element tagTag = tagE.getChild("Tag");
		        	Element tagPid = tagE.getChild("Pid");
		        	Tag tagM = new Tag();
		        	tagM.setUri(tagUri.getText());
		        	tagM.setTag(tagTag.getText());
		        	tagM.setPid(tagPid.getText());
		        	ret.add(tagM);
		        }
	        }
		} catch (Exception e) {
			// do nothing
		}
        return ret;
	}
}
