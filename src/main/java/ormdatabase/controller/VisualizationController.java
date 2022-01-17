package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import ormdatabase.entity.CompressionStack;
import ormdatabase.entity.ReboundStack;
import ormdatabase.entity.Record;
import ormdatabase.entity.Shim;
import ormdatabase.utils.EditCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ormdatabase.controller.InitRecordViewController.SERIALIZED_MIME_TYPE;
import static ormdatabase.controller.MainController.editableRecord;
import static ormdatabase.controller.MainController.newRecord;

public class VisualizationController {

    @FXML
    protected TableView<Shim> reboundTable;

    @FXML
    private TableColumn<Shim, String> reboundNumberColumn;

    @FXML
    private TableColumn<Shim, String> reboundDiameterColumn;

    @FXML
    private TableColumn<Shim, String> reboundThicknessColumn;

    @FXML
    private Button reboundAddButton;

    @FXML
    private Button reboundDeleteButton;

    @FXML
    private Button reboundResetButton;

    @FXML
    protected TableView<Shim> compressionTable;

    @FXML
    private TableColumn<Shim, String> compressionNumberColumn;

    @FXML
    private TableColumn<Shim, String> compressionDiameterColumn;

    @FXML
    private TableColumn<Shim, String> compressionThicknessColumn;

    @FXML
    private Button compressionAddButton;

    @FXML
    private Button compressionDeleteButton;

    @FXML
    private Button compressionResetButton;

    @FXML
    private VBox stackVisualizationVBox;

    @FXML
    private ComboBox<String> reboundPageComboBox;

    @FXML
    private ComboBox<String> reboundShockAbsorberComboBox;

    @FXML
    private Button reboundSendButton;

    @FXML
    private ComboBox<String> compressionPageComboBox;

    @FXML
    private ComboBox<String> compressionShockAbsorberComboBox;

    @FXML
    private Button compressionSendButton;

    private Record reboundTargetRecord;

    private Record compressionTargetRecord;

    private int reboundCurrentVersion;

    private int compressionCurrentVersion;

    private EditController editController;

    private AddController addController;

