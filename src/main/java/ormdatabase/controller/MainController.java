package ormdatabase.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ormdatabase.DataSource;
import ormdatabase.entity.Record;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
    private Button print;

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

    private final DataSource dataSource = new DataSource();

    public void start(Stage stage) {
        this.stage = stage;
        loadFXML(stage);
    }

    @FXML
    private void initialize() {
        search.setOnAction(event -> switchPage(searchNode, search));
        view.setOnAction(event -> {
            if(Objects.nonNull(observableRecord)) {
                switchPage(viewNode, view);
                viewController.viewRecord(observableRecord);
            }
        });
        edit.setOnAction(event -> {
            if(Objects.nonNull(editableRecord)) {
                switchPage(editNode, edit);
                editController.viewRecord(editableRecord);
            }
        });
        add.setOnAction(event -> {
            switchPage(addNode, add);
            addController.viewRecord(newRecord);
        });
        visualization.setOnAction(event -> {
            switchPage(visualizationNode, visualization);
            visualizationController.refreshSelection();
        });
        print.setOnAction(event -> printPage());
        downloadBackup.setOnAction(event -> dataSource.backup(stage));
        uploadBackup.setOnAction(event -> dataSource.recovery(stage));

        searchController.setView(view);
        searchController.setDataSource(dataSource);

        viewController.setEdit(edit);
        viewController.setVisualizationController(visualizationController);

        editController.setVisualizationController(visualizationController);
        editController.setViewController(viewController);
        editController.setView(view);
        editController.setDataSource(dataSource);

        addController.setDataSource(dataSource);
        addController.setVisualizationController(visualizationController);

        visualizationController.setAddController(addController);
        visualizationController.setEditController(editController);
    }

    private void switchPage(Node node, Button button) {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        setDisabledButton(button);
    }

    private void printPage() {
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

    public void closeDB() {
        dataSource.emf.close();
    }

    private void loadFXML(Stage stage) {
        Task<Parent> loadTask = new Task<>() {
            @Override
            public Parent call() throws IOException {
                loader = getNewLoader("search.fxml");
                loader.setController(searchController);
                searchNode = loader.load();

                loader = getNewLoader("record.fxml");
                loader.setController(viewController);
                viewNode = loader.load();

                loader = getNewLoader("record.fxml");
                loader.setController(editController);
                editNode = loader.load();

                searchController.setViewEditControllers(viewController, editController);

                loader = getNewLoader("record.fxml");
                loader.setController(addController);
                addNode = loader.load();

                loader = getNewLoader("visualization.fxml");
                loader.setController(visualizationController);
                visualizationNode = loader.load();

                return (Parent) searchNode;
            }
        };

        loadTask.setOnSucceeded(e -> {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
            loader.setController(this);
            try {
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(Objects.requireNonNull(MainController.class.getResource("light-with-shadows.css")).toExternalForm());
                stage.setTitle("ShimStack");
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.getIcons().add(new Image(Objects.requireNonNull(MainController.class.getResourceAsStream("icon.png"))));
                stage.show();
                switchPage(searchNode, search);
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
}
