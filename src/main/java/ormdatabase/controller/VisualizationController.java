package ormdatabase.controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;
import ormdatabase.SceneSwitcher;

public class VisualizationController extends SceneSwitcher {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private TableView<?> compressionTable1;

    @FXML
    private Button ct1addButton;

    @FXML
    private Button ct1deleteButton;

    @FXML
    private TableColumn<?, ?> ct1diameterColumn;

    @FXML
    private TableColumn<?, ?> ct1numberColumn;

    @FXML
    private TableColumn<?, ?> ct1thicknessColumn;

    @FXML
    private Button edit;

    @FXML
    private TableView<?> reboundTable1;

    @FXML
    private Button rt1addButton;

    @FXML
    private Button rt1deleteButton;

    @FXML
    private TableColumn<?, ?> rt1diameterColumn;

    @FXML
    private TableColumn<?, ?> rt1numberColumn;

    @FXML
    private TableColumn<?, ?> rt1thicknessColumn;

    @FXML
    private Button search;

    @FXML
    private Button settings;

    @FXML
    private VBox vBox1;

    @FXML
    private Button view;

    @FXML
    private Button visualization;

    @FXML
    private Pane pane;

    @FXML
    private VBox stackVisualizationVBox;

    @FXML
    private BorderPane bp;

    PrinterJob currentPrinterJob;

    @FXML
    void initialize() {
        System.out.println("initialize-----------------------");
        System.out.println(pane.getLayoutX());
        System.out.println(pane.getLayoutY());

        System.out.println(pane.widthProperty());
        System.out.println(pane.heightProperty());

//        pane.setMaxWidth();
//        pane.getScaleY()

//        Line line = new Line(
//                pane.getLayoutX() + (pane.getWidth() / 2),
//                pane.getLayoutY() + pane.getHeight() / 2,
//                pane.getLayoutX() + 100,
//                pane.getLayoutY() + pane.getHeight() / 2
//        );

//        Line line = new Line();
//        line.startXProperty().bind(pane.widthProperty().divide(2));
//        line.startYProperty().bind(pane.heightProperty().divide(2));
//        line.endXProperty().bind(pane.widthProperty().divide(2));
//        line.endYProperty().bind(pane.heightProperty().divide(2).add(100));

        stackVisualizationVBox.setSpacing(20);


        VBox vBox1 = new VBox();
        vBox1.setSpacing(5);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(5);

        vBox1.getChildren().add(newLine());
        vBox1.getChildren().add(newLine());
        vBox1.getChildren().add(newLine());
        vBox1.getChildren().add(newLine());
        vBox1.getChildren().add(newLine());
        vBox1.getChildren().add(newLine());


        vBox2.getChildren().add(newLine());
        vBox2.getChildren().add(newLine());
        vBox2.getChildren().add(newLine());
        vBox2.getChildren().add(newLine());

        stackVisualizationVBox.getChildren().add(vBox1);
        stackVisualizationVBox.getChildren().add(vBox2);
//        pane.getChildren().add(line);

//        Node node = bp;
//        PrinterJob job = PrinterJob.createPrinterJob();
//        if (job != null) {
//            boolean success = job.printPage(stackVisualizationVBox);
//            if (success) {
//                job.endJob();
//            }
//        }

    }

    @FXML
    void addDeleteRow(ActionEvent event) {

    }

    public Line newLine() {
        Line line = new Line();
        line.startXProperty().bind(pane.widthProperty().divide(2));
        line.startYProperty().bind(pane.heightProperty().divide(2));
        line.endXProperty().bind(pane.widthProperty().divide(2).add(100));
        line.endYProperty().bind(pane.heightProperty().divide(2));
        return line;
    }

}
