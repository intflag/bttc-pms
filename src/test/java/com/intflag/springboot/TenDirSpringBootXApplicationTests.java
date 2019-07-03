package com.intflag.springboot;

import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.EmailUtils;
import com.intflag.springboot.entity.admin.SysUser;
import com.intflag.springboot.entity.app.PmsBlogAppendix;
import com.intflag.springboot.entity.app.PmsGroup;
import com.intflag.springboot.service.app.PmsBlogAppendixService;
import com.intflag.springboot.service.app.PmsGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TenDirSpringBootXApplicationTests {

    @Autowired
    private PmsBlogAppendixService pmsBlogAppendixService;

    /**
     * 模拟菜单递归检索
     */
    @Test
    public void findAll() throws Exception {
        StatusResult result = pmsBlogAppendixService.findAll();
        System.out.println(result.getData());
    }

    @Test
    public void add() throws Exception {
        PmsBlogAppendix pmsBlogAppendix = new PmsBlogAppendix();
        pmsBlogAppendix.setAppendixId("1234");
        pmsBlogAppendix.setBlogId("5678");
        StatusResult add = pmsBlogAppendixService.add(pmsBlogAppendix);
        System.out.println(add.getData());
    }

    @Test
    public void delete() throws Exception {
        PmsBlogAppendix pmsBlogAppendix = new PmsBlogAppendix();
        pmsBlogAppendix.setAppendixId("1234");
        pmsBlogAppendix.setBlogId("5678");
        StatusResult add = pmsBlogAppendixService.delete(pmsBlogAppendix);
        System.out.println(add.getData());
    }

    @Autowired
    private PmsGroupService pmsGroupService;

    @Test
    public void findGroup() throws Exception {
        String id = "2";
        Set<String> groupIds = new HashSet<>();
        Set<String> res = findByPid(groupIds, id);
        System.out.println(res);

    }

    public Set<String> findByPid(Set<String> groupIds, String pId) throws Exception {
        List<PmsGroup> pmsGroupList = pmsGroupService.findByPid(pId);
        if (pmsGroupList != null) {
            for (PmsGroup pmsGroup : pmsGroupList) {
                String id = pmsGroup.getGroupId();
                groupIds.add(id);
                groupIds = findByPid(groupIds, id);
            }
        }
        return groupIds;
    }

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;

    @Test
    public void sendEmail() throws Exception {
        String toAddress = "1598749808@qq.com";
        String subject = "BTTC-PMS：论文审核通知";
        String contentText = "<h1>您指导的学生：刘国鑫，已于2019年4月13日18:17:45提交开题报告，请您及时审核，开题报告见附件</h1>";
        boolean isHtml = true;
        String filePath = "D:\\100 - Work - 工作\\100 - Work - 工作资料库\\115 - 亚信\\2019年需求033 - BBOSS流量统付关停客户手动生成告警\\2019年需求033需求上线实施方案.docx";

//        boolean send = EmailUtils.send(javaMailSender,mailProperties,toAddress, subject, contentText, isHtml, filePath);
//        System.out.println(send);

    }

}
