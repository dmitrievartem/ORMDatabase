package ormdatabase.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ormdatabase.model.Record;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Controller {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public VBox menu;
    
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
        scene.getStylesheets().add(Objects.requireNonNull(Controller.class.getResource("light.css")).toExternalForm());
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
        System.out.println("switchPane ActionEvent -----------------------");
        System.out.println(id);
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
