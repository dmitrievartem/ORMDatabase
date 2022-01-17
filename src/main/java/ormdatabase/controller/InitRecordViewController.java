package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import ormdatabase.entity.Shim;
import ormdatabase.utils.EditCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InitRecordViewController {

    @FXML
    protected Label id;

    @FXML
    protected TextField name;

    @FXML
    protected TextField car;

    @FXML
    protected DatePicker date;

    @FXML
    protected TextField phone;

    @FXML
    protected TextField city;

    @FXML
    protected CheckBox favorites;

    @FXML
    protected Label version;

    @FXML
    protected ComboBox<String> type;

    @FXML
    protected DatePicker versionDate;

    @FXML
    protected TextArea comment;

    @FXML
    protected TextField author;

    @FXML
    protected Button previousVersion;

    @FXML
    protected Button nextVersion;

    @FXML
    protected Button deleteVersion;

    @FXML
    protected Button addVersion;

    @FXML
    protected Button editRecord;

    @FXML
    protected Button save;

    protected final Label frontLeftLabel = new Label();
    protected final Label frontRightLabel = new Label();
    protected final Label rearLeftLabel = new Label();
    protected final Label rearRightLabel = new Label();

    protected final Label frontLabel = new Label();
    protected final Label rearLabel = new Label();

    protected final Label emptyLabel = new Label();

    @FXML
    protected VBox vBox1;

    @FXML
    protected VBox vBox2;

    @FXML
    protected VBox vBox3;

    @FXML
    protected VBox vBox4;

    @FXML
    protected TableView<Shim> reboundTable1;

    @FXML
    protected TableColumn<Shim, String> rt1numberColumn;

    @FXML
    protected TableColumn<Shim, String> rt1diameterColumn;

    @FXML
    protected TableColumn<Shim, String> rt1thicknessColumn;

    @FXML
    protected Button rt1addButton;

    @FXML
    protected Button rt1deleteButton;

    @FXML
    protected Button rt1resetButton;

    @FXML
    protected TableView<Shim> reboundTable2;

    @FXML
    protected TableColumn<Shim, String> rt2numberColumn;

    @FXML
    protected TableColumn<Shim, String> rt2diameterColumn;

    @FXML
    protected TableColumn<Shim, String> rt2thicknessColumn;

    @FXML
    protected Button rt2addButton;

    @FXML
    protected Button rt2deleteButton;

    @FXML
    protected Button rt2resetButton;

    @FXML
    protected TableView<Shim> reboundTable3;

    @FXML
    protected TableColumn<Shim, String> rt3numberColumn;

    @FXML
    protected TableColumn<Shim, String> rt3diameterColumn;

    @FXML
    protected TableColumn<Shim, String> rt3thicknessColumn;

    @FXML
    protected Button rt3addButton;

    @FXML
    protected Button rt3deleteButton;

    @FXML
    protected Button rt3resetButton;

    @FXML
    protected TableView<Shim> reboundTable4;

    @FXML
    protected TableColumn<Shim, String> rt4numberColumn;

    @FXML
    protected TableColumn<Shim, String> rt4diameterColumn;

    @FXML
    protected TableColumn<Shim, String> rt4thicknessColumn;

    @FXML
    protected Button rt4addButton;

    @FXML
    protected Button rt4deleteButton;

    @FXML
    protected Button rt4resetButton;

    @FXML
    protected TableView<Shim> compressionTable1;

    @FXML
    protected TableColumn<Shim, String> ct1numberColumn;

    @FXML
    protected TableColumn<Shim, String> ct1diameterColumn;

    @FXML
    protected TableColumn<Shim, String> ct1thicknessColumn;

    @FXML
    protected Button ct1addButton;

    @FXML
    protected Button ct1deleteButton;

    @FXML
    protected Button ct1resetButton;

    @FXML
    protected TableView<Shim> compressionTable2;

    @FXML
    protected TableColumn<Shim, String> ct2numberColumn;

    @FXML
    protected TableColumn<Shim, String> ct2diameterColumn;

    @FXML
    protected TableColumn<Shim, String> ct2thicknessColumn;

    @FXML
    protected Button ct2addButton;

    @FXML
    protected Button ct2deleteButton;

    @FXML
    protected Button ct2resetButton;

    @FXML
    protected TableView<Shim> compressionTable3;

    @FXML
    protected TableColumn<Shim, String> ct3numberColumn;

    @FXML
    protected TableColumn<Shim, String> ct3diameterColumn;

    @FXML
    protected TableColumn<Shim, String> ct3thicknessColumn;

    @FXML
    protected Button ct3addButton;

    @FXML
    protected Button ct3deleteButton;

    @FXML
    protected Button ct3resetButton;

    @FXML
    protected TableView<Shim> compressionTable4;

    @FXML
    protected TableColumn<Shim, String> ct4numberColumn;

    @FXML
    protected TableColumn<Shim, String> ct4diameterColumn;

    @FXML
    protected TableColumn<Shim, String> ct4thicknessColumn;

    @FXML
    protected Button ct4addButton;

    @FXML
    protected Button ct4deleteButton;

    @FXML
    protected Button ct4resetButton;

    @FXML
    protected Button visualizationButton1;

    @FXML
    protected Button visualizationButton2;

    @FXML
    protected Button visualizationButton3;

    @FXML
    protected Button visualizationButton4;

    @FXML
    protected Button visualizationButton5;

    @FXML
    protected Button visualizationButton6;

    @FXML
    protected Button visualizationButton7;

    @FXML
    protected Button visualizationButton8;

    protected static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    private VisualizationController visualizationController;

    protected void setVisualizationController(VisualizationController visualizationController) {
        this.visualizationController = visualizationController;
    }

    protected void initMainFieldsAndButtons() {
        setLabels();
        setTextFieldsLimits();
        setTypeComboBox();
        setColumnProperties();
        setTableCellsEditable();
        setEditButtonsAction();
    }

    private void setLabels() {
        frontLeftLabel.setText("ПЛ");
        frontRightLabel.setText("ПП");
        rearLeftLabel.setText("ЗЛ");
        rearRightLabel.setText("ЗП");
        frontLabel.setText("П");
        rearLabel.setText("З");
        emptyLabel.setText("-");
        vBox1.getChildren().add(0, frontLeftLabel);
        vBox2.getChildren().add(0, frontRightLabel);
        vBox3.getChildren().add(0, rearLeftLabel);
        vBox4.getChildren().add(0, rearRightLabel);
    }

    private void setTextFieldsLimits() {
        phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue) && !newValue.matches("\\d*")) {
                phone.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (Objects.nonNull(newValue) && newValue.length() > 11) {
                phone.setText(newValue.substring(0, 11));
            }
        });

        comment.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue) && newValue.length() > 500) {
                comment.setText(newValue.substring(0, 500));
            }
        });
    }

    private void setTypeComboBox() {
        List<String> typeList = FXCollections.observableArrayList(List.of("4 одинаковые", "4 разные", "перед-зад"));
        type.setItems(FXCollections.observableArrayList(typeList));
        type.getSelectionModel().select("4 разные");
        type.setOnAction(event -> setType(type.getSelectionModel().getSelectedItem()));
    }

    protected void setType(String selectedType) {
        switch (selectedType) {
            case "4 одинаковые":
                vBox1.getChildren().remove(0);
                vBox1.getChildren().add(0, emptyLabel);
                vBox2.getChildren().remove(0);
                vBox2.getChildren().add(0, frontRightLabel);
                vBox1.setDisable(false);
                vBox2.setDisable(true);
                vBox3.setDisable(true);
                vBox4.setDisable(true);
                break;
            case "перед-зад":
                vBox1.getChildren().remove(0);
                vBox1.getChildren().add(0, frontLabel);
                vBox2.getChildren().remove(0);
                vBox2.getChildren().add(0, rearLabel);
                vBox1.setDisable(false);
                vBox2.setDisable(false);
                vBox3.setDisable(true);
                vBox4.setDisable(true);
                break;
            case "4 разные":
                vBox1.getChildren().remove(0);
                vBox1.getChildren().add(0, frontLeftLabel);
                vBox2.getChildren().remove(0);
                vBox2.getChildren().add(0, frontRightLabel);
                vBox1.setDisable(false);
                vBox2.setDisable(false);
                vBox3.setDisable(false);
                vBox4.setDisable(false);
                break;
        }
    }

    private void setColumnProperties() {
        for (TableView<Shim> tableView : getAllTables()) {
            tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("number"));
            tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("diameter"));
            tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("thickness"));
        }
    }

    private void setTableCellsEditable() {
        List<TableColumn<Shim, String>> columnList = getAllcolumns();
        getAllTables().forEach(tableView -> tableView.getSelectionModel().setCellSelectionEnabled(true));
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
                                        && event.getNewValue().matches("^\\d+(?:[\\.]\\d+)?$")
                                        && Float.parseFloat(event.getNewValue()) <= 50) {
                                    shim.setDiameter(event.getNewValue());
                                } else {
                                    shim.setDiameter("1");
                                }
                                break;
                            case 2:
                                if (Objects.nonNull(event.getNewValue())
                                        && event.getNewValue().matches("^\\d+(?:[\\.]\\d+)?$")
                                        && Float.parseFloat(event.getNewValue()) <= 50) {
                                    shim.setThickness(event.getNewValue());
                                } else {
                                    shim.setThickness("1");
                                }
                                break;
                        }
                    }
            );
        }

        getAllTables().forEach(tableView -> tableView.setRowFactory(tv -> {
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
                }
            });

            return row ;
        }));
    }


    @SuppressWarnings("unchecked")
    private void addTableRow(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        ObservableList<Shim> shims = targetTable.getItems();
        shims.add(new Shim("1", "1", "0"));
        targetTable.setItems(shims);
    }

    @SuppressWarnings("unchecked")
    private void deleteTableRow(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        ObservableList<Shim> shims = targetTable.getItems();
        if (shims.size() > 1) {
            shims.remove(shims.size() - 1);
            targetTable.setItems(shims);
        }
    }

    @SuppressWarnings("unchecked")
    private void resetTable(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        ObservableList<Shim> shims = targetTable.getItems();
        shims.clear();
        shims.add(new Shim("1", "1", "1"));
        targetTable.setItems(shims);
    }

    @SuppressWarnings("unchecked")
    private void sendToReboundVisualization(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        visualizationController.reboundTable.setItems(targetTable.getItems());
        visualizationController.drawShimStack();
        Notifications.create()
                .owner(targetTable.getScene().getWindow())
                .position(Pos.BOTTOM_RIGHT)
                .hideCloseButton()
                .text("Таблица отбоя отправлена на страницу \"Визуализация\"")
                .hideAfter(Duration.seconds(3))
                .show();
    }

    @SuppressWarnings("unchecked")
    private void sendToCompressionVisualization(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        visualizationController.compressionTable.setItems(targetTable.getItems());
        visualizationController.drawShimStack();
        Notifications.create()
                .owner(targetTable.getScene().getWindow())
                .position(Pos.BOTTOM_RIGHT)
                .hideCloseButton()
                .text("Таблица сжатия отправлена на страницу \"Визуализация\"")
                .hideAfter(Duration.seconds(3))
                .show();
    }

    private void setSendToVisualization() {
        for (Button button : List.of(visualizationButton1, visualizationButton3, visualizationButton5, visualizationButton7)) {
            button.setOnAction(this::sendToReboundVisualization);
        }
        for (Button button : List.of(visualizationButton2, visualizationButton4, visualizationButton6, visualizationButton8)) {
            button.setOnAction(this::sendToCompressionVisualization);
        }
    }

    private void setAddButtonAction() {
        List<Button> buttonList = List.of(
                rt1addButton, rt2addButton, rt3addButton, rt4addButton,
                ct1addButton, ct2addButton, ct3addButton, ct4addButton
        );
        for (Button button : buttonList) {
            button.setOnAction(this::addTableRow);
        }
    }

    private void setDeleteButtonAction() {
        List<Button> buttonList = List.of(
                rt1deleteButton, rt2deleteButton, rt3deleteButton, rt4deleteButton,
                ct1deleteButton, ct2deleteButton, ct3deleteButton, ct4deleteButton
        );
        for (Button button : buttonList) {
            button.setOnAction(this::deleteTableRow);
        }
    }

    private void setResetButtonAction() {
        List<Button> buttonList = List.of(
                rt1resetButton, rt2resetButton, rt3resetButton, rt4resetButton,
                ct1resetButton, ct2resetButton, ct3resetButton, ct4resetButton
        );
        for (Button button : buttonList) {
            button.setOnAction(this::resetTable);
        }
    }

    private void setEditButtonsAction() {
        setAddButtonAction();
        setDeleteButtonAction();
        setResetButtonAction();
        setSendToVisualization();
    }


    protected void disableInputs() {
        name.setEditable(false);
        car.setEditable(false);
        phone.setEditable(false);
        city.setEditable(false);
        favorites.setDisable(true);
        comment.setEditable(false);
        author.setEditable(false);
        for (TableView<Shim> tableView : getAllTables()) {
            tableView.setEditable(false);
        }

        date.setDisable(true);
        type.setDisable(true);
        versionDate.setDisable(true);

        deleteVersion.setDisable(true);
        addVersion.setDisable(true);
        save.setDisable(true);
        for (Button button : getAllTableButtons()) {
            button.setDisable(true);
        }
    }

    protected void enableInputs() {
        name.setEditable(true);
        car.setEditable(true);
        phone.setEditable(true);
        city.setEditable(true);
        favorites.setDisable(false);
        comment.setEditable(true);
        author.setEditable(true);
        for (TableView<Shim> tableView : getAllTables()) {
            tableView.setEditable(true);
        }

        date.setDisable(false);
        type.setDisable(false);
        versionDate.setDisable(false);

        deleteVersion.setDisable(false);
        addVersion.setDisable(false);
        save.setDisable(false);
        for (Button button : getAllTableButtons()) {
            button.setDisable(false);
        }
    }

    protected List<TableView<Shim>> getAllTables() {
        return List.of(
                reboundTable1, compressionTable1,
                reboundTable2, compressionTable2,
                reboundTable3, compressionTable3,
                reboundTable4, compressionTable4
        );
    }

    protected List<TableView<Shim>> getActualTables() {
        List<TableView<Shim>> tableList = List.of(
                reboundTable1, compressionTable1,
                reboundTable2, compressionTable2,
                reboundTable3, compressionTable3,
                reboundTable4, compressionTable4
        );
        List<TableView<Shim>> actualTableList = new ArrayList<>();
        for (int i = 0; i < (getTypeNumber() * 2); i++) {
            actualTableList.add(tableList.get(i));
        }
        return actualTableList;
    }

    private int getTypeNumber() {
        switch (type.getSelectionModel().getSelectedItem()) {
            case "4 одинаковые":
                return 1;
            case "перед-зад":
                return 2;
            case "4 разные":
            default:
                return 4;
        }
    }

    protected List<Button> getAllTableButtons() {
        return List.of(
                rt1addButton, rt2addButton, rt3addButton, rt4addButton,
                rt1deleteButton, rt2deleteButton, rt3deleteButton, rt4deleteButton,
                rt1resetButton, rt2resetButton, rt3resetButton, rt4resetButton,
                ct1addButton, ct2addButton, ct3addButton, ct4addButton,
                ct1deleteButton, ct2deleteButton, ct3deleteButton, ct4deleteButton,
                ct1resetButton, ct2resetButton, ct3resetButton, ct4resetButton
        );
    }

    private List<TableColumn<Shim, String>> getAllcolumns() {
        return List.of(
                rt1numberColumn, rt1diameterColumn, rt1thicknessColumn,
                rt2numberColumn, rt2diameterColumn, rt2thicknessColumn,
                rt3numberColumn, rt3diameterColumn, rt3thicknessColumn,
                rt4numberColumn, rt4diameterColumn, rt4thicknessColumn,
                ct1numberColumn, ct1diameterColumn, ct1thicknessColumn,
                ct2numberColumn, ct2diameterColumn, ct2thicknessColumn,
                ct3numberColumn, ct3diameterColumn, ct3thicknessColumn,
                ct4numberColumn, ct4diameterColumn, ct4thicknessColumn
        );
    }
}
