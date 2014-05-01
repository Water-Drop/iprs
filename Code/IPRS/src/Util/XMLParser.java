package Util;
public class XMLParser {
	private String type = null;
	private String data = null;
	
	public XMLParser(String type)
	{
		this.type = type.toUpperCase();
		this.data = "";
	}
	
	public boolean add(String method, String target, String value)
	{
		boolean ret = true;
		String addData = "";
		if (method.equals("set"))
		{
			addData = "<Operation-set><Target>" + target + "</Target><Value>" + value + "</Value></Operation-set>";
		}
		else if (method.equals("add"))
		{
			addData = "<Operation-add><Target>" + target + "</Target><Value>" + value + "</Value></Operation-add>";
		}
		else if (method.equals("remove"))
		{
			addData = "<Operation-remove><Target>" + target + "</Target><Value>" + value + "</Value></Operation-remove>";
		}
		else
		{
			ret = false;
		}
		data += addData;
		return ret;
	}
	
	public String getXML()
	{
		String ret = null;
		if (this.type.equals("PUT"))
		{
			ret = "<PUT>" + this.data + "</PUT>";
		}
		else if (this.type.equals("POST"))
		{
			ret = "<POST>" + this.data + "</POST>";
		}
		return ret;
	}
}
