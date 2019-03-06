package com.intflag.springboot.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.MD5Utils;
import com.intflag.springboot.common.util.VerifyCode;
import com.intflag.springboot.entity.admin.SysUser;
import com.intflag.springboot.service.admin.SysUserService;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月19日15:11:42
 * @Description 用户管理
 * @version V1.0
 */
@RestController
public class SysUserController {

	@Value("${TenDir.constant.adminUsername}")
	private String ADMIN_USERNAME;

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 显示验证码页面
	 * 
	 * @return
	 */
	@GetMapping("/admin/sysUser/validateCode")
	public void showValidatecode(HttpServletRequest request, HttpServletResponse response) {
		try {
			VerifyCode code = new VerifyCode();
			BufferedImage image = code.createImage();
			String validateCode = code.getText();
			request.getSession().setAttribute("validateCode", validateCode);
			ImageIO.write(image, "png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示登录页面
	 * 
	 * @return
	 */
	@GetMapping("/admin/sysUser/login")
	public ModelAndView showloginPage() {
		ModelAndView view = new ModelAndView("admin/login");
		return view;
	}

	/**
	 * user login使用shiro框架进行认证
	 * 
	 * @param sysUser
	 * @return
	 */
	@PostMapping("/admin/sysUser/login")
	public StatusResult login(SysUser sysUser, String ckcode, HttpSession session) {
		String validateCode = (String) session.getAttribute("validateCode");
		System.out.println(ADMIN_USERNAME);
		if (validateCode.equalsIgnoreCase(ckcode)) {
			// 获得当前用户对象，状态为未认证状态
			Subject subject = SecurityUtils.getSubject();
			// 创建用户名密码认证令牌
			AuthenticationToken token = new UsernamePasswordToken(sysUser.getUsername(),
					MD5Utils.md5(sysUser.getPassword()));
			try {
				subject.login(token);
				SysUser loginUser = (SysUser) subject.getPrincipal();
				// 登陆成功，将用户放入session中
				session.setAttribute("loginUser", loginUser);
				return StatusResult.ok();
			} catch (UnknownAccountException e) {
				e.printStackTrace();
				return StatusResult.error("用户名不存在");
			} catch (IncorrectCredentialsException e) {
				e.printStackTrace();
				return StatusResult.error("密码错误");
			} catch (Exception e) {
				e.printStackTrace();
				return StatusResult.error("用户名或密码错误");
			}
		} else {
			return StatusResult.error("验证码错误");
		}
	}
	
	/**
	 * 获取当前用户
	 * @param session
	 * @return
	 */
	@GetMapping("/admin/sysUser/currentUser")
	public StatusResult getCurrentUser(HttpSession session) {
		SysUser loginUser = (SysUser) session.getAttribute("loginUser");
		if (loginUser != null) {
			SysUser currUser = new SysUser();
			currUser.setUsername(loginUser.getUsername());
			currUser.setNickname(loginUser.getNickname());
			return StatusResult.ok(currUser);
		} else {
			return StatusResult.error(StatusResult.FIND_NONE);
		}
	}

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	@GetMapping("/admin/sysUser/logout")
	public StatusResult logout() {
		// 获取subject，判断是否已经认证，调用注销方法
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
		// 返回登录页
		return StatusResult.ok();
	}

	/**
	 * 查询菜单
	 * 
	 * @param userId
	 * @param username
	 * @param adminUsername
	 * @return
	 */
	@GetMapping("/admin/sysUser/menu")
	public StatusResult findMenu(HttpSession session) {
		try {
			SysUser loginUser = (SysUser) session.getAttribute("loginUser");
			return sysUserService.findMenu(loginUser.getUserId(), loginUser.getUsername(), ADMIN_USERNAME);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 分页
	 * 
	 * @param pageBean
	 * @return
	 */
	@GetMapping("/admin/sysUsers")
	public PageBean pageQuery(PageBean pageBean, HttpSession session) {
		try {
			SecurityUtils.getSubject().checkPermission("sysUser-list");
			SysUser loginUser = (SysUser) session.getAttribute("loginUser");
			return sysUserService.pageQuery(pageBean, loginUser);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return PageBean.noAuthority(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			return PageBean.error(pageBean);
		}
	}

	/**
	 * 新增
	 * 
	 * @param sysUser
	 * @return
	 */
	@PostMapping("/admin/sysUser")
	public StatusResult add(SysUser sysUser) {
		try {
			SecurityUtils.getSubject().checkPermission("sysUser-add");
			return sysUserService.add(sysUser);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.ADD_FAIL);
		}
	}

	/**
	 * 查找
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/admin/sysUser/{userId}")
	public StatusResult findById(@PathVariable String userId) {
		try {
			return sysUserService.findById(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 更新
	 * 
	 * @param sysUser
	 * @return
	 */
	@PutMapping("/admin/sysUser")
	public StatusResult update(SysUser sysUser) {
		try {
			SecurityUtils.getSubject().checkPermission("sysUser-update");
			return sysUserService.update(sysUser);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			// 异常返回
			return StatusResult.error(StatusResult.UPDATE_FAIL);
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @param session
	 * @return
	 */
	@DeleteMapping("/admin/sysUser/{ids}")
	public StatusResult deleteBatch(@PathVariable("ids") String ids, HttpSession session) {
		try {
			SecurityUtils.getSubject().checkPermission("sysUser-delete");
			SysUser loginUser = (SysUser) session.getAttribute("loginUser");
			return sysUserService.delete(ids, loginUser);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			// 异常返回
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}
	}
}
