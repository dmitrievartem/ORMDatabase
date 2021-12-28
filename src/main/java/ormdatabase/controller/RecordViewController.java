package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import ormdatabase.SceneSwitcher;
import ormdatabase.model.Shim;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RecordViewController extends SceneSwitcher {

    private int currentVersion = observableRecord.getShimStackSetList().size() - 1;

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
    private TableView<Shim> reboundTable1;

    @FXML
    private TableColumn<Shim, Float> rt1numberColumn;

    @FXML
    private TableColumn<Shim, Float> rt1diameterColumn;

    @FXML
    private TableColumn<Shim, Float> rt1thicknessColumn;

    @FXML
    private TableView<Shim> reboundTable2;

    @FXML
    private TableColumn<Shim, Float> rt2numberColumn;

    @FXML
    private TableColumn<Shim, Float> rt2diameterColumn;

    @FXML
    private TableColumn<Shim, Float> rt2thicknessColumn;

    @FXML
    private TableView<Shim> reboundTable3;

    @FXML
    private TableColumn<Shim, Float> rt3numberColumn;

    @FXML
    private TableColumn<Shim, Float> rt3diameterColumn;

    @FXML
    private TableColumn<Shim, Float> rt3thicknessColumn;

    @FXML
    private TableView<Shim> reboundTable4;

    @FXML
    private TableColumn<Shim, Float> rt4numberColumn;

    @FXML
    private TableColumn<Shim, Float> rt4diameterColumn;

    @FXML
    private TableColumn<Shim, Float> rt4thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable1;

    @FXML
    private TableColumn<Shim, Float> ct1numberColumn;

    @FXML
    private TableColumn<Shim, Float> ct1diameterColumn;

    @FXML
    private TableColumn<Shim, Float> ct1thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable2;

    @FXML
    private TableColumn<Shim, Float> ct2numberColumn;

    @FXML
    private TableColumn<Shim, Float> ct2diameterColumn;

    @FXML
    private TableColumn<Shim, Float> ct2thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable3;

    @FXML
    private TableColumn<Shim, Float> ct3numberColumn;

    @FXML
    private TableColumn<Shim, Float> ct3diameterColumn;

    @FXML
    private TableColumn<Shim, Float> ct3thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable4;

    @FXML
    private TableColumn<Shim, Float> ct4numberColumn;

    @FXML
    private TableColumn<Shim, Float> ct4diameterColumn;

    @FXML
    private TableColumn<Shim, Float> ct4thicknessColumn;

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
    private Button search;

    @FXML
    private Button settings;

    @FXML
    private Label typeLabel;

    @FXML
    private Label versionLabel;

    @FXML
    private Label versionDateLabel;

    @FXML
    private Button view;

    @FXML
    void initialize() {
        System.out.println("--------------------------");
        System.out.println(currentVersion);

        ct1numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        ct1diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        ct1thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        ct2numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        ct2diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        ct2thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        ct3numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        ct3diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        ct3thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        ct4numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        ct4diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        ct4thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        rt1numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        rt1diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        rt1thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        rt2numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        rt2diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        rt2thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        rt3numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        rt3diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        rt3thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        rt4numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        rt4diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        rt4thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));

        setValues();
    }

    void setValues() {
        idLabel.setText(String.valueOf(observableRecord.getId()));
        nameLabel.setText(observableRecord.getName());
        carLabel.setText(observableRecord.getCar());
        dateLabel.setText(String.valueOf(observableRecord.getDate()));
        phoneLabel.setText(observableRecord.getPhone());
        cityLabel.setText(observableRecord.getCity());
        typeLabel.setText(observableRecord.getShimStackSetList().get(currentVersion - 1).getType());
        setVersion(currentVersion);
    }

    void setVersion(int targetVersion) {
        String versionAmount = String.valueOf(observableRecord.getShimStackSetList().size());
        versionLabel.setText(String.valueOf(targetVersion).concat("/").concat(versionAmount));
        versionDateLabel.setText(String.valueOf(observableRecord.getShimStackSetList().get(targetVersion - 1).getDate()));
        commentLabel.setText(observableRecord.getShimStackSetList().get(targetVersion - 1).getComment());
        authorLabel.setText(observableRecord.getShimStackSetList().get(targetVersion - 1).getAuthor());
        setTableValues(targetVersion);
        currentVersion = targetVersion;
    }

    void setNextVersion() {
        int versionAmount = observableRecord.getShimStackSetList().size();
        if (currentVersion + 1 <= versionAmount) {
            int targetVersion = currentVersion + 1;
            setVersion(targetVersion);
        }
    }

    void setPreviousVersion() {
        if (currentVersion - 1 > 0) {
            int targetVersion = currentVersion - 1;
            setVersion(targetVersion);
        }
    }

    void setTableValues(Integer targetVersion) {
        List<Pair<TableView<Shim>, TableView<Shim>>> tableList =
                Arrays.asList(new Pair<>(reboundTable1, compressionTable1),
                        new Pair<>(reboundTable2, compressionTable2),
                        new Pair<>(reboundTable3, compressionTable3),
                        new Pair<>(reboundTable4, compressionTable4)
                );
        List<Shim> reboundList;
        List<Shim> compressionList;
        for(int i = 0; i < observableRecord.getShimStackSetList().get(currentVersion - 1).getTypeNumber(); i++) {
            reboundList = observableRecord.getShimStackSetList().get(targetVersion - 1).getShimStackList().get(0).getReboundStack().getStack();
            compressionList = observableRecord.getShimStackSetList().get(targetVersion - 1).getShimStackList().get(0).getCompressionStack().getStack();

            tableList.get(i).getKey().setItems(FXCollections.observableArrayList(reboundList));
            tableList.get(i).getValue().setItems(FXCollections.observableArrayList(compressionList));
        }
    }
}
