package home;

import eu.hansolo.medusa.*;
import eu.hansolo.medusa.skins.ModernSkin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static Merac readThread;
    @FXML
    private VBox vB;
    @FXML
    private AnchorPane ancPane;
    Gauge gauge = new Gauge();

    private Merac merac;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vB.setBackground(Background.EMPTY);
        ancPane.setBackground(Background.EMPTY);
        gauge.setSkin(new ModernSkin(gauge));
        gauge.setTitle("Air Quality");
        gauge.setUnit("PPM");
        gauge.setDecimals(0);
        gauge.setValueColor(Color.WHITE);
        gauge.setTitleColor(Color.WHITE);
        gauge.setSubTitleColor(Color.WHITE);
        gauge.setBarColor(Color.rgb(0, 214, 215));
        gauge.setNeedleColor(Color.WHITE);
        gauge.setThresholdColor(Color.rgb(204, 0, 0));
        gauge.setTickLabelColor(Color.rgb(151, 151, 151));
        gauge.setTickMarkColor(Color.BLACK);
        gauge.setTickLabelOrientation(TickLabelOrientation.ORTHOGONAL);
        gauge.setThreshold(125);
        gauge.setInteractive(true);
        gauge.setButtonTooltipText("ON");
        gauge.setOnButtonReleased(buttonEvent -> startReading());
        gauge.setMaxMeasuredValue(999);
        vB.getChildren().add(gauge);
    }

    public void startReading(){
        merac = new Merac((Gauge)vB.getChildren().get(0));
        Thread read = new Merac(gauge);
        readThread = (Merac)read;
        read.start();
    }
}
