package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import ormdatabase.model.DataSource;
import ormdatabase.model.Record;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class SearchController extends Controller {

    @FXML
    protected HBox headerHbox;

    @FXML
    protected TextField idTextField;

    @FXML
    protected TextField nameTextField;

    @FXML
    protected TextField carTextField;

    @FXML
    protected TextField cityTextField;

    @FXML
    protected CheckBox favoritesCheckBox;

    @FXML
    protected TableView<Record> searchTable;

    @FXML
    protected TableColumn<Record, String> idColumn;

    @FXML
    protected TableColumn<Record, String> nameColumn;

    @FXML
    protected TableColumn<Record, String> carColumn;

    @FXML
    protected TableColumn<Record, Date> dateColumn;

    @FXML
    private TableColumn<Record, String> phoneNumberColumn;

    @FXML
    private TableColumn<Record, String> cityColumn;

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

        searchTable.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    if (searchTable.getSelectionModel().getSelectedItem() != null) {
                        observableRecord = searchTable.getSelectionModel().getSelectedItem();
                        staticView.fire();
                    }
                }
            }
        });
    }

    public void search() {
        DataSource dataSource = new DataSource();
        List<Record> queryResults = dataSource.select(favoritesCheckBox.isSelected(), idTextField.getText(), nameTextField.getText(), carTextField.getText(), cityTextField.getText());
        ObservableList<Record> recordList = FXCollections.observableArrayList(queryResults);
        searchTable.setItems(recordList);
    }
}
