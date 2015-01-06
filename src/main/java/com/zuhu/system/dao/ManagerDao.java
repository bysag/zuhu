package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zuhu.system.pojo.Manager;
import com.zuhu.system.util.DateUtil;

@Service
public class ManagerDao extends Dao<Manager> {

    private final static String SELECT_COMMON = " SELECT  id, CODE, NAME, created, creater,status,password,updated,updater FROM manager  ";

    private final static String COUNT = " SELECT count(*) FROM manager ";

    private final static String INSERT = " INSERT INTO manager ( CODE, NAME, created, creater,status,password,updated,updater) VALUES(?, ?, ?, ?,?,?,?,?);";

    private final static String UPDATE = " UPDATE manager SET %s WHERE id = ? ;";

    @Override
    public Manager selectSingle(Manager manager) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(manager, sbr, params);
         List<Manager> results = namedParameterJdbcTemplate.query(sbr.toString(),params,
                new BeanPropertyRowMapper<Manager>(Manager.class));
         if (CollectionUtils.isEmpty(results)) {
            return null;
        }
         return results.get(0);
    }

    @Override
    public List<Manager> select(Manager manager, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        Map<String,Object> params = new LinkedHashMap<String,Object>();
        generatorWhere(manager, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return namedParameterJdbcTemplate.query(sbr.toString(), params,
                new BeanPropertyRowMapper<Manager>(Manager.class));
    }

    public int count(Manager manager) {
        StringBuilder sbr = new StringBuilder(COUNT);
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(manager, sbr, params);
        return super.count(sbr.toString(), params);
    }

    public int insert(Manager manager) {
        return super.insert(INSERT, objectConvertArray(manager));
    }

    public int update(Manager manager) {
        StringBuilder setContent = new StringBuilder();
        List<Object> updateParam = new ArrayList<Object>();
        generatorUpdate(manager,setContent,updateParam);
        if(StringUtils.isEmpty(setContent.toString())){
            return 0;
        }
        updateParam.add(manager.getId());
        return super.update(String.format(UPDATE, setContent.toString()),updateParam.toArray());
    }
    
    /**
     * 生成update需要的信息： set语句 及 修改参数值
     */
    public void generatorUpdate(Manager manager,StringBuilder setContent,List<Object> updateParam){
        if (manager.getStatus()!=-1) {
            setContent.append(" status = ?, ");
            updateParam.add(manager.getStatus());
        }
        if (StringUtils.isNotBlank(manager.getPassword())) {
            setContent.append(" password = ?, ");
            updateParam.add(manager.getPassword());
        }
        if (StringUtils.isNotBlank(manager.getName())) {
            setContent.append(" name = ?, ");
            updateParam.add(manager.getName());
        }
        if(StringUtils.isNotEmpty(setContent.toString())) {       
            setContent.append(" updater = ?, ");
            updateParam.add(manager.getUpdater());
        }
        if(StringUtils.isNotEmpty(setContent.toString())) {       
            setContent.append(" updated = now() ");
        }
    }

    public void generatorWhere(Manager manager, StringBuilder sbr, Map<String,Object> params) {
        if (manager == null) {
            return;
        }
        sbr.append(" where ");
        if (manager.getId() != 0l) {
            sbr.append(" id = :id and ");
            params.put("id",manager.getId());
        }
        if (StringUtils.isNotBlank(manager.getCode())) {
            sbr.append(" code = :code and ");
            params.put("code",manager.getCode());
        }
        if (StringUtils.isNotBlank(manager.getName())) {
            sbr.append(" name like :name and ");
            params.put("name","%"+manager.getName()+"%");
        }
        if (StringUtils.isNotBlank(manager.getPassword())) {
            sbr.append(" password = :password and ");
            params.put("password",manager.getPassword());
        }
        if (manager.getStatus() !=-1) {
            sbr.append(" status = :status and ");
            params.put("status",manager.getStatus());
        }
        if (manager.getCreated() != null) {
            sbr.append(" created = :created and ");
            params.put("created",DateUtil.format(manager.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (manager.getUpdated() != null) {
            sbr.append(" updated = :updated and ");
            params.put("updated",DateUtil.format(manager.getUpdated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        sbr.append(" 1=1 ");
    }

    public Object[] objectConvertArray(Manager manager) {
        if (manager == null) return null;
        List<Object> result = new ArrayList<Object>();
            result.add(manager.getCode());
            result.add(manager.getName());
            result.add(manager.getCreated());
            result.add(manager.getCreater());
            result.add(manager.getStatus());
            result.add(manager.getPassword());
            result.add(manager.getUpdated());
            result.add(manager.getUpdater());
        return result.toArray();
    }
}
