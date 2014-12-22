package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.zuhu.system.pojo.Company;
import com.zuhu.system.util.DateUtil;

@Service
public class CompanyDao extends Dao<Company> {
    
    private final static String SELECT_COMMON = " SELECT  id, NAME, address, lat, lng, created, updated, TYPE, employee_amount, company_created FROM company ";

    private final static String COUNT = " SELECT count(*) FROM company ";

    private final static String INSERT = " INSERT INTO company (NAME, address, lat, lng, created, updated, TYPE, employee_amount, company_created)  VALUES(?, ?, ?, ?, ?, ?, ?,?,?); ";

    private final static String UPDATE = "";

    @Override
    public Company selectSingle(Company company) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(company, sbr, params);
        return jdbcTemplate.queryForObject(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Company>(Company.class));
    }
    @Override
    public List<Company> select(Company company, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(company, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return jdbcTemplate.query(sbr.toString(), params.toArray(),
                new BeanPropertyRowMapper<Company>(Company.class));
    }


    public int count(Company company) {
        StringBuilder sbr = new StringBuilder(COUNT);
        sbr.append(" where ");
        List<Object> params = new ArrayList<Object>();
        generatorWhere(company, sbr, params);
        return super.count(sbr.toString(), params.toArray());
    }

    public int insert(Company company) {
        return super.insert(INSERT, objectConvertArray(company));
    }

    public int update(Company company) {
        return super.update(UPDATE, objectConvertArray(company));
    }

    // TODO 拼凑sql
    public void generatorWhere(Company company, StringBuilder sbr, List<Object> params) {
        if (company == null) {
            return;
        }
        if (company.getId() != 0l) {
            sbr.append(" id = ? and ");
            params.add(company.getId());
        }
      // id, NAME, address, lat, lng, created, updated, TYPE, employee_amount, company_created
        if (StringUtils.isNotBlank(company.getName())) {
            sbr.append(" name = ? and ");
            params.add(company.getName());
        }
        if (StringUtils.isNotBlank(company.getAddress())) {
            sbr.append(" address like '%?%' and ");
            params.add(company.getAddress());
        }
        if (company.getLat()!=0) {
            sbr.append(" lat = ? and ");
            params.add(company.getLat());
        }        
        if (company.getLng()!=0) {
            sbr.append(" lng = ? and ");
            params.add(company.getLng());
        }        
        if (company.getCreated() != null) {
            sbr.append(" created = ? and ");
            params.add(DateUtil.format(company.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (company.getUpdated() != null) {
            sbr.append(" updated = ? and ");
            params.add(DateUtil.format(company.getUpdated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }        
        if (company.getType()!=-1) {
            sbr.append(" type = ? and ");
            params.add(company.getType());
        }          
        if (company.getEmployeeAmount()!=0) {
            sbr.append(" employee_amount = ? and ");
            params.add(company.getEmployeeAmount());
        } 
        if (company.getCompanyCreated()!=null) {
            sbr.append(" company_created = ? and ");
            params.add(DateUtil.format(company.getCompanyCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }         
        sbr.append(" 1=1 ");
    }

    // TODO 查找把对象转成Object[]数据
    public Object[] objectConvertArray(Company company) {
        if (company == null) return null;
        List<Object> result = new ArrayList<Object>();
        //NAME, address, lat, lng, created, updated, TYPE, employee_amount, company_created
        if (StringUtils.isNotBlank(company.getName())) {
            result.add(company.getName());
        }
        if (StringUtils.isNotBlank(company.getAddress())) {
            result.add(company.getAddress());
        }
        if (company.getLat()!=0) {
            result.add(company.getLat());
        }       
        if (company.getLng()!=0) {
            result.add(company.getLng());
        }
        if (company.getCreated() != null) {
            result.add(company.getCreated());
        }
        if (company.getUpdated() != null) {
            result.add(company.getUpdated());
        }
        if (company.getType()!=-1) {
            result.add(company.getType());
        }   
        if (company.getEmployeeAmount()!=-1) {
            result.add(company.getEmployeeAmount());
        }  
        if (company.getCompanyCreated()!=null) {
            result.add(company.getCompanyCreated());
        }          
        return result.toArray();
    }
}
