module org.example.frogger {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.frogger to javafx.fxml;
    exports org.example.frogger;
}