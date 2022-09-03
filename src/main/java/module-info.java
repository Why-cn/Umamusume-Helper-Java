module Haoyu_Wang.Umamusume_Helper_Java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.sun.jna;
    requires javafx.swing;

    exports Haoyu_Wang.Umamusume_Helper_Java;
    exports Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows;
    exports Haoyu_Wang.Umamusume_Helper_Java.GUI;
    opens Haoyu_Wang.Umamusume_Helper_Java.GUI to javafx.fxml;
}