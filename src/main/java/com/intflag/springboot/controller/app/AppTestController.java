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
import com.intflag.springboot.entity.app.AppTest;
import com.intflag.springboot.service.app.AppTestService;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2018-08-29 10:34:09
 * @Description 测试管理
 * @version V1.0
 */
@RestController
public class AppTestController {

	@Autowired
	private AppTestService appTestService;

	/**
	 * 分页
	 */
	@GetMapping("/app/appTests")
	public PageBean pageQuery(PageBean pageBean) {
		try {
			//SecurityUtils.getSubject().checkPermission("appTest-list");//权限校验，配置菜单后去掉注释即可
			return appTestService.pageQuery(pageBean);
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
	@PostMapping("/app/appTest")
	public StatusResult add(AppTest appTest) {
		try {
			//SecurityUtils.getSubject().checkPermission("appTest-add");//权限校验，配置菜单后去掉注释即可
			return appTestService.add(appTest);
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
	@GetMapping("/app/appTest/{id}")
	public StatusResult findById(@PathVariable String id) {
		try {
			//SecurityUtils.getSubject().checkPermission("appTest-find");//权限校验，配置菜单后去掉注释即可
			return appTestService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @param appTest
	 * @return
	 */
	@PutMapping("/app/appTest")
	public StatusResult update(AppTest appTest) {
		try {
			//SecurityUtils.getSubject().checkPermission("appTest-update");//权限校验，配置菜单后去掉注释即可
			return appTestService.update(appTest);
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
	@DeleteMapping("/app/appTest/{ids}")
	public StatusResult deleteBatch(@PathVariable("ids") String ids) {
		try {
			//SecurityUtils.getSubject().checkPermission("appTest-delete");//权限校验，配置菜单后去掉注释即可
			return appTestService.delete(ids);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}
	}
}
