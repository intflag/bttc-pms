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
import com.intflag.springboot.entity.app.PmsRecord;
import com.intflag.springboot.service.app.PmsRecordService;

import javax.servlet.http.HttpSession;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-03-29 15:58:59
 * @Description 指导记录管理
 * @version V1.0
 */
@RestController
public class PmsRecordController {

	@Autowired
	private PmsRecordService pmsRecordService;

	/**
	 * 分页
	 */
	@GetMapping("/app/pmsRecords")
	public PageBean pageQuery(PageBean pageBean,HttpSession session) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsRecord-list");//权限校验，配置菜单后去掉注释即可
			return pmsRecordService.pageQuery(pageBean,session);
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
	@PostMapping("/app/pmsRecord")
	public StatusResult add(PmsRecord pmsRecord, HttpSession session) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsRecord-add");//权限校验，配置菜单后去掉注释即可
			return pmsRecordService.add(pmsRecord,session);
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
	@GetMapping("/app/pmsRecord/{id}")
	public StatusResult findById(@PathVariable String id) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsRecord-find");//权限校验，配置菜单后去掉注释即可
			return pmsRecordService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 根据用户查找指导记录
	 * @param session
	 * @return
	 */
	@GetMapping("/app/pmsRecord/user")
	public StatusResult findByUser(HttpSession session) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsRecord-find");//权限校验，配置菜单后去掉注释即可
			return pmsRecordService.findByUser(session);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.FIND_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @param pmsRecord
	 * @return
	 */
	@PutMapping("/app/pmsRecord")
	public StatusResult update(PmsRecord pmsRecord) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsRecord-update");//权限校验，配置菜单后去掉注释即可
			return pmsRecordService.update(pmsRecord);
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
	@DeleteMapping("/app/pmsRecord/{ids}")
	public StatusResult deleteBatch(@PathVariable("ids") String ids) {
		try {
			//SecurityUtils.getSubject().checkPermission("pmsRecord-delete");//权限校验，配置菜单后去掉注释即可
			return pmsRecordService.delete(ids);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.NO_AUTHORITY);
		} catch (Exception e) {
			e.printStackTrace();
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}
	}
}
