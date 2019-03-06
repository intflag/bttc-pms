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
import com.intflag.springboot.entity.app.${classNameUppercase};
import com.intflag.springboot.service.app.${classNameUppercase}Service;

/**
 * @author ${author}
 * @date ${date}
 * @Description ${functioncomment}管理
 * @version V1.0
 */
@RestController
public class ${classNameUppercase}Controller {

	@Autowired
	private ${classNameUppercase}Service ${classNameLowercase}Service;

	/**
	 * 分页
	 */
	@GetMapping("/app/${classNameLowercase}s")
	public PageBean pageQuery(PageBean pageBean) {
		try {
			//SecurityUtils.getSubject().checkPermission("${classNameLowercase}-list");//权限校验，配置菜单后去掉注释即可
			return ${classNameLowercase}Service.pageQuery(pageBean);
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
	@PostMapping("/app/${classNameLowercase}")
	public StatusResult add(${classNameUppercase} ${classNameLowercase}) {
		try {
			//SecurityUtils.getSubject().checkPermission("${classNameLowercase}-add");//权限校验，配置菜单后去掉注释即可
			return ${classNameLowercase}Service.add(${classNameLowercase});
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
	@GetMapping("/app/${classNameLowercase}/{id}")
	public StatusResult findById(@PathVariable String id) {
		try {
			//SecurityUtils.getSubject().checkPermission("${classNameLowercase}-find");//权限校验，配置菜单后去掉注释即可
			return ${classNameLowercase}Service.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @param ${classNameLowercase}
	 * @return
	 */
	@PutMapping("/app/${classNameLowercase}")
	public StatusResult update(${classNameUppercase} ${classNameLowercase}) {
		try {
			//SecurityUtils.getSubject().checkPermission("${classNameLowercase}-update");//权限校验，配置菜单后去掉注释即可
			return ${classNameLowercase}Service.update(${classNameLowercase});
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
	@DeleteMapping("/app/${classNameLowercase}/{ids}")
	public StatusResult deleteBatch(@PathVariable("ids") String ids) {
		try {
			//SecurityUtils.getSubject().checkPermission("${classNameLowercase}-delete");//权限校验，配置菜单后去掉注释即可
			return ${classNameLowercase}Service.delete(ids);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}
	}
}
