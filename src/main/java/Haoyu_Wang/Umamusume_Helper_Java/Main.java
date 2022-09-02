package Haoyu_Wang.Umamusume_Helper_Java;

import Haoyu_Wang.Umamusume_Helper_Java.Utils.Android.ScrcpyHelper;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.PropertiesLoader;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows.CMDHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // 加载config.properties
        PropertiesLoader.load();

        // 查看ADB连接情况
        if (!ScrcpyHelper.isADBDevicesEmpty()) {
            ScrcpyHelper.adbDisconnectAll();
        }
        switch (ScrcpyHelper.adbConnect()) {
            case 0 -> ScrcpyHelper.adbConnect();
            case 1 -> System.out.println("错误的端口号或者需要认证。");
            case 2 -> System.out.println("无法连接，查看网络问题。");
            case -1 -> System.out.println("未知问题。");
        }

        ScrcpyHelper.startScrcpy();

    }
}