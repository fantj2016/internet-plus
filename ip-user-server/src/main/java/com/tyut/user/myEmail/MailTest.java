package com.tyut.user.myEmail;

import com.tyut.core.constants.ConsParams;
import com.tyut.user.myEmail.emailEnum.MailContentTypeEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Fant.J.
 */
public class MailTest {
    @Test
    public void test() throws Exception {
        for (int i = 0;i<20;i++){
            new MailSender()
                    .title("焦爸爸给你发送的邮件")
                    .content("你就是傻，开不开心")
                    .contentType(MailContentTypeEnum.TEXT)
                    .targets(new ArrayList<String>(){{
                        add("jiaofanting@dingtalk.com");
                    }})
                    .send();
            Thread.sleep(1000);
            System.out.println("第"+i+"次发送成功!");
        }
    }

    @Test
    public void testHtml() throws Exception {
        String email = "844072586";
        String uuid = UUID.randomUUID().toString().replace("-","");
            new MailSender()
                    .title("重设你的晋软杯账户密码")
                    .content("<a href='"+ConsParams.Portrait.PRIFIX_PORTRAIT+"/user/findPasswd/"+ email +"/"+uuid+"' target='_blank'> 点击此链接修改密码</a>")
                    .contentType(MailContentTypeEnum.HTML)
                    .targets(new ArrayList<String>(){{
                        add("jiaofanting@dingtalk.com");
                    }})
                    .send();
            System.out.println("发送成功!uuid 为"+uuid+"");
    }
}
