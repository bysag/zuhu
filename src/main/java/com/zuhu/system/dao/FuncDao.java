package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.Func;
import com.zuhu.system.util.DateUtil;

@Service
public class FuncDao extends Dao<Func> {

    private final static String SELECT_COMMON = " SELECT id,name,uri,type,created,creater,updated,parent_id,description,sort FROM func ";

    private final static String COUNT = " SELECT count(*) FROM func ";

    private final static String INSERT = " INSERT INTO func (name, uri, TYPE, created, creater, updated, parent_id, description,sort) VALUES( ?, ?, ?, ?, ?, ?, ?, ?,?);";

    private final static String UPDATE = "UPDATE func SET %s WHERE id = ? ;";

    private final static String MANAGER_FUNC_LIST = " SELECT * FROM func f,role_func rf WHERE rf.roleId IN (:roleIds) AND f.id = rf.funcId order by f.created asc ;";
    
    @Override
    public Func selectSingle(Func func) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(func, sbr, params);
        return namedParameterJdbcTemplate.queryForObject(sbr.toString(),params,
                new BeanPropertyRowMapper<Func>(Func.class));
    }
    @Override
    public List<Func> select(Func func, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(func, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return namedParameterJdbcTemplate.query(sbr.toString(),params,
                new BeanPropertyRowMapper<Func>(Func.class));
    }
    
    public int count(Func func) {
        StringBuilder sbr = new StringBuilder(COUNT);
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(func, sbr, params);
        return super.count(sbr.toString(), params);
    }

    public int insert(Func func) {
        return super.insert(INSERT, objectConvertArray(func));
    }
    
    public int update(Func func) {
        StringBuilder setContent = new StringBuilder();
        List<Object> updateParam = new ArrayList<Object>();
        generatorUpdate(func,setContent,updateParam);
        if(StringUtils.isEmpty(setContent.toString())){
            return 0;
        }
        updateParam.add(func.getId());
        return super.update(String.format(UPDATE, setContent.toString()),updateParam.toArray());
    }
    
    /**
     * 生成update需要的信息： set语句 及 修改参数值
     */
    public void generatorUpdate(Func func,StringBuilder setContent,List<Object> updateParam){
        if (StringUtils.isNotBlank(func.getName())) {
            setContent.append(" name = ?, ");
            updateParam.add(func.getName());
        }
        if (func.getType() != -1) {
            setContent.append(" type = ?, ");
            updateParam.add(func.getType());
        }
        if (StringUtils.isNotBlank(func.getUri())) {
            setContent.append(" uri = ?, ");
            updateParam.add(func.getUri());
        }
        if (func.getParentId() != -1) {
            setContent.append(" parent_id = ?, ");
            updateParam.add(func.getParentId());
        } 
        if (func.getSort() != -1) {
            setContent.append(" sort=?, ");
            updateParam.add(func.getSort());
        }
        if (StringUtils.isNotBlank(func.getDescription())) {
            setContent.append(" description=?, ");
            updateParam.add(func.getDescription());
        }
        if(StringUtils.isNotEmpty(setContent.toString()))        
            setContent.append(" updated = now() ");
    }
    
    // TODO 拼凑sql
    public void generatorWhere(Func func, StringBuilder sbr, Map<String,Object> params) {
        if (func == null) {
            return;
        }
        sbr.append(" where ");
        if (func.getId() != -1l) {
            sbr.append(" id = :id and ");
            params.put("id",func.getId());
        }
        if (StringUtils.isNotBlank(func.getName())) {
            sbr.append(" name like :name and ");
            params.put("name","%"+func.getName()+"%");
        }
        if (func.getType() != -1) {
            sbr.append(" type = :type and ");
            params.put("type",func.getType());
        }
        if (StringUtils.isNotBlank(func.getUri())) {
            sbr.append(" uri like :uri  and ");
            params.put("uri","%"+func.getUri()+"%");
        }
        if (func.getParentId() != -1) {
            sbr.append(" parent_id = :parentId and ");
            params.put("parentId",func.getParentId());
        } 
        if (func.getUpdated() != null) {
            sbr.append(" updated = :updated and ");
            params.put("updated",DateUtil.format(func.getUpdated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (func.getCreated() != null) {
            sbr.append(" created = :created and ");
            params.put("created",DateUtil.format(func.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
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
