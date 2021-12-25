package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import ormdatabase.SceneSwitcher;
import ormdatabase.model.Shim;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddViewController extends SceneSwitcher {

    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Shim> reboundTable1;

    @FXML
    private TableColumn<Shim, String> rt1numberColumn;

    @FXML
    private TableColumn<Shim, String> rt1diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt1thicknessColumn;

    @FXML
    private TableView<Shim> reboundTable2;

    @FXML
    private TableColumn<Shim, String> rt2numberColumn;

    @FXML
    private TableColumn<Shim, String> rt2diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt2thicknessColumn;

    @FXML
    private TableView<Shim> reboundTable3;

    @FXML
    private TableColumn<Shim, String> rt3numberColumn;

    @FXML
    private TableColumn<Shim, String> rt3diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt3thicknessColumn;

    @FXML
    private TableView<Shim> reboundTable4;

    @FXML
    private TableColumn<Shim, String> rt4numberColumn;

    @FXML
    private TableColumn<Shim, String> rt4diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt4thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable1;

    @FXML
    private TableColumn<Shim, String> ct1numberColumn;

    @FXML
    private TableColumn<Shim, String> ct1diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct1thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable2;

    @FXML
    private TableColumn<Shim, String> ct2numberColumn;

    @FXML
    private TableColumn<Shim, String> ct2diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct2thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable3;

    @FXML
    private TableColumn<Shim, String> ct3numberColumn;

    @FXML
    private TableColumn<Shim, String> ct3diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct3thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable4;

    @FXML
    private TableColumn<Shim, String> ct4numberColumn;

    @FXML
    private TableColumn<Shim, String> ct4diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct4thicknessColumn;

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button favorites;

    @FXML
    private Button search;

    @FXML
    private Button settings;

    @FXML
    private Label versionLabel;

    @FXML
    private Button view;

    @FXML
    void initialize() {

        rt1numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        rt1diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        rt1thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));

        List<Shim> shimList = FXCollections.observableArrayList();
        Shim shim = new Shim("1", "0.34", "0.2");
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        reboundTable1.setItems(FXCollections.observableArrayList(shimList));

        reboundTable1.setEditable(true);
        List<TableColumn<Shim, String>> columnList = List.of(
                rt1numberColumn, rt1diameterColumn, rt1thicknessColumn,
                rt2numberColumn, rt2diameterColumn, rt2thicknessColumn,
                rt3numberColumn, rt3diameterColumn, rt3thicknessColumn,
                rt4numberColumn, rt4diameterColumn, rt4thicknessColumn,
                ct1numberColumn, ct1diameterColumn, ct1thicknessColumn,
                ct2numberColumn, ct2diameterColumn, ct2thicknessColumn,
                ct3numberColumn, ct3diameterColumn, ct3thicknessColumn,
                ct4numberColumn, ct4diameterColumn, ct4thicknessColumn
        );

        for(TableColumn<Shim, String> column : columnList) {
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Shim, String>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Shim, String> event) {
                            Shim shim = event.getTableView().getItems().get(event.getTablePosition().getRow());
                            System.out.println("COLUMN");
                            System.out.println(event.getTablePosition().getColumn());
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
                        }
                    }
            );
        }

        reboundTable1.setRowFactory(tv -> {
            TableRow<Shim> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (! row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();

                    System.out.println("------1111111111------");
                    System.out.println(index);
                    System.out.println(reboundTable1.getItems().get(index).getNumber() + ", "
                            + reboundTable1.getItems().get(index).getDiameter() + ", "
                            + reboundTable1.getItems().get(index).getThickness());
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != (Integer) db.getContent(SERIALIZED_MIME_TYPE)) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }


//                System.out.println("------222222222------");
//                System.out.println(row.getIndex());
//                System.out.println(reboundTable1.getItems().get(row.getIndex()).getNumber() + ", "
//                        + reboundTable1.getItems().get(row.getIndex()).getDiameter() + ", "
//                        + reboundTable1.getItems().get(row.getIndex()).getThickness());
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
//                    Shim draggedShim = new Shim();
//                    System.out.println("------3333333333------");
//                    System.out.println(draggedIndex);
//                    System.out.println(reboundTable1.getItems().get(draggedIndex).getNumber() + ", "
//                            + reboundTable1.getItems().get(draggedIndex).getDiameter() + ", "
//                            + reboundTable1.getItems().get(draggedIndex).getThickness());

//                    draggedShim.setNumber(reboundTable1.getItems().get(draggedIndex).getNumber());
//                    draggedShim.setDiameter(reboundTable1.getItems().get(draggedIndex).getDiameter());
//                    draggedShim.setNumber(reboundTable1.getItems().get(draggedIndex).getThickness());
                    Shim draggedShim = reboundTable1.getItems().remove(draggedIndex);

                    int dropIndex ;

                    if (row.isEmpty()) {
                        dropIndex = reboundTable1.getItems().size() ;
                    } else {
                        dropIndex = row.getIndex();
                    }

                    System.out.println(draggedShim.getNumber() + ", " + draggedShim.getDiameter() + ", " + draggedShim.getThickness());
                    reboundTable1.getItems().add(dropIndex, draggedShim);

                    event.setDropCompleted(true);
                    reboundTable1.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row ;
        });

    }
}