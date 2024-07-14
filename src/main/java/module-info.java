module com.mycompany.imageviewnodeinfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.imageviewnodeinfx to javafx.fxml;
    exports com.mycompany.imageviewnodeinfx;
}
