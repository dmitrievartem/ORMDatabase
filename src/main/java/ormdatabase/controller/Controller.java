package ormdatabase.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ormdatabase.model.Record;

import java.io.IOException;
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
        scene.getStylesheets().add(Objects.requireNonNull(Controller.class.getResource("light.css")).toExternalForm());
        stage.setTitle("ShimStack");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        staticAnchorPane = anchorPane;
        switchPane("search");
    }

    public void switchPane(ActionEvent event) {
        String id = ((Button) event.getSource()).getId();
        if (Objects.isNull(observableRecord) && (id.equals("view") || id.equals("edit"))) {
            staticAnchorPane.getChildren().clear();
            return;
        }
        switchPane(id);
    }

    public void switchPane(String id) {
        System.out.println("switchPane-------------------------");
        try {
            Node node;
            if (id.equals("view")) {
                node = getLoaderWithController("record.fxml", new ViewController()).load();
            } else if (id.equals("edit")) {
                node = getLoaderWithController("record.fxml", new EditController()).load();
            } else if (id.equals("add")) {
                node = getLoaderWithController("record.fxml", new AddController()).load();
            } else {
                node = FXMLLoader.load(Objects.requireNonNull(Controller.class.getClassLoader().getResource(id.concat(".fxml"))));
            }
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

    private FXMLLoader getLoaderWithController(String view, Object controller) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(Controller.class.getClassLoader().getResource(view)));
        loader.setController(controller);
        return loader;
    }
}
