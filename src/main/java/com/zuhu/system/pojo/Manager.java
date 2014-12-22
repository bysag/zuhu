package com.zuhu.system.pojo;

import java.io.Serializable;
import java.util.Date;

public class Manager implements Serializable {

    private static final long serialVersionUID = -3477566201279918146L;

    private long id;

    private String code;

    private String name;

    private Date created;

    private String creater;

    private int status = -1;

    private String password;

    private Date updated;

    private String updater;

    private Long[] roleId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Long[] getRoleId() {
        return roleId;
    }

    public void setRoleId(Long[] roleId) {
        this.roleId = roleId;
    }

}
