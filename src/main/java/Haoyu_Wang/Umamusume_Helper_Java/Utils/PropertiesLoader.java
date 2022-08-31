package Haoyu_Wang.Umamusume_Helper_Java.Utils;

import Haoyu_Wang.Umamusume_Helper_Java.PropertiesStorage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取config.properties的工具类。
 */
public class PropertiesLoader {
    /**
     * @apiNote 读取config.properties文件并将其中的键值对读取到PropertiesStorage中进行存储。</p>
     * 将文件读取到内存中。
     */
    public static void load() {
        Properties properties = new Properties();
        InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            // 我不认为这个报错会出现
            e.printStackTrace();
        }

        PropertiesStorage.AndroidDeviceName = properties.getProperty("AndroidDeviceName");

        String WinGUIScaling = properties.getProperty("WinGUIScaling");
        if (WinGUIScaling != null) {
            PropertiesStorage.WinGUIScaling = Double.parseDouble(WinGUIScaling);
        }
    }
}
