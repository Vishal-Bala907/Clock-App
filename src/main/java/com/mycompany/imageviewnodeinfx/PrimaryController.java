package com.mycompany.imageviewnodeinfx;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    @FXML
    ImageView IVA;
    Image im1 = new Image(App.class .getResource("/AMPM/AW.png").toExternalForm());
    Image im2 = new Image(App.class .getResource("/AMPM/AY.png").toExternalForm());
    Image im3 = new Image(App.class .getResource("/AMPM/PW.png").toExternalForm());
    Image im4 = new Image(App.class .getResource("/AMPM/PY.png").toExternalForm());

    @SuppressWarnings("exports")
	@FXML
    public Label Hour, Min, Sec,DateLable;
    @FXML 
    private ComboBox<String> TimeFormat,Clocks;
    private String formats[] = { "12 Hour Format", "24 Hour Format" };
    private String CLocks[] = { "Timer", "StopWatch" };
    private ObservableList<String> li  =  FXCollections.observableArrayList(formats);
    private ObservableList<String> li2  =  FXCollections.observableArrayList(CLocks);
//    @FXML
    @SuppressWarnings("exports")
	public Button BT= new Button("12 Hour");
    @SuppressWarnings("exports")
	public Button BT2 = new Button("24 Hour");
    public String s = "Vishal bala";
    private Date date;
    private Calendar calendar;
    private String CurrentDate;
    private String[] Dust, DateArray;
    private int TimeState = 0; // 1 = 12 hour , 2 = 24 hour

// For Switching Scenes
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button T, T2, StopWatchButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        TimeFormat.setItems(li);
        Clocks.setItems(li2);
        Timer timer = new Timer();
        TimerTask Task = new TimerTask() {
            @Override
            public void run() {
                date = new Date();
                Dust = date.toString().split("\\s");
                CurrentDate = Dust[3];
//                System.out.println(Dust[0]+Dust[1]+Dust[2]+Dust[Dust.length-1]);
                DateArray = CurrentDate.split("\\:");
                Platform.runLater(() -> Hour.setText(DateArray[0]));
                Platform.runLater(() -> Min.setText(DateArray[1]));
                Platform.runLater(() -> Sec.setText(DateArray[2]));
                // Upadting Date
                 Platform.runLater(() -> DateLable.setText(Dust[0]+" - "+Dust[1]+" - "+Dust[2]+" - "+Dust[Dust.length-1]));
                SetAmPm(DateArray[0], DateArray[2]);
//                System.out.println(CurrentDate);
//                System.out.println(TimeState);
                if (TimeState == 1) {
                    Set12HourFormat(DateArray[0], DateArray[1], DateArray[2]);
                }
            }
        };
        timer.scheduleAtFixedRate(Task, 1000, 1000);
        
        Clocks.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle(""); // Reset the style
                } else {
                    setText(item);
                    setStyle("-fx-background-color: #d0bbfc;  -fx-border-radius: 15px; -fx-border-color: #097187; -fx-border-width: 2PX;"
                    );
                }
            }
        });
        TimeFormat.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle(""); // Reset the style
                } else {
                    setText(item);
                    setStyle("-fx-background-color: #d0bbfc;  -fx-border-radius: 15px; -fx-border-color: #097187; -fx-border-width: 2PX;"
                    );
                }
            }
        });
    }

    public void SetAmPm(String H, String S) {
        IVA.setImage(im1);

        if (Integer.parseInt(H) > 12) {
            IVA.setImage(im4);
        } else {
            IVA.setImage(im2);
        }

    }

    public void Set12HourFormat(String hour, String minute, String second) {
        int H;
        H = Integer.parseInt(hour);
        if (H > 12) {
            H = H % 12;
        }
        String hr = String.valueOf(H);
        if (H > 9) {
            Platform.runLater(() -> Hour.setText(hr));
            Platform.runLater(() -> Min.setText(minute));
            Platform.runLater(() -> Sec.setText(second));
        } else {
            Platform.runLater(() -> Hour.setText("0" + hr));
            Platform.runLater(() -> Min.setText(minute));
            Platform.runLater(() -> Sec.setText(second));
        }

    }
    public void SetTimeFormat(ActionEvent actionEvent) {
        if(TimeFormat.getValue() == "12 Hour Format"){
            TimeState = 1;}
        else if(TimeFormat.getValue() == "24 Hour Format"){
            TimeState = 2;
        }
            
    }
    

    public void SwitchBetween(ActionEvent event) throws IOException {
        
        if(Clocks.getValue() == "Timer"){
        root = FXMLLoader.load(getClass().getResource("hello.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
            }
        else if(Clocks.getValue() == "StopWatch"){
        root = FXMLLoader.load(getClass().getResource("StopWatchFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    }
}
