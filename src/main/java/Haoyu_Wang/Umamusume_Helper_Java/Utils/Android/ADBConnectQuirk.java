package Haoyu_Wang.Umamusume_Helper_Java.Utils.Android;

public class ADBConnectQuirk {
    public static String withReadableReply(String AndroidDeviceIP, String AndroidDevicePort) {
        StringBuffer stringBuffer = new StringBuffer();
        // 查看ADB连接情况
        if (!ScrcpyHelper.isADBDevicesEmpty()) {
            ScrcpyHelper.adbDisconnectAll();
            stringBuffer.append("已有多个设备连接到ADB，正在将其全部断连……\n");
        }
        switch (ScrcpyHelper.adbConnect(AndroidDeviceIP, AndroidDevicePort)) {
            case 0 -> stringBuffer.append("设备已成功连接。");
            case 1 -> stringBuffer.append("错误的端口号或者需要认证。");
            case 2 -> stringBuffer.append("无法连接，查看网络问题。");
            case -1 -> stringBuffer.append("未知问题。");
        }
        return stringBuffer.toString();
    }
}
