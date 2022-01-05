package ormdatabase.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ormdatabase.model.Record;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    @FXML
    public AnchorPane anchorPane;

    public static AnchorPane staticAnchorPane;

    @FXML
    public Button search;

    public static Button staticSearch;

    @FXML
    public Button view;

    public static Button staticView;

    @FXML
    public Button edit;

    public static Button staticEdit;

    @FXML
    public Button add;

    public static Button staticAdd;

    public static Record observableRecord;

    private static Button currentPageButton;

    public Stage stage1;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(Controller.class.getResource("light.css")).toExternalForm());
        stage.setTitle("ShimStack");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        stage1 = stage;
    }

    @FXML
    void initialize() {
        staticAnchorPane = anchorPane;
        currentPageButton = search;
        staticSearch = search;
        staticView = view;
        staticEdit = edit;
        staticAdd = add;
        currentPageButton.setDisable(true);
        switchPane("search");
    }

    public void switchPane(ActionEvent event) {
        currentPageButton.setDisable(false);
        currentPageButton = ((Button) event.getSource());
        currentPageButton.setDisable(true);
        String id = currentPageButton.getId();
        if (Objects.isNull(observableRecord) && (id.equals("view") || id.equals("edit"))) {
            staticAnchorPane.getChildren().clear();
            return;
        }
        switchPane(id);
    }

    public void switchPane(String id) {
        try {
            Node node;
            if (id.equals("view")) {
                node = getLoaderWithController(new ViewController()).load();
            } else if (id.equals("edit")) {
                node = getLoaderWithController(new EditController()).load();
            } else if (id.equals("add")) {
                node = getLoaderWithController(new AddController()).load();
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

    private FXMLLoader getLoaderWithController(Object controller) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(Controller.class.getClassLoader().getResource("record.fxml")));
        loader.setController(controller);
        return loader;
    }

    public void switchButton(Button buttonFrom, Button buttonTo) {
        buttonFrom.setDisable(false);
        buttonTo.setDisable(true);
        currentPageButton = buttonTo;
    }

    public void printPage() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setHeight(850);
        stage.setWidth(1480);
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A3, PageOrientation.REVERSE_LANDSCAPE, 0, 0, 0, 0);
            boolean success = job.printPage(pageLayout, anchorPane.getChildren().get(0));
            if (success) {
                job.endJob();
            }
            stage.setMaximized(false);
            stage.setMaximized(true);
            stage.show();
        }
    }
}
