package util;

public class RDFHelper {
	private String content = "";
	public int initRDF(){
		content = content + "<?xml version=\"1.0\"?>" + "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:paper=\"http://59.78.3.25/iprs/paper#\">";
		return 0;
	}
	public String getRDF(){
		return content;
	}
	public int setDescription(String uri){
		content = content + "<rdf:Description about=\"" + uri + "\">";
		return 0;
	}
	public int setTarget(String tag, String value){
		content = content + "<paper:" + tag + ">" + value + "</paper:" + tag + ">";
		return 0;
	}
	public int endRDF(){
		content += "</rdf:Description></rdf:RDF>";
		return 0;
	}
}
