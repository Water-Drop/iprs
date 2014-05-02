package model;

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
}
