package com.mycompany.imageviewnodeinfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
//import javafx.util.;

public class StopWatchController implements Initializable {

    @FXML
    private Label Seconds, Hour, Col1, Minute, Col2, PPLable, Fl, Reset;
    @FXML
    private TextArea MYLB;
    @FXML
    private AnchorPane APane;
    @FXML
    private Button PlayPauseButton, Flag, ResetB;
    
    private int Counter_Secs = 0, Mins = 0, Hr, code = 1, PlayPause = 0;
    private boolean pause = false;
    private String FlagTimne = "";
    //Effects
    private DropShadow shadow;
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private ComboBox<String> MYCB2;
    private final String CLocks[] = {"Clock", "Timer"};
    private final ObservableList<String> li2 = FXCollections.observableArrayList(CLocks);
    private boolean isReset = false;

    Image PU = new Image(App.class.getResource("/AMPM/Newpause.png").toExternalForm());
    Image PL = new Image(App.class.getResource("/AMPM/Play.png").toExternalForm());
    Image FL = new Image(App.class.getResource("/AMPM/NewFlag.png").toExternalForm());
    Image rs = new Image(App.class.getResource("/AMPM/reset.png").toExternalForm());
    public void HelloHello(ActionEvent event) throws IOException {
//        BugFix++;
        PlayPause++;
        pause = false;
        isReset = false;
        Changelayout_1(code);
        Timer timer = new Timer();
        if (PlayPause % 2 != 0 && !isReset) {
            PlayPauseButton.setGraphic(new ImageView(PU)); // setting the Play image on Button
            PPLable.setText("PAUSE"); // Setting the label text to PAUSE
            code = 0; // to stop calling the method again and again with the para 1 , when the Start button or enter pressed repeatedly By the User
            TimerTask task;
            task = new TimerTask() {
                @Override
                public void run() {
//                    isReset = true;
                    if (Counter_Secs < 9) {
                        Platform.runLater(() -> Seconds.setText("0" + String.valueOf(Counter_Secs)));
                    } else if (Counter_Secs >= 9) {
                        System.out.println(Counter_Secs);
                        Platform.runLater(() -> Seconds.setText(String.valueOf(Counter_Secs)));
                    }
                    Counter_Secs++;
                    if (Counter_Secs == 60) {
                        if (Mins == 59) {
                            Hr++;
                            Mins = 0;
                            Counter_Secs = 0;
                            Changelayout_1(3);
                            Platform.runLater(() -> Seconds.setText("00"));
                            Platform.runLater(() -> Minute.setText("00"));//+String.valueOf(Mins)));
                            if (Hr < 9) {
                                Platform.runLater(() -> Hour.setText("0" + String.valueOf(Hr)));
                            } else if (Hr >= 9) {
                                Platform.runLater(() -> Hour.setText(String.valueOf(Hr)));
                            }
                        } else { // Minutes updation
                            Counter_Secs = 0;
                            Mins++;
                            if (Hr > 0) {
//                        Changelayout_1(3);
                            } else {
                                Changelayout_1(2);
                            }
                            Platform.runLater(() -> Seconds.setText("00"));
                            if (Mins < 9) {
                                Platform.runLater(() -> Minute.setText("0" + String.valueOf(Mins)));
                            } else if (Mins >= 9) {
                                Platform.runLater(() -> Minute.setText(String.valueOf(Mins)));
                            }
                        }
                    }
                    if (pause) {
                        this.cancel();
                        Counter_Secs = Integer.parseInt(Seconds.getText());
                        Mins = Integer.parseInt(Minute.getText());
                        Hr = Integer.parseInt(Hour.getText());
                    }
                    if (isReset) {
                        this.cancel();
                        Platform.runLater(() -> PlayPauseButton.setGraphic(new ImageView(PL)));
                        Platform.runLater(() -> Seconds.setText("00"));
                        Platform.runLater(() -> Minute.setText("00"));
                        Platform.runLater(() -> Hour.setText("00"));
                        Counter_Secs = Integer.parseInt("00");
                        Mins = Integer.parseInt("00");
                        Hr = Integer.parseInt("00");
                    }

                }

            };
            timer.schedule(task, 0, 1000);
        } else if (PlayPause % 2 == 0) {
            pause = true;
            Platform.runLater(() -> PlayPauseButton.setGraphic(new ImageView(PL)));
            Platform.runLater(() -> PPLable.setText("PLAY"));
        }

    }

