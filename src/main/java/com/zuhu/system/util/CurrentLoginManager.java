package com.zuhu.system.util;

import com.zuhu.system.pojo.Manager;


public class CurrentLoginManager {
    public static Manager getCurrentManager(){
        Manager manager = new Manager();
        manager.setCode("yunan.zheng");
        return manager;
    }
}
