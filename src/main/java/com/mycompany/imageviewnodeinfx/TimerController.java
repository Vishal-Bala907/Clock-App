package com.mycompany.imageviewnodeinfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TimerController implements Initializable {

    @FXML
    private Label Hour, Min, Sec, HourLabel, MinLabel, SecLabel;

    @FXML
    private TextField HourText, MinText, SecText;
    @FXML
    Button StartButton, ResetButton, PauseButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML 
    private ComboBox MYCB3;
    private String CLocks[] = { "Clock", "StopWatch" };
    private ObservableList<String> li2  =  FXCollections.observableArrayList(CLocks);

    private String HH, MM, SS;
    private int hh, mm, ss;
    private boolean Reset = false, Pause = false;
    private int TimeOverCounter;
    private String h, m, s; // PauseVariable
    //For Go Back To Watch
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void StartTimer(ActionEvent event) {
        
        Reset = false;
        Pause = false;

        HH = HourText.getText();
        MM = MinText.getText();
        SS = SecText.getText();

        hh = Integer.parseInt(HH);
        mm = Integer.parseInt(MM);
        ss = Integer.parseInt(SS) + 1;
        // Hour ->Secs + Min - > sec + sec
        TimeOverCounter = hh * 60 * 60 + mm * 60 + ss;
        // Upadating Min and Hour

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (mm > 0 && mm < 10) {
                    Platform.runLater(() -> Min.setText("0" + String.valueOf(mm)));
                } else if (mm > 9) {
                    Platform.runLater(() -> Min.setText(String.valueOf(mm)));
                } else if (mm == 0) {
                    Platform.runLater(() -> Min.setText("00"));
                }
                
                if (hh > 0 && hh < 10) {
                    Platform.runLater(() -> Hour.setText("0" + String.valueOf(hh)));
                } else if (hh > 9) {
                    Platform.runLater(() -> Hour.setText(String.valueOf(hh)));
                } else if (hh == 0) {
                    Platform.runLater(() -> Hour.setText("00"));
                }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if (ss == 0) {
                    Platform.runLater(() -> Sec.setText("00"));
                    ss = ss - 1;
                } else if (ss > 0 && ss < 11) {
                    Platform.runLater(() -> Sec.setText("0" + String.valueOf(ss)));
                    ss--;
                } else if (ss > 9) {
                    System.out.println("HEllo " + ss);
                    Platform.runLater(() -> Sec.setText(String.valueOf(ss)));
                    ss--;
                } // Changing the Min and Sec Here
                if (ss < 0) {

                    // Changing the Min
                    if (mm > 0) {
                        
                        ss = 59;
                        if (mm < 10 && mm > 0) {
                            mm--;
                            Platform.runLater(() -> Min.setText("0" + String.valueOf(mm)));
                            Platform.runLater(() -> Sec.setText(String.valueOf(ss)));
                        } else if (mm > 9) {
                            mm--;
                            Platform.runLater(() -> Min.setText(String.valueOf(mm)));
                            Platform.runLater(() -> Sec.setText(String.valueOf(ss)));
                        } else if (mm == 0) {
                            if (hh == 0) {
                                Reset = true;
                            } else if (hh > 0) {
                                hh--;
                                if (hh == 0) {
                                    mm = 59;
                                    ss = 59;
                                    Platform.runLater(() -> Hour.setText("0" + String.valueOf(hh)));
                                    Platform.runLater(() -> Min.setText(String.valueOf(mm)));
                                    Platform.runLater(() -> Sec.setText(String.valueOf(ss)));
                                } else if (hh < 10 && hh > 0) {
                                    mm = 59;
                                    ss = 59;
                                    System.out.println("Hello");
                                    Platform.runLater(() -> Hour.setText("0" + String.valueOf(hh)));
                                    Platform.runLater(() -> Min.setText(String.valueOf(mm)));
                                    Platform.runLater(() -> Sec.setText(String.valueOf(ss)));
                                } else if (hh >= 10) {
                                    mm = 59;
                                    ss = 59;
                                    Platform.runLater(() -> Hour.setText(String.valueOf(hh)));
                                    Platform.runLater(() -> Min.setText(String.valueOf(mm)));
                                    Platform.runLater(() -> Sec.setText(String.valueOf(ss)));
                                }
                            }
                        }

                    }
                    else if(mm==0){
                                if (hh == 0) {
                                    Reset = true;
                                } else if (hh < 10 && hh > 0) {
                                    hh--;
                                    mm = 59;
                                    ss = 59;
                                    System.out.println("Hello");
                                    Platform.runLater(() -> Hour.setText("0" + String.valueOf(hh)));
                                    Platform.runLater(() -> Min.setText(String.valueOf(mm)));
                                    Platform.runLater(() -> Sec.setText(String.valueOf(ss)));
                                } else if (hh >= 10) {
                                    hh--;
                                    mm = 59;
                                    ss = 59;
                                    Platform.runLater(() -> Hour.setText(String.valueOf(hh)));
                                    Platform.runLater(() -> Min.setText(String.valueOf(mm)));
                                    Platform.runLater(() -> Sec.setText(String.valueOf(ss)));
                                }
                    }
                }

//                    ss--;
                if (Reset) {
                    this.cancel();
                    // Setting the timer to initial State
                    System.out.println("Reset");
                    Platform.runLater(() -> Sec.setText("00"));
                    Platform.runLater(() -> Min.setText("00"));
                    Platform.runLater(() -> Hour.setText("00"));

                    Platform.runLater(() -> HourText.setText("00"));
                    Platform.runLater(() -> MinText.setText("00"));
                    Platform.runLater(() -> SecText.setText("30"));
                    //  ss++;
                }
                if (Pause) {
                    this.cancel();
                    h = Hour.getText();
                    m = Min.getText();
                    s = Sec.getText();
                    Platform.runLater(() -> HourText.setText(h));
                    Platform.runLater(() -> MinText.setText(m));
                    Platform.runLater(() -> SecText.setText(s));

                    Platform.runLater(() -> Hour.setText(h));
                    Platform.runLater(() -> Min.setText(m));
                    Platform.runLater(() -> Sec.setText(s));
                }

            }
        };

        timer.schedule(task, 100, 1000);
    }

    public void ResetTimer(ActionEvent event) {

        Reset = true;

    }

    public void PauseTimer(ActionEvent event) {
        Pause = true;
    }

    public void SetLabel(ActionEvent event) {
//        Hour.setText(HourText.getText());
    }

    public void WatchAgain(ActionEvent event) throws IOException {
        
        if(MYCB3.getValue() == "StopWatch"){
        root = FXMLLoader.load(getClass().getResource("StopWatchFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        }
        else if(MYCB3.getValue() == "Clock")
        {
            root = FXMLLoader.load(getClass().getResource("primary.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MYCB3.setItems(li2);
        ResetButton.setCursor(Cursor.OPEN_HAND);
        StartButton.setCursor(Cursor.OPEN_HAND);
        //Setting The Stage Background Color (AnchorPane)
        /* RED*/
        Background red = new Background(new BackgroundFill(Color.rgb(245, 68, 59), null, null));
        /* Yellow*/
        Background yellow = new Background(new BackgroundFill(Color.rgb(247, 232, 10), null, null));
        /* Green*/
        Background green = new Background(new BackgroundFill(Color.rgb(10, 201, 80), null, null));
        Platform.runLater(() -> anchorPane.setBackground(new Background(new BackgroundFill(Color.PURPLE, null, null))));
        // Setting The Button Color
        StartButton.setBackground(green);
        ResetButton.setBackground(red);
        PauseButton.setBackground(yellow);

        // Changing label Text
        Hour.setTextFill(Color.WHITE);
        Min.setTextFill(Color.WHITE);
        Sec.setTextFill(Color.WHITE);
        HourLabel.setTextFill(Color.WHITE);
        MinLabel.setTextFill(Color.WHITE);
        SecLabel.setTextFill(Color.WHITE);

        // Glow  and Blur Effects For Buttons
        Glow glow = new Glow(.85);
        GaussianBlur blur = new GaussianBlur(3);

        // For Button Hover or Mouse Enterd Over Buttons for ResetButton
        EventHandler<MouseEvent> haldler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                glow.setLevel(.85);
                blur.setRadius(3);
                if (t.getSource() == StartButton) {
                    StartButton.setFont(Font.font("Comic sans", FontWeight.BOLD, 12));
                    StartButton.setEffect(glow);
                    StartButton.setEffect(blur);

                } else if (t.getSource() == ResetButton) {
                    ResetButton.setFont(Font.font("Comic sans", FontWeight.BOLD, 12));
                    ResetButton.setEffect(glow);
                    ResetButton.setEffect(blur);

                } else if (t.getSource() == PauseButton) {

                    PauseButton.setFont(Font.font("Comic sans", FontWeight.BOLD, 12));
                    PauseButton.setEffect(glow);
                    PauseButton.setEffect(blur);

                }
            }
        };
//         For Button Hover or Mouse Enterd Over Buttons for StartButton
        EventHandler<MouseEvent> haldler_Remove = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getSource() == PauseButton) {
                    PauseButton.setFont(Font.font("System", FontWeight.NORMAL, 12));
                    PauseButton.setEffect(new Glow(1));
                    PauseButton.setEffect(new GaussianBlur(0));
                } else if (t.getSource() == ResetButton) {
                    ResetButton.setFont(Font.font("System", FontWeight.NORMAL, 12));
                    ResetButton.setEffect(new Glow(1));
                    ResetButton.setEffect(new GaussianBlur(0));
                } else if (t.getSource() == StartButton) {
                    StartButton.setFont(Font.font("System", FontWeight.NORMAL, 12));
                    StartButton.setEffect(new Glow(1));
                    StartButton.setEffect(new GaussianBlur(0));
                }
            }
        };
        
        ResetButton.setOnMouseEntered(haldler);
        StartButton.setOnMouseEntered(haldler);
        PauseButton.setOnMouseEntered(haldler);
        ResetButton.setOnMouseExited(haldler_Remove);
        StartButton.setOnMouseExited(haldler_Remove);
        PauseButton.setOnMouseExited(haldler_Remove);
        
             MYCB3.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle(""); // Reset the style
                } else {
                    setText(item);
                    setStyle("-fx-background-color: #ecbcf7; -fx-border-color: #9c2d6a; -fx-border-width: 2px; -fx-border-radius: 15px;"
                    );
                }
            }
        });
    }

}
