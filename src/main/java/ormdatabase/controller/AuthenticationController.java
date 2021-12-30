package ormdatabase.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import ormdatabase.SceneSwitcher;

public class AuthenticationController extends SceneSwitcher {

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        passwordField.setOnAction(event -> switchScene(((Node) event.getSource()).getScene(), "search.fxml"));
    }
}
