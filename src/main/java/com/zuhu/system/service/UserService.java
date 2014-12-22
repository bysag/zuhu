package com.zuhu.system.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zuhu.system.dao.UserDao;
import com.zuhu.system.pojo.User;

@Service
public class UserService extends AbstractService {

    @Resource
    private UserDao userDao;

    /**
     * 登录校验
     * 
     * @param code
     * @param password
     * @return User , null 代表 没有此用户
     */
    public User validateLogin(String code, String password) {
        User user = new User();
        user.setCode(code);
        user.setPassword(password);
        User res = userDao.selectSingle(user);
        if (res != null && res.getStatus() == 1) {
            return res; 
        }
        return null;
    }

    /**
     * 分页查询
     * @param user
     * @return Map<String,Object>
     *              totalCount:10
     *              data:list<User>
     */
    public Map<String, Object> listByCondition(User user) {
        if (user == null) {
            return Collections.emptyMap();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        int amount = userDao.count(user);
        List<User> userList = new ArrayList<User>();
        if (amount > 0) {
            userList =  userDao.select(user, user.getLimit(), user.getOffset());
        } 
        generatorListResult(result,amount,userList);
        return result;
    }

    
    /**
     * 新增用户
     * @param user
     * @return
     */
    public int insert(User user){
        if(user == null) return 0;
        return userDao.insert(user);
    }
    
    /**
     * 修改用户
     * @param user
     * @return
     */
    public int update(User user){
        if(user == null) return 0;
        return  userDao.update(user);
    }

    
}
