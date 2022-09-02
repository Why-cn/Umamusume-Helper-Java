package Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows;

import Haoyu_Wang.Umamusume_Helper_Java.PropertiesStorage;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ScreenCapturer {
    static int hWnd = -1;
    static WindowInfo w;
    static Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
    // 截图监控窗口纵向冗余长度（像素），用于让智障JPanel能够显示完全图像
    static int extraLength = 40;

    /**
     * 初始化屏幕截取功能，初始化后获得要截取的窗口的句柄号和窗口大小信息。
     *
     * @return true: 初始化成功 false: 初始化失败
     */
    public static boolean init() {
        // 第一步：按照设定的窗口标题找到该窗口的窗口句柄号hWnd
        if (PropertiesStorage.ScrcpyTitle != null) {
            hWnd = User32.instance.FindWindowA(null, PropertiesStorage.ScrcpyTitle);
        } else if (PropertiesStorage.AndroidDeviceName != null) {
            hWnd = User32.instance.FindWindowA(null, PropertiesStorage.AndroidDeviceName);
        }

        // 第二步：获取窗口句柄号为hWnd的窗口的窗口信息
        if (hWnd != -1) {
            w = getWindowInfo(hWnd);
            User32.instance.SetForegroundWindow(w.hwnd);
            return true;
        }
        return false;
    }

    /**
     * 根据初始化之后的数据对屏幕固定区域进行截取，要求该窗口处于桌面上层。
     *
     * @return 以BufferedImage类进行存储的截图内容
     */
    public static BufferedImage capture() {
        try {
            return new Robot().createScreenCapture(new Rectangle(w.rect.left, w.rect.top, w.rect.right - w.rect.left, w.rect.bottom - w.rect.top));
        } catch (AWTException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 一个测试程序，用于监测BufferedImage的成像内容以及质量，并将窗口置于桌面右上角。<p>
     * 智障JPanel显示区域居然还包括标题，因此不得不加了一个extraLength来让截图下方也能够显示出来。<p>
     * 为后续的工作提供灵感。
     *
     * @param bufferedImage 要进行成像的BufferedImage
     */
    public static void showImage(BufferedImage bufferedImage) {
//        System.out.println("截取图像大小： " + bufferedImage.getWidth() + " * " + bufferedImage.getHeight());
        JFrame frame = new JFrame("Captured Image");
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bufferedImage, 0, 0, null);
            }
        };
        frame.add(panel);
        frame.setLocation((int) (displaySize.getWidth() - bufferedImage.getWidth()), 0);
        frame.setSize(bufferedImage.getWidth(), bufferedImage.getHeight() + extraLength);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @see <a href="https://stackoverflow.com/questions/464593/how-to-capture-selected-screen-of-other-application-using-java">how-to-capture-selected-screen-of-other-application-using-java</a>
     */
    public static WindowInfo getWindowInfo(int hWnd) {
        RECT r = new RECT();
        User32.instance.GetWindowRect(hWnd, r);
        byte[] buffer = new byte[1024];
        User32.instance.GetWindowTextA(hWnd, buffer, buffer.length);
        String title = Native.toString(buffer);

        // 修正Windows GUI缩放导致的截图偏位
        r.left = (int) (r.left / PropertiesStorage.WinGUIScaling);
        r.right = (int) (r.right / PropertiesStorage.WinGUIScaling);
        r.top = (int) (r.top / PropertiesStorage.WinGUIScaling);
        r.bottom = (int) (r.bottom / PropertiesStorage.WinGUIScaling);

        return new WindowInfo(hWnd, r, title);
    }

    /**
     * @see <a href="https://stackoverflow.com/questions/464593/how-to-capture-selected-screen-of-other-application-using-java">how-to-capture-selected-screen-of-other-application-using-java</a>
     */
    public static interface WndEnumProc extends StdCallLibrary.StdCallCallback {
        boolean callback(int hWnd, int lParam);
    }

    /**
     * @see <a href="https://stackoverflow.com/questions/464593/how-to-capture-selected-screen-of-other-application-using-java">how-to-capture-selected-screen-of-other-application-using-java</a>
     */
    public interface User32 extends StdCallLibrary {

        final User32 instance = (User32) Native.loadLibrary("user32", User32.class);

        boolean EnumWindows(WndEnumProc wndenumproc, int lParam);

        boolean IsWindowVisible(int hWnd);

        int GetWindowRect(int hWnd, RECT r);

        void GetWindowTextA(int hWnd, byte[] buffer, int buflen);

        int GetTopWindow(int hWnd);

        int GetWindow(int hWnd, int flag);

        boolean ShowWindow(int hWnd);

        boolean BringWindowToTop(int hWnd);

        int GetActiveWindow();

        boolean SetForegroundWindow(int hWnd);

        int FindWindowA(String winClass, String title);

        long SendMessageA(int hWnd, int msg, int num1, int num2);

        final int GW_HWNDNEXT = 2;
    }

    /**
     * @see <a href="https://stackoverflow.com/questions/464593/how-to-capture-selected-screen-of-other-application-using-java">how-to-capture-selected-screen-of-other-application-using-java</a>
     */
    public static class RECT extends Structure {
        public int left, top, right, bottom;

        @Override
        protected List<String> getFieldOrder() {
            List<String> order = new ArrayList<>();
            order.add("left");
            order.add("top");
            order.add("right");
            order.add("bottom");
            return order;
        }
    }

    /**
     * @see <a href="https://stackoverflow.com/questions/464593/how-to-capture-selected-screen-of-other-application-using-java">how-to-capture-selected-screen-of-other-application-using-java</a>
     */
    public static class WindowInfo {
        int hwnd;
        RECT rect;
        String title;

        public WindowInfo(int hwnd, RECT rect, String title) {
            this.hwnd = hwnd;
            this.rect = rect;
            this.title = title;
        }

        public String toString() {
            return String.format("(%d,%d)-(%d,%d) : \"%s\"", rect.left, rect.top, rect.right, rect.bottom, title);
        }
    }
}
