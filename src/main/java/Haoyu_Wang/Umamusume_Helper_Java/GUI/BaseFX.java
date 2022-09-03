package Haoyu_Wang.Umamusume_Helper_Java.GUI;

import Haoyu_Wang.Umamusume_Helper_Java.PropertiesStorage;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.PropertiesLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class BaseFX extends Application {

    protected int defaultWidth;
    protected int defaultHeight;
    /**
     * 短边框宽度
     */
    private final int thinBorder = 5;
    /**
     * 预留控件宽度
     */
    private final int thiccBorder = 300;

    public static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        // 加载默认窗口大小
        PropertiesLoader.load();
        defaultWidth = PropertiesStorage.DefaultWidth;
        defaultHeight = PropertiesStorage.DefaultHeight;

        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Base.fxml"));

        // Scene(parent, 宽=缺省宽度+短边框宽度+预留控件宽度, 高=缺省高度+短边框宽度*2)
        scene = new Scene(parent, defaultWidth + thinBorder + thiccBorder, defaultHeight + thinBorder * 2);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle("Umamusume Helper Project");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(BaseFX.class, args);
    }

}
