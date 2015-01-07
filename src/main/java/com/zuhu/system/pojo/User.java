package com.zuhu.system.pojo;

import java.io.Serializable;
import java.util.Date;

public class User extends PoJo implements Serializable {

    private static final long serialVersionUID = 1786135271062575825L;

    private int id;

    private String code;

    private String name;

    private String password;

    private Date created;

    private String type = "";

    private int status = -1;

    private Date updated;

    private String updater;

    private String thirdpartyType;

    private String mobile;

    private String email;
    
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getThirdpartyType() {
        return thirdpartyType;
    }

    public void setThirdpartyType(String thirdpartyType) {
        this.thirdpartyType = thirdpartyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}
