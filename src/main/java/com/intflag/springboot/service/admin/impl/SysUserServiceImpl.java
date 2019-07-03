package com.intflag.springboot.service.admin.impl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.intflag.springboot.entity.admin.*;
import com.intflag.springboot.entity.app.PmsGroup;
import com.intflag.springboot.mapper.admin.SysRoleMapper;
import com.intflag.springboot.service.app.PmsGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.MD5Utils;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.admin.SysUserExample.Criteria;
import com.intflag.springboot.mapper.admin.SysResourceMapper;
import com.intflag.springboot.mapper.admin.SysRoleUserMapper;
import com.intflag.springboot.mapper.admin.SysUserMapper;
import com.intflag.springboot.service.admin.SysUserService;

import javax.servlet.http.HttpSession;

/**
 * @author 刘国鑫 QQ:1598749808
 * @version V1.0
 * @date 2018年8月4日 上午11:37:12
 * @Description 师生用户业务层接口
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Value("${TenDir.constant.adminUsername}")
    private String ADMIN_USERNAME;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public StatusResult findById(String userId) throws Exception {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUser != null) {
            return StatusResult.ok(sysUser);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }

    @Override
    public StatusResult findList() throws Exception {
        // 查询所有
        SysUserExample example = new SysUserExample();
        List<SysUser> list = sysUserMapper.selectByExample(example);
        // 正常返回
        return StatusResult.ok(list);
    }

    @Override
    public StatusResult add(SysUser sysUser) throws Exception {
        if (sysUser != null && sysUser.getUsername() != null) {
            SysUserExample example = new SysUserExample();
            example.or().andUsernameEqualTo(sysUser.getUsername());
            List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
            if (sysUsers != null && sysUsers.size() > 0) {
                return StatusResult.error("该用户"+StatusResult.FIND_EXIST);
            }
        } else {
            return StatusResult.error(StatusResult.ADD_FAIL);
        }
        // 设置用户信息
        sysUser.setUserId(UUIDUtils.getCode());
        sysUser.setCdate(new Date());
        sysUser.setMdate(new Date());
        sysUser.setPassword(MD5Utils.md5(sysUser.getPassword()));
        // 向用户表表中插入
        sysUserMapper.insert(sysUser);
        // 向角色用户表中插入数据
        String[] roleIds = sysUser.getRoleId();
        if (roleIds != null) {
            for (String roleId : roleIds) {
                SysRoleUserKey roleUserKey = new SysRoleUserKey();
                roleUserKey.setRoleId(roleId);
                roleUserKey.setUserId(sysUser.getUserId());
                sysRoleUserMapper.insert(roleUserKey);
            }
        }
        // 正常返回
        return StatusResult.ok(StatusResult.ADD_SUCCESS);
    }

    @Override
    public StatusResult update(SysUser sysUser) throws Exception {
        // 设置属性
        sysUser.setMdate(new Date());
        sysUser.setPassword(MD5Utils.md5(sysUser.getPassword()));
        // 根据主键更新
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        // 删除已授权的角色
        SysRoleUserExample example = new SysRoleUserExample();
        example.createCriteria().andUserIdEqualTo(sysUser.getUserId());
        sysRoleUserMapper.deleteByExample(example);
        // 重新添加授权角色
        // 向角色用户表中插入数据
        String[] roleIds = sysUser.getRoleId();
        if (roleIds != null) {
            for (String roleId : roleIds) {
                SysRoleUserKey roleUserKey = new SysRoleUserKey();
                roleUserKey.setRoleId(roleId);
                roleUserKey.setUserId(sysUser.getUserId());
                sysRoleUserMapper.insert(roleUserKey);
            }
        }
        // 正常返回
        return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
    }

    @Override
    public StatusResult delete(SysUser sysUser) throws Exception {
        // 根据主键删除
        sysUserMapper.deleteByPrimaryKey(sysUser.getUserId());
        // 删除对应的角色授权
        SysRoleUserExample example = new SysRoleUserExample();
        example.createCriteria().andUserIdEqualTo(sysUser.getUserId());
        sysRoleUserMapper.deleteByExample(example);
        // 正常返回
        return StatusResult.ok(StatusResult.DELETE_SUCCESS);
    }

    @Override
    public SysUser login(SysUser sysUser) throws Exception {
        SysUserExample example = new SysUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(sysUser.getUsername()).andPasswordEqualTo(MD5Utils.md5(sysUser.getPassword()));
        List<SysUser> list = sysUserMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public StatusResult findMenu(String userId, String username, String adminUsername) throws Exception {
        List<SysResource> list = null;
        if (adminUsername.equals(username)) {
            list = sysResourceMapper.selectMenuByType("0");
        } else {
            list = sysResourceMapper.selectByUserId(userId);
            if (list != null && list.size() > 0) {
                List<SysResource> menu = new ArrayList<>();
                for (SysResource sysResource : list) {
                    if ("0".equals(sysResource.getType())) {
                        List<SysResource> children = findChildren(sysResource.getResourceId(), list);
                        sysResource.setSysResources(children);
                        menu.add(sysResource);
                    }
                }
                list = menu;
            }
        }
        if (null != list) {
            return StatusResult.ok(list);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }

    private List<SysResource> findChildren(String id, List<SysResource> list) {
        List<SysResource> children = new ArrayList<>();
        for (SysResource sysResource : list) {
            if (!"0".equals(sysResource.getType()) && !"2".equals(sysResource.getType())
                    && sysResource.getParentId().equals(id)) {
                sysResource.setSysResources(findChildren(sysResource.getResourceId(), list));
                children.add(sysResource);
            }
        }
        return children;
    }

    @Override
    public StatusResult checkUsernameIsExist(String username) throws Exception {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUser> list = sysUserMapper.selectByExample(example);
        if (list != null && list.size() == 0) {
            return StatusResult.ok();
        } else if (list != null && list.size() > 0) {
            return StatusResult.exist(StatusResult.FIND_EXIST);
        } else {
            return StatusResult.none(StatusResult.FIND_NONE);
        }
    }

    @Override
    public PageBean pageQuery(PageBean pageBean, SysUser loginUser) throws Exception {
        String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
        int pageNum = pageBean.getCurrPage();// 当前页
        int pageSize = pageBean.getPageSize();// 每页显示条数
        // 查询当前页数据
        PageHelper.startPage(pageNum, pageSize);// 设置分页信息
        // 执行查询
        SysUserExample example = new SysUserExample();
        Criteria criteria = example.createCriteria();
        // 设置条件
        if (!loginUser.getUsername().equals(ADMIN_USERNAME)) {
            criteria.andUsernameNotEqualTo(ADMIN_USERNAME);
        }
        criteria.andUsernameLike("%" + keyWords + "%");
        example.setOrderByClause("mdate desc");
        List<SysUser> list = sysUserMapper.selectByExample(example);
        // 取出分页信息
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        pageBean.setList(list);
        pageBean.setTotalCount(pageInfo.getTotal());// 设置总记录数
        pageBean.setTotalPage(pageInfo.getPages());// 设置总页数
        // 返回结果集
        return PageBean.ok(pageBean);
    }

    @Override
    public StatusResult delete(String ids, SysUser loginUser) throws Exception {
        String loginUserId = loginUser.getUserId();
        if (ids != null) {
            String[] userIds = ids.split(",");
            if (userIds != null && userIds.length > 0) {
                for (String userId : userIds) {
                    if (!loginUserId.equals(userId)) {
                        // 根据主键删除
                        sysUserMapper.deleteByPrimaryKey(userId);
                        // 删除对应的角色授权
                        SysRoleUserExample example = new SysRoleUserExample();
                        example.createCriteria().andUserIdEqualTo(userId);
                        sysRoleUserMapper.deleteByExample(example);
                    } else {
                        // 异常返回
                        return StatusResult.error(StatusResult.DELETE_FAIL_SUICIDE);
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
    public StatusResult addImportUser(SysUser sysUser, HttpSession session) throws Exception {
        List<SysUser> importUserInfo = (List<SysUser>) session.getAttribute("importUserInfo");
        if (importUserInfo == null) {
            return StatusResult.error("导入失败，请先上传要导入的文件");
        }
        String groupId = sysUser.getGroupId();
        String flag = sysUser.getFlag();
        String userType = sysUser.getUserType();
        String groupName = sysUser.getGroupName();

        Map<String, Object> map = new ConcurrentHashMap<>(8);
        int errorCount = 0;
        int errNameCount = 0;
        int errNameExist = 0;
        List<String> existList = new ArrayList<>();
        for (SysUser user : importUserInfo) {
            String username = user.getUsername();
            if (StringUtils.isBlank(username)) {
                errorCount++;
                errNameCount++;
                continue;
            }
            StatusResult result = checkUsernameIsExist(username);
            if (result.getStatus() == 444) {
                errorCount++;
                errNameExist++;
                existList.add(username);
                continue;
            } else {
                // 设置用户信息
                user.setUserId(UUIDUtils.getCode());
                user.setCdate(new Date());
                user.setMdate(new Date());
                user.setGroupId(groupId);
                user.setGroupName(groupName);
                user.setFlag(flag);
                user.setUserType(userType);
                user.setPassword("123456");
                user.setPassword(MD5Utils.md5(user.getPassword()));
                // 向用户表表中插入
                sysUserMapper.insert(user);
                // 向角色用户表中插入数据
                addRolesInfo(user);
            }

        }
        map.put("totalCount", importUserInfo.size());
        map.put("errorCount", errorCount);
        map.put("errNameCount", errNameCount);
        map.put("errNameExist", errNameExist);
        map.put("existList", existList);

        // 正常返回
        return StatusResult.build(200, 0, "批量导入用户信息完成", map);
    }

    @Override
    public PageBean pageQuery(PageBean pageBean, String groupId, SysUser loginUser) throws Exception {
        String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
        int pageNum = pageBean.getCurrPage();// 当前页
        int pageSize = pageBean.getPageSize();// 每页显示条数

        // 执行查询
        SysUserExample example = new SysUserExample();
        Criteria criteria = example.createCriteria();
        // 设置条件
        if (!loginUser.getUsername().equals(ADMIN_USERNAME)) {
            criteria.andUsernameNotEqualTo(ADMIN_USERNAME);
        }


        //判断资质id是否为空，为空则查全量的组织
        Set<String> setA = new HashSet<>();
        if (StringUtils.isBlank(groupId)) {
            StatusResult all = pmsGroupService.findAll();
            List<PmsGroup> allGroups = (List<PmsGroup>) all.getData();
            for (PmsGroup allGroup : allGroups) {
                setA.add(allGroup.getGroupId());
            }
        } else {
            setA = findByPid(setA, groupId);
            setA.add(groupId);
            /*if (setA.size() <= 0) {
            }*/
        }
        String uGid = loginUser.getGroupId();
        Set<String> setB = new HashSet<>();
        if (StringUtils.isBlank(uGid)) {
            StatusResult all = pmsGroupService.findAll();
            List<PmsGroup> allGroups = (List<PmsGroup>) all.getData();
            for (PmsGroup allGroup : allGroups) {
                setB.add(allGroup.getGroupId());
            }
        } else {
            setB = findByPid(setB, uGid);
            //将自身条件到集合中去
            setB.add(uGid);
        }
        //两个结果取交集
        Set<String> res = new HashSet<>();
        res.addAll(setA);
        res.retainAll(setB);

        criteria.andGroupIdIn(new ArrayList<>(res));

        criteria.andUserTypeIn(Arrays.asList(new String[]{"1","2"}));
        criteria.andUserIdNotEqualTo(loginUser.getUserId());
        criteria.andNicknameLike("%" + keyWords + "%");
        example.setOrderByClause("mdate desc");
        example.setDistinct(true);
        List<SysUser> list = new ArrayList<>();

        // 查询当前页数据
        PageHelper.startPage(pageNum, pageSize);// 设置分页信息
        if (res.size() > 0) {
            list = sysUserMapper.selectByExample(example);
        }

        // 取出分页信息
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        pageBean.setList(list);
        pageBean.setTotalCount(pageInfo.getTotal());
        pageBean.setTotalPage(pageInfo.getPages());
        // 返回结果集
        return PageBean.ok(pageBean);
    }

    @Override
    public StatusResult addUser(SysUser sysUser) throws Exception {
        if (sysUser != null && sysUser.getUsername() != null) {
            SysUserExample example = new SysUserExample();
            example.or().andUsernameEqualTo(sysUser.getUsername());
            List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
            if (sysUsers != null && sysUsers.size() > 0) {
                return StatusResult.error("该用户"+StatusResult.FIND_EXIST);
            }
        } else {
            return StatusResult.error(StatusResult.ADD_FAIL);
        }
        // 设置用户信息
        sysUser.setUserId(UUIDUtils.getCode());
        sysUser.setCdate(new Date());
        sysUser.setMdate(new Date());
        sysUser.setPassword(MD5Utils.md5(sysUser.getPassword()));
        // 向用户表表中插入
        sysUserMapper.insert(sysUser);
        addRolesInfo(sysUser);
        // 正常返回
        return StatusResult.ok(StatusResult.ADD_SUCCESS);
    }

    @Override
    public StatusResult updateUser(SysUser sysUser) throws Exception {
        // 设置属性
        sysUser.setMdate(new Date());
        sysUser.setPassword(MD5Utils.md5(sysUser.getPassword()));
        // 根据主键更新
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        // 正常返回
        return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
    }

    @Override
    public StatusResult findByGroup(String userId) throws Exception {
        if (StringUtils.isNotBlank(userId)) {
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
            String groupId = sysUser.getGroupId();
            if (StringUtils.isNotBlank(groupId)) {
                Set<String> set = new HashSet<>();
                set = findByPid(set, groupId);
                set.add(groupId);

                SysUserExample example = new SysUserExample();
                Criteria criteria = example.createCriteria();
                criteria.andGroupIdIn(new ArrayList<>(set)).andUserTypeIn(Arrays.asList("2", "3"));
                List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
                if (sysUsers != null) {
                    return StatusResult.ok(sysUsers);
                }
            }
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }

    private void addRolesInfo(SysUser sysUser) {
        /**
         * 判断角色，增加用户角色关系表信息
         */
        String userType = sysUser.getUserType();
        String roleName = "";
        if ("1".equals(userType)) {
            roleName = "学生";
        }
        if ("2".equals(userType)) {
            roleName = "教师";
        }
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.or().andRolenameEqualTo(roleName);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        if (sysRoles != null && sysRoles.size() > 0) {
            SysRoleUserKey roleUserKey = new SysRoleUserKey();
            String roleId = sysRoles.get(0).getRoleId();
            if (StringUtils.isNotBlank(roleId)) {
                roleUserKey.setRoleId(roleId);
                roleUserKey.setUserId(sysUser.getUserId());
                sysRoleUserMapper.insert(roleUserKey);
            }
        }
    }

    @Autowired
    private PmsGroupService pmsGroupService;

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
