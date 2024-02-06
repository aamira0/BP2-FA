module com.example.energiebedrijf1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.energiebedrijf1 to javafx.fxml;
    exports com.example.energiebedrijf1;
    exports com.example.energiebedrijf1.classes;
    opens com.example.energiebedrijf1.classes to javafx.fxml;
}