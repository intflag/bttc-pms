package com.intflag.springboot.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.admin.SysRole;
import com.intflag.springboot.service.admin.SysRoleService;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月21日21:20:18
 * @Description 角色管理
 * @version V1.0
 */
@RestController
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@GetMapping("/admin/sysRoleList")
	public StatusResult findList() {
		try {
			return sysRoleService.findList();
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 根据角色ID查询角色ID
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/admin/sysRole/sysRoleUserKeys/{userId}")
	public StatusResult findRoleIdByUserId(@PathVariable String userId) {
		try {
			return sysRoleService.findRoleIdByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 根据角色ID查询资源ID
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/admin/sysRole/sysRoleResKeys/{roleId}")
	public StatusResult findResourceIdByRoleId(@PathVariable String roleId) {
		try {
			return sysRoleService.findResourceIdByRoleId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 查询角色名称是否重复
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping("/admin/sysRole/checkRolenameIsExist")
	public StatusResult checkRolenameIsExist(String name) {
		try {
			return sysRoleService.checkRolenameIsExist(name);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_EXIST);
		}
	}

	/**
	 * 分页
	 */
	@GetMapping("/admin/sysRoles")
	public PageBean pageQuery(PageBean pageBean) {
		try {
			SecurityUtils.getSubject().checkPermission("sysRole-list");
			return sysRoleService.pageQuery(pageBean);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return PageBean.noAuthority(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			return PageBean.error(pageBean);
		}
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@PostMapping("/admin/sysRole")
	public StatusResult add(SysRole sysRole) {
		try {
			SecurityUtils.getSubject().checkPermission("sysRole-add");
			return sysRoleService.add(sysRole);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.ADD_FAIL);
		}
	}

	/**
	 * 根据ID查询
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/admin/sysRole/{roleId}")
	public StatusResult findById(@PathVariable String roleId) {
		try {
			return sysRoleService.findById(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @param sysRole
	 * @return
	 */
	@PutMapping("/admin/sysRole")
	public StatusResult update(SysRole sysRole) {
		try {
			SecurityUtils.getSubject().checkPermission("sysRole-update");
			return sysRoleService.update(sysRole);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.UPDATE_FAIL);
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/admin/sysRole/{ids}")
	public StatusResult deleteBatch(@PathVariable("ids") String ids) {
		try {
			SecurityUtils.getSubject().checkPermission("sysRole-delete");
			return sysRoleService.delete(ids);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}
	}

	/**
	 * 获取所有菜单
	 * 
	 * @return
	 */
	@GetMapping("/admin/sysRole/resources")
	public StatusResult findResourceList() {
		try {
			return sysRoleService.findResourceList();
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}
}