package com.intflag.springboot.controller.app;

import com.intflag.springboot.common.util.FastDFSClient;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.admin.SysUser;
import com.intflag.springboot.entity.app.PmsAppendix;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.PmsPaper;
import com.intflag.springboot.service.app.PmsPaperService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘国鑫QQ1598749808
 * @version V1.0
 * @date 2019-03-29 16:06:08
 * @Description 论文提交管理
 */
@RestController
public class PmsPaperController {

    @Autowired
    private PmsPaperService pmsPaperService;

    @Value("${TenDir.file.rootPath}")
    private String fileRootPath;

    @Value("${TenDir.image.server.address}")
    private String serverAddress;

    @PostMapping("/app/pmsPaper/uploadPaper")
    public StatusResult appendixUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
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

            // 封装参数
            BigDecimal fileSize = new BigDecimal(file.getSize() / 1024.0 / 1024.0);
            url = serverAddress + url;
            PmsPaper pmsPaper = new PmsPaper();
            pmsPaper.setFileType(extName);
//            pmsPaper.setFileSize(fileSize);

            map.put("src", url);
            map.put("title", originalFilename);
            return StatusResult.build(null, 0, "文件上传成功", map);
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
