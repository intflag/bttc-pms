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
import com.intflag.springboot.entity.app.PmsPlan;
import com.intflag.springboot.service.app.PmsPlanService;

import javax.servlet.http.HttpSession;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-03-27 21:31:50
 * @Description 论文计划管理
 * @version V1.0
 */
@RestController
public class PmsPlanController {

	@Autowired
	private PmsPlanService pmsPlanService;

	/**
	 * 分页
	 */
	@GetMapping("/app/pmsPlans")
	public PageBean pageQuery(PageBean pageBean) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsPlan-list");//权限校验，配置菜单后去掉注释即可
			return pmsPlanService.pageQuery(pageBean);
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
	@PostMapping("/app/pmsPlan")
	public StatusResult add(PmsPlan pmsPlan, HttpSession session) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsPlan-add");//权限校验，配置菜单后去掉注释即可
			return pmsPlanService.add(pmsPlan,session);
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
	@GetMapping("/app/pmsPlan/{id}")
	public StatusResult findById(@PathVariable String id) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsPlan-find");//权限校验，配置菜单后去掉注释即可
			return pmsPlanService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @param pmsPlan
	 * @return
	 */
	@PutMapping("/app/pmsPlan")
	public StatusResult update(PmsPlan pmsPlan) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsPlan-update");//权限校验，配置菜单后去掉注释即可
			return pmsPlanService.update(pmsPlan);
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
	@DeleteMapping("/app/pmsPlan/{ids}")
	public StatusResult deleteBatch(@PathVariable("ids") String ids) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsPlan-delete");//权限校验，配置菜单后去掉注释即可
			return pmsPlanService.delete(ids);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}
	}
}
