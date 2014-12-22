package com.zuhu.system.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zuhu.system.dao.FuncDao;
import com.zuhu.system.dao.RoleDao;
import com.zuhu.system.pojo.Func;
import com.zuhu.system.pojo.Role;

@Service
public class FuncService {
    
    @Resource
    private FuncDao funcDao;
    
    @Resource
    private RoleDao roleDao;
    
    /**
     * 新增功能
     * @param func
     * @return
     */
    public int insert(Func func){
        return funcDao.insert(func);
    }
    
    /**
     * 修改功能
     * @param func
     * @return
     */
    public int update(Func func){
        return funcDao.insert(func);
    }
    
    /**
     * 获取功能列表
     * @return
     */
    public List<Func> getFuncs(int limit,int offset){
        return funcDao.select(null, limit, offset);
    }
    
    /**
     * 获取功能详情
     * @return
     */
    public Func getFunc(long funcId){
        Func func = new Func();
        func.setId(funcId);
        return funcDao.selectSingle(func);
    }
    
    /**
     * 按条件查询功能列表
     * @param func
     * @param limit
     * @param offset
     * @return
     */
    public List<Func> getFuncsByWhere(Func func,int limit,int offset){
        return funcDao.select(func, limit, offset);
    }
    
    /**
     * 获取管理员的功能列表
     * @param managerCode
     * @return
     */
    public List<Func> getFuncsByManagerCode(String managerCode){
        List<Role> roles = roleDao.getRolesByManagerId(managerCode);
        if(CollectionUtils.isEmpty(roles))
            return Collections.emptyList();
        
        List<Long> roleIds = new ArrayList<Long>();
        for (Role role : roles) {
            roleIds.add(role.getId());
        }
        return funcDao.getRolesByRoleIds(roleIds);
    }
    
    /**
     * 获取角色的功能列表
     * @param managerCode
     * @return
     */
    public List<Func> getFuncsByRoleIds(Long [] roleIds){
        return funcDao.getRolesByRoleIds(Arrays.asList(roleIds));
    }
}


