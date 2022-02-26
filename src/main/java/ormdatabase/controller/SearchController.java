package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import ormdatabase.DataSource;
import ormdatabase.entity.Record;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import static ormdatabase.controller.MainController.editableRecord;
import static ormdatabase.controller.MainController.observableRecord;

public class SearchController {

    protected Button view;

    private DataSource dataSource;

    @FXML
    public HBox headerHbox;

    @FXML
    public TextField idTextField;

    @FXML
    public TextField nameTextField;

    @FXML
    public TextField carTextField;

    @FXML
    public TextField cityTextField;

    @FXML
    public CheckBox favoritesCheckBox;

    @FXML
    public Button searchButton;

    @FXML
    public TableView<Record> searchTable;

    @FXML
    public TableColumn<Record, String> idColumn;

    @FXML
    public TableColumn<Record, String> nameColumn;

    @FXML
    public TableColumn<Record, String> carColumn;

    @FXML
    public TableColumn<Record, Date> dateColumn;

    @FXML
    private TableColumn<Record, String> phoneNumberColumn;

    @FXML
    private TableColumn<Record, String> cityColumn;

    public SearchController() {
    }

    @FXML
    void initialize() {
        for (Node node : headerHbox.getChildren()) {
            HBox.setHgrow(node, Priority.ALWAYS);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        carColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        idTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue) && !newValue.matches("\\d*")) {
                idTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (Objects.nonNull(newValue) && newValue.length() > 10) {
                idTextField.setText(newValue.substring(0, 10));
            }
        });

        searchButton.setOnAction(event -> {
            List<Record> queryResults = dataSource.select(favoritesCheckBox.isSelected(), idTextField.getText(), nameTextField.getText().trim(), carTextField.getText().trim(), cityTextField.getText().trim());
            ObservableList<Record> recordList = FXCollections.observableArrayList(queryResults);
            searchTable.setItems(recordList);
        });

        searchTable.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    if (searchTable.getSelectionModel().getSelectedItem() != null) {
                        observableRecord = searchTable.getSelectionModel().getSelectedItem();
                        editableRecord = new Record(observableRecord);
                        view.fire();
                    }
                }
            }
        });

        headerHbox.getParent().setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                searchButton.fire();
            }
        });
    }

    public void setView(Button view) {
        this.view = view;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
