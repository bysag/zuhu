package com.zuhu.system.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserDetail extends User implements Serializable {
    
    private static final long serialVersionUID = -2031684642409293522L;

    private String realname;

    private String education;

    private String school;

    private String identity_card;

    private String married;

    private String gender;

    private long companyId;

    private Date created;

    private Date updated;

    private String updater;
    
    public String getRealname() {
        return realname;
    }

    
    public void setRealname(String realname) {
        this.realname = realname;
    }

    
    public String getEducation() {
        return education;
    }

    
    public void setEducation(String education) {
        this.education = education;
    }

    
    public String getSchool() {
        return school;
    }

    
    public void setSchool(String school) {
        this.school = school;
    }

    
    public String getIdentity_card() {
        return identity_card;
    }

    
    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    
    public String getMarried() {
        return married;
    }

    
    public void setMarried(String married) {
        this.married = married;
    }

    
    public String getGender() {
        return gender;
    }

    
    public void setGender(String gender) {
        this.gender = gender;
    }

    
    public long getCompanyId() {
        return companyId;
    }

    
    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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

    
    public String getUpdater() {
        return updater;
    }

    
    public void setUpdater(String updater) {
        this.updater = updater;
    }
    
    

}
