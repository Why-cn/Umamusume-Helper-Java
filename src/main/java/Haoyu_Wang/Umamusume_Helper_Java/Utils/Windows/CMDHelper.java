package Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 执行Windows命令行命令的工具类。
 */
public class CMDHelper {
    static Runtime runtime = Runtime.getRuntime();

    /**
     * 单次执行Windows命令行命令并返回控制台输出。<p>
     * ！请注意，这个线程不等到执行完毕是不会退出的！
     *
     * @param commandLine Windows 命令行
     * @return 控制台输出，以"/n"为换行符
     */
    public static String oneTimeExecute(String commandLine) {
        String line;
        StringBuffer outputBuffer = new StringBuffer();
        try {
            Process process = runtime.exec(commandLine);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            while ((line = bufferedReader.readLine()) != null) {
                outputBuffer.append(line + "\n");
            }

            // 这里可能会造成死锁
            process.waitFor();
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return outputBuffer.toString();
    }

    public static CMDThread newThreadExecute(String commandLine) {
        return new CMDThread(commandLine);
    }
}
