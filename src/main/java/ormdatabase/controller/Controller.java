package ormdatabase.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ormdatabase.model.Record;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    @FXML
    public SubScene subScene;

    public static Record observableRecord;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ShimStack");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene(String view) {
        try {
            Parent newPage = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(view)));
            subScene.setRoot(newPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchScene(ActionEvent event) {
        String id = ((Button) event.getSource()).getId();
        if (Objects.isNull(observableRecord) && (id.equals("view") || id.equals("edit"))) {
            return;
        }
        switchScene(id.concat(".fxml"));
    }

    @FXML
    void initialize() {
//        Parent newPage = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("search.fxml")));
//        subScene = new SubScene(newPage);
        switchScene("search.fxml");
    }
}
