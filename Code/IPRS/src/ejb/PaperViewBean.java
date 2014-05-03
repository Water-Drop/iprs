package ejb;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import model.Authors;
import model.Paper;
import ejb.PaperBean;
import util.HttpHelper;

@Stateless
public class PaperViewBean {
	private String domain = "http://59.78.3.25:8080/RMP/Entity/";
	public List<Paper> getPaperbyCondition(String title, String abst, String author){
		List<Paper> papers = new ArrayList<Paper>();
		PaperBean pb = new PaperBean();
		if (null != author){
			String url = domain + "iprs/Authors/?Authors.Name=(like)" + author;
			String resultXML = HttpHelper.SendHttpRequest("get", url, null);
			List<Authors> as = Authors.parseXML(resultXML);

			for (int i = 0; i < as.size(); i++){
				Paper p = pb.get(as.get(i).getPid());
				papers.add(p);
			}
		} else {
			papers = pb.getAll();
		}
		if (null != title)
			for(int i = 0; i < papers.size(); i++){
				if (papers.get(i).getTitle().contains(title) == false){
					papers.remove(i);
				}
			}
		if (null != title){
			for(int i = 0; i < papers.size(); i++){
				if (papers.get(i).getTitle().contains(title) == false){
					papers.remove(i);
				}
			}
		}
		if (null != abst){
			for(int i = 0; i < papers.size(); i++){
				if (papers.get(i).getAbstract().contains(abst) == false){
					papers.remove(i);
				}
			}
		}
		return papers;
	}

}
