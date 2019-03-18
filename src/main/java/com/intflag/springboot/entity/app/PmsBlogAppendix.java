package com.intflag.springboot.entity.app;

/**
 * @author liugx  QQ:1598749808
 * @version V1.0
 * @date 2019-03-13 21:13
 * @Description 博客附件关联表
 */
public class PmsBlogAppendix {
    private String blogId;
    private String appendixId;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getAppendixId() {
        return appendixId;
    }

    public void setAppendixId(String appendixId) {
        this.appendixId = appendixId;
    }

    @Override
    public String toString() {
        return "PmsBlogAppendix{" +
                "blogId='" + blogId + '\'' +
                ", appendixId='" + appendixId + '\'' +
                '}';
    }
}
