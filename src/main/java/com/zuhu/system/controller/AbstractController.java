package com.zuhu.system.controller;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractController {
    public Map<String, Object> generatorResult(Object res) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("result", res);
        return result;
    }
}
