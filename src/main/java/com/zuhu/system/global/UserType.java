package com.zuhu.system.global;

/**
 * 用户类型枚举
 * 
 * @author yunan.zheng
 * @date 2015-01-17
 */

public enum UserType {
    SHOPER("商店老板"),ZUHU("租户"),HOUSE_OWN("房东");
    public String desc;
    UserType(String desc){
        this.desc = desc;
    }
}
