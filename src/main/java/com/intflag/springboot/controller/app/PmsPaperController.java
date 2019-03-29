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
import com.intflag.springboot.entity.app.PmsPaper;
import com.intflag.springboot.service.app.PmsPaperService;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-03-29 16:06:08
 * @Description 论文提交管理
 * @version V1.0
 */
@RestController
public class PmsPaperController {

	@Autowired
	private PmsPaperService pmsPaperService;

	/**
	 * 分页
	 */
	@GetMapping("/app/pmsPapers")
	public PageBean pageQuery(PageBean pageBean) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsPaper-list");//权限校验，配置菜单后去掉注释即可
			return pmsPaperService.pageQuery(pageBean);
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
	@PostMapping("/app/pmsPaper")
	public StatusResult add(PmsPaper pmsPaper) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsPaper-add");//权限校验，配置菜单后去掉注释即可
			return pmsPaperService.add(pmsPaper);
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
	@GetMapping("/app/pmsPaper/{id}")
	public StatusResult findById(@PathVariable String id) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsPaper-find");//权限校验，配置菜单后去掉注释即可
			return pmsPaperService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @param pmsPaper
	 * @return
	 */
	@PutMapping("/app/pmsPaper")
	public StatusResult update(PmsPaper pmsPaper) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsPaper-update");//权限校验，配置菜单后去掉注释即可
			return pmsPaperService.update(pmsPaper);
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
	@DeleteMapping("/app/pmsPaper/{ids}")
	public StatusResult deleteBatch(@PathVariable("ids") String ids) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsPaper-delete");//权限校验，配置菜单后去掉注释即可
			return pmsPaperService.delete(ids);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}
	}
}
