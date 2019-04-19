package com.intflag.springboot.service.app.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.admin.SysUser;
import com.intflag.springboot.entity.app.*;
import com.intflag.springboot.mapper.app.PmsPlanMapper;
import com.intflag.springboot.mapper.app.PmsRecordMapper;
import com.intflag.springboot.service.app.PmsGroupService;
import com.intflag.springboot.service.app.PmsPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author 刘国鑫QQ1598749808
 * @version V1.0
 * @date 2019-03-27 21:31:50
 * @Description 论文计划业务层接口实现
 */
@Service
@Transactional
public class PmsPlanServiceImpl implements PmsPlanService {

    @Autowired
    private PmsPlanMapper pmsPlanMapper;

    @Autowired
    private PmsRecordMapper pmsRecordMapper;

    @Override
    public StatusResult add(PmsPlan pmsPlan, HttpSession session) throws Exception {
        // 设置信息
        pmsPlan.setPlanId(UUIDUtils.getCode());
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        if (loginUser != null) {
            pmsPlan.setUserId(loginUser.getUserId());
            pmsPlan.setUsername(loginUser.getUsername());
            pmsPlan.setNickname(loginUser.getNickname());
            pmsPlan.setRealityCount(0);
            pmsPlan.setFlag("1");
            pmsPlan.setCdate(new Date());
            pmsPlan.setMdate(new Date());
            /**
             * 根据组织ID，查询所有下级组织
             */
            String groupId = pmsPlan.getGroupId();
            Set<String> set = new HashSet<>();
            Set<String> byPid = findByPid(set, groupId);
            pmsPlan.setField01(byPid.toString());
            pmsPlanMapper.insert(pmsPlan);
        }
        // 正常返回
        return StatusResult.ok(StatusResult.ADD_SUCCESS);
    }

    @Override
    public StatusResult update(PmsPlan pmsPlan) throws Exception {
        pmsPlanMapper.updateByPrimaryKeySelective(pmsPlan);
        // 正常返回
        return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
    }

    @Override
    public StatusResult findById(String id) throws Exception {
        PmsPlan pmsPlan = pmsPlanMapper.selectByPrimaryKey(id);
        if (pmsPlan != null) {
            return StatusResult.ok(pmsPlan);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }

    @Autowired
    private PmsGroupService pmsGroupService;

    @Override
    public PageBean pageQuery(PageBean pageBean, String groupId, SysUser loginUser) throws Exception {
        String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
        int pageNum = pageBean.getCurrPage();// 当前页
        int pageSize = pageBean.getPageSize();// 每页显示条数
        // 查询当前页数据
        PageHelper.startPage(pageNum, pageSize);// 设置分页信息
        // 执行查询
        PmsPlanExample example = new PmsPlanExample();
        PmsPlanExample.Criteria criteria = example.createCriteria();

        String userId = loginUser.getUserId();

        if (StringUtils.isNotBlank(userId)) {
            criteria.andUserIdEqualTo(userId);
        }

        if (StringUtils.isNotBlank(groupId)) {
            Set<String> set = new HashSet<>();
            set = findByPid(set, groupId);
            set.add(groupId);
            criteria.andGroupIdIn(new ArrayList<>(set));
        }

        criteria.andPlanNameLike("%" + keyWords + "%");
        example.setOrderByClause("mdate desc");
        List<PmsPlan> list = pmsPlanMapper.selectByExample(example);
        // 取出分页信息
        PageInfo<PmsPlan> pageInfo = new PageInfo<>(list);
        pageBean.setList(list);
        pageBean.setTotalCount(pageInfo.getTotal());
        pageBean.setTotalPage(pageInfo.getPages());
        // 返回结果集
        return PageBean.ok(pageBean);
    }

    @Override
    public StatusResult delete(String ids) throws Exception {
        if (ids != null) {
            String[] objIds = ids.split(",");
            if (objIds != null && objIds.length > 0) {
                for (String id : objIds) {
                    // 根据主键删除
                    //删除前查询该学术计划下是否存在指导记录，存在则不能删除
                    PmsRecordExample example = new PmsRecordExample();
                    example.or().andPlanIdEqualTo(id);
                    int count = pmsRecordMapper.countByExample(example);
                    if (count>0) {
                        // 异常返回
                        return StatusResult.error(StatusResult.DELETE_FAIL+"，请先删除该学术计划下所有指导记录");
                    } else {
                        pmsPlanMapper.deleteByPrimaryKey(id);
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
        if (loginUser != null) {
            PmsPlanExample example = new PmsPlanExample();
            example.or().andField01Like("%" + loginUser.getGroupId() + "%");
            List<PmsPlan> pmsPlans = pmsPlanMapper.selectByExample(example);
            if (pmsPlans != null) {
                return StatusResult.ok(pmsPlans);
            }
        }
        return StatusResult.none(StatusResult.FIND_NONE);
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
}