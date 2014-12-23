package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.Func;
import com.zuhu.system.util.DateUtil;

@Service
public class FuncDao extends Dao<Func> {

    private final static String SELECT_COMMON = " SELECT　id,  NAME, uri, TYPE, created, creater, updated, parent_id, description,sort FROM func  ";

    private final static String COUNT = " SELECT count(*) FROM func ";

    private final static String INSERT = " INSERT INTO func (NAME, uri, TYPE, created, creater, updated, parent_id, description,sort) VALUES( ?, ?, ?, ?, ?, ?, ?, ?,?);";

    private final static String UPDATE = "UPDATE func SET SET NAME =?,uri = ?,TYPE =?,updated = ? ,parent_id = ? ,description = ? ,sort = ? WHERE id = %s ;";

    private final static String MANAGER_FUNC_LIST = " SELECT * FROM func f,role_func rf WHERE rf.roleId IN (:roleIds) AND f.id = rf.funcId order by f.created asc ;";
    
    @Override
    public Func selectSingle(Func func) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(func, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Func>(Func.class));
    }
    @Override
    public List<Func> select(Func func, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(func, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Func>(Func.class));
    }
    
    public int count(Func func) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(func, sbr, params);
        return super.count(sbr.toString(), params.toArray());
    }

    public int insert(Func func) {
        return super.insert(INSERT, objectConvertArray(func));
    }
    
    public int update(Func func) {
        return super.update(UPDATE,new Object[]{});
    }
    
    // TODO 拼凑sql
    public void generatorWhere(Func func, StringBuilder sbr, List<Object> params) {
        if (func == null) {
            return;
        }
        if (func.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(func.getId());
        }
        if (StringUtils.isNotBlank(func.getName())) {
            sbr.append(" name like '%?%' and ");
            params.add(func.getName());
        }
        if (func.getType() != -1) {
            sbr.append(" type = ? and ");
            params.add(func.getType());
        }
        if (StringUtils.isNotBlank(func.getUri())) {
            sbr.append(" uri  like '%?%'  and ");
            params.add(func.getUri());
        }
        if (func.getParentId() != -1) {
            sbr.append(" parent_id = ? and ");
            params.add(func.getParentId());
        } 
        if (func.getUpdated() != null) {
            sbr.append(" updated = ? and ");
            params.add(DateUtil.format(func.getUpdated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (func.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(func.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        sbr.append(" 1=1 ");
    }
    public Object[] objectConvertArray(Func func) {
        if (func == null) return null;
        List<Object> result = new ArrayList<Object>();
        result.add(func.getName());
        result.add(func.getUri());
        result.add(func.getType());
        result.add(func.getCreated());
        result.add(func.getCreater());
        result.add(func.getUpdated());
        result.add(func.getParentId());
        result.add(func.getDescription());
        result.add(func.getSort());
        return result.toArray();
    }
    
    /**
     * 根据角色获取功能列表
     * @param roleIds
     * @return
     */
    public List<Func> getRolesByRoleIds(List<Long> roleIds){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rolesId", roleIds);
        return namedParameterJdbcTemplate.query(MANAGER_FUNC_LIST, params, new BeanPropertyRowMapper<Func>(Func.class));
    }

}
