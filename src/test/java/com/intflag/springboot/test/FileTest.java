package com.intflag.springboot.test;
/**
 * @author 刘国鑫  QQ:1598749808
 * @date 2018年8月28日 下午6:46:27
 * @Description
 * @version V1.0
 */

import com.intflag.springboot.common.util.FastDFSClient;
import com.intflag.springboot.common.util.TenDirFileUtils;
import org.junit.Test;

import com.intflag.springboot.common.util.FileHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileTest {

    @Test
    public void testName() throws Exception {
        boolean b = FileHandle.isExists("D:/test/template/generatorConfig.xml");
        System.out.println(b);
    }

    @Test
    public void testName2() throws Exception {
        boolean b = FileHandle.deleteFile("D:/test/template/generatorConfig.xml");
        System.out.println(b);
    }

    @Test
    public void fileUpload() throws Exception {
        // 将图片上传到图片服务器
        FastDFSClient fastDFSClient = new FastDFSClient("classpath:client.conf");
        // 返回图片文件名
//        String path = "D:/test/001.jpg";
        String path = "D:/test/2018年需求291需求上线实施方案.docx";
        File file = new File(path);
        String originalFilename = file.getName();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String url = fastDFSClient.uploadFile(path, extName);
        System.out.println(url);
    }

    @Test
    public void fun1() {
        String path1 = "D:\\100 - Work - 工作\\100 - Work - 工作资料库\\115 - 亚信\\2019年需求033 - BBOSS流量统付关停客户手动生成告警\\2019年需求033需求上线实施方案.docx";
        String path = "2019年需求033需求上线实施方案.docx";
        int i = path.lastIndexOf("\\");
        String s = path.substring(i + 1);
        System.out.println(s);
    }

    @Test
    public void fun2() {
        String s = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒"));
        System.out.println(s);
    }

    @Test
    public void fun3() {
        String url = "http://192.168.25.133/group1/M00/00/01/wKgZhVyxqFaACbwEAAF2Or1YZ7k04.docx";
        File file = TenDirFileUtils.getFileByUrl(url, "GET");
        System.out.println(file.getName());
    }

}
