package ormdatabase.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuthenticationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        passwordField.setOnAction(event -> {
//            if (passwordField.getText().equals("1234")) {
            if (true) {
                try {
                    Parent newPage = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("search-view.fxml")));
                    ((Node) event.getSource()).getScene().setRoot(newPage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Правильный пароль");
            } else {
                System.out.println("НЕПРАВИЛЬНО!!! НЕПРАВИЛЬНО!!!");
            }
        });
    }
}
