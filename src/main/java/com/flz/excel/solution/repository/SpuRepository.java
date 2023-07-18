package com.flz.excel.solution.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SpuRepository {
    private static final Map<String, String> SPU_MAP;

    static {
        SPU_MAP = new HashMap<>();
        SPU_MAP.put("SPU20230718001", "联想拯救者");
        SPU_MAP.put("SPU20230718002", "小米数字版");
        SPU_MAP.put("SPU20230718003", "Iphone系列");
        SPU_MAP.put("SPU20230718004", "索尼游戏机");
    }

    public String findByCode(String code) {
        return SPU_MAP.get(code);
    }

    public Map<String, String> findAll() {
        return SPU_MAP;
    }
}
