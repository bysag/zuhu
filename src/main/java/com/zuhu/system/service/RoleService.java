package com.zuhu.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zuhu.system.dao.RoleDao;
import com.zuhu.system.pojo.Role;

@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;
    
    /**
     * 新增角色
     * @param role
     * @return
     */
    public int insert(Role role){
        return roleDao.insert(role);
    }
    
    /**
     * 修改角色
     * @param role
     * @return
     */
    public int update(Role role){
        return roleDao.update(role);
    }
    
    /**
     * 获取单个角色
     * @param roleId
     * @return
     */
    public Role getRole(long roleId){
        Role role = new Role();
        role.setId(roleId);
        return roleDao.selectSingle(role);
    }
    
    /**
     * 获取角色列表
     * @param limit
     * @param offset
     * @return
     */
    public List<Role> getRoles(int limit ,int offset){
        return roleDao.select(null, limit, offset);
    }
    
    /**
     * 按条件搜索角色列表
     * @param role
     * @param limit
     * @param offset
     * @return
     */
    public List<Role> getRolesByWhere(Role role,int limit,int offset){
        return roleDao.select(role, limit, offset);
    }
    
    /**
     * 获取管理员的角色列表
     * @return
     */
    public List<Role> getRolesByManageId(String managerCode){
        return roleDao.getRolesByManagerId(managerCode);
    }
}
