package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.User;
import com.zuhu.system.util.DateUtil;

@Service
public class UserDao extends Dao<User> {

    private final static String SELECT_COMMON = " SELECT id, code, name, password, created, type FROM user ";

    private final static String COUNT = " SELECT count(*) FROM user ";

    private final static String INSERT = " INSERT INTO USER ( CODE, NAME, PASSWORD, created, TYPE, STATUS)  VALUES  (?, ?, ?, NOW(), ?, ?); ";

    private final static String UPDATE = "";

    @Override
    public User selectSingle(User user) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(user, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<User>(User.class));
    }
    @Override
    public List<User> select(User user, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(user, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<User>(User.class));
    }

    public int count(User user) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(user, sbr, params);
        return super.count(sbr.toString(), params.toArray());
    }

    public int insert(User user) {
        return super.insert(INSERT, objectConvertArray(user));
    }

    public int update(User user) {
        return super.update(UPDATE, objectConvertArray(user));
    }

    // TODO 拼凑sql
    public void generatorWhere(User user, StringBuilder sbr, List<Object> params) {
        if (user == null) {
            return;
        }
        if (user.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(user.getId());
        }
        if (StringUtils.isNotBlank(user.getCode())) {
            sbr.append(" code = ? and ");
            params.add(user.getCode());
        }
        if (StringUtils.isNotBlank(user.getName())) {
            sbr.append(" name = ? and ");
            params.add(user.getName());
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            sbr.append(" password = ? and ");
            params.add(user.getPassword());
        }
        if (user.getType()<0) {
            sbr.append(" type = ? and ");
            params.add(user.getType());
        }
        if (user.getStatus()<0) {
            sbr.append(" status = ? and ");
            params.add(user.getStatus());
        }        
        if (user.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(user.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(User user) {
        if (user == null) return null;
        List<Object> result = new ArrayList<Object>();
        if (StringUtils.isNotBlank(user.getCode())) {
            result.add(user.getCode());
        }
        if (StringUtils.isNotBlank(user.getName())) {
            result.add(user.getName());
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            result.add(user.getPassword());
        }
        if (user.getType()<0) {
            result.add(user.getType());
        }
        if (user.getCreated() != null) {
            result.add(user.getCreated());
        }
        return result.toArray();
    }
}
