module com.josuesch {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.josuesch to javafx.fxml;
    exports com.josuesch;
    exports com.josuesch.model;
    opens com.josuesch.model to javafx.fxml;
    exports com.josuesch.model.enums;
    opens com.josuesch.model.enums to javafx.fxml;
    exports com.josuesch.model.item;
    opens com.josuesch.model.item to javafx.fxml;
    exports com.josuesch.controller;
    opens com.josuesch.controller to javafx.fxml;
}
