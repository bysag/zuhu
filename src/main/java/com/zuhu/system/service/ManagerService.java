package com.zuhu.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zuhu.system.dao.FuncDao;
import com.zuhu.system.dao.ManagerDao;
import com.zuhu.system.dao.RoleDao;
import com.zuhu.system.pojo.Manager;
import com.zuhu.system.util.CurrentLoginManager;

@Service
public class ManagerService {
    @Resource
    private ManagerDao managerDao;
    
    @Resource
    private RoleDao roleDao;
    
    @Resource
    private FuncDao funcDao;
    /**
     * 获取管理员列表
     * @return
     */
    public List<Manager> getManagers(int limit,int offset){
        return managerDao.select(null, limit, offset);
    }
    
    /**
     * 新增管理员
     * @return
     */
    @Transactional
    public int addManager(Manager manager){
        int result = 0;
        Manager selectManager = new Manager();
        selectManager.setCode(manager.getCode());
        Manager selectResultManager = managerDao.selectSingle(selectManager);
        if (selectResultManager!=null) {
            return result;
        }
        if(managerDao.insert(manager)>0){
            roleDao.delManagerRoleByManagerCode(manager.getCode());
            roleDao.insertManagerRole(generatorInsertManagerRoleParams(manager));
            result = 1;
        }
        return result;
    }
    
    private List<Object[]> generatorInsertManagerRoleParams(Manager manager){
        List<Object[]> insertParams = new ArrayList<Object[]>();
        for (Long roleId : manager.getRoleId()) {
            if (roleId==null) {
                continue;
            }
            List<Object> element = new ArrayList<Object>();
            element.add(manager.getCode());
            element.add(roleId);
            element.add(CurrentLoginManager.getCurrentManager().getCode());
            insertParams.add(element.toArray());
        }
        return insertParams;
    }
    
    
    /**
     * 修改管理员信息
     * @return
     */
    @Transactional
    public int updateManager(Manager manager){
        int result = 0;
        if(managerDao.update(manager)>0){
            roleDao.delManagerRoleByManagerCode(manager.getCode());
            roleDao.insertManagerRole(generatorInsertManagerRoleParams(manager));
            result = 1;
         }
        return result;
    }
    
    /**
     * 获取单个管理员
     * @param managerCode
     * @return
     */
    public Manager getManager(String managerCode){
        Manager manager = new Manager();
        manager.setCode(managerCode);
        return managerDao.selectSingle(manager);
    }
}
