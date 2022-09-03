package Haoyu_Wang.Umamusume_Helper_Java.GUI;

import Haoyu_Wang.Umamusume_Helper_Java.PropertiesStorage;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.Android.ADBConnectQuirk;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.Android.ScrcpyHelper;
import Haoyu_Wang.Umamusume_Helper_Java.Utils.Windows.ScreenCapturer;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.image.BufferedImage;


public class BaseController {

    @FXML
    private ImageView capturedImageView;
    @FXML
    private TextField ipTextField;
    @FXML
    private TextField portTextField;
    @FXML
    private Button adbConnectButton;
    @FXML
    private Text adbReplyText;
    @FXML
    private Button startScrcpyButton;
    @FXML
    private Button capturingTestButton;

    public void initialize() {
        ipTextField.setText(PropertiesStorage.AndroidDeviceIP);
        portTextField.setText(PropertiesStorage.AndroidDevicePort);
        adbReplyText.setText("如果需要认证，尝试adb pair。");
    }

    @FXML
    public void adbConnect() {
        adbReplyText.setText("正在尝试adb连接……");
        String readableReply =
                ADBConnectQuirk.withReadableReply(ipTextField.getText(), portTextField.getText());
        adbReplyText.setText(readableReply);
    }

    @FXML
    public void startScrcpyServer() {
        ScrcpyHelper.startScrcpy();
    }

    @FXML
    public void capturingTest() {
        if (ScreenCapturer.init()) {
            BufferedImage bufferedImage = ScreenCapturer.capture();
            assert bufferedImage != null; // 如果是null则一定有先前报错
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            capturedImageView.setImage(image);
        }
    }

    @FXML
    public void resetSceneSize() {
//        BaseFX.scene.
    }
}
