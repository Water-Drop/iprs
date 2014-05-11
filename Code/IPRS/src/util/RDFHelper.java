package util;

public class RDFHelper {
	private String content = "";

	public int initRDF() {
		content = content + "<?xml version=\"1.0\"?>" + "<RDF>";
		return 0;
	}

	public String getRDF() {
		return content;
	}

	public int setDescription(String uri) {
		content = content + "<Description about=\"" + uri + "\">";
		return 0;
	}

	public int setTarget(String tag, String value) {
		content = content + "<" + tag + ">" + value + "</" + tag + ">";
		return 0;
	}

	public int endRDF() {
		content += "</Description></RDF>";
		return 0;
	}
}
