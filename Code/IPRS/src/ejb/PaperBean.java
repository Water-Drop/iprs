package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import model.Authors;
import model.Paper;
import model.User;
import util.XMLParser;
import util.HttpHelper;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PaperBean {
	private String domain = "http://59.78.3.25:8080/RMP/Entity/";
	
	public int add(Paper paper, List<Authors> as, List<Keyword> ks)
	{
		return 0;
	}
}
