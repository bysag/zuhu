package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.Manager;
import com.zuhu.system.util.DateUtil;

@Service
public class ManagerDao extends Dao<Manager> {

    private final static String SELECT_COMMON = " SELECT  id, CODE, NAME, created, creater,status,password,updated,updater FROM manager  ";

    private final static String COUNT = " SELECT count(*) FROM manager ";

    private final static String INSERT = " INSERT INTO manager ( CODE, NAME, created, creater,status,password,updated,updater) VALUES(?, ?, ?, ?,?,?,?,?);";

    private final static String UPDATE = " UPDATE manager SET CODE = ? ,NAME = ? , created = ? ,creater = ?　,status = ?,password=?,updated=?,updater=? WHERE   id = %s ;";

    @Override
    public Manager selectSingle(Manager manager) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(manager, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Manager>(Manager.class));
    }

    @Override
    public List<Manager> select(Manager manager, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(manager, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Manager>(Manager.class));
    }

    public int count(Manager manager) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(manager, sbr, params);
        return super.count(sbr.toString(), params.toArray());
    }

    public int insert(Manager manager) {
        return super.insert(INSERT, objectConvertArray(manager));
    }

    public int update(Manager manager) {
        return super.update(UPDATE, objectConvertArray(manager));
    }

    // TODO 拼凑sql
    public void generatorWhere(Manager manager, StringBuilder sbr, List<Object> params) {
        if (manager == null) {
            return;
        }

        if (manager.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(manager.getId());
        }
        if (StringUtils.isNotBlank(manager.getCode())) {
            sbr.append(" code = ? and ");
            params.add(manager.getCode());
        }
        if (StringUtils.isNotBlank(manager.getName())) {
            sbr.append(" name = ? and ");
            params.add(manager.getName());
        }
        if (StringUtils.isNotBlank(manager.getPassword())) {
            sbr.append(" password = ? and ");
            params.add(manager.getPassword());
        }
        if (manager.getStatus() < 0) {
            sbr.append(" status = ? and ");
            params.add(manager.getStatus());
        }
        if (manager.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(manager.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (manager.getUpdated() != null) {
            sbr.append(" updated = ? and ");
            params.add(DateUtil.format(manager.getUpdated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(Manager manager) {
        if (manager == null) return null;
        List<Object> result = new ArrayList<Object>();
        if (StringUtils.isNotBlank(manager.getCode())) {
            result.add(manager.getCode());
        }
        if (StringUtils.isNotBlank(manager.getName())) {
            result.add(manager.getName());
        }
        if (manager.getCreated() != null) {
            result.add(manager.getCreated());
        }
        if (manager.getCreater() != null) {
            result.add(manager.getCreater());
        }
        if (manager.getStatus() != -1) {
            result.add(manager.getCreater());
        }
        if (StringUtils.isNotBlank(manager.getPassword())) {
            result.add(manager.getPassword());
        }
        if (manager.getUpdated() != null) {
            result.add(manager.getUpdated());
        }
        if (manager.getUpdater() != null) {
            result.add(manager.getUpdater());
        }
        return result.toArray();
    }
}
