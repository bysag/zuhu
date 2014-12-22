package com.zuhu.system.pojo;

import java.io.Serializable;
import java.util.Date;

public class UGC implements Serializable {

    private static final long serialVersionUID = -3649762189255757373L;

    private long id;

    private String content;

    private Date created;

    private String creater;

    private long beanId = -1;

    private int type = -1;

    private int level = -1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public long getBeanId() {
        return beanId;
    }

    public void setBeanId(long beanId) {
        this.beanId = beanId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
