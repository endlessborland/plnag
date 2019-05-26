package ru.mirea.skorobogatov.plang;

import java.util.HashMap;

public class Memory {
    public HashMap<String, Integer> varValueMap = new HashMap<String, Integer>();

    public void addVar(String string) {
        varValueMap.put(string, 0);
    }

    public void putVar(String string, Integer value) {
        varValueMap.put(string, value);
    }

    public Integer getVar(String string) {
        return varValueMap.get(string);
    }

    public void deleteMap() {
        varValueMap.clear();
    }
}
