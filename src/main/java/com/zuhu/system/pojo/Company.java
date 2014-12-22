package com.zuhu.system.pojo;

import java.io.Serializable;
import java.util.Date;

public class Company extends PoJo implements Serializable {

    private static final long serialVersionUID = 6387727514144480098L;

    private long id;

    private String name;

    private String address;

    private float lat;

    private float lng;

    private Date created;

    private Date updated;

    private int type =-1;

    private int employeeAmount =-1;

    private Date companyCreated ;

    private long buildingId;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getEmployeeAmount() {
        return employeeAmount;
    }

    public void setEmployeeAmount(int employeeAmount) {
        this.employeeAmount = employeeAmount;
    }

    public Date getCompanyCreated() {
        return companyCreated;
    }

    public void setCompanyCreated(Date companyCreated) {
        this.companyCreated = companyCreated;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

}