    public void FlagMethod(ActionEvent Event) {
        setFlagTimne(getFlagTimne() + "Time : Hour -> " + String.valueOf(Hr) + " Minutes -> " + String.valueOf(Mins) + " Seconds -> " + String.valueOf(Counter_Secs) + "\n");
        Platform.runLater(() -> MYLB.setText(getFlagTimne()));
    }

    public void Changelayout_1(int code) {
        ScaleTransition sts = new ScaleTransition();
        ScaleTransition stL1 = new ScaleTransition();
        ScaleTransition stM = new ScaleTransition();
        ScaleTransition stH = new ScaleTransition();
        ScaleTransition stL2 = new ScaleTransition();
//
        TranslateTransition TTs = new TranslateTransition();
        TranslateTransition TTL1 = new TranslateTransition();
        TranslateTransition TTL2 = new TranslateTransition();
        TranslateTransition TTM = new TranslateTransition();
        TranslateTransition TTH = new TranslateTransition();
        if (code == 1) {
            sts.setFromX(1);
            sts.setFromY(1);
            sts.setToX(5.0);
            sts.setToY(5.0);
            sts.setCycleCount(1);
            sts.setDuration(Duration.millis(200));
            sts.setNode(Seconds);
            sts.play();

            TTs.setDuration(Duration.millis(200));
            TTs.setNode(Seconds);
            TTs.setFromX(-100);
            TTs.setToX(100 + 120 + 260);
            TTs.setFromY(320);
            TTs.setToY(-80);
            TTs.play();
        } else if (code == 2) {
            TTs.setDuration(Duration.millis(200));
            TTs.setNode(Seconds);
            TTs.setFromX(420);
            TTs.setByX(140);
            TTs.play();

            stL1.setFromY(1);
            stL1.setFromX(1);
            stL1.setToY(5.0);
            stL1.setToX(5.0);
            stL1.setCycleCount(1);
            stL1.setDuration(Duration.millis(200));
            stL1.setNode(Col2);
            stL1.play();

            TTL1.setDuration(Duration.millis(200));
            TTL1.setNode(Col2);
            TTL1.setFromX(-100);
            TTL1.setFromY(280);
            TTL1.setToX(80 + 120 + 140 + 200);
            TTL1.setToY(-50);
            TTL1.play();

            stM.setFromX(1);
            stM.setFromY(1);
            stM.setToX(5.0);
            stM.setToY(5.0);
            stM.setCycleCount(1);
            stM.setDuration(Duration.millis(200));
            stM.setNode(Minute);
            stM.play();

            TTM.setDuration(Duration.millis(200));
            TTM.setNode(Minute);
            TTM.setFromX(-100);
            TTM.setToX(100 + 120 + 140);
//            TTM.setFromY(240);
//            TTM.setToY();
            TTM.play();

        } else if (code == 3) {
            TTs.setByX(100 + 20);
            TTL1.setByX(100 + 20);
            TTM.setByX(70 + 20);
            TTs.setNode(Seconds);
            TTM.setNode(Minute);
            TTL1.setNode(Col2);
            TTs.play();
            TTL1.play();
            TTM.play();

            stL2.setFromY(1);
            stL2.setFromX(1);
            stL2.setToY(5.0);
            stL2.setToX(5.0);
            stL2.setCycleCount(1);
            stL2.setDuration(Duration.millis(200));
            stL2.setNode(Col1);
            stL2.play();

            TTL2.setDuration(Duration.millis(200));
            TTL2.setNode(Col1);
            TTL2.setFromX(-100);
            TTL2.setFromY(200);
            TTL2.setToX(80 + 20 + 120 + 200);
            TTL2.setToY(30);
            TTL2.play();

            stH.setFromY(1);
            stH.setFromX(1);
            stH.setToY(5.0);
            stH.setToX(5.0);
            stH.setCycleCount(1);
            stH.setDuration(Duration.millis(200));
            stH.setNode(Hour);
            stH.play();

            TTH.setDuration(Duration.millis(200));
            TTH.setNode(Hour);
            TTH.setFromX(-100);
            TTH.setFromY(100);
            TTH.setToX(80 + 120 + 20);
            TTH.setToY(80);
            TTH.play();
        } else {
            System.out.println("LayOut Saved !");
        }

    }

