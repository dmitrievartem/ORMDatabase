package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import ormdatabase.model.DataSource;
import ormdatabase.model.Record;

import java.sql.Date;
import java.util.List;

public class SearchController {

    Controller controller;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField carTextField;

    @FXML
    private CheckBox favoritesCheckBox;

    @FXML
    private TableView<Record> searchTable;

    @FXML
    private TableColumn<Record, String> idColumn;

    @FXML
    private TableColumn<Record, String> nameColumn;

    @FXML
    private TableColumn<Record, String> carColumn;

    @FXML
    private TableColumn<Record, Date> dateColumn;

    @FXML
    private TableColumn<Record, String> phoneNumberColumn;

    @FXML
    private TableColumn<Record, String> cityColumn;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        carColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        idColumn.setMinWidth(65);
        idColumn.setMaxWidth(65);
        idColumn.setPrefWidth(65);
        nameColumn.setMinWidth(200);
        nameColumn.setPrefWidth(200);
        dateColumn.setMinWidth(150);
        dateColumn.setMaxWidth(150);
        dateColumn.setPrefWidth(150);
        carColumn.setMinWidth(250);
        carColumn.setMaxWidth(250);
        carColumn.setPrefWidth(250);
        phoneNumberColumn.setMinWidth(250);
        phoneNumberColumn.setMaxWidth(250);
        phoneNumberColumn.setPrefWidth(250);

        searchTable.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    if (searchTable.getSelectionModel().getSelectedItem() != null) {
                        Controller.observableRecord = searchTable.getSelectionModel().getSelectedItem();
                        Controller.switchPane("view.fxml");
                    }
                }
            }
        });

    }

    public void search() {
        DataSource dataSource = new DataSource();
        List<Record> queryResults = dataSource.select(favoritesCheckBox.isSelected(), idTextField.getText(), nameTextField.getText(), carTextField.getText());
        ObservableList<Record> recordList = FXCollections.observableArrayList(queryResults);
        searchTable.setItems(recordList);
    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
