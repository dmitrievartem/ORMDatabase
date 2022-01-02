package ormdatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ormdatabase.model.Record;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitcher {

    public static Record observableRecord;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("hello.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ORM Database");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene(Scene scene, String view) {
        try {
            Parent newPage = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(view)));
            scene.setRoot(newPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchScene(Scene scene, String view, Record record) {
        observableRecord = record;
        try {
            Parent newPage = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(view)));
            scene.setRoot(newPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchScene(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Scene scene = btn.getScene();
        String id = btn.getId();
        String targetView;
        switch (id) {
            default:
            case "search":
                targetView = "search.fxml";
                break;
            case "view":
                targetView = "view.fxml";
                break;
            case "add":
                targetView = "add.fxml";
                break;
            case "edit":
                targetView = "edit.fxml";
                break;
            case "visualization":
                targetView = "visualization.fxml";
                break;
        }
        switchScene(scene, targetView);
    }
}
