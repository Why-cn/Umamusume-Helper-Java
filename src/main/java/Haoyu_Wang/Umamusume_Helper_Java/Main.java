package Haoyu_Wang.Umamusume_Helper_Java;

import Haoyu_Wang.Umamusume_Helper_Java.Utils.Android.ScrcpyHelper;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.PropertiesLoader;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows.ImageMonitor;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows.ScreenCapturer;

import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) throws InterruptedException {
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

        Thread.sleep(2000);

        if (ScreenCapturer.init()) {
            BufferedImage bufferedImage = ScreenCapturer.capture();
            assert bufferedImage != null; // 如果是null则一定有先前报错
            ImageMonitor imageMonitor = new ImageMonitor(bufferedImage);
            imageMonitor.start();
        }

    }
}