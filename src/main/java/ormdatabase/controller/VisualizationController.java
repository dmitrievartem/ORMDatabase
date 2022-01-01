package ormdatabase.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import ormdatabase.SceneSwitcher;
import ormdatabase.model.Shim;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizationController extends SceneSwitcher {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button search;

    @FXML
    private Button settings;

    @FXML
    private Button view;

    @FXML
    private Button visualization;

    @FXML
    private TableView<Shim> reboundTable;

    @FXML
    private TableColumn<Shim, String> reboundNumberColumn;

    @FXML
    private TableColumn<Shim, String> reboundDiameterColumn;

    @FXML
    private TableColumn<Shim, String> reboundThicknessColumn;

    @FXML
    private TableView<Shim> compressionTable;

    @FXML
    private TableColumn<Shim, String> compressionNumberColumn;

    @FXML
    private TableColumn<Shim, String> compressionDiameterColumn;

    @FXML
    private TableColumn<Shim, String> compressionThicknessColumn;

    @FXML
    private Pane pane;

    @FXML
    private VBox stackVisualizationVBox;

    @FXML
    private BorderPane bp;

    PrinterJob currentPrinterJob;

    @FXML
    void initialize() {

        List<TableView<Shim>> tableViewList = List.of(reboundTable, compressionTable);

        for (TableView<Shim> tableView : tableViewList) {
            tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("number"));
            tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("diameter"));
            tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("thickness"));
        }

        List<TableColumn<Shim, String>> columnList = List.of(
                reboundNumberColumn, reboundDiameterColumn, reboundThicknessColumn,
                compressionNumberColumn, compressionDiameterColumn, compressionThicknessColumn
        );

        for (TableColumn<Shim, String> column : columnList) {
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit(
                    event -> {
                        Shim shim = event.getTableView().getItems().get(event.getTablePosition().getRow());
                        switch (event.getTablePosition().getColumn()) {
                            case 0:
                                shim.setNumber(event.getNewValue());
                                break;
                            case 1:
                                shim.setDiameter(event.getNewValue());
                                break;
                            case 2:
                                shim.setThickness(event.getNewValue());
                                break;
                        }
                        drawShimStack();
                    }
            );
        }

        stackVisualizationVBox.setAlignment(Pos.CENTER);

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
    public void addDeleteRow(ActionEvent event) {
        TableView<Shim> targetTable;
        Button btn = (Button) event.getSource();
        String id = btn.getId();

        switch (id) {
            default:
            case "reboundAddButton":
            case "reboundDeleteButton":
                targetTable = reboundTable;
                break;
            case "compressionAddButton":
            case "compressionDeleteButton":
                targetTable = compressionTable;
                break;
        }
        Shim newShim = new Shim("0", "0", "0");
        ObservableList<Shim> shims = targetTable.getItems();

        if (id.contains("Add")) {
            shims.add(newShim);
        } else if (id.contains("Delete") && targetTable.getItems().size() > 0) {
            shims.remove(targetTable.getItems().size() - 1);
        }
        targetTable.setItems(shims);
    }

//    public Line newLine() {
//        Line line = new Line();
//        line.startXProperty().bind(pane.widthProperty().divide(2));
//        line.startYProperty().bind(pane.heightProperty().divide(2));
//        line.endXProperty().bind(pane.widthProperty().divide(2).add(100));
//        line.endYProperty().bind(pane.heightProperty().divide(2));
//        return line;
//    }

    public void drawShimStack() {
        stackVisualizationVBox.getChildren().removeAll(stackVisualizationVBox.getChildren());
        System.out.println(stackVisualizationVBox.getChildren().size());
        for (Shim shim : reboundTable.getItems()) {
            for (int i = 0; i < Integer.parseInt(shim.getNumber()); i++) {
                stackVisualizationVBox.getChildren().add(
                        new Line(0, 0, Float.parseFloat(shim.getDiameter()) * 300, 0)
                );
            }
        }
//        Line line = new Line(0, 0, 100, 0);
//        line.setStrokeWidth(1);

        Rectangle rectangle = new Rectangle(300, 100);
        stackVisualizationVBox.getChildren().add(rectangle);

        for (Shim shim : compressionTable.getItems()) {
            for (int i = 0; i < Integer.parseInt(shim.getNumber()); i++) {
                stackVisualizationVBox.getChildren().add(
                        new Line(0, 0, Float.parseFloat(shim.getDiameter()) * 300, 0)
                );
            }
        }
    }

}
