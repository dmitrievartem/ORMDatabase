package ormdatabase;

import javafx.stage.Stage;
import ormdatabase.controller.Controller;

import java.io.IOException;

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Controller controller = new Controller();
        controller.start(stage);
    }

    public static void main(String[] args) {
        System.setProperty("objectdb.home", "./src/main/resources/odb/");
        launch();
    }
}