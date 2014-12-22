package com.zuhu.system.service;

import java.util.Collections;
import java.util.Map;

public abstract class AbstractService {

    /**
     * 生成返回列表页结果集
     * 
     * @param result
     * @param amount
     * @param dataList
     */
    // TODO 考虑用泛型
    public void generatorListResult(Map<String, Object> result, int amount, Object dataList) {
        if (amount > 0) {
            result.put("data", dataList);
        } else {
            result.put("data", Collections.emptyList());
        }
        result.put("totalCount", amount);
    }

}
