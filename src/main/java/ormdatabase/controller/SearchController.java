package ormdatabase.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ormdatabase.model.DataSource;
import ormdatabase.model.Record;

public class SearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button search;

    @FXML
    private Button view;

    @FXML
    private Button edit;

    @FXML
    private Button add;

    @FXML
    private Button favorites;

    @FXML
    private Button settings;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField carTextField;

    @FXML
    private Button searchButton;

    private ObservableList<Record> recordList = FXCollections.observableArrayList();

    @FXML
    private TableView<Record> searchTable;

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
        initData();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        carColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        // заполняем таблицу данными
//        searchTable.setItems(recordList);
    }

    @FXML
    private void search() {
        DataSource dataSource = new DataSource();
        List<Record> queryResults = dataSource.select(nameTextField.getText(), carTextField.getText());
        recordList = FXCollections.observableArrayList(queryResults);

        System.out.println(recordList.size());

        searchTable.setItems(recordList);
    }

    private void initData() {
//        DataSource dataSource = new DataSource();
//        dataSource.insert(new Record("name", "car", new Date(20211201), "phobeNumber", "city"));
//        dataSource.insert(new Record("name22", "car22", new Date(20211202), "phobeNumber22", "city22"));
//        dataSource.insert(new Record("name333", "car333", new Date(20211203), "phobeNumber33", "city33"));
    }
}
