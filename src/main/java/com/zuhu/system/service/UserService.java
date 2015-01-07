package com.zuhu.system.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zuhu.system.dao.UserDao;
import com.zuhu.system.dao.UserDetailDao;
import com.zuhu.system.global.UserType;
import com.zuhu.system.pojo.User;
import com.zuhu.system.pojo.UserDetail;

@Service
public class UserService extends AbstractService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserDetailDao userDetailDao;
    
    /**
     * 登录校验
     * 
     * @param code
     * @param password
     * @return User , null 代表 没有此用户
     */
    public User validateLogin(String code, String password,UserType userType) {
        User user = new User();
        user.setCode(code);
        user.setPassword(password);
        User res = userDao.selectSingle(user);
        if (res != null && res.getStatus() == 1) {
            return res; 
        }
        return null;
    }

    public Map<String, Object> selectUserSingleWithoutDetail(User user) {
        if (user == null) {
            return Collections.emptyMap();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        int amount = userDao.count(user);
        List<User> userList = new ArrayList<User>();
        if (amount > 0) {
            User selectSingle = userDao.selectSingle(user);
            if (selectSingle!=null) {
                userList.add(selectSingle);
            }
        } 
        generatorListResult(result,amount,userList);
        return result;
    }
    
    public Map<String, Object> selectUserSingleWithDetail(User user) {
        if (user == null) {
            return Collections.emptyMap();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        int amount = userDao.count(user);
        List<UserDetail> userList = new ArrayList<UserDetail>();
        if (amount > 0) {
            UserDetail selectSingleWithDetail = userDao.selectSingleWithDetail(user);
            if (selectSingleWithDetail!=null) {
                userList.add(selectSingleWithDetail);
            }
        } 
        generatorListResult(result,amount,userList);
        return result;
    }
    
    public Map<String, Object> selectUsersWithoutDetail(User user) {
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
    

    public Map<String, Object> selectUsersWithDetail(User user) {
        if (user == null) {
            return Collections.emptyMap();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        int amount = userDao.count(user);
        List<UserDetail> userList = new ArrayList<UserDetail>();
        if (amount > 0) {
            userList =  userDao.selectWithDetai(user, user.getLimit(), user.getOffset());
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
        return userDao.update(user);
    }

    /**
     * 添加用户详细
     * @param userDetail
     * @return
     */
    public int addUserDetail(UserDetail userDetail){
        if(userDetail==null) return 0;
        return userDetailDao.insert(userDetail);
    }
  
    /**
     * 修改用户详细
     * @param userDetail
     * @return
     */
    public int updateUserDetail(UserDetail userDetail){
        if(userDetail==null) return 0;
        return userDetailDao.update(userDetail);
    }
}
