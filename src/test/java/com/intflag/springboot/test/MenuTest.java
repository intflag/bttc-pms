package com.intflag.springboot.test;
/** 
* @author 刘国鑫  QQ:1598749808
* @date 2018年8月27日 下午3:18:06
* @Description 
* @version V1.0
*/

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MenuTest {

	@Test
	public void fun1() throws Exception {
		// 父级菜单1
		TenDirMenu p1 = new TenDirMenu("p1", "父1", null);
		TenDirMenu c1 = new TenDirMenu("c1", "子1", "p1");
		TenDirMenu c2 = new TenDirMenu("c2", "子2", "p1");

		TenDirMenu s1 = new TenDirMenu("s1", "孙1", "c1");
		TenDirMenu s2 = new TenDirMenu("s2", "孙2", "c1");

		TenDirMenu p2 = new TenDirMenu("p2", "父2", null);
		TenDirMenu c3 = new TenDirMenu("c3", "子3", "p2");
		TenDirMenu c4 = new TenDirMenu("c4", "子4", "p2");

		TenDirMenu s3 = new TenDirMenu("s3", "孙3", "c4");
		TenDirMenu s4 = new TenDirMenu("s4", "孙4", "c4");
		
		TenDirMenu z1 = new TenDirMenu("z1", "曾孙1", "s4");

		List<TenDirMenu> list = new ArrayList<>();
		list.add(p1);
		list.add(c1);
		list.add(c2);
		list.add(s1);
		list.add(s2);
		list.add(p2);
		list.add(c3);
		list.add(c4);
		list.add(s3);
		list.add(s4);
		list.add(z1);
		// 递归
		List<TenDirMenu> menu = new ArrayList<>();
		for (TenDirMenu tenDirMenu : list) {
			if (tenDirMenu.getpId() == null) {
				List<TenDirMenu> children = findChildren(tenDirMenu.getId(), list);
				tenDirMenu.setChildren(children);
				menu.add(tenDirMenu);
			}
		}
		System.out.println(menu);
	}

	private List<TenDirMenu> findChildren(String id, List<TenDirMenu> list) {
		List<TenDirMenu> children = new ArrayList<>();
		for (TenDirMenu tenDirMenu : list) {
			if (tenDirMenu.getpId() != null && tenDirMenu.getpId().equals(id)) {
				tenDirMenu.setChildren(findChildren(tenDirMenu.getId(), list));
				children.add(tenDirMenu);
			}
		}
		return children;
	}
}

/**
 * 模拟菜单类
 * 
 * @author Administrator
 *
 */
class TenDirMenu {
	private String id;
	private String name;
	private String pId;
	private List<TenDirMenu> children = new ArrayList<>();

	public TenDirMenu(String id, String name, String pId) {
		super();
		this.id = id;
		this.name = name;
		this.pId = pId;
	}

	public TenDirMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public List<TenDirMenu> getChildren() {
		return children;
	}

	public void setChildren(List<TenDirMenu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TenDirMenu [id=" + id + ", name=" + name + ", pId=" + pId + "]";
	}

	
}