    public void WatchAgain(ActionEvent event) throws IOException {

        if ("Timer".equals(MYCB2.getValue())) {
            root = FXMLLoader.load(getClass().getResource("Hello.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } else if ("Clock".equals(MYCB2.getValue())) {
            root = FXMLLoader.load(getClass().getResource("primary.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        }

    }

    public void ResetMethod(ActionEvent event) {
        isReset = true;
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MYCB2.setItems(li2);
        Platform.runLater(() -> APane.setBackground(new Background(new BackgroundFill(Color.rgb(120, 142, 250), null, null))));
        Platform.runLater(() -> PlayPauseButton.setGraphic(new ImageView(PL)));
//        Flag.setGraphic(new ImageView(new Image("C:/Users/Lenovo/Pictures/AMPM/NewFlag.png")));
        Flag.setGraphic(new ImageView(FL));
        ResetB.setGraphic(new ImageView(rs));
        //New Font
        Font f = Font.font("Forte", FontWeight.BOLD, 44.0);
        Font f2 = Font.font("System", FontWeight.NORMAL, 28.0);

        // Setting up the shadow Effect
        shadow = new DropShadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.BLACK);
        shadow.setHeight(-10);
        shadow.setWidth(50);
        shadow.setRadius(40);
        shadow.setOffsetX(10);
        shadow.setOffsetY(10);
        shadow.setSpread(0.3);
        @SuppressWarnings("unchecked")
        EventHandler<MouseEvent> MEvent = new EventHandler() {
            @Override
            public void handle(Event t) {
                if (t.getSource() == PlayPauseButton /*&& t.getSource()!=Flag*/) {
                    PlayPauseButton.setEffect(shadow);
                    PPLable.setFont(Font.font("Forte", FontWeight.BOLD, 44.0));
//                    Fl.setFont(f2);
                } else if (t.getSource() == Flag /*&& t.getSource() != PlayPauseButton*/) {
                    Flag.setEffect(shadow);
                    Fl.setFont(f);
                } else if (t.getSource() == ResetB) {
                    ResetB.setEffect(shadow);
                    Reset.setFont(f);
                }
            }
        };
        @SuppressWarnings("unchecked")
        EventHandler<MouseEvent> MExitEvent = new EventHandler() {
            @Override
            public void handle(Event t) {
                if (t.getSource() == PlayPauseButton) {
                    PlayPauseButton.setEffect(null);
                    PPLable.setFont(f2);
//                    Fl.setFont(f2);

                } else if (t.getSource() == Flag) {
                    Flag.setEffect(null);
                    Fl.setFont(f2);
                } else if (t.getSource() == ResetB) {
                    ResetB.setEffect(null);
                    Reset.setFont(f2);
                }
            }

        };

        PlayPauseButton.setOnMouseEntered(MEvent);
        Flag.setOnMouseEntered(MEvent);
        PlayPauseButton.setOnMouseExited(MExitEvent);
        Flag.setOnMouseExited(MExitEvent);

        ResetB.setOnMouseEntered(MEvent);
        ResetB.setOnMouseExited(MExitEvent);
        
         MYCB2.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle(""); // Reset the style
                } else {
                    setText(item);
                    setStyle("-fx-background-color: #abe9ff; -fx-border-color: blue; -fx-border-width: 2px; -fx-border-radius: 15px;"
                    );
                }
            }
        });
    }

    public String getFlagTimne() {
        return FlagTimne;
    }

    public void setFlagTimne(String FlagTimne) {
        this.FlagTimne = FlagTimne;
    }
}
