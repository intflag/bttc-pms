package com.intflag.springboot.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.*;
import com.intflag.springboot.entity.admin.SysResource;
import com.intflag.springboot.entity.admin.SysTemplateparams;
import com.intflag.springboot.entity.admin.SysTemplateparamsExample;
import com.intflag.springboot.mapper.admin.SysResourceMapper;
import com.intflag.springboot.mapper.admin.SysTemplateparamsMapper;
import com.intflag.springboot.service.admin.SysTemplateParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘国鑫 QQ:1598749808
 * @version V1.0
 * @date 2018年8月27日 下午7:23:50
 * @Description 代码生成模板业务层接口实现
 */
@Service
@Transactional
public class SysTemplateParamsServiceImpl implements SysTemplateParamsService {

    @Autowired
    private SysTemplateparamsMapper templateparamsMapper;

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Value("${TenDir.constant.dataBase}")
    private String dataBase;
    @Value("${TenDir.constant.dataBase.username}")
    private String username;
    @Value("${TenDir.constant.dataBase.password}")
    private String password;

    @Override
    public PageBean pageQuery(PageBean pageBean) throws Exception {
        String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
        int pageNum = pageBean.getCurrPage();// 当前页
        int pageSize = pageBean.getPageSize();// 每页显示条数
        // 查询当前页数据
        PageHelper.startPage(pageNum, pageSize);// 设置分页信息
        // 执行查询
        SysTemplateparamsExample example = new SysTemplateparamsExample();
        example.or().andTablenameLike("%" + keyWords + "%");
        example.setOrderByClause("cdate desc");
        List<SysTemplateparams> list = templateparamsMapper.selectByExample(example);
        // 取出分页信息
        PageInfo<SysTemplateparams> pageInfo = new PageInfo<>(list);
        pageBean.setList(list);
        pageBean.setTotalCount(pageInfo.getTotal());// 设置总记录数
        pageBean.setTotalPage(pageInfo.getPages());// 设置总页数
        // 返回结果集
        return PageBean.ok(pageBean);
    }

