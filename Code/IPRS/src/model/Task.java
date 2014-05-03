package model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class Task {
	private String uri;
	private String Pid;
	private String Uid;
	private int Order;
	private int Status;
	
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
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public int getOrder() {
		return Order;
	}
	public void setOrder(int order) {
		Order = order;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	
	static public List<Task> parseXML(String xml)
	{
		List<Task> ret = new  ArrayList<Task>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> taskList = collection.getChildren("Task");
	        for(int i = 0, j = taskList.size();i < j; i++)  
	        {
	        	Element taskE = taskList.get(i);
	        	Element taskUri = taskE.getChild("uri");
	        	Element taskUid = taskE.getChild("Uid");
	        	Element taskPid = taskE.getChild("Pid");
	        	Element taskOrder = taskE.getChild("Order");
	        	Element taskStatus = taskE.getChild("Status");
	        	Task taskM = new Task();
	        	taskM.setUri(taskUri.getText());
	        	taskM.setUid(taskUid.getText());
	        	taskM.setPid(taskPid.getText());
                taskM.setOrder(Integer.parseInt(taskOrder.getText()));
	        	taskM.setStatus(Integer.parseInt(taskStatus.getText()));
	        	ret.add(taskM);
	        }
		} catch (Exception e) {
			// do nothing
		}
        return ret;
	}
}
