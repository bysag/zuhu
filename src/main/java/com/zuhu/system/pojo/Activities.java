package com.zuhu.system.pojo;

import java.io.Serializable;
import java.util.Date;

public class Activities extends PoJo implements Serializable {

    private static final long serialVersionUID = 1954059234825572657L;

    private long id;

    private String name;

    private String orger;

    private String content;

    private Date orgtime;

    private Date created;

    private int status =-1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrger() {
        return orger;
    }

    public void setOrger(String orger) {
        this.orger = orger;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getOrgtime() {
        return orgtime;
    }

    public void setOrgtime(Date orgtime) {
        this.orgtime = orgtime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
