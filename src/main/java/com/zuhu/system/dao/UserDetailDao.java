package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.util.CollectionUtils;

import com.zuhu.system.pojo.UserDetail;
import com.zuhu.system.util.DateUtil;


public class UserDetailDao  extends Dao<UserDetail> {
    
    private final static String SELECT_COMMON = " SELECT userId as id, realname, education, school, identity_card, married, gender, companyId, created, updated, updater FROM user_detail  ";

    private final static String COUNT = " SELECT count(*) FROM user u  left join user_detail ud on u.id=ud.userId  ";

    private final static String INSERT = " INSERT INTO USER ( userId, realname, education, school, identity_card, married, gender, companyId, created, updated, updater)  VALUES  (?, ?, ?, ?, ?, ?,?,?,?,?,?); ";

    private final static String UPDATE = " UPDATE user_detail SET %s WHERE userId = ? ;";

    private final static String SELECT_COMMON_WITH_USER = " SELECT u.id, u.code, u.name, u.password, u.created, u.type, u.STATUS,u.thirdparty_type,u.mobile,u.email,ud.realname,ud.education, ud.school, ud.identity_card, ud.married, ud.gender, ud.companyId, ud.created, ud.updated,ud.updater FROM user u  left join user_detail ud on u.id=ud.userId  ";


    @Override
    public UserDetail selectSingle(UserDetail userDetail) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(userDetail, sbr, params);
        List<UserDetail> results = namedParameterJdbcTemplate.query(sbr.toString(), params,
                new BeanPropertyRowMapper<UserDetail>(UserDetail.class));
        if (CollectionUtils.isEmpty(results)) {
            return null;
        }
        return results.get(0);
    }

    @Override
    public List<UserDetail> select(UserDetail userDetail, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON_WITH_USER);
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(userDetail, sbr, params);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return namedParameterJdbcTemplate.query(sbr.toString(), params,
                new BeanPropertyRowMapper<UserDetail>(UserDetail.class));
    }

    public int count(UserDetail userDetail) {
        StringBuilder sbr = new StringBuilder(COUNT);
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(userDetail, sbr, params);
        return super.count(sbr.toString(), params);
    }

    public int insert(UserDetail userDetail) {
        return super.insert(INSERT, objectConvertArray(userDetail));
    }

    public int update(UserDetail userDetail) {
        StringBuilder setContent = new StringBuilder();
        List<Object> updateParam = new ArrayList<Object>();
        generatorUpdate(userDetail, setContent, updateParam);
        if (StringUtils.isEmpty(setContent.toString())) {
            return 0;
        }
        updateParam.add(userDetail.getId());
        return super.update(String.format(UPDATE, setContent.toString()), updateParam.toArray());
    }

    /**
     * 生成update需要的信息： set语句 及 修改参数值
     */
    public void generatorUpdate(UserDetail userDetail, StringBuilder setContent, List<Object> updateParam) {
        if (StringUtils.isNotBlank(userDetail.getRealname())) {
            setContent.append(" realname = ?, ");
            updateParam.add(userDetail.getRealname());
        }
        if (StringUtils.isNotBlank(userDetail.getEducation())) {
            setContent.append(" education = ?, ");
            updateParam.add(userDetail.getEducation());
        }
        if (StringUtils.isNotBlank(userDetail.getSchool())) {
            setContent.append(" school = ?, ");
            updateParam.add(userDetail.getSchool());
        }
        if (StringUtils.isNotEmpty(userDetail.getIdentity_card())) {
            setContent.append(" identity_card = ?, ");
            updateParam.add(userDetail.getIdentity_card());
        }
        if (StringUtils.isNotEmpty(userDetail.getMarried())) {
            setContent.append(" married = ?, ");
            updateParam.add(userDetail.getMarried());
        }
        if (StringUtils.isNotEmpty(userDetail.getGender())) {
            setContent.append(" gender = ?, ");
            updateParam.add(userDetail.getGender());
        }
        if (userDetail.getCompanyId()!=-1) {
            setContent.append(" companyId = ?, ");
            updateParam.add(userDetail.getCompanyId());
        }
        if (StringUtils.isNotEmpty(setContent.toString())) {
            setContent.append(" updater =? , ");
            updateParam.add(userDetail.getUpdater());
        }
        if (StringUtils.isNotEmpty(setContent.toString())) {
            setContent.append(" updated = now() ");
        }
    }
    public void generatorWhere(UserDetail user, StringBuilder sbr, Map<String, Object> params) {
        if (user == null) {
            return;
        }
        sbr.append(" and ");
        if (user.getId() != 0l) {
            sbr.append(" u.id = :id and ");
            params.put("id", user.getId());
        }
        if (StringUtils.isNotBlank(user.getCode())) {
            sbr.append(" u.code = :code and ");
            params.put("code", user.getCode());
        }
        if (StringUtils.isNotBlank(user.getName())) {
            sbr.append(" u.name = :name and ");
            params.put("name", user.getName());
        }
        if (StringUtils.isNotBlank(user.getType())) {
            sbr.append(" u.type = :type and ");
            params.put("type", user.getType());
        }
        if (user.getStatus() != -1) {
            sbr.append(" u.status = :status and ");
            params.put("status", user.getStatus());
        }
        if (user.getCreated() != null) {
            sbr.append(" u.created = :created and ");
            params.put("created",
                    DateUtil.format(user.getCreated(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        }
        if (StringUtils.isNotBlank(user.getThirdpartyType())) {
            sbr.append(" u.thirdparty_type = :thirdpartyType and ");
            params.put("thirdpartyType", user.getThirdpartyType());
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            sbr.append(" u.email like :email and ");
            params.put("email", "%" + user.getEmail() + "%");
        }
        if (StringUtils.isNotBlank(user.getMobile())) {
            sbr.append(" u.mobile like :mobile and ");
            params.put("mobile", "%" + user.getMobile() + "%");
        }
        if (StringUtils.isNotBlank(user.getRealname())) {
            sbr.append(" ud.realname like :realname and ");
            params.put("realname", "%" + user.getRealname() + "%");
        }
        if (StringUtils.isNotBlank(user.getEducation())) {
            sbr.append(" ud.education like :education and ");
            params.put("education", user.getEducation());
        }  
        if (StringUtils.isNotBlank(user.getSchool())) {
            sbr.append(" ud.school like :school and ");
            params.put("school", "%" + user.getSchool() + "%");
        }   
        if (StringUtils.isNotBlank(user.getMarried())) {
            sbr.append(" ud.married like :married and ");
            params.put("married", user.getMarried());
        }        
        if (StringUtils.isNotBlank(user.getGender())) {
            sbr.append(" ud.gender like :gender and ");
            params.put("gender", user.getGender());
        }     
        if (user.getCompanyId()!=-1) {
            sbr.append(" ud.companyId like :companyId and ");
            params.put("companyId", user.getCompanyId());
        }           
        sbr.append(" 1=1 ");
    }


    public Object[] objectConvertArray(UserDetail userDetail) {
        if (userDetail == null) return null;
        List<Object> result = new ArrayList<Object>();
        result.add(userDetail.getCode());
        result.add(userDetail.getName());
        result.add(userDetail.getPassword());
        result.add(userDetail.getCreated());
        result.add(userDetail.getType());
        result.add(userDetail.getStatus());
        result.add(userDetail.getThirdpartyType());
        result.add(userDetail.getMobile());
        result.add(userDetail.getEmail());
        return result.toArray();
    }
}
