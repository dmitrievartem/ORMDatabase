package ormdatabase.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import ormdatabase.DataSource;
import ormdatabase.entity.Record;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MainController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button search;

    @FXML
    private Button view;

    @FXML
    private Button edit;

    @FXML
    private Button add;

    @FXML
    private Button visualization;

    @FXML
    private Button downloadBackup;

    @FXML
    private Button uploadBackup;

    private FXMLLoader loader;

    private Node searchNode;

    private Node viewNode;

    private Node addNode;

    private Node editNode;

    private Node visualizationNode;

    private Stage stage;

    protected SearchController searchController = new SearchController();

    protected ViewController viewController = new ViewController();

    protected EditController editController = new EditController();

    protected AddController addController = new AddController();

    protected VisualizationController visualizationController = new VisualizationController();

    protected static Record observableRecord = null;

    protected static Record editableRecord = null;

    protected static Record newRecord = new Record();

    private Button currentPageButton;

    private final DataSource dataSource = new DataSource();

    public void start(Stage stage) {
        this.stage = stage;
        loadFXML(stage);
    }

    @FXML
    private void initialize() {
        search.setOnAction(event -> {
            switchPage(searchNode, search);
            searchController.searchButton.fire();
        });
        view.setOnAction(event -> {
            if (Objects.nonNull(observableRecord)) {
                switchPage(viewNode, view);
                viewController.viewRecord(observableRecord);
            }
        });
        edit.setOnAction(event -> {
            if (Objects.nonNull(editableRecord)) {
                switchPage(editNode, edit);
                editController.viewRecord(editableRecord);
                editController.name.requestFocus();
            }
        });
        add.setOnAction(event -> {
            switchPage(addNode, add);
            addController.viewRecord(newRecord);
            addController.name.requestFocus();
        });
        visualization.setOnAction(event -> {
            switchPage(visualizationNode, visualization);
            visualizationController.refreshSelection();
        });
        downloadBackup.setOnAction(event -> dataSource.backup(stage));
        uploadBackup.setOnAction(event -> {
            dataSource.recovery(stage);
            switchPage(searchNode, search);
            searchController.searchButton.fire();
            observableRecord = null;
            editableRecord = null;
        });

        searchController.setView(view);
        searchController.setDataSource(dataSource);

        viewController.setEdit(edit);
        viewController.setVisualizationController(visualizationController);

        editController.setVisualizationController(visualizationController);
        editController.setView(view);
        editController.setDataSource(dataSource);

        addController.setDataSource(dataSource);
        addController.setVisualizationController(visualizationController);

        visualizationController.setAddController(addController);
        visualizationController.setEditController(editController);
    }

    private void switchPage(Node node, Button button) {
        if (currentPageButton.getId().equals("add")) {
            addController.saveObject(newRecord);
        } else if (currentPageButton.getId().equals("edit")) {
            editController.saveObject(editableRecord);
        }
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        setDisabledButton(button);
        currentPageButton = button;
    }

    public void closeDB() {
        dataSource.emf.close();
    }

    private void loadFXML(Stage stage) {
        Task<Parent> loadTask = new Task<>() {
            @Override
            public Parent call() throws IOException {
                loader = getNewLoader("fxml/search.fxml");
                loader.setController(searchController);
                searchNode = loader.load();

                loader = getNewLoader("fxml/record.fxml");
                loader.setController(viewController);
                viewNode = loader.load();

                loader = getNewLoader("fxml/record.fxml");
                loader.setController(editController);
                editNode = loader.load();

                loader = getNewLoader("fxml/record.fxml");
                loader.setController(addController);
                addNode = loader.load();

                loader = getNewLoader("fxml/visualization.fxml");
                loader.setController(visualizationController);
                visualizationNode = loader.load();

                return (Parent) searchNode;
            }
        };

        loadTask.setOnSucceeded(e -> {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/main.fxml"));
            loader.setController(this);
            try {
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("light-with-shadows.css")).toExternalForm());
                stage.setTitle("ShimStack");
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("icon.png"))));
                stage.show();
                currentPageButton = search;
                switchPage(searchNode, search);
                searchController.searchButton.fire();
                stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //noinspection ThrowableNotThrown
        loadTask.setOnFailed(e -> loadTask.getException().printStackTrace());

        Thread thread = new Thread(loadTask);
        thread.start();
    }

    private FXMLLoader getNewLoader(String resource) {
        return new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(resource)));
    }

    private void setDisabledButton(Button current) {
        List.of(search, view, edit, add, visualization).forEach(button -> button.setDisable(false));
        current.setDisable(true);
    }

    private void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        ButtonType okButton = new ButtonType("Да");
        ButtonType cancelButton = new ButtonType("Нет, надо проверить");

        alert.getButtonTypes().add(okButton);
        alert.getButtonTypes().add(cancelButton);

        alert.setTitle("Устал работать?");
        alert.setContentText("Уверен что всё сохранил, инженер?");
        Optional<ButtonType> res = alert.showAndWait();

        if (res.isPresent()) {
            if (res.get().equals(cancelButton))
                event.consume();
        }
    }
}
