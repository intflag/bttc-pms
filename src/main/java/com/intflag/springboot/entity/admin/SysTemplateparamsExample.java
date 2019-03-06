package com.intflag.springboot.entity.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysTemplateparamsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysTemplateparamsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(String value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(String value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(String value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(String value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLike(String value) {
            addCriterion("template_id like", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotLike(String value) {
            addCriterion("template_id not like", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<String> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<String> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(String value1, String value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(String value1, String value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTablenameIsNull() {
            addCriterion("tableName is null");
            return (Criteria) this;
        }

        public Criteria andTablenameIsNotNull() {
            addCriterion("tableName is not null");
            return (Criteria) this;
        }

        public Criteria andTablenameEqualTo(String value) {
            addCriterion("tableName =", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotEqualTo(String value) {
            addCriterion("tableName <>", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameGreaterThan(String value) {
            addCriterion("tableName >", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameGreaterThanOrEqualTo(String value) {
            addCriterion("tableName >=", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLessThan(String value) {
            addCriterion("tableName <", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLessThanOrEqualTo(String value) {
            addCriterion("tableName <=", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLike(String value) {
            addCriterion("tableName like", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotLike(String value) {
            addCriterion("tableName not like", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameIn(List<String> values) {
            addCriterion("tableName in", values, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotIn(List<String> values) {
            addCriterion("tableName not in", values, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameBetween(String value1, String value2) {
            addCriterion("tableName between", value1, value2, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotBetween(String value1, String value2) {
            addCriterion("tableName not between", value1, value2, "tablename");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentIsNull() {
            addCriterion("functionComment is null");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentIsNotNull() {
            addCriterion("functionComment is not null");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentEqualTo(String value) {
            addCriterion("functionComment =", value, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentNotEqualTo(String value) {
            addCriterion("functionComment <>", value, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentGreaterThan(String value) {
            addCriterion("functionComment >", value, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentGreaterThanOrEqualTo(String value) {
            addCriterion("functionComment >=", value, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentLessThan(String value) {
            addCriterion("functionComment <", value, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentLessThanOrEqualTo(String value) {
            addCriterion("functionComment <=", value, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentLike(String value) {
            addCriterion("functionComment like", value, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentNotLike(String value) {
            addCriterion("functionComment not like", value, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentIn(List<String> values) {
            addCriterion("functionComment in", values, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentNotIn(List<String> values) {
            addCriterion("functionComment not in", values, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentBetween(String value1, String value2) {
            addCriterion("functionComment between", value1, value2, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andFunctioncommentNotBetween(String value1, String value2) {
            addCriterion("functionComment not between", value1, value2, "functioncomment");
            return (Criteria) this;
        }

        public Criteria andClassnamesIsNull() {
            addCriterion("classNames is null");
            return (Criteria) this;
        }

        public Criteria andClassnamesIsNotNull() {
            addCriterion("classNames is not null");
            return (Criteria) this;
        }

        public Criteria andClassnamesEqualTo(String value) {
            addCriterion("classNames =", value, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesNotEqualTo(String value) {
            addCriterion("classNames <>", value, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesGreaterThan(String value) {
            addCriterion("classNames >", value, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesGreaterThanOrEqualTo(String value) {
            addCriterion("classNames >=", value, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesLessThan(String value) {
            addCriterion("classNames <", value, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesLessThanOrEqualTo(String value) {
            addCriterion("classNames <=", value, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesLike(String value) {
            addCriterion("classNames like", value, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesNotLike(String value) {
            addCriterion("classNames not like", value, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesIn(List<String> values) {
            addCriterion("classNames in", values, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesNotIn(List<String> values) {
            addCriterion("classNames not in", values, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesBetween(String value1, String value2) {
            addCriterion("classNames between", value1, value2, "classnames");
            return (Criteria) this;
        }

        public Criteria andClassnamesNotBetween(String value1, String value2) {
            addCriterion("classNames not between", value1, value2, "classnames");
            return (Criteria) this;
        }

        public Criteria andClasspathIsNull() {
            addCriterion("classPath is null");
            return (Criteria) this;
        }

        public Criteria andClasspathIsNotNull() {
            addCriterion("classPath is not null");
            return (Criteria) this;
        }

        public Criteria andClasspathEqualTo(String value) {
            addCriterion("classPath =", value, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathNotEqualTo(String value) {
            addCriterion("classPath <>", value, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathGreaterThan(String value) {
            addCriterion("classPath >", value, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathGreaterThanOrEqualTo(String value) {
            addCriterion("classPath >=", value, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathLessThan(String value) {
            addCriterion("classPath <", value, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathLessThanOrEqualTo(String value) {
            addCriterion("classPath <=", value, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathLike(String value) {
            addCriterion("classPath like", value, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathNotLike(String value) {
            addCriterion("classPath not like", value, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathIn(List<String> values) {
            addCriterion("classPath in", values, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathNotIn(List<String> values) {
            addCriterion("classPath not in", values, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathBetween(String value1, String value2) {
            addCriterion("classPath between", value1, value2, "classpath");
            return (Criteria) this;
        }

        public Criteria andClasspathNotBetween(String value1, String value2) {
            addCriterion("classPath not between", value1, value2, "classpath");
            return (Criteria) this;
        }

        public Criteria andFilepathIsNull() {
            addCriterion("filePath is null");
            return (Criteria) this;
        }

        public Criteria andFilepathIsNotNull() {
            addCriterion("filePath is not null");
            return (Criteria) this;
        }

        public Criteria andFilepathEqualTo(String value) {
            addCriterion("filePath =", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotEqualTo(String value) {
            addCriterion("filePath <>", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathGreaterThan(String value) {
            addCriterion("filePath >", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathGreaterThanOrEqualTo(String value) {
            addCriterion("filePath >=", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLessThan(String value) {
            addCriterion("filePath <", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLessThanOrEqualTo(String value) {
            addCriterion("filePath <=", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLike(String value) {
            addCriterion("filePath like", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotLike(String value) {
            addCriterion("filePath not like", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathIn(List<String> values) {
            addCriterion("filePath in", values, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotIn(List<String> values) {
            addCriterion("filePath not in", values, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathBetween(String value1, String value2) {
            addCriterion("filePath between", value1, value2, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotBetween(String value1, String value2) {
            addCriterion("filePath not between", value1, value2, "filepath");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnIsNull() {
            addCriterion("keyWordColumn is null");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnIsNotNull() {
            addCriterion("keyWordColumn is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnEqualTo(String value) {
            addCriterion("keyWordColumn =", value, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnNotEqualTo(String value) {
            addCriterion("keyWordColumn <>", value, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnGreaterThan(String value) {
            addCriterion("keyWordColumn >", value, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnGreaterThanOrEqualTo(String value) {
            addCriterion("keyWordColumn >=", value, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnLessThan(String value) {
            addCriterion("keyWordColumn <", value, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnLessThanOrEqualTo(String value) {
            addCriterion("keyWordColumn <=", value, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnLike(String value) {
            addCriterion("keyWordColumn like", value, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnNotLike(String value) {
            addCriterion("keyWordColumn not like", value, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnIn(List<String> values) {
            addCriterion("keyWordColumn in", values, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnNotIn(List<String> values) {
            addCriterion("keyWordColumn not in", values, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnBetween(String value1, String value2) {
            addCriterion("keyWordColumn between", value1, value2, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andKeywordcolumnNotBetween(String value1, String value2) {
            addCriterion("keyWordColumn not between", value1, value2, "keywordcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnIsNull() {
            addCriterion("pkColumn is null");
            return (Criteria) this;
        }

        public Criteria andPkcolumnIsNotNull() {
            addCriterion("pkColumn is not null");
            return (Criteria) this;
        }

        public Criteria andPkcolumnEqualTo(String value) {
            addCriterion("pkColumn =", value, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnNotEqualTo(String value) {
            addCriterion("pkColumn <>", value, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnGreaterThan(String value) {
            addCriterion("pkColumn >", value, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnGreaterThanOrEqualTo(String value) {
            addCriterion("pkColumn >=", value, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnLessThan(String value) {
            addCriterion("pkColumn <", value, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnLessThanOrEqualTo(String value) {
            addCriterion("pkColumn <=", value, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnLike(String value) {
            addCriterion("pkColumn like", value, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnNotLike(String value) {
            addCriterion("pkColumn not like", value, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnIn(List<String> values) {
            addCriterion("pkColumn in", values, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnNotIn(List<String> values) {
            addCriterion("pkColumn not in", values, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnBetween(String value1, String value2) {
            addCriterion("pkColumn between", value1, value2, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andPkcolumnNotBetween(String value1, String value2) {
            addCriterion("pkColumn not between", value1, value2, "pkcolumn");
            return (Criteria) this;
        }

        public Criteria andActionurlIsNull() {
            addCriterion("actionUrl is null");
            return (Criteria) this;
        }

        public Criteria andActionurlIsNotNull() {
            addCriterion("actionUrl is not null");
            return (Criteria) this;
        }

        public Criteria andActionurlEqualTo(String value) {
            addCriterion("actionUrl =", value, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlNotEqualTo(String value) {
            addCriterion("actionUrl <>", value, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlGreaterThan(String value) {
            addCriterion("actionUrl >", value, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlGreaterThanOrEqualTo(String value) {
            addCriterion("actionUrl >=", value, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlLessThan(String value) {
            addCriterion("actionUrl <", value, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlLessThanOrEqualTo(String value) {
            addCriterion("actionUrl <=", value, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlLike(String value) {
            addCriterion("actionUrl like", value, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlNotLike(String value) {
            addCriterion("actionUrl not like", value, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlIn(List<String> values) {
            addCriterion("actionUrl in", values, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlNotIn(List<String> values) {
            addCriterion("actionUrl not in", values, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlBetween(String value1, String value2) {
            addCriterion("actionUrl between", value1, value2, "actionurl");
            return (Criteria) this;
        }

        public Criteria andActionurlNotBetween(String value1, String value2) {
            addCriterion("actionUrl not between", value1, value2, "actionurl");
            return (Criteria) this;
        }

        public Criteria andAddfunIsNull() {
            addCriterion("addFun is null");
            return (Criteria) this;
        }

        public Criteria andAddfunIsNotNull() {
            addCriterion("addFun is not null");
            return (Criteria) this;
        }

        public Criteria andAddfunEqualTo(String value) {
            addCriterion("addFun =", value, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunNotEqualTo(String value) {
            addCriterion("addFun <>", value, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunGreaterThan(String value) {
            addCriterion("addFun >", value, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunGreaterThanOrEqualTo(String value) {
            addCriterion("addFun >=", value, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunLessThan(String value) {
            addCriterion("addFun <", value, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunLessThanOrEqualTo(String value) {
            addCriterion("addFun <=", value, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunLike(String value) {
            addCriterion("addFun like", value, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunNotLike(String value) {
            addCriterion("addFun not like", value, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunIn(List<String> values) {
            addCriterion("addFun in", values, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunNotIn(List<String> values) {
            addCriterion("addFun not in", values, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunBetween(String value1, String value2) {
            addCriterion("addFun between", value1, value2, "addfun");
            return (Criteria) this;
        }

        public Criteria andAddfunNotBetween(String value1, String value2) {
            addCriterion("addFun not between", value1, value2, "addfun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunIsNull() {
            addCriterion("updateFun is null");
            return (Criteria) this;
        }

        public Criteria andUpdatefunIsNotNull() {
            addCriterion("updateFun is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatefunEqualTo(String value) {
            addCriterion("updateFun =", value, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunNotEqualTo(String value) {
            addCriterion("updateFun <>", value, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunGreaterThan(String value) {
            addCriterion("updateFun >", value, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunGreaterThanOrEqualTo(String value) {
            addCriterion("updateFun >=", value, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunLessThan(String value) {
            addCriterion("updateFun <", value, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunLessThanOrEqualTo(String value) {
            addCriterion("updateFun <=", value, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunLike(String value) {
            addCriterion("updateFun like", value, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunNotLike(String value) {
            addCriterion("updateFun not like", value, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunIn(List<String> values) {
            addCriterion("updateFun in", values, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunNotIn(List<String> values) {
            addCriterion("updateFun not in", values, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunBetween(String value1, String value2) {
            addCriterion("updateFun between", value1, value2, "updatefun");
            return (Criteria) this;
        }

        public Criteria andUpdatefunNotBetween(String value1, String value2) {
            addCriterion("updateFun not between", value1, value2, "updatefun");
            return (Criteria) this;
        }

        public Criteria andSelectfunIsNull() {
            addCriterion("selectFun is null");
            return (Criteria) this;
        }

        public Criteria andSelectfunIsNotNull() {
            addCriterion("selectFun is not null");
            return (Criteria) this;
        }

        public Criteria andSelectfunEqualTo(String value) {
            addCriterion("selectFun =", value, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunNotEqualTo(String value) {
            addCriterion("selectFun <>", value, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunGreaterThan(String value) {
            addCriterion("selectFun >", value, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunGreaterThanOrEqualTo(String value) {
            addCriterion("selectFun >=", value, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunLessThan(String value) {
            addCriterion("selectFun <", value, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunLessThanOrEqualTo(String value) {
            addCriterion("selectFun <=", value, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunLike(String value) {
            addCriterion("selectFun like", value, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunNotLike(String value) {
            addCriterion("selectFun not like", value, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunIn(List<String> values) {
            addCriterion("selectFun in", values, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunNotIn(List<String> values) {
            addCriterion("selectFun not in", values, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunBetween(String value1, String value2) {
            addCriterion("selectFun between", value1, value2, "selectfun");
            return (Criteria) this;
        }

        public Criteria andSelectfunNotBetween(String value1, String value2) {
            addCriterion("selectFun not between", value1, value2, "selectfun");
            return (Criteria) this;
        }

        public Criteria andDeletefunIsNull() {
            addCriterion("deleteFun is null");
            return (Criteria) this;
        }

        public Criteria andDeletefunIsNotNull() {
            addCriterion("deleteFun is not null");
            return (Criteria) this;
        }

        public Criteria andDeletefunEqualTo(String value) {
            addCriterion("deleteFun =", value, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunNotEqualTo(String value) {
            addCriterion("deleteFun <>", value, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunGreaterThan(String value) {
            addCriterion("deleteFun >", value, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunGreaterThanOrEqualTo(String value) {
            addCriterion("deleteFun >=", value, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunLessThan(String value) {
            addCriterion("deleteFun <", value, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunLessThanOrEqualTo(String value) {
            addCriterion("deleteFun <=", value, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunLike(String value) {
            addCriterion("deleteFun like", value, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunNotLike(String value) {
            addCriterion("deleteFun not like", value, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunIn(List<String> values) {
            addCriterion("deleteFun in", values, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunNotIn(List<String> values) {
            addCriterion("deleteFun not in", values, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunBetween(String value1, String value2) {
            addCriterion("deleteFun between", value1, value2, "deletefun");
            return (Criteria) this;
        }

        public Criteria andDeletefunNotBetween(String value1, String value2) {
            addCriterion("deleteFun not between", value1, value2, "deletefun");
            return (Criteria) this;
        }

        public Criteria andAddmenuIsNull() {
            addCriterion("addmenu is null");
            return (Criteria) this;
        }

        public Criteria andAddmenuIsNotNull() {
            addCriterion("addmenu is not null");
            return (Criteria) this;
        }

        public Criteria andAddmenuEqualTo(String value) {
            addCriterion("addmenu =", value, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuNotEqualTo(String value) {
            addCriterion("addmenu <>", value, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuGreaterThan(String value) {
            addCriterion("addmenu >", value, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuGreaterThanOrEqualTo(String value) {
            addCriterion("addmenu >=", value, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuLessThan(String value) {
            addCriterion("addmenu <", value, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuLessThanOrEqualTo(String value) {
            addCriterion("addmenu <=", value, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuLike(String value) {
            addCriterion("addmenu like", value, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuNotLike(String value) {
            addCriterion("addmenu not like", value, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuIn(List<String> values) {
            addCriterion("addmenu in", values, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuNotIn(List<String> values) {
            addCriterion("addmenu not in", values, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuBetween(String value1, String value2) {
            addCriterion("addmenu between", value1, value2, "addmenu");
            return (Criteria) this;
        }

        public Criteria andAddmenuNotBetween(String value1, String value2) {
            addCriterion("addmenu not between", value1, value2, "addmenu");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(String value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(String value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(String value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(String value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(String value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLike(String value) {
            addCriterion("pid like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotLike(String value) {
            addCriterion("pid not like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<String> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<String> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(String value1, String value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(String value1, String value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andPatternIsNull() {
            addCriterion("pattern is null");
            return (Criteria) this;
        }

        public Criteria andPatternIsNotNull() {
            addCriterion("pattern is not null");
            return (Criteria) this;
        }

        public Criteria andPatternEqualTo(String value) {
            addCriterion("pattern =", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotEqualTo(String value) {
            addCriterion("pattern <>", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternGreaterThan(String value) {
            addCriterion("pattern >", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternGreaterThanOrEqualTo(String value) {
            addCriterion("pattern >=", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLessThan(String value) {
            addCriterion("pattern <", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLessThanOrEqualTo(String value) {
            addCriterion("pattern <=", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLike(String value) {
            addCriterion("pattern like", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotLike(String value) {
            addCriterion("pattern not like", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternIn(List<String> values) {
            addCriterion("pattern in", values, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotIn(List<String> values) {
            addCriterion("pattern not in", values, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternBetween(String value1, String value2) {
            addCriterion("pattern between", value1, value2, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotBetween(String value1, String value2) {
            addCriterion("pattern not between", value1, value2, "pattern");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("flag like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("flag not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andCdateIsNull() {
            addCriterion("cdate is null");
            return (Criteria) this;
        }

        public Criteria andCdateIsNotNull() {
            addCriterion("cdate is not null");
            return (Criteria) this;
        }

        public Criteria andCdateEqualTo(Date value) {
            addCriterion("cdate =", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateNotEqualTo(Date value) {
            addCriterion("cdate <>", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateGreaterThan(Date value) {
            addCriterion("cdate >", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateGreaterThanOrEqualTo(Date value) {
            addCriterion("cdate >=", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateLessThan(Date value) {
            addCriterion("cdate <", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateLessThanOrEqualTo(Date value) {
            addCriterion("cdate <=", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateIn(List<Date> values) {
            addCriterion("cdate in", values, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateNotIn(List<Date> values) {
            addCriterion("cdate not in", values, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateBetween(Date value1, Date value2) {
            addCriterion("cdate between", value1, value2, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateNotBetween(Date value1, Date value2) {
            addCriterion("cdate not between", value1, value2, "cdate");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}