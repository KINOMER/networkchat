package com.hsu.netchat.bean;

import java.util.ArrayList;
import java.util.List;

public class AnnoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnnoExample() {
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

        public Criteria andAnnoIsNull() {
            addCriterion("anno is null");
            return (Criteria) this;
        }

        public Criteria andAnnoIsNotNull() {
            addCriterion("anno is not null");
            return (Criteria) this;
        }

        public Criteria andAnnoEqualTo(String value) {
            addCriterion("anno =", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoNotEqualTo(String value) {
            addCriterion("anno <>", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoGreaterThan(String value) {
            addCriterion("anno >", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoGreaterThanOrEqualTo(String value) {
            addCriterion("anno >=", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoLessThan(String value) {
            addCriterion("anno <", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoLessThanOrEqualTo(String value) {
            addCriterion("anno <=", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoLike(String value) {
            addCriterion("anno like", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoNotLike(String value) {
            addCriterion("anno not like", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoIn(List<String> values) {
            addCriterion("anno in", values, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoNotIn(List<String> values) {
            addCriterion("anno not in", values, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoBetween(String value1, String value2) {
            addCriterion("anno between", value1, value2, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoNotBetween(String value1, String value2) {
            addCriterion("anno not between", value1, value2, "anno");
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