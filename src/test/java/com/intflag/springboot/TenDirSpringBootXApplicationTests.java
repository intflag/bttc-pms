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

    @Test
    public void sendEmail2() throws Exception {
        String toAddress = "1598749808@qq.com";
        String subject = "BTTC-PMS：论文审核通知";
        String contentText = "各位领导好：2019年04月18日，北京中心KPI考核成功率、文件传输中断情况的统计结果如下。统计周期为：2019年04月17日15点-22点和2019年04月18日8点-15点。<br /><br />1、KPI考核成功率统计：<p></p><table border='1' style='border:1px solid #000;text-align: center;'><tr><th>系统</th><th>成功率</th></tr><tr><td>一级客服</td><td>0</td></tr><tr><td>一级BBOSS</td><td>0</td></tr><tr><td>网状网</td><td>0</td></tr><tr><td>一级能力开放平台</td><td>0</td></tr></table><p></p>成功率统计说明：成功率为统计周期内，截至当天下午15点成功率(非月累计或年累计)。<br /><br />2、文件传输中断统计：<p></p><table border='1'style='border: 1px solid #000000;text-align: center;'><tr><th>系统</th><th>中断次数</th></tr><tr><td>一级客服</td><td>28.00</td></tr><tr><td>一级BBOSS</td><td>28.00</td></tr><tr><td>网状网</td><td>28.00</td></tr><tr><td>一级能力开放平台</td><td>28.00</td></tr></table><p></p>文件传输中断次数统计说明：为统计周期内，截至当天下午15点，仍为中断的次数，接口文件情况见附件。<br /><br />";
        boolean isHtml = true;

        boolean send = EmailUtils.send(javaMailSender, mailProperties, toAddress, subject, contentText, isHtml, null, null);
        System.out.println(send);

    }
}
