package ru.mirea.skorobogatov.plang.Runner;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

public class VarReplacer {

    public static String replaceString(String string, HashMap<String, Integer> varMap) {
        if (varMap.isEmpty())
            return string;
        Set<String> keys = varMap.keySet();
        String r = string;
        for (String key: keys) {
            r = r.replace(key, varMap.get(key).toString());
        }
        return r;
    }
}
