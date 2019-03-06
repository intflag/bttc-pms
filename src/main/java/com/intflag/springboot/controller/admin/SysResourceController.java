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
import com.intflag.springboot.entity.admin.SysResource;
import com.intflag.springboot.service.admin.SysResourceService;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月22日 下午1:59:18
 * @Description 资源菜单管理
 * @version V1.0
 */
@RestController
public class SysResourceController {

	@Autowired
	private SysResourceService sysResourceService;

	/**
	 * 分页
	 */
	@GetMapping("/admin/sysResources")
	public PageBean pageQuery(PageBean pageBean) {
		try {
			SecurityUtils.getSubject().checkPermission("sysResource-list");
			return sysResourceService.pageQuery(pageBean);
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
	@PostMapping("/admin/sysResource")
	public StatusResult add(SysResource sysResource) {
		try {
			SecurityUtils.getSubject().checkPermission("sysResource-add");
			return sysResourceService.add(sysResource);
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
	 * @param resourceId
	 * @return
	 */
	@GetMapping("/admin/sysResource/{resourceId}")
	public StatusResult findById(@PathVariable String resourceId) {
		try {
			return sysResourceService.findById(resourceId);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @param sysResource
	 * @return
	 */
	@PutMapping("/admin/sysResource")
	public StatusResult update(SysResource sysResource) {
		try {
			SecurityUtils.getSubject().checkPermission("sysResource-update");
			return sysResourceService.update(sysResource);
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
	@DeleteMapping("/admin/sysResource/{ids}")
	public StatusResult deleteBatch(@PathVariable("ids") String ids) {
		try {
			SecurityUtils.getSubject().checkPermission("sysResource-delete");
			return sysResourceService.delete(ids);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}
	}

	/**
	 * 根据类型查询
	 * 
	 * @param resourceId
	 * @return
	 */
	@GetMapping("/admin/sysResource/type/{type}")
	public StatusResult findByType(@PathVariable String type) {
		try {
			return sysResourceService.findByType(type);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

}
