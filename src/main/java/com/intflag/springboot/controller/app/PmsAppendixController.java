package com.intflag.springboot.controller.app;

import com.intflag.springboot.common.util.FastDFSClient;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.admin.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.PmsAppendix;
import com.intflag.springboot.service.app.PmsAppendixService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 刘国鑫QQ1598749808
 * @version V1.0
 * @date 2019-02-24 16:16:49
 * @Description 附件管理管理
 */
@RestController
public class PmsAppendixController {

    @Autowired
    private PmsAppendixService pmsAppendixService;

    @Value("${TenDir.file.rootPath}")
    private String fileRootPath;

    @Value("${TenDir.image.server.address}")
    private String serverAddress;

    @PostMapping("/app/appendixUpload")
    public StatusResult appendixUpload(@RequestParam("file") MultipartFile file,HttpSession session) {
        try {
            //SecurityUtils.getSubject().checkPermission("pmsAppendix-add");//权限校验，配置菜单后去掉注释即可
            if (file == null) {
                return StatusResult.error("附件不能为空");
            }
            if (StringUtils.isEmpty(fileRootPath)) {
                return StatusResult.error("没有配置附件的保存地址");
            }
            Map<String, Object> map = new HashMap<>(16);
            // 将图片上传到图片服务器
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:client.conf");
            // 返回图片文件名
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String url = fastDFSClient.uploadFile(file.getBytes(), extName);
            //将附件信息插入附件表
            PmsAppendix pmsAppendix = new PmsAppendix();
            pmsAppendix.setAppendixName(originalFilename);
            pmsAppendix.setSize(new BigDecimal(file.getSize()/1024.0/1024.0));
            SysUser loginUser = (SysUser) session.getAttribute("loginUser");
            if (loginUser != null) {
                pmsAppendix.setPublisher(loginUser.getNickname());
                pmsAppendix.setUserId(loginUser.getUserId());
            }

            // 封装参数
            url = serverAddress + url;
            pmsAppendix.setAppendixUrl(url);
            pmsAppendix.setFlag("1");
            pmsAppendix.setCdate(new Date());
            pmsAppendix.setMdate(new Date());
            String appendixId = UUIDUtils.getCode();
            pmsAppendix.setAppendixId(appendixId);
            pmsAppendixService.add(pmsAppendix);
            map.put("src",url);
            map.put("title",originalFilename);
            map.put("appendixId",appendixId);
            return StatusResult.build(null,0,"文件上传成功",map);
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return StatusResult.error(StatusResult.NO_AUTHORITY);
        } catch (Exception e) {
            e.printStackTrace();
            return StatusResult.error("附件上传失败");
        }
    }

    /**
     * 分页
     */
    @GetMapping("/app/pmsAppendixs")
    public PageBean pageQuery(PageBean pageBean) {
        try {
            //SecurityUtils.getSubject().checkPermission("pmsAppendix-list");//权限校验，配置菜单后去掉注释即可
            return pmsAppendixService.pageQuery(pageBean);
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
    @GetMapping("api/app/pmsAppendixs")
    public PageBean apiPageQuery(PageBean pageBean) {
        try {
            //SecurityUtils.getSubject().checkPermission("pmsAppendix-list");//权限校验，配置菜单后去掉注释即可
            return pmsAppendixService.pageQuery(pageBean);
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
    @GetMapping("/app/blogAndPmsAppendixs")
    public PageBean blogAndPmsAppendixs(PageBean pageBean) {
        try {
            //SecurityUtils.getSubject().checkPermission("pmsAppendix-list");//权限校验，配置菜单后去掉注释即可
            return pmsAppendixService.blogAndPmsAppendixs(pageBean);
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
    @GetMapping("/api/app/blogAndPmsAppendixs")
    public PageBean apiBlogAndPmsAppendixs(PageBean pageBean) {
        try {
            //SecurityUtils.getSubject().checkPermission("pmsAppendix-list");//权限校验，配置菜单后去掉注释即可
            return pmsAppendixService.blogAndPmsAppendixs(pageBean);
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
    @PostMapping("/app/pmsAppendix")
    public StatusResult add(PmsAppendix pmsAppendix) {
        try {
            //SecurityUtils.getSubject().checkPermission("pmsAppendix-add");//权限校验，配置菜单后去掉注释即可
            return pmsAppendixService.add(pmsAppendix);
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
    @GetMapping("/app/pmsAppendix/{id}")
    public StatusResult findById(@PathVariable String id) {
        try {
            //SecurityUtils.getSubject().checkPermission("pmsAppendix-find");//权限校验，配置菜单后去掉注释即可
            return pmsAppendixService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return StatusResult.error(StatusResult.FIND_FAIL);
        }
    }

    /**
     * 修改
     *
     * @param pmsAppendix
     * @return
     */
    @PutMapping("/app/pmsAppendix")
    public StatusResult update(PmsAppendix pmsAppendix) {
        try {
            //SecurityUtils.getSubject().checkPermission("pmsAppendix-update");//权限校验，配置菜单后去掉注释即可
            return pmsAppendixService.update(pmsAppendix);
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
    @DeleteMapping("/app/pmsAppendix/{ids}")
    public StatusResult deleteBatch(@PathVariable("ids") String ids) {
        try {
            //SecurityUtils.getSubject().checkPermission("pmsAppendix-delete");//权限校验，配置菜单后去掉注释即可
            return pmsAppendixService.delete(ids);
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return StatusResult.error(StatusResult.NO_AUTHORITY);
        } catch (Exception e) {
            e.printStackTrace();
            return StatusResult.error(StatusResult.DELETE_FAIL);
        }
    }
}
