package ormdatabase;

import javafx.stage.Stage;
import ormdatabase.controller.Controller;

public class Main extends javafx.application.Application {

    private Controller controller;

    @Override
    public void start(Stage stage) {
        controller = new Controller();
        controller.start(stage);
    }

    public static void main(String[] args) {
        System.setProperty("objectdb.home", "./src/main/resources/odb/");
        launch();
    }

    @Override
    public void stop(){
        controller.closeDB();
    }
}