module com.josuesch {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.josuesch to javafx.fxml;
    exports com.josuesch;
}
