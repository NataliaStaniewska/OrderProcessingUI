package com.example.zamowienie;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {

    // Definicja boxa do wyboru daty
    @FXML
    private DatePicker deliveryDate;
    // Definicja pól tekstowych
    @FXML
    private TextField quantity;
    @FXML
    private TextField treeType;
    // Definicja etykiety
    @FXML
    private Label result;


    // Lista dozwolonych typów choinek
    private static final List<String> TREE_TYPES = Arrays.asList(
            "Świerk", "Sosna", "Jodła", "Modrzew"
    );


    // Metoda do validacji zamówienia
    private void validateOrder(String type, LocalDate date, int quantity) throws InvalidOrderException {

        if (!TREE_TYPES.contains(type)) {
            throw new InvalidTreeTypeException("Wpisz poprawny typ choinki. Dostępne opcje: Świerk, Sosna, Jodła, Modrzew");
        }

        LocalDate today = LocalDate.now();

        if (date == null ||date.isBefore(today)) {
            throw new InvalidOrderDateException("Wpisz datę dostawy oddaloną conajmniej o 1 dzień od daty dzisiejszej");
        }

        if (quantity <= 0 || quantity > 3) {
            throw new InvalidOrderQuantityException("Wpisz ilość choinek w przedziale od 1 do 3");
        }
    }

    // Metoda do przetwarzania zamówienia
    @FXML
    private void processingOrder() {

            // Przetwarzanie danych z formularza
            String type = treeType.getText();
            LocalDate date = deliveryDate.getValue();
            int quantity = 0;

            // Obsługa wyjątku jeśli podana ilość zamówienia nie jest liczbą
            try {
                 quantity = Integer.parseInt(this.quantity.getText());
            }
            catch (NumberFormatException e) {
                showError("Błąd: ", "Ilość musi być liczbą");
                return;
            }

            try{
            // Validacja
            validateOrder(type, date, quantity);

            // Wyświetlenie podsumowania zamówienia
            result.setText(String.format(
                    "Zamówienie potwierdzone!\n" +
                            "Typ: %s\n" +
                            "Ilość: %d\n" +
                            "Data dostawy: %s\n",
                    type, quantity, date
            ));

        }
        catch (InvalidTreeTypeException e) {
            showError("Błąd: ", e.getMessage());
        } catch (InvalidOrderDateException e) {
            showError("Błąd: ", e.getMessage());
        } catch (InvalidOrderQuantityException e) {
            showError("Błąd: ", e.getMessage());
        } catch (InvalidOrderException e) {
            showError("Błąd: ", e.getMessage());
        }
    }
    // Metoda do wyświetlania okienka z błędem
    private void showError(String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait(); // Wyświetlenie okna i czekanie na zamknięcie
    }


}