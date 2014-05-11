/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: MailBean.java
 * Date		: May 11, 2014
 */

package ejb;

import javax.ejb.Stateless;

import util.MailSender;
import util.MailSenderInfo;
import model.User;

@Stateless
public class MailBean {
	public static void registerInfo(User user) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("sjtuiprs@163.com");
		mailInfo.setPassword("q8c9EHkmlW9S9q9t");
		mailInfo.setFromAddress("sjtuiprs@163.com");
		mailInfo.setToAddress(user.getEmail());
		mailInfo.setSubject("ע��ɹ�");
		mailInfo.setContent(user.getUsername() + " ���ã����ѳɹ���IPRSע�ᡣ");
		MailSender.sendTextMail(mailInfo);
	}
}
