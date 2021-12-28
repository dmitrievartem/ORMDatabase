package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ormdatabase.SceneSwitcher;
import ormdatabase.model.*;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController extends SceneSwitcher {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idTextField;

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
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        carColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
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

        searchTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        if (searchTable.getSelectionModel().getSelectedItem() != null) {
                            System.out.println("You clicked on " + searchTable.getSelectionModel().getSelectedItem().getId());
                            switchScene(searchTable.getScene(), "record-4diff-view.fxml", searchTable.getSelectionModel().getSelectedItem());
                        }
                    }
                }
            }
        });

    }

    @FXML
    private void search() {
        DataSource dataSource = new DataSource();
        List<Record> queryResults = dataSource.select(idTextField.getText(), nameTextField.getText(), carTextField.getText());
        recordList = FXCollections.observableArrayList(queryResults);

        System.out.println(recordList.size());

        searchTable.setEditable(true);
        searchTable.setItems(recordList);
    }


    private void initData() {
        DataSource dataSource = new DataSource();

        CompressionStack compressionStack = new CompressionStack();
        ReboundStack reboundStack = new ReboundStack();

//        Shim shim = new Shim(1f, 0.34f, 0.2f);
        Shim shim = new Shim("1", "0.34", "0.2");
        List<Shim> shimList = new ArrayList<>();
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);
        shimList.add(shim);

        compressionStack.setStack(shimList);
        reboundStack.setStack(shimList);

        List<StackPair> shimStackList = new ArrayList<>();
        StackPair stackPair = new StackPair(compressionStack, reboundStack);
        shimStackList.add(stackPair);
        shimStackList.add(stackPair);

        ShimStackSet shimStackSet1 = new ShimStackSet( 1, "4 разных", new Date(2021, 12, 20), "comment", "tester", true,
                shimStackList);

        ShimStackSet shimStackSet2 = new ShimStackSet( 2, "4 разных", new Date(1999, 1, 5), "comment2", "tester2", false,
                shimStackList);

        List<ShimStackSet> shimStackSetList = new ArrayList<>();
        shimStackSetList.add(shimStackSet1);
        shimStackSetList.add(shimStackSet2);

        Record record = new Record("перед-зад", "CAR", new Date(1933, 2, 2), "PHONE", "CITY", shimStackSetList);
        dataSource.insert(record);
    }
}
