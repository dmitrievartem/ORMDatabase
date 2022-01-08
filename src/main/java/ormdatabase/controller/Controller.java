package ormdatabase.controller;

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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ormdatabase.model.DataSource;
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

    public static Record observableRecord = null;

    public static Record editableRecord = observableRecord;

    public static Record newRecord;

    private static Button currentPageButton;

    private FXMLLoader loader;

    public static BaseViewController baseViewController;

    public static VisualizationController visualizationController;

    protected static String pageId = "view";

    private Scene scene;

    private static Node searchNode;

    private static Node recordNode;

    private static Node visualizationNode;

    public void start(Stage stage) throws IOException {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        scene = new Scene(loader.load());

        scene.getStylesheets().add(Objects.requireNonNull(Controller.class.getResource("light-with-shadows.css")).toExternalForm());
        stage.setTitle("ShimStack");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("icon.png"))));
        stage.show();


        Task<Parent> loadTask = new Task<>() {
            @Override
            public Parent call() throws IOException {
                loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("search.fxml")));
                searchNode = loader.load();

                loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("record.fxml")));
                baseViewController = new BaseViewController();
                loader.setController(baseViewController);
                recordNode = loader.load();
                baseViewController.addController.initNewRecord();

                loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("visualization.fxml")));
                visualizationController = new VisualizationController();
                loader.setController(visualizationController);
                visualizationNode = loader.load();

                return (Parent) searchNode;
            }
        };

        loadTask.setOnSucceeded(e -> {
            Node node = loadTask.getValue();
            staticAnchorPane.getChildren().add(node);
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            System.out.println("thread complete");
        });

        loadTask.setOnFailed(e -> loadTask.getException().printStackTrace());

        Thread thread = new Thread(loadTask);
        thread.start();


//        Path dir = Files.createDirectories(Paths.get("path", "to", "files"));
//        OutputStream out = Files.newOutputStream(dir.resolve("shimstack.odb"));
//
//        final FileChooser fileChooser = new FileChooser();
//        File file = fileChooser.showOpenDialog(stage);
//        byte[] fileContent = Files.readAllBytes(file.toPath());
//
//        out.write(fileContent);

    }

    @FXML
    void initialize() {
        staticAnchorPane = anchorPane;
        currentPageButton = search;
        staticSearch = search;
        staticView = view;
        staticEdit = edit;
        staticAdd = add;
//        staticSearch.fire();
        currentPageButton.setDisable(true);
    }

    public void switchPane(ActionEvent event) {
        if (currentPageButton.getId().equals("edit") && Objects.nonNull(observableRecord)) {
            if (!baseViewController.saveObject(editableRecord)) {
                return;
            }
        } else if (currentPageButton.getId().equals("add")) {
            if (!baseViewController.addController.saveObject(newRecord)) {
                return;
            }
        }
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
        staticAnchorPane.getChildren().clear();
        Node node = null;
        if (id.equals("search")) {
            node = searchNode;
        } else if (id.equals("view") || id.equals("edit") || id.equals("add")) {
            node = recordNode;
        } else if (id.equals("visualization")) {
            node = visualizationNode;
        }
        staticAnchorPane.getChildren().add(node);
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

    public void backup() {
        DataSource dataSource = new DataSource();
        dataSource.backup();
    }
}
