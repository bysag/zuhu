package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zuhu.system.pojo.UGC;
import com.zuhu.system.util.DateUtil;


public class UgcDao extends Dao<UGC>{

    private final static String SELECT_COMMON = " SELECT id, content, created, creater, beanId, TYPE,level FROM ugc   ";

    private final static String COUNT = " SELECT count(*) FROM ugc ";

    private final static String INSERT = " INSERT INTO ugc (content, created, creater, beanId, TYPE,level)VALUES (?,?,?,?,?,?);";

    private final static String UPDATE = "";

    @Override
    public UGC selectSingle(UGC ugc) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(ugc, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<UGC>(UGC.class));
    }
    @Override
    public List<UGC> select(UGC ugc, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(ugc, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<UGC>(UGC.class));
    }


    public int count(UGC ugc) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(ugc, sbr, params);
        return super.count(sbr.toString(), params.toArray());
    }

    public int insert(UGC ugc) {
        return super.insert(INSERT, objectConvertArray(ugc));
    }

    public int update(UGC ugc) {
        return super.update(UPDATE, objectConvertArray(ugc));
    }

    // TODO 拼凑sql
    public void generatorWhere(UGC ugc, StringBuilder sbr, List<Object> params) {
        if (ugc == null) {
            return;
        }
        if (ugc.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(ugc.getId());
        }
      //id, content, created, creater, beanId, TYPE
        if (StringUtils.isNotBlank(ugc.getContent())) {
            sbr.append(" content = ? and ");
            params.add(ugc.getContent());
        }
        if (ugc.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(ugc.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (StringUtils.isNotBlank(ugc.getCreater())) {
            sbr.append(" creater like '%?%' and ");
            params.add(ugc.getCreater());
        }  
        if (ugc.getBeanId()!=-1) {
            sbr.append(" beanId = ? and ");
            params.add(ugc.getBeanId());
        }   
        if (ugc.getType()!=-1) {
            sbr.append(" type = ? and ");
            params.add(ugc.getType());
        }   
        if (ugc.getLevel()!=-1) {
            sbr.append(" level = ? and ");
            params.add(ugc.getLevel());
        }           
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(UGC ugc) {
        if (ugc == null) return null;
        List<Object> result = new ArrayList<Object>();
        // id, content, created, creater, beanId, TYPE
        if (StringUtils.isNotBlank(ugc.getContent())) {
            result.add(ugc.getContent());
        }
        if (ugc.getCreated() != null) {
            result.add(ugc.getCreated());
        }
        if (StringUtils.isNotBlank(ugc.getCreater())) {
            result.add(ugc.getCreater());
        }
        if (ugc.getBeanId()!=-1) {
            result.add(ugc.getBeanId());
        }  
        if (ugc.getType()!=-1) {
            result.add(ugc.getType());
        } 
        if (ugc.getLevel()!=-1) {
            result.add(ugc.getLevel());
        } 
        return result.toArray();
    }

}
