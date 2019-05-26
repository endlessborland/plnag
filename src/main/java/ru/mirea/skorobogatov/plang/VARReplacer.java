package ru.mirea.skorobogatov.plang;

import java.util.Set;

public class VARReplacer {

    public static String replaceString(String string, Memory memory) {
        Set<String> keys = memory.varValueMap.keySet();
        for (String key: keys) {
            string = string.replace(key, memory.varValueMap.get(key).toString());
        }
        return string;
    }
}
