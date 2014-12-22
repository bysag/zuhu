package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.Activities;
import com.zuhu.system.util.DateUtil;

@Service
public class ActivitiesDao extends Dao<Activities> {

    private final static String SELECT_COMMON = " SELECT id, NAME, orger, content, orgtime, created,status FROM activities  ";

    private final static String COUNT = " SELECT count(*) FROM activities ";

    private final static String INSERT = " INSERT INTO activities (NAME, orger, content, orgtime, created ,status ) VALUES (?,?,?,?,?,?);";

    private final static String UPDATE = "";

    @Override
    public Activities selectSingle(Activities activities) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(activities, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Activities>(Activities.class));
    }

    @Override
    public List<Activities> select(Activities activities, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(activities, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Activities>(Activities.class));
    }

    public int count(Activities activities) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(activities, sbr, params);
        return super.count(sbr.toString(), params.toArray());
    }

    public int insert(Activities activities) {
        return super.insert(INSERT, objectConvertArray(activities));
    }

    public int update(Activities activities) {
        return super.update(UPDATE, objectConvertArray(activities));
    }

    // TODO 拼凑sql
    public void generatorWhere(Activities activities, StringBuilder sbr, List<Object> params) {
        if (activities == null) {
            return;
        }
        if (activities.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(activities.getId());
        }
        // id, NAME, orger, content, orgtime, created,status
        if (StringUtils.isNotBlank(activities.getName())) {
            sbr.append(" name = ? and ");
            params.add(activities.getName());
        }
        if (StringUtils.isNotBlank(activities.getOrger())) {
            sbr.append(" orger = ? and ");
            params.add(activities.getOrger());
        }
        if (StringUtils.isNotBlank(activities.getContent())) {
            sbr.append(" content = ? and ");
            params.add(activities.getContent());
        }
        if (activities.getOrgtime() != null) {
            sbr.append(" orgtime = ? and ");
            params.add(DateUtil.format(activities.getOrgtime(),
                    DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (activities.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(activities.getCreated(),
                    DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (activities.getStatus() != -1) {
            sbr.append(" status = ? and ");
            params.add(activities.getStatus());
        }
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(Activities activities) {
        if (activities == null) return null;
        List<Object> result = new ArrayList<Object>();
        // NAME, orger, content, orgtime, created,status
        if (StringUtils.isNotBlank(activities.getName())) {
            result.add(activities.getName());
        }
        if (StringUtils.isNotBlank(activities.getOrger())) {
            result.add(activities.getOrger());
        }
        if (StringUtils.isNotBlank(activities.getContent())) {
            result.add(activities.getContent());
        }
        if (activities.getOrgtime() != null) {
            result.add(activities.getOrgtime());
        }
        if (activities.getCreated() != null) {
            result.add(activities.getCreated());
        }
        if (activities.getStatus() != -1) {
            result.add(activities.getStatus());
        }
        return result.toArray();
    }

}
