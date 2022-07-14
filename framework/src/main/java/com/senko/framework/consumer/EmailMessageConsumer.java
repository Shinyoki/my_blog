package com.senko.framework.consumer;

import com.alibaba.fastjson.JSON;
import com.senko.common.constants.RabbitMQConstants;
import com.senko.common.core.dto.EmailDTO;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件 消费者
 *
 * @author senko
 * @date 2022/7/14 12:28
 */
@Component
@RabbitListener(queues = RabbitMQConstants.EMAIL_QUEUE)
public class EmailMessageConsumer {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(EmailMessageConsumer.class);

    /**
     * 发送者
     */
    @Value("${spring.mail.username}")
    private String senderName;

    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitHandler
    public void process(byte[] message) {
        String s = new String(message);

        EmailDTO emailDTO = JSON.parseObject(s, EmailDTO.class);

        // 发送网页邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            // 加工mimeMessage
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(senderName);
            mimeMessageHelper.setTo(emailDTO.getEmail());
            mimeMessageHelper.setSubject(emailDTO.getSubject());
            mimeMessageHelper.setText(getScheme(emailDTO.getEmail(), emailDTO.getContent()), true);
        } catch (MessagingException e) {
            logger.error("邮箱创建失败", e);
            throw new RuntimeException(e);
        }
        // 设置为网页邮件
        javaMailSender.send(mimeMessage);
    }

    private String getScheme(String username, String content) {
        String[] split = username.split("@");
        return "<div id=\"mailContentContainer\" class=\"qmbox qm_con_body_content qqmail_webmail_only\"\n" +
                "       style=\"opacity: 1; height: 231px;\">\n" +
                "    <a href=\"http://senkotech.top\" style=\"text-decoration: none; color: #262e33; border: 0;\" rel=\"noopener\"\n" +
                "       target=\"_blank\">\n" +
                "      <h3>SenkoTech:</h3>\n" +
                "    </a>\n" +
                "    <table width=\"100%\" cellpadding=\"20\">\n" +
                "      <tbody>\n" +
                "      <tr>\n" +
                "        <td dir=\"ltr\"\n" +
                "            style=\"font-family: 'Helvetica Neue', helvetica, sans-serif; font-size: 15px; color: #333333; line-height: 21px\">\n" +
                "          <strong style=\"font-size: 17px\">您好 " + split[0] + ",</strong>\n" +
                "          <br>\n" +
                "          " + content + "</strong><br>\n" +
                "          <br>\n" +
                "          <a href=\"http://senkotech.top\"\n" +
                "             style=\"color: #ffffff; font-family: 'Helvetica Neue', helvetica, sans-serif; text-decoration: none; font-size: 12px; background: #3f6d98; line-height: 32px; padding: 0 10px; display: inline-block; border-radius: 3px;\"\n" +
                "             rel=\"noopener\" target=\"_blank\">转到 SenkoTech</a>\n" +
                "          <br>\n" +
                "          <br>\n" +
                "          <em style=\"color: #8c8c8c\">— SenkoTech</em>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "  </div>";
    }


}
