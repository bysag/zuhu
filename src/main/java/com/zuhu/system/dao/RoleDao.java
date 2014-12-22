package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.List;

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

    private final static String UPDATE = "UPDATE role SET NAME = ? , description = ? , updated = ? , updater = ?,status = ? WHERE id = %s ;";

    private final static String MANAGER_ROLE_LIST = " SELECT r.id, r.NAME, r.description FROM role r ,manager_role mr WHERE mr.managerCode= ? AND r.id = mr.roleId order by r.created asc;";
   
    private final static String INSERT_MANAGER_ROLE = " INSERT INTO manager_role ( managerCode, roleId, created, creater) VALUES ( ?,?, NOW(), ?);";
    
    private final static String DELETE_MANAGER_ROLE = " DELETE FROM manager_role WHERE managerCode = ? ; ";
   
    @Override
    public Role selectSingle(Role role) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(role, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Role>(Role.class));
    }

    @Override
    public List<Role> select(Role role, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(role, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Role>(Role.class));
    }

    public int count(Role role) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(role, sbr, params);
        return super.count(sbr.toString(), params.toArray());
    }

    public int insert(Role role) {
        return super.insert(INSERT, objectConvertArray(role));
    }

    public int update(Role role) {
        return super.update(UPDATE, objectConvertArray(role));
    }

    // TODO 拼凑sql
    public void generatorWhere(Role role, StringBuilder sbr, List<Object> params) {
        if (role == null) {
            return;
        }
        if (role.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(role.getId());
        }
        if (StringUtils.isNotBlank(role.getName())) {
            sbr.append(" name like '%?%' and ");
            params.add(role.getName());
        }

        if (StringUtils.isNotBlank(role.getDescription())) {
            sbr.append(" description like '%?%' and ");
            params.add(role.getDescription());
        }

        if (role.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(role.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (role.getUpdated() != null) {
            sbr.append(" updated = ? and ");
            params.add(DateUtil.format(role.getUpdated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (StringUtils.isNotBlank(role.getUpdater())) {
            sbr.append(" updater like '%?%' and ");
            params.add(role.getUpdater());
        }
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(Role role) {
        if (role == null) return null;
        List<Object> result = new ArrayList<Object>();
        if (StringUtils.isNotBlank(role.getName())) {
            result.add(role.getName());
        }
        if (StringUtils.isNotBlank(role.getDescription())) {
            result.add(role.getDescription());
        }
        if (role.getCreated() != null) {
            result.add(role.getCreated());
        }
        if (StringUtils.isNotBlank(role.getCreater())) {
            result.add(role.getCreater());
        }          
        if (role.getUpdated() != null) {
            result.add(role.getUpdated());
        }
        if (StringUtils.isNotBlank(role.getUpdater())) {
            result.add(role.getUpdater());
        }        
        if (role.getStatus()!=-1) {
            result.add(role.getStatus());
        } 
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
