package com.intflag.springboot.common.util;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author liugx  QQ:1598749808
 * @version V1.0
 * @date 2019-04-13 18:49
 * @Description
 */
public class EmailUtils {

    public static boolean send(JavaMailSender javaMailSender, MailProperties mailProperties, String toAddress, String subject, String contentText, boolean isHtml, String attachFileName, File attachFile) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            // 第二个参数表示是否开启multipart模式
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(subject);
            // 第二个参数表示是否html，设为true
            messageHelper.setText(contentText, isHtml);
            if (attachFile != null && attachFileName != null) {
                messageHelper.addAttachment(attachFileName, attachFile);
            }
            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();

        }
        return false;
    }
}
