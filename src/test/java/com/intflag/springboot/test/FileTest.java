package com.intflag.springboot.test;
/**
* @author 刘国鑫  QQ:1598749808
* @date 2018年8月28日 下午6:46:27
* @Description
* @version V1.0
*/

import com.intflag.springboot.common.util.FastDFSClient;
import org.junit.Test;

import com.intflag.springboot.common.util.FileHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.io.File;

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

}
