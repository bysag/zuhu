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

    private int type = -1;

    private int status = -1;
    
    private Date updated;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
