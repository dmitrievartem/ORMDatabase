package ormdatabase.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private TextField carInput;

    @FXML
    private Button edit;

    @FXML
    private Button favorites;

    @FXML
    private TextField nameInput;

    @FXML
    private Button search;

    @FXML
    private Button searchButton;

    @FXML
    private Button settings;

    @FXML
    private TableView<?> table;

    @FXML
    private Button view;

    @FXML
    void initialize() {
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'search-view.fxml'.";
        assert carInput != null : "fx:id=\"carInput\" was not injected: check your FXML file 'search-view.fxml'.";
        assert edit != null : "fx:id=\"edit\" was not injected: check your FXML file 'search-view.fxml'.";
        assert favorites != null : "fx:id=\"favorites\" was not injected: check your FXML file 'search-view.fxml'.";
        assert nameInput != null : "fx:id=\"nameInput\" was not injected: check your FXML file 'search-view.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'search-view.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'search-view.fxml'.";
        assert settings != null : "fx:id=\"settings\" was not injected: check your FXML file 'search-view.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'search-view.fxml'.";
        assert view != null : "fx:id=\"view\" was not injected: check your FXML file 'search-view.fxml'.";

    }

}
