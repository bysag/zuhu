package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.Building;
import com.zuhu.system.util.DateUtil;

@Service
public class BuildingDao extends Dao<Building> {
    
    private final static String SELECT_COMMON = " SELECT id, NAME, address, lng, lat, created, TYPE FROM building ";

    private final static String COUNT = " SELECT count(*) FROM building ";

    private final static String INSERT = " INSERT INTO building (NAME, address, lng, lat, created, TYPE) VALUES(?,?,?, ?,?, ?); ";

    private final static String UPDATE = "";

    @Override
    public Building selectSingle(Building building) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(building, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Building>(Building.class));
    }
    @Override
    public List<Building> select(Building building, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(building, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Building>(Building.class));
    }


    public int count(Building building) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(building, sbr, params);
        return super.count(sbr.toString(), params.toArray());
    }

    public int insert(Building building) {
        return super.insert(INSERT, objectConvertArray(building));
    }

    public int update(Building building) {
        return super.update(UPDATE, objectConvertArray(building));
    }

    // TODO 拼凑sql
    public void generatorWhere(Building building, StringBuilder sbr, List<Object> params) {
        if (building == null) {
            return;
        }
        if (building.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(building.getId());
        }
      //id, NAME, address, lng, lat, created, TYPE
        if (StringUtils.isNotBlank(building.getName())) {
            sbr.append(" name = ? and ");
            params.add(building.getName());
        }
        if (StringUtils.isNotBlank(building.getAddress())) {
            sbr.append(" address like '%?%' and ");
            params.add(building.getAddress());
        }
        if (building.getLng()!=0) {
            sbr.append(" lng = ? and ");
            params.add(building.getLng());
        }        
        if (building.getLat()!=0) {
            sbr.append(" lat = ? and ");
            params.add(building.getLat());
        }
        if (building.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(building.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (building.getType()!=-1) {
            sbr.append(" type = ? and ");
            params.add(building.getType());
        }             
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(Building building) {
        if (building == null) return null;
        List<Object> result = new ArrayList<Object>();
        // NAME, address, lng, lat, created, TYPE
        if (StringUtils.isNotBlank(building.getName())) {
            result.add(building.getName());
        }
        if (StringUtils.isNotBlank(building.getAddress())) {
            result.add(building.getAddress());
        }
        if (building.getLng()!=0) {
            result.add(building.getLng());
        }
        if (building.getLat()!=0) {
            result.add(building.getLat());
        }        
        if (building.getCreated() != null) {
            result.add(building.getCreated());
        }
        if (building.getType()!=-1) {
            result.add(building.getType());
        }          
        return result.toArray();
    }

}
