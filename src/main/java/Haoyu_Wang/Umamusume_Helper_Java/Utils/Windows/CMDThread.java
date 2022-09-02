package Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CMDThread extends Thread {
    private String commandLine;
    private StringBuffer outputBuffer = new StringBuffer();
    private String line;
    private Integer exitStatus = null;

    public CMDThread(String commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public void run() {
        try {
            Process process = CMDHelper.runtime.exec(this.commandLine);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            while ((line = bufferedReader.readLine()) != null) {
                outputBuffer.append(line + "\n");
            }

            // 这里应该不会造成死锁
            exitStatus = process.waitFor();
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public String getOutput() {
        return outputBuffer.toString();
    }

    public Integer getExitStatus() {
        return exitStatus;
    }
}
