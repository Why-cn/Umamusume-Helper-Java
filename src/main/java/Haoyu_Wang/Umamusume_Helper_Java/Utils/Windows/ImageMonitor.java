package Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows;

import Haoyu_Wang.Umamusume_Helper_Java.PropertiesStorage;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.Android.ScrcpyHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows.ScreenCapturer.displaySize;
import static Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows.ScreenCapturer.extraLength;

public class ImageMonitor extends Thread {
    String JFrameTitle = "Captured Images Monitor";
    JFrame frame = new JFrame(JFrameTitle);
    BufferedImage capturedImage;

    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(capturedImage, 0, 0, null);
        }
    };

    /**
     * 构造一个用于监视BufferedImage成像的JFrame线程，位置及大小由手动设置。
     *
     * @param locationX JFrame在屏幕的X位置
     * @param locationY JFrame在屏幕的Y位置
     * @param width     JFrame的宽度
     * @param height    JFrame的高度
     */
    public ImageMonitor(int locationX, int locationY, int width, int height) {
        frame.add(panel);
        frame.setLocation(locationX, locationY);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 构造一个用于监视BufferedImage成像的JFrame线程，位置及大小由屏幕大小及一个固定的BufferedImage自动调整。
     *
     * @param bufferedImage 用于获取BufferedImage大小的示例BufferedImage
     */
    public ImageMonitor(BufferedImage bufferedImage) {
        new ImageMonitor((int) (displaySize.getWidth() - bufferedImage.getWidth()),
                0, bufferedImage.getWidth(), bufferedImage.getHeight() + extraLength);
    }

    @Override
    public void run() {
        while (ScrcpyHelper.getServerThread() != null && ScrcpyHelper.getServerThread().getExitStatus() == null) {
            try {
                Thread.sleep(PropertiesStorage.CapturingIntervalMM);
                capturedImage = ScreenCapturer.capture();
                System.out.println("captured");
                panel.paint(capturedImage.createGraphics());
                frame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer exitStatus = ScrcpyHelper.getServerThread().getExitStatus();
        System.out.println("getExitStatus: " + (exitStatus == null ? "null" : exitStatus));
        System.exit(exitStatus == null ? 1 : exitStatus);
//        capturedImage = ScreenCapturer.capture();
//
//        frame.add(panel);
    }
}
