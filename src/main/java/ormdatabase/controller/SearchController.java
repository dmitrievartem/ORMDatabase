package ormdatabase.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import ormdatabase.SceneSwitcher;
import ormdatabase.model.*;

public class SearchController extends SceneSwitcher {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;@FXML
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
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

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
        List<Record> queryResults = dataSource.select(nameTextField.getText(), carTextField.getText());
        recordList = FXCollections.observableArrayList(queryResults);

        System.out.println(recordList.size());

        searchTable.setItems(recordList);
    }


    private void initData() {
        DataSource dataSource = new DataSource();
//        dataSource.insert(new Record("name", "car", new Date(20211201), "phobeNumber", "city"));
//        dataSource.insert(new Record("name22", "car22", new Date(20211202), "phobeNumber22", "city22"));
//        dataSource.insert(new Record("name333", "car333", new Date(20211203), "phobeNumber33", "city33"));

        CompressionStack compressionStack = new CompressionStack();
        ReboundStack reboundStack = new ReboundStack();

//        Float[] shim = {1f, 0.34f, 0.2f};

        Shim shim = new Shim(1f, 0.34f, 0.2f);
        List<Shim> shimList = new ArrayList<>();
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

        ShimStackSet shimStackSet1 = new ShimStackSet( 1, new Date(20212020), "comment", "tester", true,
                shimStackList);

        ShimStackSet shimStackSet2 = new ShimStackSet( 2, new Date(20212020), "comment2", "tester2", false,
                shimStackList);

        List<ShimStackSet> shimStackSetList = new ArrayList<>();
        shimStackSetList.add(shimStackSet1);
        shimStackSetList.add(shimStackSet2);

        Record record = new Record("NAME", "CAR", new Date(202022020), "PHONE", "CITY", "TYPE", shimStackSetList);
        dataSource.insert(record);
    }
}
