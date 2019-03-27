package com.intflag.springboot.service.app.impl;

import java.util.Date;
import java.util.List;

import com.intflag.springboot.entity.admin.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.app.PmsPlan;
import com.intflag.springboot.entity.app.PmsPlanExample;
import com.intflag.springboot.mapper.app.PmsPlanMapper;
import com.intflag.springboot.service.app.PmsPlanService;

import javax.servlet.http.HttpSession;

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
            pmsPlanMapper.insert(pmsPlan);
        }
        // 正常返回
        return StatusResult.ok(StatusResult.ADD_SUCCESS);
    }

    public StatusResult update(PmsPlan pmsPlan) throws Exception {
        pmsPlanMapper.updateByPrimaryKeySelective(pmsPlan);
        // 正常返回
        return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
    }

    public StatusResult findById(String id) throws Exception {
        PmsPlan pmsPlan = pmsPlanMapper.selectByPrimaryKey(id);
        if (pmsPlan != null) {
            return StatusResult.ok(pmsPlan);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }

    public PageBean pageQuery(PageBean pageBean) throws Exception {
        String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
        int pageNum = pageBean.getCurrPage();// 当前页
        int pageSize = pageBean.getPageSize();// 每页显示条数
        // 查询当前页数据
        PageHelper.startPage(pageNum, pageSize);// 设置分页信息
        // 执行查询
        PmsPlanExample example = new PmsPlanExample();
        example.or().andPlanNameLike("%" + keyWords + "%");
        List<PmsPlan> list = pmsPlanMapper.selectByExample(example);
        // 取出分页信息
        PageInfo<PmsPlan> pageInfo = new PageInfo<>(list);
        pageBean.setList(list);
        pageBean.setTotalCount(pageInfo.getTotal());// 设置总记录数
        pageBean.setTotalPage(pageInfo.getPages());// 设置总页数
        // 返回结果集
        return PageBean.ok(pageBean);
    }

    public StatusResult delete(String ids) {
        if (ids != null) {
            String[] objIds = ids.split(",");
            if (objIds != null && objIds.length > 0) {
                for (String id : objIds) {
                    // 根据主键删除
                    pmsPlanMapper.deleteByPrimaryKey(id);
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
}