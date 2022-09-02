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
            e.printStackTrace();
        }

        PropertiesStorage.AndroidDeviceName = properties.getProperty("AndroidDeviceName");

        String winGUIScaling = properties.getProperty("WinGUIScaling");
        if (winGUIScaling != null) {
            PropertiesStorage.WinGUIScaling = Double.parseDouble(winGUIScaling);
        }

        PropertiesStorage.AndroidDeviceIP = properties.getProperty("AndroidDeviceIP");

        PropertiesStorage.AndroidDevicePort = properties.getProperty("AndroidDevicePort");

        PropertiesStorage.ScrcpyPath = properties.getProperty("ScrcpyPath");

        String MaxFPS = properties.getProperty("MaxFPS");
        if (MaxFPS != null) {
            PropertiesStorage.MaxFPS = Integer.parseInt(MaxFPS);
        }

        String LongestScreenSize = properties.getProperty("LongestScreenSize");
        if (LongestScreenSize != null && !LongestScreenSize.isBlank()) {
            PropertiesStorage.LongestScreenSize = Integer.parseInt(LongestScreenSize);
        }

        String ScreenOff = properties.getProperty("ScreenOff");
        if (ScreenOff != null && !ScreenOff.isBlank()) {
            PropertiesStorage.ScreenOff = Boolean.parseBoolean(ScreenOff);
        }

        String Borderless = properties.getProperty("Borderless");
        if (Borderless != null && !Borderless.isBlank()) {
            PropertiesStorage.Borderless = Boolean.parseBoolean(Borderless);
        }

        String ScrcpyTitle = properties.getProperty("ScrcpyTitle");
        if (ScrcpyTitle != null) {
            if (!ScrcpyTitle.isBlank()) {
                PropertiesStorage.ScrcpyTitle = ScrcpyTitle;
            }
        }

        String ScrcpyWindowX = properties.getProperty("ScrcpyWindowX");
        if (ScrcpyWindowX != null && !ScrcpyWindowX.isBlank()) {
            PropertiesStorage.ScrcpyWindowX = Integer.parseInt(ScrcpyWindowX);
        }

        String ScrcpyWindowY = properties.getProperty("ScrcpyWindowY");
        if (ScrcpyWindowY != null && !ScrcpyWindowY.isBlank()) {
            PropertiesStorage.ScrcpyWindowY = Integer.parseInt(ScrcpyWindowY);
        }

        String CapturingIntervalMM = properties.getProperty("CapturingIntervalMM");
        if (CapturingIntervalMM != null && !CapturingIntervalMM.isBlank()) {
            PropertiesStorage.CapturingIntervalMM = Integer.parseInt(CapturingIntervalMM);
        }
    }
}
