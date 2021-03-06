package com.zuhu.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class Dao<T> {

    private final static int LIMIT = 10;

    private final static int OFFSET = 0;

    @Resource
    public JdbcTemplate jdbcTemplate;

    @Resource
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    public abstract T selectSingle(T obj);
    public abstract List<T> select(T obj, int limit, int offset);
    
   
    public int insert(String insertSql, Object[] params) {
        if (StringUtils.isBlank(insertSql)) return 0;
        if (ArrayUtils.isEmpty(params)) return 0;
        return jdbcTemplate.update(insertSql, params);
    }

    public int update(String updateSql, Object[] params) {
        if (StringUtils.isBlank(updateSql)) return 0;
        if (ArrayUtils.isEmpty(params)) return 0;
        return jdbcTemplate.update(updateSql, params);
    }

    public int count(String selectSql, Map<String, Object> params) {
        if (StringUtils.isBlank(selectSql)) return 0;
        if (params==null||params.size()==0) return 0;
        return namedParameterJdbcTemplate.queryForInt(selectSql,params);
    }

    public int countWithIn(String selectSql, Map<String, Object> params) {
        if (StringUtils.isBlank(selectSql)) return 0;
        if (params == null || params.size() == 0) return 0;
        return namedParameterJdbcTemplate.queryForInt(selectSql, params);
    }

    public void generatorLimitOffset(StringBuilder sbr, Map<String, Object> params, int limit, int offset) {
        if (limit == 0) {
            limit = LIMIT;
        }
        if (offset < 0) {
            offset = OFFSET;
        }
        sbr.append(" limit :limit offset :offset ;");
        params.put("limit", limit);
        params.put("offset",offset);
    }
}