    @Override
    public StatusResult delete(String ids) throws Exception {
        if (ids != null) {
            String[] templateIds = ids.split(",");
            if (templateIds != null && templateIds.length > 0) {
                for (String templateId : templateIds) {
                    // 根据主键删除
                    templateparamsMapper.deleteByPrimaryKey(templateId);
                }
                // 正常返回
                return StatusResult.ok(StatusResult.DELETE_SUCCESS);
            } else {
                // 异常返回
                return StatusResult.error(StatusResult.DELETE_FAIL);
            }
        } else {
            // 异常返回
            return StatusResult.error(StatusResult.DELETE_FAIL);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public StatusResult selectDataBaseTables() throws Exception {
        List<Map> tables = templateparamsMapper.selectTablesByDataBase(dataBase);
        if (tables != null) {
            return StatusResult.ok(tables);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public StatusResult selectFieldByTableName(String tableName) throws Exception {
        List<Map> fields = templateparamsMapper.selectFieldByTableName(dataBase, tableName);
        if (fields != null) {
            return StatusResult.ok(fields);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }

    @Override
    public StatusResult add(SysTemplateparams sysTemplateparams) throws Exception {
        // 设置资源菜单信息
        sysTemplateparams.setTemplateId(UUIDUtils.getCode());
        sysTemplateparams.setCdate(new Date());
        sysTemplateparams.setFlag("1");
        sysTemplateparams.setClassnames(TenDirStringUtils.string2HumpAndUppercase(sysTemplateparams.getTablename()));
        sysTemplateparams.setActionurl("app/" + TenDirStringUtils.string2HumpAndLowercase(sysTemplateparams.getTablename()) + "/");
        // 查询表主键
        String primaryKey = templateparamsMapper.selectPrimaryKeyByTableName(dataBase,
                sysTemplateparams.getTablename());
        sysTemplateparams.setPkcolumn(primaryKey);
        // 生成Entity，Mapper以及映射文件
        sysTemplateparams = generateCode(sysTemplateparams);
        templateparamsMapper.insert(sysTemplateparams);
        // 正常返回
        return StatusResult.ok(StatusResult.ADD_SUCCESS);
    }

    /**
     * 生成增删改查相关代码
     *
     * @param sysTemplateparams
     * @return
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private SysTemplateparams generateCode(SysTemplateparams sysTemplateparams) throws Exception {
        Map<String, Object> data = new HashMap<>();
        // 文件头相关信息
        data.put("functioncomment", sysTemplateparams.getFunctioncomment());
        data.put("author", sysTemplateparams.getAuthor());
        data.put("date", DateUtils.date2String(new Date()));
        // 数据库信息
        data.put("dataBase", dataBase);
        data.put("username", username);
        data.put("password", password);
        // 相关数据库表
        data.put("tablename", sysTemplateparams.getTablename());
        // 工作空间路径
        String filepath = sysTemplateparams.getFilepath();
        data.put("filepath", filepath);
        // 设置主键列名
        data.put("pkcolumn", TenDirStringUtils.string2HumpAndUppercase(sysTemplateparams.getPkcolumn()));
        data.put("pkcolumnLowercase", TenDirStringUtils.string2HumpAndLowercase(sysTemplateparams.getPkcolumn()));
        // 模糊查询关键字列名
        data.put("keywordcolumn", TenDirStringUtils.string2HumpAndUppercase(sysTemplateparams.getKeywordcolumn()));

        // 实体生成路径
        data.put("entityFilePath", filepath + "/src/main/java/");
        // 接口生成路径
        data.put("mapperFilePath", filepath + "/src/main/java/");
        // 映射文件生成路径
        data.put("xmlFilePath", filepath + "/src/main/resources/mybatis/");

        // 模块名称大小写
        String classNameUppercase = TenDirStringUtils.string2HumpAndUppercase(sysTemplateparams.getTablename());
        String classNameLowercase = TenDirStringUtils.string2HumpAndLowercase(sysTemplateparams.getTablename());
        data.put("classNameUppercase", classNameUppercase);
        data.put("classNameLowercase", classNameLowercase);

        // 生成Controller、Service、ServiceImpl
        generateControllerAndServiceAndImpl(data);
        // 生成POJO实体、DAO接口、Mybatis映射文件
        generateEntityAndMapperAndXml(data);

        // 是否生成菜单，生成菜单即代表生成静态页
        if ("1".equals(sysTemplateparams.getAddmenu()) && !StringUtils.isEmpty(sysTemplateparams.getPid())) {
            // 生成菜单
            addMenu(sysTemplateparams);
            // 根据表名和数据库查询所有字段
            List<Map> cloums = templateparamsMapper.selectFieldByTableName(dataBase, sysTemplateparams.getTablename());
            //将字段转换为驼峰式命名
            for (Map map : cloums) {
                String cName = (String) map.get("COLUMN_NAME");
                cName = TenDirStringUtils.string2HumpAndLowercase(cName);
                map.put("COLUMN_NAME", cName);
            }
            data.put("cloums", cloums);
            // 生成静态页
            generateHtmlAndJs(data);
        }
        return sysTemplateparams;
    }

    /**
     * 生成静态页面和js
     *
     * @param data
     * @throws Exception
     * @throws IOException
     */
    private void generateHtmlAndJs(Map<String, Object> data) throws IOException, Exception {

        String htmlFilePath = data.get("filepath") + "/src/main/resources/static/content/dist/views/app/"
                + data.get("classNameLowercase");
        String jsFilePath = data.get("filepath") + "/src/main/resources/static/admin-static/javascript/"
                + data.get("classNameLowercase") + "/" + data.get("classNameLowercase") + ".js";
        // 生成html和js
        TemplateHelp.creatTemplate(data, "Html-Index.ftl", htmlFilePath + "/index.html");
        TemplateHelp.creatTemplate(data, "Html-Add.ftl", htmlFilePath + "/add.html");
        TemplateHelp.creatTemplate(data, "Html-Edit.ftl", htmlFilePath + "/edit.html");
        TemplateHelp.creatTemplate(data, "Html-Js.ftl", jsFilePath);

    }

    /**
     * 生成菜单
     *
     * @param sysTemplateparams
     */
    private void addMenu(SysTemplateparams sysTemplateparams) {
        String classNameLowercase = TenDirStringUtils.string2HumpAndLowercase(sysTemplateparams.getTablename());
        String pid = sysTemplateparams.getPid();
        SysResource resource = new SysResource();
        resource.setResourceId(UUIDUtils.getCode());
        resource.setParentId(pid);// 设置上级菜单
        resource.setResname(sysTemplateparams.getFunctioncomment());// 设置菜单名称
        resource.setResurl("app/" + classNameLowercase + "/");// 设置菜单URL
        resource.setRescode(classNameLowercase + "-list");// 授权标识
        resource.setIcon("fa fa-adjust");// 图标
        resource.setType("1");// 类型
        resource.setDescription(sysTemplateparams.getFunctioncomment());// 描述
        resource.setFlag("1");// 标记
        resource.setSort(1);// 排序
        resource.setCdate(new Date());// 创建时间
        resource.setMdate(new Date());// 修改时间
        sysResourceMapper.insert(resource);
    }

    /**
     * 生成Controller、Service、ServiceImpl
     *
     * @param data
     * @throws Exception
     * @throws IOException
     */
    private void generateControllerAndServiceAndImpl(Map<String, Object> data) throws IOException, Exception {
        // controller文件生成路径
        String controllerFilePath = data.get("filepath") + "/src/main/java/com/intflag/springboot/controller/app/"
                + data.get("classNameUppercase") + "Controller.java";
        TemplateHelp.creatTemplate(data, "Controller.ftl", controllerFilePath);
        // Service文件生成路径
        String serviceFilePath = data.get("filepath") + "/src/main/java/com/intflag/springboot/service/app/"
                + data.get("classNameUppercase") + "Service.java";
        // ServiceImpl文件生成路径
        String serviceImplFilePath = data.get("filepath") + "/src/main/java/com/intflag/springboot/service/app/impl/"
                + data.get("classNameUppercase") + "ServiceImpl.java";
        TemplateHelp.creatTemplate(data, "Service.ftl", serviceFilePath);
        TemplateHelp.creatTemplate(data, "ServiceImpl.ftl", serviceImplFilePath);
    }

    /**
     * 生成POJO实体、DAO接口、Mybatis映射文件
     *
     * @param data
     * @throws Exception
     * @throws IOException
     */
    private void generateEntityAndMapperAndXml(Map<String, Object> data) throws Exception {
        // 生成逆向工程配置文件
        String generatorConfigFilePath = data.get("filepath") + "/src/main/resources/generatorConfig.xml";
        TemplateHelp.creatTemplate(data, "GeneratorConfig.ftl", generatorConfigFilePath);
        // 删除已存在映射文件，放置重复生成配置
        String classNameUppercase = TenDirStringUtils.string2HumpAndUppercase(data.get("tablename").toString());
        String xmlFilePath = data.get("xmlFilePath").toString();
        File xmlFile = new File(xmlFilePath);
        if (!xmlFile.exists()) {
            xmlFile.mkdirs();
        }
        String path = xmlFilePath + "/mapper/app/" + classNameUppercase + "Mapper.xml";
        if (FileHandle.isExists(path)) {
            FileHandle.deleteFile(path);
        }
        // 生成POJO实体、DAO接口、Mybatis映射文件
        GeneratorSqlmapHelper.generator(generatorConfigFilePath);
    }

}