    @FXML
    private void initialize() {
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

        List.of(reboundTable, compressionTable).forEach(tableView -> tableView.setRowFactory(tv -> {
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
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    Shim shim = tableView.getItems().remove(draggedIndex);

                    int dropIndex ;

                    if (row.isEmpty()) {
                        dropIndex = tableView.getItems().size() ;
                    } else {
                        dropIndex = row.getIndex();
                    }

                    tableView.getItems().add(dropIndex, shim);

                    event.setDropCompleted(true);
                    tableView.getSelectionModel().select(dropIndex);
                    event.consume();
                    drawShimStack();
                }
            });

            return row ;
        }));


        List.of(reboundTable, compressionTable).forEach(tableView -> tableView.getSelectionModel().setCellSelectionEnabled(true));
        Callback<TableColumn<Shim, String>, TableCell<Shim, String>> editCell = (TableColumn<Shim, String> p) -> EditCell.createStringEditCell();
        for (TableColumn<Shim, String> column : columnList) {
            column.setCellFactory(editCell);
            column.setOnEditCommit(
                    event -> {
                        Shim shim = event.getTableView().getItems().get(event.getTablePosition().getRow());
                        switch (event.getTablePosition().getColumn()) {
                            case 0:
                                if (Objects.nonNull(event.getNewValue())
                                        && event.getNewValue().matches("\\d+")
                                        && Integer.parseInt(event.getNewValue()) <= 50) {
                                    shim.setNumber(event.getNewValue());
                                } else {
                                    shim.setNumber("1");
                                }
                                break;
                            case 1:
                                if (Objects.nonNull(event.getNewValue())
                                        && event.getNewValue().matches("^\\d+(?:[.]\\d+)?$")
                                        && Float.parseFloat(event.getNewValue()) <= 50) {
                                    shim.setDiameter(event.getNewValue());
                                } else {
                                    shim.setDiameter("1");
                                }
                                break;
                            case 2:
                                if (Objects.nonNull(event.getNewValue())
                                        && event.getNewValue().matches("^\\d+(?:[.]\\d+)?$")
                                        && Float.parseFloat(event.getNewValue()) <= 50) {
                                    shim.setThickness(event.getNewValue());
                                } else {
                                    shim.setThickness("1");
                                }
                                break;
                        }
                        drawShimStack();
                    }
            );
        }

        reboundAddButton.setOnAction(this::addTableRow);
        reboundDeleteButton.setOnAction(this::deleteTableRow);
        reboundResetButton.setOnAction(this::resetTable);
        compressionAddButton.setOnAction(this::addTableRow);
        compressionDeleteButton.setOnAction(this::deleteTableRow);
        compressionResetButton.setOnAction(this::resetTable);

        drawShimStack();

        sendValidation();
        reboundPageComboBox.setItems(FXCollections.observableArrayList(List.of("Добавление", "Редактирование")));
        compressionPageComboBox.setItems(FXCollections.observableArrayList(List.of("Добавление", "Редактирование")));

        reboundPageComboBox.setOnAction(event -> {
            int shockAbsorberNumber = 1;
            if (Objects.nonNull(reboundPageComboBox.getSelectionModel().getSelectedItem())) {
                if (reboundPageComboBox.getSelectionModel().getSelectedItem().equals("Редактирование")) {
                    if (Objects.nonNull(editableRecord)) {
                        reboundTargetRecord = editableRecord;
                        reboundCurrentVersion = editController.currentVersion - 1;
                        shockAbsorberNumber = reboundTargetRecord.getShimStackSetList().get(reboundCurrentVersion).getTypeNumber();
                    } else {
                        reboundTargetRecord = null;
                    }
                } else {
                    reboundTargetRecord = newRecord;
                    reboundCurrentVersion = addController.currentVersion - 1;
                    shockAbsorberNumber = reboundTargetRecord.getShimStackSetList().get(addController.currentVersion - 1).getTypeNumber();
                }
            }

            setComboBox(reboundTargetRecord, reboundShockAbsorberComboBox, shockAbsorberNumber);
        });

        compressionPageComboBox.setOnAction(event -> {
            int shockAbsorberNumber = 1;
            if (Objects.nonNull(compressionPageComboBox.getSelectionModel().getSelectedItem())) {
                if (compressionPageComboBox.getSelectionModel().getSelectedItem().equals("Редактирование")) {
                    if (Objects.nonNull(editableRecord)) {
                        compressionTargetRecord = editableRecord;
                        compressionCurrentVersion = editController.currentVersion - 1;
                        shockAbsorberNumber = compressionTargetRecord.getShimStackSetList().get(compressionCurrentVersion).getTypeNumber();
                    } else {
                        compressionTargetRecord = null;
                    }
                } else {
                    compressionTargetRecord = newRecord;
                    compressionCurrentVersion = addController.currentVersion - 1;
                    shockAbsorberNumber = compressionTargetRecord.getShimStackSetList().get(addController.currentVersion - 1).getTypeNumber();
                }
            }

            setComboBox(compressionTargetRecord, compressionShockAbsorberComboBox, shockAbsorberNumber);
        });

        reboundShockAbsorberComboBox.setOnAction(event -> sendValidation());
        compressionShockAbsorberComboBox.setOnAction(event -> sendValidation());

        reboundSendButton.setOnAction(event -> {
            ReboundStack reboundStack = new ReboundStack(new ArrayList<>(reboundTable.getItems()));
            int index = reboundShockAbsorberComboBox.getSelectionModel().getSelectedIndex();
            reboundTargetRecord.getShimStackSetList().get(reboundCurrentVersion).getShimStackList().get(index).setReboundStack(reboundStack);
            Notifications.create()
                    .owner(compressionSendButton.getScene().getWindow())
                    .position(Pos.BOTTOM_RIGHT)
                    .hideCloseButton()
                    .text(String.format("Таблица отбоя отправлена на страницу \"%s\"", reboundPageComboBox.getSelectionModel().getSelectedItem()))
                    .hideAfter(Duration.seconds(3))
                    .show();
            reboundPageComboBox.getSelectionModel().clearSelection();
            reboundShockAbsorberComboBox.getSelectionModel().clearSelection();
            reboundShockAbsorberComboBox.setDisable(true);
        });

        compressionSendButton.setOnAction(event -> {
            CompressionStack compressionStack = new CompressionStack(new ArrayList<>(compressionTable.getItems()));
            int index = compressionShockAbsorberComboBox.getSelectionModel().getSelectedIndex();
            compressionTargetRecord.getShimStackSetList().get(compressionCurrentVersion).getShimStackList().get(index).setCompressionStack(compressionStack);
            Notifications.create()
                    .owner(compressionSendButton.getScene().getWindow())
                    .position(Pos.BOTTOM_RIGHT)
                    .hideCloseButton()
                    .text(String.format("Таблица сжатия отправлена на страницу \"%s\"", compressionPageComboBox.getSelectionModel().getSelectedItem()))
                    .hideAfter(Duration.seconds(3))
                    .show();
            compressionPageComboBox.getSelectionModel().clearSelection();
            compressionShockAbsorberComboBox.getSelectionModel().clearSelection();
            compressionShockAbsorberComboBox.setDisable(true);
        });
    }

    protected void drawShimStack() {
        stackVisualizationVBox.getChildren().removeAll(stackVisualizationVBox.getChildren());
        addLines(reboundTable);
        Rectangle rectangle = new Rectangle(350, 75);
        rectangle.setFill(Color.web("#ffffff"));
        rectangle.setArcWidth(10);
        rectangle.setArcHeight(10);
        rectangle.setStroke(Color.web("#bbc4d1"));
        stackVisualizationVBox.getChildren().add(rectangle);
        addLines(compressionTable);
    }

    private void addLines(TableView<Shim> tableView) {
        for (Shim shim : tableView.getItems()) {
            VBox vBox = new VBox();
            vBox.setSpacing(5);
            vBox.setAlignment(Pos.CENTER);
            for (int i = 0; i < Integer.parseInt(shim.getNumber()); i++) {
                vBox.getChildren().add(
                        new Line(0, 0, Float.parseFloat(shim.getDiameter()) * 8, 0)
                );
            }
            stackVisualizationVBox.getChildren().add(vBox);
        }
    }

    @SuppressWarnings("unchecked")
    private void addTableRow(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        ObservableList<Shim> shims = targetTable.getItems();
        shims.add(new Shim("1", "1", "0"));
        targetTable.setItems(shims);
        drawShimStack();
        sendValidation();
    }

    @SuppressWarnings("unchecked")
    private void deleteTableRow(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        if (targetTable.getItems().size() > 0) {
            ObservableList<Shim> shims = targetTable.getItems();
            shims.remove(targetTable.getItems().size() - 1);
            targetTable.setItems(shims);
            drawShimStack();
            sendValidation();
        }
    }

    @SuppressWarnings("unchecked")
    private void resetTable(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        targetTable.getItems().clear();
        drawShimStack();
        sendValidation();
    }

    private void sendValidation() {
        reboundSendButton.setDisable(reboundTable.getItems().size() <= 0
                || Objects.isNull(reboundTargetRecord)
                || reboundPageComboBox.getSelectionModel().isEmpty()
                || reboundShockAbsorberComboBox.getSelectionModel().isEmpty());
        compressionSendButton.setDisable(compressionTable.getItems().size() <= 0
                || Objects.isNull(compressionTargetRecord)
                || compressionPageComboBox.getSelectionModel().isEmpty()
                || compressionShockAbsorberComboBox.getSelectionModel().isEmpty());
    }

    protected void refreshSelection() {
        reboundPageComboBox.getSelectionModel().clearSelection();
        reboundShockAbsorberComboBox.getSelectionModel().clearSelection();
        reboundShockAbsorberComboBox.setDisable(true);
        compressionPageComboBox.getSelectionModel().clearSelection();
        compressionShockAbsorberComboBox.getSelectionModel().clearSelection();
        compressionShockAbsorberComboBox.setDisable(true);
    }

    public void setEditController(EditController editController) {
        this.editController = editController;
    }

    public void setAddController(AddController addController) {
        this.addController = addController;
    }

    private List<String> getShockAbsorberList(int shockAbsorberNumber) {
        if (shockAbsorberNumber == 4) {
            return FXCollections.observableArrayList(List.of("ПЛ", "ПП", "ЗЛ", "ЗП"));
        } else if (shockAbsorberNumber == 2) {
            return FXCollections.observableArrayList(List.of("П", "З"));
        } else {
            return FXCollections.observableArrayList(List.of("4 одинаковые"));
        }
    }

    private void setComboBox(Record targetRecord, ComboBox<String> comboBox, int shockAbsorberNumber) {
        if (Objects.isNull(targetRecord)) {
            comboBox.getSelectionModel().clearSelection();
            comboBox.setDisable(true);
            sendValidation();
        } else {
            sendValidation();
            comboBox.setDisable(false);
            List<String> shockAbsorberList = getShockAbsorberList(shockAbsorberNumber);
            comboBox.setItems(FXCollections.observableArrayList(shockAbsorberList));
        }
    }
}
