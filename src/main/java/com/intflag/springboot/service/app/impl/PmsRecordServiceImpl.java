package com.intflag.springboot.service.app.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.admin.SysUser;
import com.intflag.springboot.entity.app.*;
import com.intflag.springboot.mapper.app.PmsPaperMapper;
import com.intflag.springboot.mapper.app.PmsPlanMapper;
import com.intflag.springboot.mapper.app.PmsRecordMapper;
import com.intflag.springboot.service.app.PmsRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘国鑫QQ1598749808
 * @version V1.0
 * @date 2019-03-29 15:58:59
 * @Description 指导记录业务层接口实现
 */
@Service
@Transactional
@CacheConfig(cacheNames = "pmsRecord")
public class PmsRecordServiceImpl implements PmsRecordService {

    @Autowired
    private PmsRecordMapper pmsRecordMapper;

    @Autowired
    private PmsPlanMapper pmsPlanMapper;

    @Autowired
    private PmsPaperMapper pmsPaperMapper;

    @Override
    public StatusResult add(PmsRecord pmsRecord, HttpSession session) throws Exception {
        // 设置信息
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            return StatusResult.error(StatusResult.ADD_FAIL);
        }
        //去重判断
        String planId = pmsRecord.getPlanId();
        String userId = loginUser.getUserId();
        PmsRecordExample example = new PmsRecordExample();
        PmsRecordExample.Criteria criteria = example.createCriteria();
        criteria.andPlanIdEqualTo(planId).andUserIdEqualTo(userId);
        List<PmsRecord> pmsRecords = pmsRecordMapper.selectByExample(example);
        if (pmsRecords != null && pmsRecords.size() > 0) {
            return StatusResult.error(pmsRecord.getPlanName() + "已存在，不能重复添加");
        }

        pmsRecord.setRecordId(UUIDUtils.getCode());
        pmsRecord.setUserId(userId);
        pmsRecord.setUsername(loginUser.getUsername());
        pmsRecord.setNickname(loginUser.getNickname());
        pmsRecord.setGuideCount(0);
        pmsRecord.setPaperSubmitCount(0);
        pmsRecord.setFlag("1");
        pmsRecordMapper.insert(pmsRecord);
        // 正常返回
        return StatusResult.ok(StatusResult.ADD_SUCCESS);
    }

    @Override
    public StatusResult update(PmsRecord pmsRecord) throws Exception {
        pmsRecordMapper.updateByPrimaryKeySelective(pmsRecord);
        // 正常返回
        return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
    }

    @Override
    @Cacheable(cacheNames = {"pmsRecord"})
    public StatusResult findById(String id) throws Exception {
        PmsRecord pmsRecord = pmsRecordMapper.selectByPrimaryKey(id);
        if (pmsRecord != null) {
            return StatusResult.ok(pmsRecord);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }


    @Override
    public PageBean pageQuery(PageBean pageBean, HttpSession session) throws Exception {
        String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
        int pageNum = pageBean.getCurrPage();// 当前页
        int pageSize = pageBean.getPageSize();// 每页显示条数

        // 执行查询
        PmsRecordExample example = new PmsRecordExample();
        PmsRecordExample.Criteria criteria = example.createCriteria();
        //根据角色判断
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        if (loginUser == null && StringUtils.isNotBlank(loginUser.getUserType())) {
            return PageBean.error();
        }
        String userType = loginUser.getUserType();

        if ("1".equals(userType)) {
            criteria.andUserIdEqualTo(loginUser.getUserId());
        } else if ("2".equals(userType)) {
            criteria.andTeachIdEqualTo(loginUser.getUserId());
        } else if ("3".equals(userType)) {
            PmsPlanExample pmsPlanExample = new PmsPlanExample();
            pmsPlanExample.or().andUserIdEqualTo(loginUser.getUserId());
            List<PmsPlan> pmsPlans = pmsPlanMapper.selectByExample(pmsPlanExample);
            List<String> pmsPlanIds = new ArrayList<>();
            if (pmsPlanIds != null && pmsPlanIds.size() > 0) {
                pmsPlans.forEach(p -> {
                    pmsPlanIds.add(p.getPlanId());
                });
                criteria.andPlanIdIn(pmsPlanIds);
            }
        }
        criteria.andPlanNameLike("%" + keyWords + "%");
        // 查询当前页数据
        PageHelper.startPage(pageNum, pageSize);// 设置分页信息
        List<PmsRecord> list = pmsRecordMapper.selectByExample(example);
        // 取出分页信息
        PageInfo<PmsRecord> pageInfo = new PageInfo<>(list);
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
                    //删除前先查询该指导记录下是否有论文提交信息，有则不能删除
                    PmsPaperExample example = new PmsPaperExample();
                    example.or().andRecordIdEqualTo(id);
                    int count = pmsPaperMapper.countByExample(example);
                    if (count > 0) {
                        // 异常返回
                        return StatusResult.error(StatusResult.DELETE_FAIL + "，请先删除该指导记录下所有论文提交记录");
                    } else {
                        pmsRecordMapper.deleteByPrimaryKey(id);
                    }

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

    @Override
    public StatusResult findByUser(HttpSession session) throws Exception {
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            return StatusResult.error(StatusResult.FIND_FAIL);
        }
        PmsRecordExample example = new PmsRecordExample();
        PmsRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(loginUser.getUserId());
        List<PmsRecord> pmsRecords = pmsRecordMapper.selectByExample(example);
        if (pmsRecords != null) {
            return StatusResult.ok(pmsRecords);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }
}