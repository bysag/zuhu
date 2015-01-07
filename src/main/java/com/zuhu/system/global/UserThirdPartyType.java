package com.zuhu.system.global;
/**
 * 用户第三方账号的类型
 * @author yunan.zheng
 */


public enum UserThirdPartyType {
    QQ("QQ"),WEIXIN("微信"),WEIBO("微博");
    public String desc;
    UserThirdPartyType(String desc){
        this.desc = desc;
    }
}
