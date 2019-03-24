package com.intflag.springboot;

import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.admin.SysUser;
import com.intflag.springboot.entity.app.PmsBlogAppendix;
import com.intflag.springboot.entity.app.PmsGroup;
import com.intflag.springboot.service.app.PmsBlogAppendixService;
import com.intflag.springboot.service.app.PmsGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TenDirSpringBootXApplicationTests {

	@Autowired
	private PmsBlogAppendixService pmsBlogAppendixService;

	/**
	 * 模拟菜单递归检索
	 */
	@Test
	public void findAll() throws Exception {
        StatusResult result = pmsBlogAppendixService.findAll();
        System.out.println(result.getData());
    }

	@Test
	public void add() throws Exception {
        PmsBlogAppendix pmsBlogAppendix = new PmsBlogAppendix();
        pmsBlogAppendix.setAppendixId("1234");
        pmsBlogAppendix.setBlogId("5678");
        StatusResult add = pmsBlogAppendixService.add(pmsBlogAppendix);
        System.out.println(add.getData());
    }
	@Test
	public void delete() throws Exception {
        PmsBlogAppendix pmsBlogAppendix = new PmsBlogAppendix();
        pmsBlogAppendix.setAppendixId("1234");
        pmsBlogAppendix.setBlogId("5678");
        StatusResult add = pmsBlogAppendixService.delete(pmsBlogAppendix);
        System.out.println(add.getData());
    }

    @Autowired
    private PmsGroupService pmsGroupService;

	@Test
	public void findGroup() throws Exception {
	    String id = "2";
        Set<String> groupIds = new HashSet<>();
        Set<String> res = findByPid(groupIds, id);
        System.out.println(res);

    }
	public Set<String> findByPid(Set<String> groupIds, String pId) throws Exception {
        List<PmsGroup> pmsGroupList = pmsGroupService.findByPid(pId);
        if (pmsGroupList != null) {
            for (PmsGroup pmsGroup : pmsGroupList) {
                String id = pmsGroup.getGroupId();
                groupIds.add(id);
                groupIds = findByPid(groupIds,id);
            }
        }
        return groupIds;
    }

}
