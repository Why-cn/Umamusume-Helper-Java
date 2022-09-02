package Haoyu_Wang.Umamusume_Helper_Java.Utils.Android;

import Haoyu_Wang.Umamusume_Helper_Java.PropertiesStorage;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows.CMDHelper;

/**
 * 连接手机并进行实时屏幕抓取的工具类。
 *
 * @author Haoyu_Wang
 * @implNote 使用了Genymobile的scrcpy实现。
 * @see <a href="https://github.com/Genymobile/scrcpy">Genymobile/scrcpy</a>
 */
public class ScrcpyHelper {

    /**
     * 根据PropertiesStorage中提供的AndroidDeviceIP和AndroidDevicePort，使用ScrcpyPath中提供的adb.exe无线连接安卓设备。
     *
     * @return 0 ：成功连接或已连接<p>
     * 1：错误的端口号或者需要认证<p>
     * 2：无法连接，查看网络问题<p>
     * -1：未知问题
     */
    public static int adbConnect() {
        String output = CMDHelper.oneTimeExecute(PropertiesStorage.ScrcpyPath +
                "\\adb connect " +
                PropertiesStorage.AndroidDeviceIP +
                ":" +
                PropertiesStorage.AndroidDevicePort);
        if (output.contains("connected")) {
            return 0;
        } else if (output.contains("10061")) {
            // 输出：由于目标计算机积极拒绝，无法连接。问题：错误的端口号或者需要认证。
            return 1;
        } else if (output.contains("10060")) {
            // 输出：由于连接方在一段时间后没有正确答复或连接的主机没有反应，连接尝试失败。问题：无法连接。
            return 2;
        }
        return -1;
    }

    /**
     * 查看是否当前ADB连接的设备数为0。<p>
     * 当系统中有多个ADB运行时，可能之前已经有ADB连接过其他设备；此举确保通过adb disconnect命令能够将它们全部断连。
     *
     * @return 是否当前ADB连接的设备数为0
     */
    public static boolean isADBDevicesEmpty() {
        String output = CMDHelper.oneTimeExecute(PropertiesStorage.ScrcpyPath + "\\adb devices");
        output = output.replace("List of devices attached\n", "");
        output = output.replaceAll("\\s*", "");
        return output.equals("");
    }

    /**
     * 让ADB断连所有设备。
     */
    public static void adbDisconnectAll() {
        CMDHelper.oneTimeExecute(PropertiesStorage.ScrcpyPath + "\\adb disconnect");
    }

    /**
     * 查看是否设备还连接着ADB。防止各种原因安卓设备和计算机断连。<p>
     * ！此命令并不会检查是否有多个设备连接着ADB
     *
     * @return 指定端口号为AndroidDevicePort的设备是否仍在连接着ADB
     */
    public static boolean isAdbStillConnected() {
        String output = CMDHelper.oneTimeExecute(PropertiesStorage.ScrcpyPath + "\\adb devices");
        return output.contains(PropertiesStorage.AndroidDevicePort);
    }

    /**
     * 启动Scrcpy服务器，进行屏幕实时截取。<p>
     * 参数解释：<p>
     * --always-on-top ：Scrcpy窗口固定在所有窗口上方
     * --disable-screensaver ：安卓设备屏幕常亮？
     * --max-fps= ：传输画面FPS上限
     * --max-size= ：传输画面长边最大像素
     * --no-clipboard-autosync ：关闭Scrcpy的自动剪贴板同步，这里没啥用
     * --turn-screen-off ：立即关闭安卓设备屏幕
     * --stay-awake ：当安卓设备连接电源时，保持唤醒
     * --window-borderless ：Scrcpy窗口无边框化
     * --window-title= ：自定义Scrcpy窗口标题
     * --window-x=0 --window-y=0 :将Scrcpy窗口放在屏幕左上角
     * --window-width= ：自定义Scrcpy窗口宽度（没用）
     * --window-height= ：自定义Scrcpy窗口高度（没用）
     */
    public static void startScrcpy() {
        String scrcpyCLI = "\\scrcpy " +
                "--always-on-top " +
                "--disable-screensaver " +
                "--max-fps=" + PropertiesStorage.MaxFPS + " " +
                "--max-size=" + PropertiesStorage.LongestScreenSize + " " +
                "--no-clipboard-autosync " +
                "--stay-awake ";
        if (PropertiesStorage.ScreenOff) {
            scrcpyCLI += "--turn-screen-off ";
        }
        if (PropertiesStorage.Borderless) {
            scrcpyCLI += "--window-borderless ";
        }
        if (PropertiesStorage.ScrcpyTitle != null) {
            scrcpyCLI += "--window-title=" + PropertiesStorage.ScrcpyTitle + " ";
        }

        // 犯懒的代码
        if (PropertiesStorage.ScrcpyWindowX == -1) {
            scrcpyCLI += "--window-x=0 --window-y=0 ";
        }
        CMDHelper.oneTimeExecute(PropertiesStorage.ScrcpyPath + scrcpyCLI);
    }
}
