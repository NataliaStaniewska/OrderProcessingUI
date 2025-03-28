module com.example.zamowienie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zamowienie to javafx.fxml;
    exports com.example.zamowienie;
}