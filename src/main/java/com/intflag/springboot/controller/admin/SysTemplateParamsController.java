package com.intflag.springboot.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.admin.SysTemplateparams;
import com.intflag.springboot.service.admin.SysTemplateParamsService;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月27日 下午7:19:15
 * @Description 代码生成模板管理
 * @version V1.0
 */
@RestController
public class SysTemplateParamsController {

	@Autowired
	private SysTemplateParamsService sysTemplateParamsService;

	/**
	 * 分页
	 */
	@GetMapping("/admin/sysTemplateParamss")
	public PageBean pageQuery(PageBean pageBean) {
		try {
			SecurityUtils.getSubject().checkPermission("sysTemplateParams-list");
			return sysTemplateParamsService.pageQuery(pageBean);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return PageBean.noAuthority(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			return PageBean.error(pageBean);
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/admin/sysTemplateParams/{ids}")
	public StatusResult deleteBatch(@PathVariable("ids") String ids) {
		try {
			SecurityUtils.getSubject().checkPermission("sysTemplateParams-delete");
			return sysTemplateParamsService.delete(ids);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}
	}

	@GetMapping("/admin/sysTemplateParams/dataBaseTables")
	public StatusResult selectDataBaseTables() {
		try {
			//SecurityUtils.getSubject().checkPermission("sysTemplateParams-selectDataBaseTables");
			return sysTemplateParamsService.selectDataBaseTables();
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}
	@GetMapping("/admin/sysTemplateParams/fieldByTableName/{tableName}")
	public StatusResult selectFieldByTableName(@PathVariable("tableName") String tableName) {
		try {
			//SecurityUtils.getSubject().checkPermission("sysTemplateParams-selectFieldByTableName");
			return sysTemplateParamsService.selectFieldByTableName(tableName);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
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
	@PostMapping("/admin/sysTemplateParams")
	public StatusResult add(SysTemplateparams sysTemplateparams) {
		try {
//			SecurityUtils.getSubject().checkPermission("sysTemplateparams-add");
			return sysTemplateParamsService.add(sysTemplateparams);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.ADD_FAIL);
		}
	}
}
