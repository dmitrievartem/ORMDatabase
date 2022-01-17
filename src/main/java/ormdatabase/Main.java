package ormdatabase;

import javafx.stage.Stage;
import ormdatabase.controller.MainController;

public class Main extends javafx.application.Application {

    private MainController mainController;

    @Override
    public void start(Stage stage) {
        mainController = new MainController();
        mainController.start(stage);
    }

    public static void main(String[] args) {
        System.setProperty("objectdb.home", "odb");
        launch();
    }

    @Override
    public void stop() {
        mainController.closeDB();
    }
}