package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ormdatabase.SceneSwitcher;
import ormdatabase.model.Shim;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecordViewController extends SceneSwitcher {

    private ObservableList<Shim> shimList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private Button addVersionButton;

    @FXML
    private Label authorLabel;

    @FXML
    private Label carLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label commentLabel;

    @FXML
    private TableView<Shim> compressionTable1;

    @FXML
    private TableColumn<Shim, Float> ct1numberColumn;

    @FXML
    private TableColumn<Shim, Float> ct1diameterColumn;

    @FXML
    private TableColumn<Shim, Float> ct1thicknessColumn;

    @FXML
    private TableView<?> compressionTable2;

    @FXML
    private TableView<?> compressionTable3;

    @FXML
    private TableView<?> compressionTable4;

    @FXML
    private Label dateLabel;

    @FXML
    private Button edit;

    @FXML
    private Button favorites;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button nextVersionButton;

    @FXML
    private Label phoneLabel;

    @FXML
    private Button previousVersionButton;

    @FXML
    private TableView<Shim> reboundTable1;

    @FXML
    private TableView<?> reboundTable2;

    @FXML
    private TableView<?> reboundTable3;

    @FXML
    private TableView<?> reboundTable4;

    @FXML
    private Button search;

    @FXML
    private Button settings;

    @FXML
    private Label typeLabel;

    @FXML
    private Label versionLabel;

    @FXML
    private Button view;

    @FXML
    void initialize() {
        idLabel.setText(String.valueOf(observableRecord.getId()));
        nameLabel.setText(observableRecord.getName());
        carLabel.setText(observableRecord.getCar());
        dateLabel.setText(String.valueOf(observableRecord.getDate()));
        phoneLabel.setText(observableRecord.getPhone());
        cityLabel.setText(observableRecord.getCity());
        typeLabel.setText(observableRecord.getType());
        commentLabel.setText(observableRecord.getShimStackSetList().get(0).getComment());
        versionLabel.setText(String.valueOf(observableRecord.getShimStackSetList().get(0).getVersion()).concat("/").concat(String.valueOf(observableRecord.getShimStackSetList().size())));
        authorLabel.setText(observableRecord.getShimStackSetList().get(0).getAuthor());

        ct1numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        ct1diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        ct1thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));

//        List<Shim> reboundShim = FXCollections.observableArrayList();
//        reboundShim = observableRecord.getShimStackSetList().get(0).getShimStackList().get(0).getCompressionStack().getStack();
//        compressionTable1.setItems((ObservableList<Shim>) reboundShim);


        List<Shim> list = observableRecord.getShimStackSetList().get(0).getShimStackList().get(0).getCompressionStack().getStack();
        shimList = FXCollections.observableArrayList(list);
        compressionTable1.setItems(shimList);
        reboundTable1.setItems(shimList);
    }

}
