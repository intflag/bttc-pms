package com.intflag.springboot.service.app.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.intflag.springboot.common.util.EmailUtils;
import com.intflag.springboot.common.util.TenDirFileUtils;
import com.intflag.springboot.common.util.ZipUtils;
import com.intflag.springboot.entity.admin.SysUser;
import com.intflag.springboot.entity.admin.SysUserExample;
import com.intflag.springboot.entity.app.*;
import com.intflag.springboot.mapper.admin.SysUserMapper;
import com.intflag.springboot.mapper.app.PmsPlanMapper;
import com.intflag.springboot.mapper.app.PmsRecordMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.mapper.app.PmsPaperMapper;
import com.intflag.springboot.service.app.PmsPaperService;

import javax.servlet.http.HttpSession;

/**
 * @author 刘国鑫QQ1598749808
 * @version V1.0
 * @date 2019-03-29 16:06:08
 * @Description 论文提交业务层接口实现
 */
@Service
@Transactional
public class PmsPaperServiceImpl implements PmsPaperService {

    @Autowired
    private PmsPaperMapper pmsPaperMapper;

    @Autowired
    private PmsRecordMapper pmsRecordMapper;

    @Autowired
    private PmsPlanMapper pmsPlanMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;

    @Override
    public StatusResult add(PmsPaper pmsPaper, HttpSession session) throws Exception {
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            return StatusResult.error(StatusResult.ADD_FAIL);
        }
        PmsPaper tempPaper = (PmsPaper) session.getAttribute("tempPaper");
        if (tempPaper == null) {
            return StatusResult.ok("请上传对应的文档");
        }
        String recordId = pmsPaper.getRecordId();
        if (StringUtils.isBlank(recordId)) {
            return StatusResult.ok("请选择指导记录");
        }
        PmsPaperExample example = new PmsPaperExample();
        PmsPaperExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("cdate desc");
        criteria.andRecordIdEqualTo(recordId);

        List<PmsPaper> pmsPapers = pmsPaperMapper.selectByExample(example);
        if (pmsPapers != null && pmsPapers.size() > 0 && "1".equals(pmsPapers.get(0).getFlag())) {
            return StatusResult.ok("该指导记录下的文档正在被导师审核，不能重复提交");
        }
        if (pmsPapers != null && pmsPapers.size() > 0 && "3".equals(pmsPapers.get(0).getFlag())) {
            return StatusResult.ok("该指导记录下的文档已通过审核，不需要重复提交");
        }
        PmsRecord pmsRecord = pmsRecordMapper.selectByPrimaryKey(recordId);
        String paperName = pmsRecord.getPaperName();
        String teachUser = pmsRecord.getTeachUser();
        String teachNick = pmsRecord.getTeachNick();
        pmsPaper.setRecordId(recordId);
        pmsPaper.setPaperName(paperName);
        pmsPaper.setTeachId(teachUser);
        pmsPaper.setTeachName(teachNick);
        pmsPaper.setFileType(tempPaper.getFileType());
        pmsPaper.setFileUrl(tempPaper.getFileUrl());
        pmsPaper.setFileSize(tempPaper.getFileSize());
        pmsPaper.setStuNum(loginUser.getUsername());
        pmsPaper.setStuName(loginUser.getNickname());
        pmsPaper.setDownloadCount(0);
        pmsPaper.setFlag("1");
        pmsPaper.setField01(tempPaper.getField01().substring(tempPaper.getField01().lastIndexOf("\\") + 1));
        pmsPaper.setField02(pmsRecord.getPlanType());
        pmsPaper.setField03(pmsRecord.getPlanName());
        pmsPaper.setCdate(new Date());
        pmsPaper.setMdate(new Date());
        // 设置信息
        pmsPaper.setPaperId(UUIDUtils.getCode());
        pmsPaperMapper.insert(pmsPaper);
        //指导记录中的论文提交次数加1
        PmsRecord record = pmsRecordMapper.selectByPrimaryKey(recordId);
        if (record != null) {
            record.setPaperSubmitCount(record.getPaperSubmitCount() + 1);
            pmsRecordMapper.updateByPrimaryKeySelective(record);
        }

