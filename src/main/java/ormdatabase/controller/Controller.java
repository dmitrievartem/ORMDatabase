package ormdatabase.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ormdatabase.model.Record;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    @FXML
    public AnchorPane anchorPane;
    
    public static Record observableRecord;

    public static AnchorPane staticAnchorPane;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ShimStack");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() throws IOException {
        staticAnchorPane = anchorPane;
        switchPane("search.fxml");
    }

    public void switchPane(ActionEvent event) {
        String id = ((Button) event.getSource()).getId();
        if (Objects.isNull(observableRecord) && (id.equals("view") || id.equals("edit"))) {
            return;
        }
        switchPane(id.concat(".fxml"));
    }

    public void switchPane(String view) {
        try {
            Node node =  FXMLLoader.load(Objects.requireNonNull(Controller.class.getClassLoader().getResource(view)));
            staticAnchorPane.getChildren().clear();
            staticAnchorPane.getChildren().add(node);
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
