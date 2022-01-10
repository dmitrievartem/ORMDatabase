package ormdatabase.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ormdatabase.model.*;

import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Controller {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Button search;

    @FXML
    public Button view;

    @FXML
    public Button edit;

    @FXML
    public Button add;

    @FXML
    public Button visualization;

    @FXML
    public Button print;

    @FXML
    public Button downloadBackup;

    @FXML
    public Button uploadBackup;

    private FXMLLoader loader;

    protected SearchController searchController;

    protected BaseViewController baseViewController;

    protected VisualizationController visualizationController;

    private Node searchNode;

    private Node recordNode;

    private Node visualizationNode;

    private static Button currentPageButton;

    protected static Record observableRecord = null;

    protected static Record editableRecord = null;

    protected static Record newRecord = new Record();

    private final DataSource dataSource = new DataSource();

    private Stage stage;

    public void start(Stage stage) {
        this.stage = stage;

        Task<Parent> loadTask = new Task<>() {
            @Override
            public Parent call() throws IOException {
                loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("search.fxml")));
                searchController = new SearchController();
                loader.setController(searchController);
                searchNode = loader.load();

                loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("record.fxml")));
                baseViewController = new BaseViewController();
                loader.setController(baseViewController);
                recordNode = loader.load();

                loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("visualization.fxml")));
                visualizationController = new VisualizationController();
                loader.setController(visualizationController);
                visualizationNode = loader.load();

                return (Parent) searchNode;
            }
        };

        loadTask.setOnSucceeded(e -> {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
            loader.setController(this);
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            assert scene != null;
            scene.getStylesheets().add(Objects.requireNonNull(Controller.class.getResource("light-with-shadows.css")).toExternalForm());
            stage.setTitle("ShimStack");
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.getIcons().add(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("icon.png"))));
            stage.show();

            Node node = loadTask.getValue();
            anchorPane.getChildren().add(node);
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            System.out.println("thread complete");
        });

        loadTask.setOnFailed(e -> loadTask.getException().printStackTrace());

        Thread thread = new Thread(loadTask);
        thread.start();
    }

    @FXML
    void initialize() throws InterruptedException {
        System.out.println("initialize");
        search.setOnAction(this::switchPane);
        view.setOnAction(this::switchPane);
        edit.setOnAction(this::switchPane);
        add.setOnAction(this::switchPane);
        visualization.setOnAction(this::switchPane);
        print.setOnAction(event -> printPage());
        downloadBackup.setOnAction(event -> downloadBackup());
        uploadBackup.setOnAction(event -> uploadBackup());
        searchController.setView(view);
        searchController.setDataSource(dataSource);
        baseViewController.viewController.setEdit(edit);
        baseViewController.editController.setView(view);
        baseViewController.editController.setDataSource(dataSource);
        baseViewController.addController.setDataSource(dataSource);
        currentPageButton = search;
        currentPageButton.setDisable(true);

        Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setContentText("Заполняй все как надо блять");
        alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(Controller.class.getResource("light-with-shadows.css")).toExternalForm());
        dialogPane.getScene().setFill(Color.TRANSPARENT);
        ((Stage) dialogPane.getScene().getWindow()).initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
//        alert.show();

//        Thread thread = new Thread(() -> {
//            try {
//                // Wait for 5 secs
//                Thread.sleep(3000);
//                if (alert.isShowing()) {
//                    Platform.runLater(() -> alert.close());
//                }
//            } catch (Exception exp) {
//                exp.printStackTrace();
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
    }

    public void switchPane(ActionEvent event) {
        if (currentPageButton.getId().equals("edit") && Objects.nonNull(observableRecord)) {
            if (!baseViewController.saveObject(editableRecord)) {
                return;
            }
        } else if (currentPageButton.getId().equals("add")) {
            if (!baseViewController.saveObject(newRecord)) {
                return;
            }
        }
        currentPageButton.setDisable(false);
        currentPageButton = ((Button) event.getSource());
        currentPageButton.setDisable(true);
        String id = currentPageButton.getId();
        if (Objects.isNull(observableRecord) && (id.equals("view") || id.equals("edit"))) {
            anchorPane.getChildren().clear();
            return;
        }
        switchPane(id);
    }

    private void switchPane(String id) {
        anchorPane.getChildren().clear();
        Node node = null;
        if (id.equals("search")) {
            node = searchNode;
        } else if (id.equals("view") || id.equals("edit") || id.equals("add")) {
            node = recordNode;
        } else if (id.equals("visualization")) {
            node = visualizationNode;
        }
        anchorPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);

        if (id.equals("view") && Objects.nonNull(observableRecord)) {
            baseViewController.viewController.start();
        } else if (id.equals("edit") && Objects.nonNull(observableRecord)) {
            baseViewController.editController.start();
        } else if (id.equals("add")) {
            baseViewController.addController.start();
        } else if (id.equals("visualization")) {
            visualizationController.refreshSelection();
        }
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

    public void downloadBackup() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);
        dataSource.backup(selectedDirectory);
    }

    public void uploadBackup() {
        dataSource.emf.close();
        try {
            Path dir = Files.createDirectories(Paths.get("src/main/resources/odb/db"));
            OutputStream out = Files.newOutputStream(dir.resolve("shimstack.odb"));
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            out.write(fileContent);
            dataSource.emf = Persistence.createEntityManagerFactory("$objectdb/db/shimstack.odb");
        } catch (IOException e) {
            e.printStackTrace();
            dataSource.emf = Persistence.createEntityManagerFactory("$objectdb/db/shimstack.odb");
        }
    }

    public void closeDB() {
        System.out.println("_______________________");
        dataSource.emf.close();
    }
}
