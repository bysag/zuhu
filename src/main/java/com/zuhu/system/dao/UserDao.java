package com.zuhu.system.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zuhu.system.pojo.User;
import com.zuhu.system.pojo.UserDetail;
import com.zuhu.system.util.DateUtil;

@Service
public class UserDao extends Dao<User> {
    
    protected enum HasDetail{
        WITH,WITHOUT;
    }

    private final static String SELECT_COMMON = " SELECT id, code, name, password, created, type, STATUS,thirdparty_type,mobile,email FROM user u ";

    private final static String COUNT = " SELECT count(*) FROM user u ";

    private final static String INSERT = " INSERT INTO USER ( CODE, NAME, PASSWORD, created, TYPE, STATUS,thirdparty_type,mobile,email)  VALUES  (?, ?, ?, ?, ?, ?,?,?,?); ";

    private final static String UPDATE = " UPDATE user SET %s WHERE id = ? ;";
    
    private final static String SELECT_COMMON_WITH_DETAIL = " SELECT u.id, u.code, u.name, u.password, u.created, u.type, u.STATUS,u.thirdparty_type,u.mobile,u.email,ud.realname,ud.education, ud.school, ud.identity_card, ud.married, ud.gender, ud.companyId, ud.created, ud.updated,ud.updater FROM user u  left join user_detail ud on u.id=ud.userId ; ";


    @Override
    public User selectSingle(User user) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(user, sbr, params,HasDetail.WITHOUT);
        List<User> results = namedParameterJdbcTemplate.query(sbr.toString(), params,
                new BeanPropertyRowMapper<User>(User.class));
        if (CollectionUtils.isEmpty(results)) {
            return null;
        }
        return results.get(0);
    }

    public UserDetail selectSingleWithDetail(User user) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON_WITH_DETAIL);
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(user, sbr, params,HasDetail.WITH);
        List<UserDetail> results = namedParameterJdbcTemplate.query(sbr.toString(), params,
                new BeanPropertyRowMapper<UserDetail>(UserDetail.class));
        if (CollectionUtils.isEmpty(results)) {
            return null;
        }
        return results.get(0);
    }
    
    @Override
    public List<User> select(User user, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON);
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(user, sbr, params,HasDetail.WITHOUT);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return namedParameterJdbcTemplate.query(sbr.toString(), params,
                new BeanPropertyRowMapper<User>(User.class));
    }
    
    public List<UserDetail> selectWithDetai(User user, int limit, int offset) {
        StringBuilder sbr = new StringBuilder(SELECT_COMMON_WITH_DETAIL);
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(user, sbr, params,HasDetail.WITH);
        super.generatorLimitOffset(sbr, params, limit, offset);
        return namedParameterJdbcTemplate.query(sbr.toString(), params,
                new BeanPropertyRowMapper<UserDetail>(UserDetail.class));
       
    }

    public int count(User user) {
        StringBuilder sbr = new StringBuilder(COUNT);
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        generatorWhere(user, sbr, params,HasDetail.WITHOUT);
        return super.count(sbr.toString(), params);
    }

    public int insert(User user) {
        return super.insert(INSERT, objectConvertArray(user));
    }

    public int update(User user) {
        StringBuilder setContent = new StringBuilder();
        List<Object> updateParam = new ArrayList<Object>();
        generatorUpdate(user, setContent, updateParam);
        if (StringUtils.isEmpty(setContent.toString())) {
            return 0;
        }
        updateParam.add(user.getId());
        return super.update(String.format(UPDATE, setContent.toString()), updateParam.toArray());
    }

    /**
     * 生成update需要的信息： set语句 及 修改参数值
     */
    public void generatorUpdate(User user, StringBuilder setContent, List<Object> updateParam) {
        if (user.getStatus() != -1) {
            setContent.append(" status = ?, ");
            updateParam.add(user.getStatus());
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            setContent.append(" password = ?, ");
            updateParam.add(user.getPassword());
        }
        if (StringUtils.isNotBlank(user.getName())) {
            setContent.append(" name = ?, ");
            updateParam.add(user.getName());
        }
        if (StringUtils.isNotEmpty(user.getEmail())) {
            setContent.append(" email = ?, ");
            updateParam.add(user.getEmail());
        }
        if (StringUtils.isNotEmpty(user.getMobile())) {
            setContent.append(" mobile = ?, ");
            updateParam.add(user.getMobile());
        }
        if (StringUtils.isNotEmpty(user.getThirdpartyType())) {
            setContent.append(" thirdparty_type = ?, ");
            updateParam.add(user.getThirdpartyType());
        }        
        if (StringUtils.isNotEmpty(setContent.toString())) {
            setContent.append(" updater = ?, ");
            updateParam.add(user.getUpdater());
        }
        if (StringUtils.isNotEmpty(setContent.toString())) {
            setContent.append(" updated = now() ");
        }
    }

    public void generatorWhere(User user, StringBuilder sbr, Map<String, Object> params,HasDetail hasDetail) {
        if (user == null) {
            return;
        }
        switch (hasDetail) {
            case WITH:
                sbr.append(" and ");
                break;
            case WITHOUT:
                sbr.append(" where ");
                break;
            default:
                break;
        }
       
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
        sbr.append(" 1=1 ");
    }

    public Object[] objectConvertArray(User user) {
        if (user == null) return null;
        List<Object> result = new ArrayList<Object>();
        result.add(user.getCode());
        result.add(user.getName());
        result.add(user.getPassword());
        result.add(user.getCreated());
        result.add(user.getType());
        result.add(user.getStatus());
        result.add(user.getThirdpartyType());
        result.add(user.getMobile());
        result.add(user.getEmail());
        return result.toArray();
    }
}
