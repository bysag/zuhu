package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.Shop;
import com.zuhu.system.util.DateUtil;

@Service
public class ShopDao extends Dao<Shop> {

    private final static String SELECT_COMMON = " SELECT  id, NAME, boss, created, creater, boss_mobile, telephone, address, TYPE,lng,lat FROM shop ";

    private final static String COUNT = " SELECT count(*) FROM shop ";

    private final static String INSERT = " INSERT INTO shop (NAME, boss, created, creater, boss_mobile, telephone, address, TYPE,lng,lat)VALUES(?,?,?,?,?,?,?,?,?,?);";

    private final static String UPDATE = "";

    @Override
    public Shop selectSingle(Shop shop) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(shop, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Shop>(Shop.class));
    }
    @Override
    public List<Shop> select(Shop shop, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(shop, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Shop>(Shop.class));
    }


    public int count(Shop shop) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(shop, sbr, params);
        return super.count(sbr.toString(), params.toArray());
    }

    public int insert(Shop shop) {
        return super.insert(INSERT, objectConvertArray(shop));
    }

    public int update(Shop shop) {
        return super.update(UPDATE, objectConvertArray(shop));
    }

    // TODO 拼凑sql
    public void generatorWhere(Shop shop, StringBuilder sbr, List<Object> params) {
        if (shop == null) {
            return;
        }
        if (shop.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(shop.getId());
        }
      //id, NAME, boss, created, creater, boss_mobile, telephone, address, TYPE,lng,lat
        if (StringUtils.isNotBlank(shop.getName())) {
            sbr.append(" name = ? and ");
            params.add(shop.getName());
        }
        if (StringUtils.isNotBlank(shop.getBoss())) {
            sbr.append(" boss = ? and ");
            params.add(shop.getBoss());
        }   
        if (shop.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(shop.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (StringUtils.isNotBlank(shop.getCreater())) {
            sbr.append(" creater like '%?%' and ");
            params.add(shop.getCreater());
        }
        if (StringUtils.isNotBlank(shop.getBossMobile())) {
            sbr.append(" boss_mobile like '%?%' and ");
            params.add(shop.getBossMobile());
        } 
        if (StringUtils.isNotBlank(shop.getTelephone())) {
            sbr.append(" telephone like '%?%' and ");
            params.add(shop.getTelephone());
        }         
        if (StringUtils.isNotBlank(shop.getAddress())) {
            sbr.append(" address like '%?%' and ");
            params.add(shop.getAddress());
        }
        if (shop.getType()!=-1) {
            sbr.append(" type = ? and ");
            params.add(shop.getType());
        }  
        if (shop.getLng()!=-1) {
            sbr.append(" lng = ? and ");
            params.add(shop.getLng());
        }  
        if (shop.getLat()!=-1) {
            sbr.append(" lat = ? and ");
            params.add(shop.getLat());
        }          
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(Shop shop) {
        if (shop == null) return null;
        List<Object> result = new ArrayList<Object>();
        //NAME, boss, created, creater, boss_mobile, telephone, address, TYPE,lng,lat
        if (StringUtils.isNotBlank(shop.getName())) {
            result.add(shop.getName());
        }
        if (StringUtils.isNotBlank(shop.getBoss())) {
            result.add(shop.getBoss());
        }         
        if (shop.getCreated() != null) {
            result.add(shop.getCreated());
        }
        if (StringUtils.isNotBlank(shop.getCreater())) {
            result.add(shop.getCreater());
        }
        if (StringUtils.isNotBlank(shop.getBossMobile())) {
            result.add(shop.getBossMobile());
        }
        if (StringUtils.isNotBlank(shop.getTelephone())) {
            result.add(shop.getTelephone());
        }
        if (StringUtils.isNotBlank(shop.getAddress())) {
            result.add(shop.getAddress());
        }    
        if (shop.getType()!=-1) {
            result.add(shop.getType());
        }          
        if (shop.getLng()!=-1) {
            result.add(shop.getLng());
        }      
        if (shop.getLat()!=-1) {
            result.add(shop.getLat());
        }              
        return result.toArray();
    }
}
