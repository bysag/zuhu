package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.House;
import com.zuhu.system.util.DateUtil;

@Service
public class HouseDao extends Dao<House> {
    
    private final static String SELECT_COMMON = " SELECT id, NAME, address, OWNER, created, creater, description FROM house  ";

    private final static String COUNT = " SELECT count(*) FROM house ";

    private final static String INSERT = " INSERT INTO house (NAME, address, OWNER, created, creater, description)  VALUES(?, ?, ?, ?, ?, ?);";

    private final static String UPDATE = "";

    @Override
    public House selectSingle(House house) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(house, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<House>(House.class));
    }
    @Override
    public List<House> select(House house, int limit, int offset) {
       /**
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(house, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<House>(House.class));
                */
        return null;
    }


    public int count(House house) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(house, sbr, params);
        return 0;//super.count(sbr.toString(), params.toArray());
    }

    public int insert(House house) {
        return super.insert(INSERT, objectConvertArray(house));
    }

    public int update(House house) {
        return super.update(UPDATE, objectConvertArray(house));
    }

    // TODO 拼凑sql
    public void generatorWhere(House house, StringBuilder sbr, List<Object> params) {
        if (house == null) {
            return;
        }
        if (house.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(house.getId());
        }
      //NAME, address, OWNER, created, creater, description
        if (StringUtils.isNotBlank(house.getName())) {
            sbr.append(" name = ? and ");
            params.add(house.getName());
        }
        if (StringUtils.isNotBlank(house.getAddress())) {
            sbr.append(" address like '%?%' and ");
            params.add(house.getAddress());
        }
        if (house.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(house.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (StringUtils.isNotBlank(house.getCreater())) {
            sbr.append(" creater like '%?%' and ");
            params.add(house.getCreater());
        }
        if (StringUtils.isNotBlank(house.getDescription())) {
            sbr.append(" description like '%?%' and ");
            params.add(house.getDescription());
        }           
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(House house) {
        if (house == null) return null;
        List<Object> result = new ArrayList<Object>();
        //NAME, address, OWNER, created, creater, description
        if (StringUtils.isNotBlank(house.getName())) {
            result.add(house.getName());
        }
        if (StringUtils.isNotBlank(house.getAddress())) {
            result.add(house.getAddress());
        }
        if (StringUtils.isNotBlank(house.getOwner())) {
            result.add(house.getOwner());
        }
        if (house.getCreated() != null) {
            result.add(house.getCreated());
        }
        if (StringUtils.isNotBlank(house.getCreater())) {
            result.add(house.getCreater());
        }    
        if (StringUtils.isNotBlank(house.getDescription())) {
            result.add(house.getDescription());
        }           
        return result.toArray();
    }
}
