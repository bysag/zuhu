package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.Role;
import com.zuhu.system.util.DateUtil;

@Service
public class RoleDao extends Dao<Role> {

    private final static String SELECT_COMMON = "  SELECT id, NAME, description, created, creater,updated,updater,status FROM role  ";

    private final static String COUNT = " SELECT count(*) FROM role ";

    private final static String INSERT = "INSERT INTO role (NAME, description, created, creater,updated,updater,status) VALUES(?, ?, ?, ?,?,?,?);";

    private final static String UPDATE = "UPDATE role SET %s WHERE id = ? ;";

    private final static String MANAGER_ROLE_LIST = " SELECT r.id, r.NAME, r.description FROM role r ,manager_role mr WHERE mr.managerCode= ? AND r.id = mr.roleId order by r.created asc;";
   
    private final static String INSERT_MANAGER_ROLE = " INSERT INTO manager_role ( managerCode, roleId, created, creater) VALUES ( ?,?, NOW(), ?);";
    
    private final static String DELETE_MANAGER_ROLE = " DELETE FROM manager_role WHERE managerCode = ? ; ";
   
    @Override
    public Role selectSingle(Role role) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(role, sbr, params);
        return namedParameterJdbcTemplate.queryForObject(sbr.toString(), params,
                new BeanPropertyRowMapper<Role>(Role.class));
    }

    @Override
    public List<Role> select(Role role, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(role, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return namedParameterJdbcTemplate.query(sbr.toString(), params,
                new BeanPropertyRowMapper<Role>(Role.class));
    }

    public int count(Role role) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(role, sbr, params);
        return super.count(sbr.toString(), params);
    }

    public int insert(Role role) {
        return super.insert(INSERT, objectConvertArray(role));
    }

    public int update(Role role) {
        StringBuilder setContent = new StringBuilder();
        List<Object> updateParam = new ArrayList<Object>();
        generatorUpdate(role,setContent,updateParam);
        if(StringUtils.isEmpty(setContent.toString())){
            return 0;
        }
        updateParam.add(role.getId());
        return super.update(String.format(UPDATE, setContent.toString()),updateParam.toArray());
    }

    /**
     * 生成update需要的信息： set语句 及 修改参数值
     */
    public void generatorUpdate(Role role,StringBuilder setContent,List<Object> updateParam){
        if (StringUtils.isNotBlank(role.getName())) {
            setContent.append(" name = ?, ");
            updateParam.add(role.getName());
        }
        if (StringUtils.isNotBlank(role.getDescription())) {
            setContent.append(" description=?, ");
            updateParam.add(role.getDescription());
        }
        if (StringUtils.isNotBlank(role.getUpdater())) {
            setContent.append(" updater=?, ");
            updateParam.add(role.getUpdater());
        }
        if (role.getStatus()!=-1) {
            setContent.append(" status=?, ");
            updateParam.add(role.getStatus());
        }
        if(StringUtils.isNotEmpty(setContent.toString())) {       
            setContent.append(" updated = now() ");
        }
    }
    
    // TODO 拼凑sql
    public void generatorWhere(Role role, StringBuilder sbr, Map<String,Object> params) {
        if(role==null){
            return;
        }
        sbr.append(" where ");
        if (role.getId() != -1l) {
            sbr.append(" id = :id and ");
            params.put("id",role.getId());
        }
        if (StringUtils.isNotBlank(role.getName())) {
            sbr.append(" name like :name and ");
            params.put("name","%"+role.getName()+"%");
        }
        if (StringUtils.isNotBlank(role.getDescription())) {
            sbr.append(" description like :description and ");
            params.put("description","%"+role.getDescription()+"%");
        }
        if (role.getCreated() != null) {
            sbr.append(" created = :created and ");
            params.put("created",DateUtil.format(role.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (role.getUpdated() != null) {
            sbr.append(" updated = :updated and ");
            params.put("updated",DateUtil.format(role.getUpdated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (StringUtils.isNotBlank(role.getUpdater())) {
            sbr.append(" updater like :updater and ");
            params.put("updater","%"+role.getUpdater()+"%");
        }
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(Role role) {
        if (role == null) return null;
        List<Object> result = new ArrayList<Object>();
            result.add(role.getName());
            result.add(role.getDescription());
            result.add(role.getCreated());
            result.add(role.getCreater());
            result.add(null);
            result.add("");
            result.add(role.getStatus());
        return result.toArray();
    }
    
    /**
     * 获取管理员的角色列表
     * @param managerId
     * @return
     */
    public List<Role> getRolesByManagerId(String managerCode){
        return jdbcTemplate.query(MANAGER_ROLE_LIST, new Object[]{managerCode},new BeanPropertyRowMapper<Role>(Role.class));
    }
    
    /**
     * 添加管理员与角色的关联关系
     * @param managerCode
     * @param roleId
     * @param creater
     * @return
     */
    public int[] insertManagerRole(List<Object[]> insertParams){
        return jdbcTemplate.batchUpdate(INSERT_MANAGER_ROLE,insertParams);
    }
    
    /**
     * 删除管理员已有的角色
     * @param managerCode
     * @return
     */
    public int delManagerRoleByManagerCode(String managerCode){
        return jdbcTemplate.update(DELETE_MANAGER_ROLE, new Object[]{managerCode});
    }
}
