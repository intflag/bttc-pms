package com.intflag.springboot.controller.app;

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
import com.intflag.springboot.entity.app.PmsGroup;
import com.intflag.springboot.service.app.PmsGroupService;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-03-18 23:48:29
 * @Description 机构管理
 * @version V1.0
 */
@RestController
public class PmsGroupController {

	@Autowired
	private PmsGroupService pmsGroupService;

	/**
	 * 分页
	 */
	@GetMapping("/app/pmsGroups")
	public PageBean pageQuery(PageBean pageBean) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsGroup-list");//权限校验，配置菜单后去掉注释即可
			return pmsGroupService.pageQuery(pageBean);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return PageBean.noAuthority(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			return PageBean.error(pageBean);
		}
	}
	/**
	 * 分页
	 */
	@GetMapping("/app/pmsGroupList")
	public StatusResult pmsGroupList() {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsGroup-find");//权限校验，配置菜单后去掉注释即可
			return pmsGroupService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@PostMapping("/app/pmsGroup")
	public StatusResult add(PmsGroup pmsGroup) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsGroup-add");//权限校验，配置菜单后去掉注释即可
			return pmsGroupService.add(pmsGroup);
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
	 * @param id
	 * @return
	 */
	@GetMapping("/app/pmsGroup/{id}")
	public StatusResult findById(@PathVariable String id) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsGroup-find");//权限校验，配置菜单后去掉注释即可
			return pmsGroupService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @param pmsGroup
	 * @return
	 */
	@PutMapping("/app/pmsGroup")
	public StatusResult update(PmsGroup pmsGroup) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsGroup-update");//权限校验，配置菜单后去掉注释即可
			return pmsGroupService.update(pmsGroup);
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
	@DeleteMapping("/app/pmsGroup/{ids}")
	public StatusResult deleteBatch(@PathVariable("ids") String ids) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsGroup-delete");//权限校验，配置菜单后去掉注释即可
			return pmsGroupService.delete(ids);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}
	}
}
