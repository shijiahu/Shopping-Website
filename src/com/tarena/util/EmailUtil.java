package com.tarena.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.tarena.dao.UserDAO;
import com.tarena.dao.impl.UserDAOImpl;
import com.tarena.entity.User;

/**
 * Send Email Util
 * 
 * @author Mr.rong
 *
 */
public class EmailUtil {
	/**
	 * send email
	 * 
	 * @param email
	 * @param title
	 * @param content
	 */
	public static void sendEmail(User user) {
		final Properties props = new Properties();
		/*
		 * 可用属性： mail.store.protocol / mail.transport.protocol / mail.host /
		 * mail.user / mail.from
		 */
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.user", "765268755@qq.com");
		// props.put("mail.user", "do__notreply@hotmail.com");
		props.put("mail.password", "a1234567");
		// props.put("mail.password", "yiidopzvaqxkhjhq");
		// props.put("mail.password", "a348645832");

		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		try {

			Session mailSession = Session.getInstance(props, authenticator);
			MimeMessage message = new MimeMessage(mailSession);
			InternetAddress form = new InternetAddress(
					props.getProperty("mail.user"));
			message.setFrom(form);
			InternetAddress to = new InternetAddress(user.getEmail());
			message.setRecipient(RecipientType.TO, to);

			// // 设置抄送
			// InternetAddress cc = new InternetAddress(user.getEmail());
			// message.setRecipient(RecipientType.CC, cc);
			//
			// // 设置密送，其他的收件人不能看到密送的邮件地址
			// InternetAddress bcc = new InternetAddress(user.getEmail());

			// message.setRecipient(RecipientType.CC, bcc);

			// 设置邮件标题
			message.setSubject("[当当网]当当网会员 " + user.getNickname() + " 邮箱地址验证");

			// 设置邮件的内容体

			message.setContent(
					"当当网会员 "
							+ user.getNickname()
							+ " 邮箱地址验证<br/>"
							+ user.getNickname()
							+ "，这封信是由当当网发送的。<br/> 您收到这封邮件，是由于在您在当当网进行了新用户注册，或用户修改 Email 使用了这个邮箱地址。"
							+ "如果您并没有访问过当当网，或没有进行上述操作，请忽略这封邮件。您不需要退订或进行其他进一步的操作。"
							+ "<br/><br/> ----------------------------------------------------------------------<br/>"
							+ "帐号激活说明"
							+ "<br/>----------------------------------------------------------------------<br/><br/>"
							+ "如果您是 当当网 的新用户，或在修改您的注册 Email 时使用了本地址，我们需要对您的地址有效性进行验证以避免垃圾邮件或地址被滥用。<br/>"
							+ "您只需点击下面的链接即可激活您的帐号：<br/> "
							+ "<a href=http://202.204.103.2:8080/dangdang/user/email_verify.action?id="+user.getId()+"&uuid="
							+ user.getEmailVerifyCode()
							+ "> 点击这里</a> <br/><br/>如果有任何问题，请致电我们。<br/>请勿回复该邮件<br/><br/>感谢您的访问，祝您使用愉快！<br/><br/>此致<br/>当当网 管理团队.",
					"text/html;charset=UTF-8");

			// 发送邮件
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("-----send email:"+email+"-------");
		// System.out.println("-----标题:"+title+"-------");
		// System.out.println("-----内容:"+content+"-------");
		// 提示,实现时可参考邮件发送示例
	}

	public static void main(String[] args) throws Exception {
		UserDAO dao = new UserDAOImpl();
		User user = dao.findByEmail("348645832@qq.com");
		sendEmail(user);
		System.out.println("success");
	}
}
