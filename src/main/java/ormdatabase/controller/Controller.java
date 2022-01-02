package ormdatabase.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ormdatabase.model.Record;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    
    public static Record observableRecord;

    @FXML
    public AnchorPane anchorPane;

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
        AnchorPane newAnchorPane =  FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("search.fxml")));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(newAnchorPane);
        staticAnchorPane = anchorPane;
    }

    public void switchPane(ActionEvent event) {
        String id = ((Button) event.getSource()).getId();
        if (Objects.isNull(observableRecord) && (id.equals("view") || id.equals("edit"))) {
            return;
        }
        switchPane(id.concat(".fxml"));
    }

    public static void switchPane(String view) {
        try {
//            Parent newPage = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(view)));
//            subScene.setRoot(newPage);
            AnchorPane newAnchorPane =  FXMLLoader.load(Objects.requireNonNull(Controller.class.getClassLoader().getResource(view)));
            staticAnchorPane.getChildren().clear();
            staticAnchorPane.getChildren().add(newAnchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
