package Zim.common;

import java.util.HashMap;

/**
 * Created by Laxton-Joe on 2017/2/10.
 */
public class SysConfigUtil {
    private static final HashMap<String, String> settingMap = new HashMap<String, String>();

    public static String getSetting(String key) {
        return settingMap.get(key).toString();
    }

    public static void setSetting(String k, String v) {
        settingMap.put(k, v);
    }
}
