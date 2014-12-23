package com.zuhu.system.pojo;

import java.io.Serializable;
import java.util.Date;

public class Func extends PoJo implements Serializable {

    private static final long serialVersionUID = -605734224514108555L;

    private long id=-1l;//表Id

    private String name;//名字

    private String uri;//uri

    private int type=-1;//类型, 0 菜单型，1 按钮型

    private Date created;//创建时间

    private String creater;//创建者 ,系统默认功能为SYSTEM_ADMIN

    private Date updated;//修改时间

    private long parentId=-1l;//父菜单

    
    private String description;//描述

    private int sort=-1;//同一级别功能菜单的排序
    
    public int getSort() {
        return sort;
    }

    
    public void setSort(int sort) {
        this.sort = sort;
    }

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
