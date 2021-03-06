package com.intflag.springboot.entity.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PmsRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PmsRecordExample() {
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

        public Criteria andRecordIdIsNull() {
            addCriterion("record_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("record_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(String value) {
            addCriterion("record_id =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(String value) {
            addCriterion("record_id <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(String value) {
            addCriterion("record_id >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(String value) {
            addCriterion("record_id >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(String value) {
            addCriterion("record_id <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(String value) {
            addCriterion("record_id <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLike(String value) {
            addCriterion("record_id like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotLike(String value) {
            addCriterion("record_id not like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<String> values) {
            addCriterion("record_id in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<String> values) {
            addCriterion("record_id not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(String value1, String value2) {
            addCriterion("record_id between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(String value1, String value2) {
            addCriterion("record_id not between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNull() {
            addCriterion("plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(String value) {
            addCriterion("plan_id =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(String value) {
            addCriterion("plan_id <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(String value) {
            addCriterion("plan_id >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(String value) {
            addCriterion("plan_id >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(String value) {
            addCriterion("plan_id <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(String value) {
            addCriterion("plan_id <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLike(String value) {
            addCriterion("plan_id like", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotLike(String value) {
            addCriterion("plan_id not like", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<String> values) {
            addCriterion("plan_id in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<String> values) {
            addCriterion("plan_id not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(String value1, String value2) {
            addCriterion("plan_id between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(String value1, String value2) {
            addCriterion("plan_id not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIsNull() {
            addCriterion("plan_type is null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIsNotNull() {
            addCriterion("plan_type is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeEqualTo(String value) {
            addCriterion("plan_type =", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotEqualTo(String value) {
            addCriterion("plan_type <>", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeGreaterThan(String value) {
            addCriterion("plan_type >", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeGreaterThanOrEqualTo(String value) {
            addCriterion("plan_type >=", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLessThan(String value) {
            addCriterion("plan_type <", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLessThanOrEqualTo(String value) {
            addCriterion("plan_type <=", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLike(String value) {
            addCriterion("plan_type like", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotLike(String value) {
            addCriterion("plan_type not like", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIn(List<String> values) {
            addCriterion("plan_type in", values, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotIn(List<String> values) {
            addCriterion("plan_type not in", values, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeBetween(String value1, String value2) {
            addCriterion("plan_type between", value1, value2, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotBetween(String value1, String value2) {
            addCriterion("plan_type not between", value1, value2, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNull() {
            addCriterion("plan_name is null");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNotNull() {
            addCriterion("plan_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNameEqualTo(String value) {
            addCriterion("plan_name =", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotEqualTo(String value) {
            addCriterion("plan_name <>", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThan(String value) {
            addCriterion("plan_name >", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThanOrEqualTo(String value) {
            addCriterion("plan_name >=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThan(String value) {
            addCriterion("plan_name <", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThanOrEqualTo(String value) {
            addCriterion("plan_name <=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLike(String value) {
            addCriterion("plan_name like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotLike(String value) {
            addCriterion("plan_name not like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameIn(List<String> values) {
            addCriterion("plan_name in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotIn(List<String> values) {
            addCriterion("plan_name not in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameBetween(String value1, String value2) {
            addCriterion("plan_name between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotBetween(String value1, String value2) {
            addCriterion("plan_name not between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andTeachIdIsNull() {
            addCriterion("teach_id is null");
            return (Criteria) this;
        }

        public Criteria andTeachIdIsNotNull() {
            addCriterion("teach_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeachIdEqualTo(String value) {
            addCriterion("teach_id =", value, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdNotEqualTo(String value) {
            addCriterion("teach_id <>", value, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdGreaterThan(String value) {
            addCriterion("teach_id >", value, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdGreaterThanOrEqualTo(String value) {
            addCriterion("teach_id >=", value, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdLessThan(String value) {
            addCriterion("teach_id <", value, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdLessThanOrEqualTo(String value) {
            addCriterion("teach_id <=", value, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdLike(String value) {
            addCriterion("teach_id like", value, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdNotLike(String value) {
            addCriterion("teach_id not like", value, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdIn(List<String> values) {
            addCriterion("teach_id in", values, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdNotIn(List<String> values) {
            addCriterion("teach_id not in", values, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdBetween(String value1, String value2) {
            addCriterion("teach_id between", value1, value2, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachIdNotBetween(String value1, String value2) {
            addCriterion("teach_id not between", value1, value2, "teachId");
            return (Criteria) this;
        }

        public Criteria andTeachUserIsNull() {
            addCriterion("teach_user is null");
            return (Criteria) this;
        }

        public Criteria andTeachUserIsNotNull() {
            addCriterion("teach_user is not null");
            return (Criteria) this;
        }

        public Criteria andTeachUserEqualTo(String value) {
            addCriterion("teach_user =", value, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserNotEqualTo(String value) {
            addCriterion("teach_user <>", value, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserGreaterThan(String value) {
            addCriterion("teach_user >", value, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserGreaterThanOrEqualTo(String value) {
            addCriterion("teach_user >=", value, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserLessThan(String value) {
            addCriterion("teach_user <", value, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserLessThanOrEqualTo(String value) {
            addCriterion("teach_user <=", value, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserLike(String value) {
            addCriterion("teach_user like", value, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserNotLike(String value) {
            addCriterion("teach_user not like", value, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserIn(List<String> values) {
            addCriterion("teach_user in", values, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserNotIn(List<String> values) {
            addCriterion("teach_user not in", values, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserBetween(String value1, String value2) {
            addCriterion("teach_user between", value1, value2, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachUserNotBetween(String value1, String value2) {
            addCriterion("teach_user not between", value1, value2, "teachUser");
            return (Criteria) this;
        }

        public Criteria andTeachNickIsNull() {
            addCriterion("teach_nick is null");
            return (Criteria) this;
        }

        public Criteria andTeachNickIsNotNull() {
            addCriterion("teach_nick is not null");
            return (Criteria) this;
        }

        public Criteria andTeachNickEqualTo(String value) {
            addCriterion("teach_nick =", value, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickNotEqualTo(String value) {
            addCriterion("teach_nick <>", value, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickGreaterThan(String value) {
            addCriterion("teach_nick >", value, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickGreaterThanOrEqualTo(String value) {
            addCriterion("teach_nick >=", value, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickLessThan(String value) {
            addCriterion("teach_nick <", value, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickLessThanOrEqualTo(String value) {
            addCriterion("teach_nick <=", value, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickLike(String value) {
            addCriterion("teach_nick like", value, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickNotLike(String value) {
            addCriterion("teach_nick not like", value, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickIn(List<String> values) {
            addCriterion("teach_nick in", values, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickNotIn(List<String> values) {
            addCriterion("teach_nick not in", values, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickBetween(String value1, String value2) {
            addCriterion("teach_nick between", value1, value2, "teachNick");
            return (Criteria) this;
        }

        public Criteria andTeachNickNotBetween(String value1, String value2) {
            addCriterion("teach_nick not between", value1, value2, "teachNick");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNull() {
            addCriterion("paper_name is null");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNotNull() {
            addCriterion("paper_name is not null");
            return (Criteria) this;
        }

        public Criteria andPaperNameEqualTo(String value) {
            addCriterion("paper_name =", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotEqualTo(String value) {
            addCriterion("paper_name <>", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThan(String value) {
            addCriterion("paper_name >", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThanOrEqualTo(String value) {
            addCriterion("paper_name >=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThan(String value) {
            addCriterion("paper_name <", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThanOrEqualTo(String value) {
            addCriterion("paper_name <=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLike(String value) {
            addCriterion("paper_name like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotLike(String value) {
            addCriterion("paper_name not like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameIn(List<String> values) {
            addCriterion("paper_name in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotIn(List<String> values) {
            addCriterion("paper_name not in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameBetween(String value1, String value2) {
            addCriterion("paper_name between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotBetween(String value1, String value2) {
            addCriterion("paper_name not between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountIsNull() {
            addCriterion("paper_submit_count is null");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountIsNotNull() {
            addCriterion("paper_submit_count is not null");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountEqualTo(Integer value) {
            addCriterion("paper_submit_count =", value, "paperSubmitCount");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountNotEqualTo(Integer value) {
            addCriterion("paper_submit_count <>", value, "paperSubmitCount");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountGreaterThan(Integer value) {
            addCriterion("paper_submit_count >", value, "paperSubmitCount");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("paper_submit_count >=", value, "paperSubmitCount");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountLessThan(Integer value) {
            addCriterion("paper_submit_count <", value, "paperSubmitCount");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountLessThanOrEqualTo(Integer value) {
            addCriterion("paper_submit_count <=", value, "paperSubmitCount");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountIn(List<Integer> values) {
            addCriterion("paper_submit_count in", values, "paperSubmitCount");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountNotIn(List<Integer> values) {
            addCriterion("paper_submit_count not in", values, "paperSubmitCount");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountBetween(Integer value1, Integer value2) {
            addCriterion("paper_submit_count between", value1, value2, "paperSubmitCount");
            return (Criteria) this;
        }

        public Criteria andPaperSubmitCountNotBetween(Integer value1, Integer value2) {
            addCriterion("paper_submit_count not between", value1, value2, "paperSubmitCount");
            return (Criteria) this;
        }

        public Criteria andGuideCountIsNull() {
            addCriterion("guide_count is null");
            return (Criteria) this;
        }

        public Criteria andGuideCountIsNotNull() {
            addCriterion("guide_count is not null");
            return (Criteria) this;
        }

        public Criteria andGuideCountEqualTo(Integer value) {
            addCriterion("guide_count =", value, "guideCount");
            return (Criteria) this;
        }

        public Criteria andGuideCountNotEqualTo(Integer value) {
            addCriterion("guide_count <>", value, "guideCount");
            return (Criteria) this;
        }

        public Criteria andGuideCountGreaterThan(Integer value) {
            addCriterion("guide_count >", value, "guideCount");
            return (Criteria) this;
        }

        public Criteria andGuideCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("guide_count >=", value, "guideCount");
            return (Criteria) this;
        }

        public Criteria andGuideCountLessThan(Integer value) {
            addCriterion("guide_count <", value, "guideCount");
            return (Criteria) this;
        }

        public Criteria andGuideCountLessThanOrEqualTo(Integer value) {
            addCriterion("guide_count <=", value, "guideCount");
            return (Criteria) this;
        }

        public Criteria andGuideCountIn(List<Integer> values) {
            addCriterion("guide_count in", values, "guideCount");
            return (Criteria) this;
        }

        public Criteria andGuideCountNotIn(List<Integer> values) {
            addCriterion("guide_count not in", values, "guideCount");
            return (Criteria) this;
        }

        public Criteria andGuideCountBetween(Integer value1, Integer value2) {
            addCriterion("guide_count between", value1, value2, "guideCount");
            return (Criteria) this;
        }

        public Criteria andGuideCountNotBetween(Integer value1, Integer value2) {
            addCriterion("guide_count not between", value1, value2, "guideCount");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNull() {
            addCriterion("release_date is null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNotNull() {
            addCriterion("release_date is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateEqualTo(Date value) {
            addCriterion("release_date =", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotEqualTo(Date value) {
            addCriterion("release_date <>", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThan(Date value) {
            addCriterion("release_date >", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("release_date >=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThan(Date value) {
            addCriterion("release_date <", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThanOrEqualTo(Date value) {
            addCriterion("release_date <=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIn(List<Date> values) {
            addCriterion("release_date in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotIn(List<Date> values) {
            addCriterion("release_date not in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateBetween(Date value1, Date value2) {
            addCriterion("release_date between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotBetween(Date value1, Date value2) {
            addCriterion("release_date not between", value1, value2, "releaseDate");
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
            addCriterion("cDate is null");
            return (Criteria) this;
        }

        public Criteria andCdateIsNotNull() {
            addCriterion("cDate is not null");
            return (Criteria) this;
        }

        public Criteria andCdateEqualTo(Date value) {
            addCriterion("cDate =", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateNotEqualTo(Date value) {
            addCriterion("cDate <>", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateGreaterThan(Date value) {
            addCriterion("cDate >", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateGreaterThanOrEqualTo(Date value) {
            addCriterion("cDate >=", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateLessThan(Date value) {
            addCriterion("cDate <", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateLessThanOrEqualTo(Date value) {
            addCriterion("cDate <=", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateIn(List<Date> values) {
            addCriterion("cDate in", values, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateNotIn(List<Date> values) {
            addCriterion("cDate not in", values, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateBetween(Date value1, Date value2) {
            addCriterion("cDate between", value1, value2, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateNotBetween(Date value1, Date value2) {
            addCriterion("cDate not between", value1, value2, "cdate");
            return (Criteria) this;
        }

        public Criteria andMdateIsNull() {
            addCriterion("mDate is null");
            return (Criteria) this;
        }

        public Criteria andMdateIsNotNull() {
            addCriterion("mDate is not null");
            return (Criteria) this;
        }

        public Criteria andMdateEqualTo(Date value) {
            addCriterion("mDate =", value, "mdate");
            return (Criteria) this;
        }

        public Criteria andMdateNotEqualTo(Date value) {
            addCriterion("mDate <>", value, "mdate");
            return (Criteria) this;
        }

        public Criteria andMdateGreaterThan(Date value) {
            addCriterion("mDate >", value, "mdate");
            return (Criteria) this;
        }

        public Criteria andMdateGreaterThanOrEqualTo(Date value) {
            addCriterion("mDate >=", value, "mdate");
            return (Criteria) this;
        }

        public Criteria andMdateLessThan(Date value) {
            addCriterion("mDate <", value, "mdate");
            return (Criteria) this;
        }

        public Criteria andMdateLessThanOrEqualTo(Date value) {
            addCriterion("mDate <=", value, "mdate");
            return (Criteria) this;
        }

        public Criteria andMdateIn(List<Date> values) {
            addCriterion("mDate in", values, "mdate");
            return (Criteria) this;
        }

        public Criteria andMdateNotIn(List<Date> values) {
            addCriterion("mDate not in", values, "mdate");
            return (Criteria) this;
        }

        public Criteria andMdateBetween(Date value1, Date value2) {
            addCriterion("mDate between", value1, value2, "mdate");
            return (Criteria) this;
        }

        public Criteria andMdateNotBetween(Date value1, Date value2) {
            addCriterion("mDate not between", value1, value2, "mdate");
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

        public Criteria andField01IsNull() {
            addCriterion("field01 is null");
            return (Criteria) this;
        }

        public Criteria andField01IsNotNull() {
            addCriterion("field01 is not null");
            return (Criteria) this;
        }

        public Criteria andField01EqualTo(String value) {
            addCriterion("field01 =", value, "field01");
            return (Criteria) this;
        }

        public Criteria andField01NotEqualTo(String value) {
            addCriterion("field01 <>", value, "field01");
            return (Criteria) this;
        }

        public Criteria andField01GreaterThan(String value) {
            addCriterion("field01 >", value, "field01");
            return (Criteria) this;
        }

        public Criteria andField01GreaterThanOrEqualTo(String value) {
            addCriterion("field01 >=", value, "field01");
            return (Criteria) this;
        }

        public Criteria andField01LessThan(String value) {
            addCriterion("field01 <", value, "field01");
            return (Criteria) this;
        }

        public Criteria andField01LessThanOrEqualTo(String value) {
            addCriterion("field01 <=", value, "field01");
            return (Criteria) this;
        }

        public Criteria andField01Like(String value) {
            addCriterion("field01 like", value, "field01");
            return (Criteria) this;
        }

        public Criteria andField01NotLike(String value) {
            addCriterion("field01 not like", value, "field01");
            return (Criteria) this;
        }

        public Criteria andField01In(List<String> values) {
            addCriterion("field01 in", values, "field01");
            return (Criteria) this;
        }

        public Criteria andField01NotIn(List<String> values) {
            addCriterion("field01 not in", values, "field01");
            return (Criteria) this;
        }

        public Criteria andField01Between(String value1, String value2) {
            addCriterion("field01 between", value1, value2, "field01");
            return (Criteria) this;
        }

        public Criteria andField01NotBetween(String value1, String value2) {
            addCriterion("field01 not between", value1, value2, "field01");
            return (Criteria) this;
        }

        public Criteria andField02IsNull() {
            addCriterion("field02 is null");
            return (Criteria) this;
        }

        public Criteria andField02IsNotNull() {
            addCriterion("field02 is not null");
            return (Criteria) this;
        }

        public Criteria andField02EqualTo(String value) {
            addCriterion("field02 =", value, "field02");
            return (Criteria) this;
        }

        public Criteria andField02NotEqualTo(String value) {
            addCriterion("field02 <>", value, "field02");
            return (Criteria) this;
        }

        public Criteria andField02GreaterThan(String value) {
            addCriterion("field02 >", value, "field02");
            return (Criteria) this;
        }

        public Criteria andField02GreaterThanOrEqualTo(String value) {
            addCriterion("field02 >=", value, "field02");
            return (Criteria) this;
        }

        public Criteria andField02LessThan(String value) {
            addCriterion("field02 <", value, "field02");
            return (Criteria) this;
        }

        public Criteria andField02LessThanOrEqualTo(String value) {
            addCriterion("field02 <=", value, "field02");
            return (Criteria) this;
        }

        public Criteria andField02Like(String value) {
            addCriterion("field02 like", value, "field02");
            return (Criteria) this;
        }

        public Criteria andField02NotLike(String value) {
            addCriterion("field02 not like", value, "field02");
            return (Criteria) this;
        }

        public Criteria andField02In(List<String> values) {
            addCriterion("field02 in", values, "field02");
            return (Criteria) this;
        }

        public Criteria andField02NotIn(List<String> values) {
            addCriterion("field02 not in", values, "field02");
            return (Criteria) this;
        }

        public Criteria andField02Between(String value1, String value2) {
            addCriterion("field02 between", value1, value2, "field02");
            return (Criteria) this;
        }

        public Criteria andField02NotBetween(String value1, String value2) {
            addCriterion("field02 not between", value1, value2, "field02");
            return (Criteria) this;
        }

        public Criteria andField03IsNull() {
            addCriterion("field03 is null");
            return (Criteria) this;
        }

        public Criteria andField03IsNotNull() {
            addCriterion("field03 is not null");
            return (Criteria) this;
        }

        public Criteria andField03EqualTo(String value) {
            addCriterion("field03 =", value, "field03");
            return (Criteria) this;
        }

        public Criteria andField03NotEqualTo(String value) {
            addCriterion("field03 <>", value, "field03");
            return (Criteria) this;
        }

        public Criteria andField03GreaterThan(String value) {
            addCriterion("field03 >", value, "field03");
            return (Criteria) this;
        }

        public Criteria andField03GreaterThanOrEqualTo(String value) {
            addCriterion("field03 >=", value, "field03");
            return (Criteria) this;
        }

        public Criteria andField03LessThan(String value) {
            addCriterion("field03 <", value, "field03");
            return (Criteria) this;
        }

        public Criteria andField03LessThanOrEqualTo(String value) {
            addCriterion("field03 <=", value, "field03");
            return (Criteria) this;
        }

        public Criteria andField03Like(String value) {
            addCriterion("field03 like", value, "field03");
            return (Criteria) this;
        }

        public Criteria andField03NotLike(String value) {
            addCriterion("field03 not like", value, "field03");
            return (Criteria) this;
        }

        public Criteria andField03In(List<String> values) {
            addCriterion("field03 in", values, "field03");
            return (Criteria) this;
        }

        public Criteria andField03NotIn(List<String> values) {
            addCriterion("field03 not in", values, "field03");
            return (Criteria) this;
        }

        public Criteria andField03Between(String value1, String value2) {
            addCriterion("field03 between", value1, value2, "field03");
            return (Criteria) this;
        }

        public Criteria andField03NotBetween(String value1, String value2) {
            addCriterion("field03 not between", value1, value2, "field03");
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