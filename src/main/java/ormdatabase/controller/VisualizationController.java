package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;
import ormdatabase.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ormdatabase.controller.Controller.*;

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

    private Record targetRecord;

    private Record reboundTargetRecord;

    private Record compressionTargetRecord;

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
                                if (Objects.nonNull(event.getNewValue())
                                        && event.getNewValue().matches("\\d*")
                                        && Integer.parseInt(event.getNewValue()) <= 50) {
                                    shim.setNumber(event.getNewValue());
                                } else {
                                    shim.setNumber("");
                                }
                                break;
                            case 1:
                                if (Objects.nonNull(event.getNewValue())
                                        && event.getNewValue().matches("^\\d+(.\\d+)?$")
                                        && Float.parseFloat(event.getNewValue()) <= 50) {
                                    shim.setDiameter(event.getNewValue());
                                } else {
                                    shim.setDiameter("");
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
            if (Objects.nonNull(reboundPageComboBox.getSelectionModel().getSelectedItem())) {
                if (reboundPageComboBox.getSelectionModel().getSelectedItem().equals("Редактирование")) {
                    reboundTargetRecord = editableRecord;
                } else {
                    reboundTargetRecord = newRecord;
                }
            }

            if (Objects.isNull(reboundTargetRecord)) {
                reboundShockAbsorberComboBox.getSelectionModel().clearSelection();
                reboundShockAbsorberComboBox.setDisable(true);
                sendValidation();
                System.out.println("targetRecord is NULL");
            } else {
                sendValidation();
                reboundShockAbsorberComboBox.setDisable(false);
                System.out.println("targetRecord non NULL");
                List<String> shockAbsorberList;
                int shockAbsorberNumber = reboundTargetRecord.getShimStackSetList().get(BaseViewController.currentVersion - 1).getTypeNumber();

                System.out.println("shockAbsorberNumber: " + shockAbsorberNumber);
                if (shockAbsorberNumber == 4) {
                    shockAbsorberList = FXCollections.observableArrayList(List.of("ПЛ", "ПП", "ЗЛ", "ЗП"));
                } else if (shockAbsorberNumber == 2) {
                    shockAbsorberList = FXCollections.observableArrayList(List.of("П", "З"));
                } else {
                    shockAbsorberList = FXCollections.observableArrayList(List.of("4 одинаковые"));
                }
                reboundShockAbsorberComboBox.setItems(FXCollections.observableArrayList(shockAbsorberList));
            }
        });

        compressionPageComboBox.setOnAction(event -> {
            if (Objects.nonNull(compressionPageComboBox.getSelectionModel().getSelectedItem())) {
                if (compressionPageComboBox.getSelectionModel().getSelectedItem().equals("Редактирование")) {
                    compressionTargetRecord = editableRecord;
                } else {
                    compressionTargetRecord = newRecord;
                }
            }

            if (Objects.isNull(compressionTargetRecord)) {
                compressionShockAbsorberComboBox.getSelectionModel().clearSelection();
                compressionShockAbsorberComboBox.setDisable(true);
                sendValidation();
                System.out.println("targetRecord is NULL");
            } else {
                sendValidation();
                compressionShockAbsorberComboBox.setDisable(false);
                System.out.println("targetRecord non NULL");
                List<String> shockAbsorberList;
                int shockAbsorberNumber = compressionTargetRecord.getShimStackSetList().get(BaseViewController.currentVersion - 1).getTypeNumber();

                System.out.println("shockAbsorberNumber: " + shockAbsorberNumber);
                if (shockAbsorberNumber == 4) {
                    shockAbsorberList = FXCollections.observableArrayList(List.of("ПЛ", "ПП", "ЗЛ", "ЗП"));
                } else if (shockAbsorberNumber == 2) {
                    shockAbsorberList = FXCollections.observableArrayList(List.of("П", "З"));
                } else {
                    shockAbsorberList = FXCollections.observableArrayList(List.of("4 одинаковые"));
                }
                compressionShockAbsorberComboBox.setItems(FXCollections.observableArrayList(shockAbsorberList));
            }
        });

        reboundShockAbsorberComboBox.setOnAction(event -> sendValidation());
        compressionShockAbsorberComboBox.setOnAction(event -> sendValidation());

        reboundSendButton.setOnAction(event -> {
            ReboundStack reboundStack = new ReboundStack(new ArrayList<>(reboundTable.getItems()));
            int index = reboundShockAbsorberComboBox.getSelectionModel().getSelectedIndex();
            reboundTargetRecord.getShimStackSetList().get(BaseViewController.currentVersion - 1).getShimStackList().get(index).setReboundStack(reboundStack);
            reboundPageComboBox.getSelectionModel().clearSelection();
            reboundShockAbsorberComboBox.getSelectionModel().clearSelection();
            reboundShockAbsorberComboBox.setDisable(true);
            Notifications.create()
                    .owner(compressionSendButton.getScene().getWindow())
                    .position(Pos.BOTTOM_RIGHT)
                    .hideCloseButton()
                    .text("Отправлено")
                    .hideAfter(Duration.seconds(3))
                    .show();
        });
        compressionSendButton.setOnAction(event -> {
            CompressionStack compressionStack = new CompressionStack(new ArrayList<>(compressionTable.getItems()));
            int index = compressionShockAbsorberComboBox.getSelectionModel().getSelectedIndex();
            compressionTargetRecord.getShimStackSetList().get(BaseViewController.currentVersion - 1).getShimStackList().get(index).setCompressionStack(compressionStack);
            compressionPageComboBox.getSelectionModel().clearSelection();
            compressionShockAbsorberComboBox.getSelectionModel().clearSelection();
            compressionShockAbsorberComboBox.setDisable(true);
            Notifications.create()
                    .owner(compressionSendButton.getScene().getWindow())
                    .position(Pos.BOTTOM_RIGHT)
                    .hideCloseButton()
                    .text("Отправлено")
                    .hideAfter(Duration.seconds(3))
                    .show();
        });
    }

    public void drawShimStack() {
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

    public void addLines(TableView<Shim> tableView) {
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
    public void addTableRow(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        ObservableList<Shim> shims = targetTable.getItems();
        if (targetTable.getId().contains("rebound")) {
            shims.add(0, new Shim("1", "1", "0"));
        } else {
            shims.add(new Shim("1", "1", "0"));
        }
        targetTable.setItems(shims);
        drawShimStack();
        sendValidation();
    }

    @SuppressWarnings("unchecked")
    public void deleteTableRow(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        ObservableList<Shim> shims = targetTable.getItems();
        if (targetTable.getId().contains("rebound")) {
            shims.remove(0);
        } else {
            shims.remove(targetTable.getItems().size() - 1);
        }
        targetTable.setItems(shims);
        drawShimStack();
        sendValidation();
    }

    @SuppressWarnings("unchecked")
    public void resetTable(ActionEvent event) {
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
}
