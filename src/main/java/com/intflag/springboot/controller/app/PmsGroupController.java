package com.intflag.springboot.controller.app;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.intflag.springboot.entity.admin.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.PmsGroup;
import com.intflag.springboot.service.app.PmsGroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

	@RequestMapping("/app/pmsGroup/userImportTemplate")
    public void downloadByPoiBaseView(ModelMap map, HttpServletRequest request,
                                      HttpServletResponse response){
        /*List<SysUser> list = new ArrayList<>();
        SysUser sysUser = new SysUser();
        sysUser.setUserId("1514104007");
        sysUser.setUsername("1514104007");
        sysUser.setPassword("123456");
        sysUser.setNickname("哈哈哈");
        sysUser.setGender("男");
        sysUser.setEmail("asddas@asd.com");
        sysUser.setTelephone("17604892557");
        sysUser.setWechatId("wx123");
        sysUser.setQqId("1598749808");
        sysUser.setGroupId("group001");
        sysUser.setGroupName("15外包一班");
        list.add(sysUser);


        String fileName = getFileNameByGroupId(id);
        if (StringUtils.isNotBlank(fileName)) {
            map.put(NormalExcelConstants.FILE_NAME, fileName+"-导入学生信息模板");
        } else {
            map.put(NormalExcelConstants.FILE_NAME, "未知机构导入模板");
        }*/

        ExportParams params = new ExportParams("学生&教师信息", "学生信息", ExcelType.XSSF);
        map.put(NormalExcelConstants.FILE_NAME, "BTTC-PMS学生&教师导入模板");
        map.put(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        map.put(NormalExcelConstants.CLASS, SysUser.class);
        map.put(NormalExcelConstants.PARAMS, params);
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);

    }

    private String getFileNameByGroupId(String id) {
	    Map<String,String> map = new HashMap<>(16);
        String fileName = null;
        if (StringUtils.isNotBlank(id)) {
            StatusResult groupRes = null;
            try {
                groupRes = pmsGroupService.findById(id);
                PmsGroup pmsGroup = (PmsGroup) groupRes.getData();
                if (pmsGroup != null) {
                    map.put("groupId",pmsGroup.getId());
                    StatusResult pGroup = pmsGroupService.findById(pmsGroup.getpId());
                    PmsGroup pPmsGroup = (PmsGroup) pGroup.getData();
                    if (pPmsGroup != null) {
                        fileName = new String(pPmsGroup.getGroupName()+pmsGroup.getGroupName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return fileName;
    }
/*
	@RequestMapping("/app/pmsGroup/userImportTemplate/")
	public String download(ModelMap map) {
		List<SysUser> list = new ArrayList<>();
        SysUser sysUser = new SysUser();
        sysUser.setUserId("1514104007");
        sysUser.setUsername("1514104007");
        sysUser.setPassword("123456");
        sysUser.setNickname("哈哈哈");
		sysUser.setGender("男");
		sysUser.setEmail("asddas@asd.com");
		sysUser.setTelephone("17604892557");
		sysUser.setWechatId("wx123");
		sysUser.setQqId("1598749808");
		sysUser.setGroupId("group001");
		sysUser.setGroupName("15外包一班");

        ExportParams params = new ExportParams("导入", "测试", ExcelType.XSSF);
		params.setFreezeCol(2);
		map.put(NormalExcelConstants.DATA_LIST, list);
		map.put(NormalExcelConstants.CLASS, SysUser.class);
		map.put(NormalExcelConstants.PARAMS, params);
		return NormalExcelConstants.EASYPOI_EXCEL_VIEW;

	}
*/
}
