package com.flz.excel.solution.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StorageRepository {
    private static final Map<String, String> STORAGE_MAP;

    static {
        STORAGE_MAP = new HashMap<>();
        STORAGE_MAP.put("sto001", "成都仓库");
        STORAGE_MAP.put("sto002", "香港仓库");
        STORAGE_MAP.put("sto003", "上海仓库");
        STORAGE_MAP.put("sto004", "北京仓库");
    }

    public String findById(String id) {
        return STORAGE_MAP.get(id);
    }

    public Map<String, String> findAll() {
        return STORAGE_MAP;
    }
}
