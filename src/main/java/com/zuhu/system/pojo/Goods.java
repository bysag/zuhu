package com.zuhu.system.pojo;

import java.io.Serializable;
import java.util.Date;

public class Goods  extends PoJo  implements Serializable {

    private static final long serialVersionUID = -494659864892607121L;

    private long id;

    private String name;

    private float price;

    private String description;

    private Date created;

    private String creater;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
