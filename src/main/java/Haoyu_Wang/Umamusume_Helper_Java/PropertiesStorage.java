package Haoyu_Wang.Umamusume_Helper_Java;

/**
 * 在内存中储存重要属性信息的类。
 */
public class PropertiesStorage {
    /**
     * @implNote 安卓设备名
     */
    public static String AndroidDeviceName = null;

    /**
     * @implNote Windows GUI 缩放系数
     */
    public static double WinGUIScaling = 1.0;

    /**
     * @implNote 安卓设备无线调试IP
     */
    public static String AndroidDeviceIP = null;

    /**
     * @implNote 安卓设备无线调试端口
     */
    public static String AndroidDevicePort = null;

    /**
     * @implNote Scrcpy-win64 文件夹绝对路径
     * @implSpec 才疏学浅没办法整合到jar包里
     */
    public static String ScrcpyPath = null;

    /**
     * @implNote 传输画面FPS上限
     */
    public static int MaxFPS = 60;

    /**
     * @implNote 传输画面长边最大像素，短边自动计算来保持原画面比例，设为0来投射原生大小
     */
    public static int LongestScreenSize = 0;

    /**
     * @implNote 安卓设备在Scrcpy运行时是否关闭屏幕
     */
    public static boolean ScreenOff = true;

    /**
     * @implNote Scrcpy窗口是否无边框化
     */
    public static boolean Borderless = true;

    /**
     * @implNote Scrcpy窗口的自定义窗口标题，默认为安卓设备名
     */
    public static String ScrcpyTitle;

    /**
     * @implNote Scrcpy窗口在电脑屏幕上的位置X
     * @deprecated 懒了，要么就固定在左上要么在中间，这个并没有实际的作用，只是一个标志位，设为-1固定在左上
     */
    public static int ScrcpyWindowX = 0;

    /**
     * @implNote Scrcpy窗口在电脑屏幕上的位置Y
     * @deprecated 懒了，这个比X那个还没用
     */
    public static int ScrcpyWindowY = 0;

    /**
     * @implNote 截图间隔（毫秒）
     */
    public static int CapturingIntervalMM = 1000;
}
