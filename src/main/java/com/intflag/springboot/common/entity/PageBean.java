package com.intflag.springboot.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月5日12:34:28
 * @Description 分页实体类
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
public class PageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	

	private List list;
	private Integer currPage = 1;
	private Integer pageSize = 5;
	private Integer totalPage;
	private Long totalCount;
	private String keyWords;
	
	private Integer code;//状态
	private String msg;//提示信息
	
	public static PageBean ok(PageBean pageBean) {
		pageBean.setMsg("OK");
		pageBean.setCode(200);
		return pageBean;
	}
	public static PageBean error(PageBean pageBean) {
		pageBean.setMsg("ERROR");
		pageBean.setCode(500);
		return pageBean;
	}
	public static PageBean noAuthority(PageBean pageBean) {
		pageBean.setMsg("您无此功能权限");
		pageBean.setCode(500);
		return pageBean;
	}

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageBean(List list, Integer currPage, Integer pageSize, Long totalCount) {
		super();
		this.list = list;
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return (int) Math.ceil(totalCount * 1.0 / pageSize);
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "PageBean [list=" + list + ", currPage=" + currPage + ", pageSize=" + pageSize + ", totalPage="
				+ totalPage + ", totalCount=" + totalCount + "]";
	}

}
