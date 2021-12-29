package ormdatabase.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import ormdatabase.SceneSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthenticationController extends SceneSwitcher {

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
                switchScene(((Node) event.getSource()).getScene(), "search-view.fxml");
                
            } else {
                
            }
        });
    }
}
