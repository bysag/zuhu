package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.Goods;
import com.zuhu.system.util.DateUtil;

@Service
public class GoodsDao extends Dao<Goods> {

    private final static String SELECT_COMMON = " SELECT id, NAME, price, description, created, creater FROM goods  ";

    private final static String COUNT = " SELECT count(*) FROM goods ";

    private final static String INSERT = " INSERT INTO goods (NAME, price, description, created, creater)VALUES(?,?,?,?,?);";

    private final static String UPDATE = "";

    @Override
    public Goods selectSingle(Goods goods) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(goods, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Goods>(Goods.class));
    }
    @Override
    public List<Goods> select(Goods goods, int limit, int offset) {
        /**
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(goods, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Goods>(Goods.class));
                */
        return null;
    }


    public int count(Goods goods) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(goods, sbr, params);
        return 0;//super.count(sbr.toString(), params.toArray());
    }

    public int insert(Goods goods) {
        return super.insert(INSERT, objectConvertArray(goods));
    }

    public int update(Goods goods) {
        return super.update(UPDATE, objectConvertArray(goods));
    }

    // TODO 拼凑sql
    public void generatorWhere(Goods goods, StringBuilder sbr, List<Object> params) {
        if (goods == null) {
            return;
        }
        if (goods.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(goods.getId());
        }
      //id, NAME, price, description, created, creater
        if (StringUtils.isNotBlank(goods.getName())) {
            sbr.append(" name = ? and ");
            params.add(goods.getName());
        }
        if (goods.getPrice()!=0) {
            sbr.append(" price = ? and ");
            params.add(goods.getPrice());
        }   
        if (StringUtils.isNotBlank(goods.getDescription())) {
            sbr.append(" description like '%?%' and ");
            params.add(goods.getDescription());
        }
        if (goods.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(goods.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (StringUtils.isNotBlank(goods.getCreater())) {
            sbr.append(" creater like '%?%' and ");
            params.add(goods.getCreater());
        }         
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(Goods goods) {
        if (goods == null) return null;
        List<Object> result = new ArrayList<Object>();
        // NAME, price, description, created, creater
        if (StringUtils.isNotBlank(goods.getName())) {
            result.add(goods.getName());
        }
        if (goods.getPrice()!=0) {
            result.add(goods.getPrice());
        }         
        if (StringUtils.isNotBlank(goods.getDescription())) {
            result.add(goods.getDescription());
        }
        if (goods.getCreated() != null) {
            result.add(goods.getCreated());
        }
        if (StringUtils.isNotBlank(goods.getCreater())) {
            result.add(goods.getCreater());
        }
        return result.toArray();
    }

}
