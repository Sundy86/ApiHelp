package com.sc.email;
import org.apache.commons.mail.*;

import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class EmailUtils {

    public static  HtmlEmail emailSet(String title,String emailMsg,String fromEmail,String ps,String[] toEmail) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setCharset("UTF-8");
        email.setHostName(EmailParams.host);
        email.setAuthentication(fromEmail,ps);
        email.setFrom(fromEmail);//发件人
        for(String touser:toEmail){
            email.addTo(touser);//收件人
        }

        email.setSubject(title); //邮件标题
        email.setHtmlMsg(emailMsg);//邮件内容
        return email;
    }
    public static  HtmlEmail emailSet(String title,String fromEmail,String ps,String[] toEmail) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setCharset("UTF-8");
        email.setHostName(EmailParams.host);
        email.setAuthentication(fromEmail,ps);
        email.setFrom(fromEmail);//发件人
        for(String touser:toEmail){
            email.addTo(touser);//收件人
        }
        email.setSubject(title); //邮件标题
        return email;
    }

    //发送一个简单的邮件，只有内容。
    public static void  sendEmail(String title,String emailMsg,String fromEmail,String ps,String[] toEmail) throws EmailException {
        HtmlEmail email =emailSet(title,emailMsg,fromEmail,ps,toEmail);
        email.send();
    }

    //发送一个多个本地附件的邮件
    public static void  sendEmailWithAttachments(String title,String emailMsg,String fromEmail,String ps,String[] toEmail,String filepath) throws EmailException {
        HtmlEmail email =emailSet(title,emailMsg,fromEmail,ps,toEmail);
        String attachmentName="apitest.xlsx,test.xml,0531_7.jpg";
        String[] attachs = attachmentName.split(",");
        for(String s:attachs){
            EmailAttachment emailAttachment = new EmailAttachment();
            emailAttachment.setPath(filepath);
            emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
            emailAttachment.setDescription("测试结果");
            emailAttachment.setName(s);
            email.attach(emailAttachment);
        }
        email.send();
    }
    //发送一个多个本地附件的邮件
    public static void  sendEmailWithAttachment(String title,String emailMsg,String fromEmail,String ps,String[] toEmail,String... filepath) throws EmailException {
        HtmlEmail email =emailSet(title,emailMsg,fromEmail,ps,toEmail);
         for(String s:filepath){
            EmailAttachment emailAttachment = new EmailAttachment();
            emailAttachment.setPath(s);
            emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
            emailAttachment.setDescription("测试结果");
            File f = new File(s);
            emailAttachment.setName(f.getName());
            email.attach(emailAttachment);
        }
        email.send();
    }

    public static void sendEmailsWithAttachments(String title, String context, String... filepath) throws EmailException, UnsupportedEncodingException {

        String hostname  = EmailParams.host;
        String password = EmailParams.password;
        String username =  EmailParams.username;
        String[] toList =EmailParams.tousers;

        // Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setHostName(hostname); // 邮件服务器域名
        //mail.setSmtpPort(smtpPort); // 邮件服务器smtp协议端口
        email.setAuthentication(username, password); // 邮箱账户
        email.setCharset("UTF-8"); // 邮件的字符集

        //mail.setSSLOnConnect(true); // 是否启用SSL
        //mail.setSslSmtpPort(sslSmtpPort); // 若启用，设置smtp协议的SSL端口号
        email.setSubject(title);
        email.setFrom(username); // 发件人地址
        email.setHtmlMsg(context);

        for (String to : toList) {
            email.addTo(to); // 收件人地址，可以设置多个
        }
        // add the attachment
        for (String fp : filepath) {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(fp);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("测试结果");
            File f = new File(fp);
            // 解决中文附件乱码
            attachment.setName( MimeUtility.encodeText(f.getName()));
            email.attach(attachment);
        }
        // send the email
        email.send();
    }

    //发送一个多个本地附件的邮件
    public static void  sendEmailWithAttachment(String title,String emailMsg,String... filepath) throws EmailException {
        HtmlEmail email =emailSet(title,emailMsg,EmailParams.username,EmailParams.password,EmailParams.tousers);
        for(String s:filepath){
            EmailAttachment emailAttachment = new EmailAttachment();
            emailAttachment.setPath(s);
            emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
            emailAttachment.setDescription("测试结果");
            File f = new File(s);
            emailAttachment.setName(f.getName());
            email.attach(emailAttachment);
        }
        email.send();
    }



    //发送网络上的文件的邮件。
    public static void  sendNetworkPicEmail(String title,String emailMsg,String fromEmail,String ps,String[] toEmail) throws EmailException, MalformedURLException {
        HtmlEmail email =emailSet(title,emailMsg,fromEmail,ps,toEmail);
        EmailAttachment emailAttachment = new EmailAttachment();
        emailAttachment.setURL(new URL("http://www.apache.org/images/asf_logo_wide.gif"));
        emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
        emailAttachment.setDescription("Apache logo 发送");
        emailAttachment.setName("Apache logo");
        email.attach(emailAttachment);
        email.send();
    }

    //发送HTML格式的邮件。
    public static void  sendHTMLEmail(String title,String fromEmail,String ps,String[] toEmail) throws EmailException, MalformedURLException {
        HtmlEmail email =emailSet(title,fromEmail,ps,toEmail);
        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        String cid =email.embed(url, "Apache logo");
        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

        email.send();
    }


}
