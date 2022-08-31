package Haoyu_Wang.Umamusume_Helper_Java.Utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;


/**
 * @apiNote 测试：意图实现可以定向选中某个Windows窗口并截图
 */
public class TestUtilOne {
    int hWnd = User32.instance.FindWindowA(null, "Minesweeper X");

    public static interface User32 extends StdCallLibrary {

        public static interface WndEnumProc extends StdCallLibrary.StdCallCallback {
            boolean callback(int hWnd, int lParam);
        }

        final User32 instance = (User32) Native.loadLibrary("user32", User32.class);

        int FindWindowA(String winClass, String title);
    }

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



}
