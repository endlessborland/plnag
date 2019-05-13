import java.util.Set;

public class VARReplacer {

    public static String replaceString(String string) {
        Set<String> keys = Memory.varValueMap.keySet();
        for (String key: keys) {
            string = string.replace(key, Memory.varValueMap.get(key).toString());
        }
        return string;
    }
}
