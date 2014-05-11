package ejb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;

import model.Comment;
//import model.User;
import model.Task;
import util.XMLParser;
import util.HttpHelper;

@Stateless
public class CommentBean {
	private String domain = "http://59.78.3.25:8080/RMP/Entity/";

	public int add(String tid, String content, int rate, int confidence) {
		int ret = 0;
		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.Tid", tid);
		xp.add("set", "this.Content", content);
		xp.add("set", "this.Rate", String.valueOf(rate));
		xp.add("set", "this.Confidence", String.valueOf(confidence));
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date lmTime = new Date();
		xp.add("set", "this.LMTime", fmt.format(lmTime));
		String xmlBody = xp.getXML();
		String url = domain + "iprs/Comment/";
		String resultXML = HttpHelper.SendHttpRequest("post", url, xmlBody);
		// TODO Process resultXML
		System.out.println(resultXML);
		return ret;
	}

	public int modify(String uri, String content, int rate, int confidence) {
		int ret = 0;
		XMLParser xp = new XMLParser("put");
		xp.add("set", "this.Content", content);
		xp.add("set", "this.Rate", String.valueOf(rate));
		xp.add("set", "this.Confidence", String.valueOf(confidence));
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date lmTime = new Date();
		xp.add("set", "this.LMTime", fmt.format(lmTime));
		String xmlBody = xp.getXML();
		String url = domain + uri;
		String resultXML = HttpHelper.SendHttpRequest("put", url, xmlBody);
		// TODO Process resultXML
		System.out.println(resultXML);
		return ret;
	}

	public List<Comment> getPaperComment(String pid) {
		List<Comment> coms = new ArrayList<Comment>();
		String url = domain + "iprs/Task/?Task.Pid=" + pid;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<Task> tasks = Task.parseXML(resultXML);
		for (int i = 0, j = tasks.size(); i < j; i++) {
			String tmp_url = domain + "iprs/Comment/?Comment.Tid="
					+ tasks.get(i).getUri().replaceAll("/iprs/Task/", "");
			String tmpXML = HttpHelper.SendHttpRequest("get", tmp_url, null);
			List<Comment> tmp_com = Comment.parseXML(tmpXML);
			for (int k = 0; k < tmp_com.size(); k++) {
				coms.add(tmp_com.get(k));
			}
		}
		return coms;
	}

	public List<Comment> getUserComment(String uid) {
		List<Comment> coms = new ArrayList<Comment>();
		String url = domain + "iprs/Task/?Task.Uid=" + uid;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<Task> tasks = Task.parseXML(resultXML);
		for (int i = 0, j = tasks.size(); i < j; i++) {
			String tmp_url = domain + "iprs/Comment/?Comment.Tid="
					+ tasks.get(i).getUri().replaceAll("/iprs/Task/", "");
			String tmpXML = HttpHelper.SendHttpRequest("get", tmp_url, null);
			List<Comment> tmp_com = Comment.parseXML(tmpXML);
			for (int k = 0; k < tmp_com.size(); k++) {
				coms.add(tmp_com.get(k));
			}
		}

		return coms;
	}

	public List<Comment> getTaskComment(String tid) {
		String url = domain + "iprs/Comment/?Comment.Tid=" + tid;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<Comment> coms = Comment.parseXML(resultXML);
		return coms;
	}

}
