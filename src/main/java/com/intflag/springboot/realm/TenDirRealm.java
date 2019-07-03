package com.intflag.springboot.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.intflag.springboot.entity.admin.SysResource;
import com.intflag.springboot.entity.admin.SysResourceExample;
import com.intflag.springboot.entity.admin.SysUser;
import com.intflag.springboot.entity.admin.SysUserExample;
import com.intflag.springboot.entity.admin.SysUserExample.Criteria;
import com.intflag.springboot.mapper.admin.SysResourceMapper;
import com.intflag.springboot.mapper.admin.SysUserMapper;


/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月19日17:07:46
 * @Description 权限控制
 * @version V1.0
 */
public class TenDirRealm extends AuthorizingRealm {

	@Value("${TenDir.constant.adminUsername}")
	private String ADMIN_USERNAME;

	/**
	 * 用于读取配置文件
	 */
	/*static {
		try {
			Properties properties = new Properties();
			// 使用ClassLoader加载properties配置文件生成对应的输入流
			InputStream is = TenDirRealm.class.getClassLoader().getResourceAsStream("config/constant.properties");
			// 使用properties对象加载输入流
			properties.load(is);
			// 获取key对应的value值
			ADMIN_USERNAME = (String) properties.get("ADMIN_USERNAME");
		} catch (Exception e) {
			e.printStackTrace();
			ADMIN_USERNAME = "admin";
		}
	}*/

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysResourceMapper sysResourceMapper;

	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("自定义Realm中的认证方法执行了");
		// 根据用户名查询数据库中的密码
		// 获取token
		UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
		// 获取前台传递的用户名
		String username = passwordToken.getUsername();
		// 查询符合用户名的用户
		SysUserExample example = new SysUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<SysUser> sysUsers = sysUserMapper.selectByExample(example);

		if (sysUsers != null && sysUsers.size() > 0) {
			SysUser sysUser = sysUsers.get(0);
			// 框架负责比对数据库中的密码和页面的密码是否一致
			// 简单认证信息对象
			AuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), this.getName());
			return info;
		}
		return null;
	}

	/**
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 为用户授权
		/// info.addStringPermission("权限");
		// 获取当前用户
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		// 如果用户为超级管理员，授予全部权限，否则根据用户查询数据库，获取实际对应的权限
		List<SysResource> sysResources = null;
		if (ADMIN_USERNAME.equals(sysUser.getUsername())) {
			SysResourceExample example = new SysResourceExample();
			sysResources = sysResourceMapper.selectByExample(example);
		} else {
			sysResources = sysResourceMapper.selectAllByUserId(sysUser.getUserId());
		}
		// 遍历权限集合，进行授权
		for (SysResource sysResource : sysResources) {
			info.addStringPermission(sysResource.getRescode());
		}
		return info;
	}

}