        //邮件通知导师
        String planType = pmsRecord.getPlanType();
        String planTypeName = "";
        planTypeName = getTypeName(planType);
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.or().andUsernameEqualTo(teachUser);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        String toAddress = "";
        if (sysUsers != null && sysUsers.size() > 0) {
            toAddress = sysUsers.get(0).getEmail();
        } else {
            return StatusResult.ok(StatusResult.ADD_SUCCESS + " 由于教师邮件未知导致发送通知邮件失败");
        }
        String subject = "BTTC-PMS：论文审核通知";
        String contentText = "<h3>您指导的学生：" + pmsPaper.getStuName() + "（" + pmsPaper.getStuNum() + "），已于" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒")) + "提交了" + planTypeName + "，请您及时审核，" + planTypeName + "见附件</h3>";
        boolean isHtml = true;
        File attachFile = TenDirFileUtils.getFileByUrl(tempPaper.getFileUrl(), "GET");
        boolean send = EmailUtils.send(javaMailSender, mailProperties, toAddress, subject, contentText, isHtml, pmsPaper.getField01(), attachFile);
        if (send) {
            return StatusResult.ok(StatusResult.ADD_SUCCESS + " 已通知导师审核");
        } else {
            return StatusResult.ok(StatusResult.ADD_SUCCESS + " 发送审核邮件失败");
        }
        // 正常返回

    }

    private String getTypeName(String planType) {
        String planTypeName = null;
        if ("1".equals(planType)) {
            planTypeName = "开题报告";
        } else if ("2".equals(planType)) {
            planTypeName = "论文初稿";
        } else if ("3".equals(planType)) {
            planTypeName = "论文终稿";
        }
        return planTypeName;
    }

    @Override
    public StatusResult update(PmsPaper pmsPaper, HttpSession session) throws Exception {
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            return StatusResult.error("审核失败");
        }
        PmsPaper paper = pmsPaperMapper.selectByPrimaryKey(pmsPaper.getPaperId());
        String flag = pmsPaper.getFlag();
        if ("3".equals(paper.getFlag())) {
            return StatusResult.ok("已经审核通过的不需要重新审核");
        }
        if ("1".equals(loginUser.getUserType())) {
            return StatusResult.error("学生无审核权限");
        }
        PmsPaper tempPaper = (PmsPaper) session.getAttribute("tempPaper");
        PmsPaper updatedPmsPaper = new PmsPaper();
        updatedPmsPaper.setPaperId(pmsPaper.getPaperId());
        updatedPmsPaper.setMdate(new Date());
        updatedPmsPaper.setFlag(pmsPaper.getFlag());

        pmsPaperMapper.updateByPrimaryKeySelective(updatedPmsPaper);
        if ("3".equals(flag)) {
            String recordId = paper.getRecordId();
            PmsRecord pmsRecord = pmsRecordMapper.selectByPrimaryKey(recordId);
            if (pmsRecord != null) {
                pmsRecord.setGuideCount(pmsRecord.getGuideCount() + 1);
                pmsRecordMapper.updateByPrimaryKey(pmsRecord);
                String planId = pmsRecord.getPlanId();
                PmsPlan pmsPlan = pmsPlanMapper.selectByPrimaryKey(planId);
                pmsPlan.setRealityCount(pmsPlan.getRealityCount() + 1);
                pmsPlanMapper.updateByPrimaryKeySelective(pmsPlan);
            }
        }
        if (!"1".equals(flag)) {
            //给学生发送邮件
            String planType = paper.getField02();
            String planTypeName = getTypeName(planType);
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.or().andUsernameEqualTo(loginUser.getUsername());
            List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
            String toAddress = "";
            if (sysUsers != null && sysUsers.size() > 0) {
                toAddress = sysUsers.get(0).getEmail();
            } else {
                return StatusResult.ok(StatusResult.ADD_SUCCESS + " 由于学生邮件未知导致发送通知邮件失败");
            }
            String subject = "BTTC-PMS：论文审核通知";
            String contentText = "";
            File attachFile = null;
            if (tempPaper == null || StringUtils.isBlank(pmsPaper.getFileUrl()) || "".equals(pmsPaper.getFileUrl())) {
                contentText = "<h3>您的指导老师：" + loginUser.getNickname() + "，已于" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒")) + "对您的" + planTypeName + "进行了审核，审核的结果为" + ("2".equals(flag) ? "继续修改" : "审核通过");
            } else {
                contentText = "<h3>您的指导老师：" + loginUser.getNickname() + "，已于" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒")) + "对您的" + planTypeName + "进行了审核，审核的结果为" + ("2".equals(flag) ? "继续修改" : "审核通过") + "，审核后的" + planTypeName + "见附件</h3>";
                attachFile = TenDirFileUtils.getFileByUrl(tempPaper.getFileUrl(), "GET");
            }
            boolean isHtml = true;
            boolean send = EmailUtils.send(javaMailSender, mailProperties, toAddress, subject, contentText, isHtml, tempPaper == null ? null : tempPaper.getField01(), attachFile);
            if (send) {
                return StatusResult.ok(StatusResult.ADD_SUCCESS + " 已邮件通知学生");
            } else {
                return StatusResult.ok(StatusResult.ADD_SUCCESS + " 发送审核邮件失败");
            }
        }
        // 正常返回
        return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
    }

    @Override
    public StatusResult findById(String id) throws Exception {
        PmsPaper pmsPaper = pmsPaperMapper.selectByPrimaryKey(id);
        if (pmsPaper != null) {
            return StatusResult.ok(pmsPaper);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }

    @Override
    public PageBean pageQuery(PageBean pageBean, HttpSession session) throws Exception {
        String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
        int pageNum = pageBean.getCurrPage();// 当前页
        int pageSize = pageBean.getPageSize();// 每页显示条数

        // 执行查询
        PmsPaperExample example = new PmsPaperExample();
        PmsPaperExample.Criteria criteria = example.createCriteria();
        //根据角色判断
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        if (loginUser == null && StringUtils.isNotBlank(loginUser.getUserType())) {
            return PageBean.error();
        }
        String userType = loginUser.getUserType();

        if ("1".equals(userType)) {
            criteria.andStuNumEqualTo(loginUser.getUsername());
        } else if ("2".equals(userType)) {
            criteria.andTeachIdEqualTo(loginUser.getUsername());
        } else if ("3".equals(userType)) {
            //查找当前系主任创建的所有计划
            PmsPlanExample pmsPlanExample = new PmsPlanExample();
            pmsPlanExample.or().andUserIdEqualTo(loginUser.getUserId());
            List<PmsPlan> pmsPlans = pmsPlanMapper.selectByExample(pmsPlanExample);
            List<String> pmsPlanIds = new ArrayList<>();
            if (pmsPlanIds != null && pmsPlanIds.size() > 0) {

                pmsPlans.forEach(p -> {
                    pmsPlanIds.add(p.getPlanId());
                });
                //查找所有计划下的指导记录
                PmsRecordExample pmsRecordExample = new PmsRecordExample();
                pmsRecordExample.or().andPlanIdIn(pmsPlanIds);
                List<PmsRecord> pmsRecords = pmsRecordMapper.selectByExample(pmsRecordExample);
                List<String> pmsRecordIds = new ArrayList<>();
                if (pmsRecordIds != null && pmsRecordIds.size() > 0) {

                    pmsRecords.forEach(r -> {
                        pmsRecordIds.add(r.getRecordId());
                    });
                    criteria.andRecordIdIn(pmsRecordIds);
                }
            }
        }


        criteria.andPaperNameLike("%" + keyWords + "%");
        example.setOrderByClause("mdate desc");
        // 查询当前页数据
        PageHelper.startPage(pageNum, pageSize);// 设置分页信息
        List<PmsPaper> list = pmsPaperMapper.selectByExample(example);
        // 取出分页信息
        PageInfo<PmsPaper> pageInfo = new PageInfo<>(list);
        pageBean.setList(list);
        pageBean.setTotalCount(pageInfo.getTotal());// 设置总记录数
        pageBean.setTotalPage(pageInfo.getPages());// 设置总页数
        // 返回结果集
        return PageBean.ok(pageBean);
    }

    @Override
    public StatusResult delete(String ids) {
        if (ids != null) {
            String[] objIds = ids.split(",");
            if (objIds != null && objIds.length > 0) {
                for (String id : objIds) {
                    // 根据主键删除
                    pmsPaperMapper.deleteByPrimaryKey(id);
                }
                // 正常返回
                return StatusResult.ok(StatusResult.DELETE_SUCCESS);
            } else {
                // 异常返回
                return StatusResult.error(StatusResult.DELETE_FAIL);
            }
        } else {
            // 异常返回
            return StatusResult.error(StatusResult.DELETE_FAIL);
        }
    }

    @Value("${TenDir.file.rootPath}")
    private String fileRootPath;

    @Override
    public StatusResult packDoc(String id) throws Exception {
        PmsPlan pmsPlan = pmsPlanMapper.selectByPrimaryKey(id);
        if (pmsPlan != null) {
            PmsRecordExample pmsRecordExample = new PmsRecordExample();
            pmsRecordExample.or().andPlanIdEqualTo(pmsPlan.getPlanId());
            List<PmsRecord> pmsRecords = pmsRecordMapper.selectByExample(pmsRecordExample);
            //创建该计划的目录
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String planFilePath = fileRootPath + File.separator + pmsPlan.getPlanName() + "-" + now;
            File planFile = new File(planFilePath);
            if (!planFile.exists()) {
                planFile.mkdirs();
            }
            //循环指导记录下通过审核的论文，进行下载
            pmsRecords.forEach(r -> {
                String recordId = r.getRecordId();
                PmsPaperExample pmsPaperExample = new PmsPaperExample();
                PmsPaperExample.Criteria criteria = pmsPaperExample.createCriteria();
                criteria.andRecordIdEqualTo(recordId).andFlagEqualTo("3");
                List<PmsPaper> pmsPapers = pmsPaperMapper.selectByExample(pmsPaperExample);
                if (pmsPapers != null && pmsPapers.size() > 0) {
                    String url = pmsPapers.get(0).getFileUrl();
                    String fileName = pmsPapers.get(0).getField01();
                    File file = TenDirFileUtils.getFileByUrl(url, planFilePath + File.separator + fileName, "GET");
                }
            });
            //对下载完成的文件进行打包
            boolean b = ZipUtils.toZip(planFilePath, planFilePath + ".zip", true);
            if (b) {
                Map<String, String> map = new HashMap<>();
                map.put("fileName", pmsPlan.getPlanName() + "-" + now + ".zip");
                map.put("filePath", planFilePath + ".zip");
                return StatusResult.ok(map, pmsPlan.getPlanName() + "下的文档打包成功");
            } else {
                return StatusResult.error(pmsPlan.getPlanName() + "下的文档打包失败");
            }
        }
        return StatusResult.error("文档打包失败");
    }

}