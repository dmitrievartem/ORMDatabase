package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import ormdatabase.SceneSwitcher;
import ormdatabase.model.Shim;

import java.util.Arrays;
import java.util.List;

public class ViewRecordController extends SceneSwitcher {

    int currentVersion = observableRecord.getShimStackSetList().size();

    @FXML
    private Label authorLabel;

    @FXML
    private Label carLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private CheckBox favoritesCheckBox;

    @FXML
    private Label commentLabel;

    private final Label frontLeftLabel = new Label();
    private final Label frontRightLabel = new Label();
    private final Label rearLeftLabel = new Label();
    private final Label rearRightLabel = new Label();

    private final Label frontLabel = new Label();
    private final Label rearLabel = new Label();

    private final Label emptyLabel = new Label();

    @FXML
    private VBox vBox1;

    @FXML
    private VBox vBox2;

    @FXML
    private VBox vBox3;

    @FXML
    private VBox vBox4;

    @FXML
    private TableView<Shim> reboundTable1;

    @FXML
    private TableView<Shim> reboundTable2;

    @FXML
    private TableView<Shim> reboundTable3;

    @FXML
    private TableView<Shim> reboundTable4;

    @FXML
    private TableView<Shim> compressionTable1;

    @FXML
    private TableView<Shim> compressionTable2;

    @FXML
    private TableView<Shim> compressionTable3;

    @FXML
    private TableView<Shim> compressionTable4;

    @FXML
    private Label dateLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label versionLabel;

    @FXML
    private Label versionDateLabel;

    @FXML
    void initialize() {
        favoritesCheckBox.setDisable(true);

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

        List<TableView<Shim>> tableViewList = List.of(
                reboundTable1, compressionTable1,
                reboundTable2, compressionTable2,
                reboundTable3, compressionTable3,
                reboundTable4, compressionTable4
        );

        for (TableView<Shim> tableView : tableViewList) {
            tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("number"));
            tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("diameter"));
            tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("thickness"));
        }

        viewRecord();
    }

    void viewRecord() {
        idLabel.setText(String.valueOf(observableRecord.getId()));
        nameLabel.setText(observableRecord.getName());
        carLabel.setText(observableRecord.getCar());
        dateLabel.setText(String.valueOf(observableRecord.getDate()));
        phoneLabel.setText(observableRecord.getPhone());
        cityLabel.setText(observableRecord.getCity());
        favoritesCheckBox.setSelected(observableRecord.isFavorites());
        viewVersion(currentVersion);
    }

    void setType(String selectedType) {
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

    void viewVersion(int targetVersion) {
        String versionAmount = String.valueOf(observableRecord.getShimStackSetList().size());
        versionLabel.setText(String.valueOf(targetVersion).concat("/").concat(versionAmount));
        versionDateLabel.setText(String.valueOf(observableRecord.getShimStackSetList().get(targetVersion - 1).getDate()));
        commentLabel.setText(observableRecord.getShimStackSetList().get(targetVersion - 1).getComment());
        authorLabel.setText(observableRecord.getShimStackSetList().get(targetVersion - 1).getAuthor());
        typeLabel.setText(observableRecord.getShimStackSetList().get(targetVersion - 1).getType());
        setType(typeLabel.getText());
        viewTableValues(targetVersion);
        currentVersion = targetVersion;
    }

    public void viewPreviousVersion() {
        if (currentVersion - 1 > 0) {
            int targetVersion = currentVersion - 1;
            viewVersion(targetVersion);
        }
    }

    public void viewNextVersion() {
        int versionAmount = observableRecord.getShimStackSetList().size();
        if (currentVersion + 1 <= versionAmount) {
            int targetVersion = currentVersion + 1;
            viewVersion(targetVersion);
        }
    }

    void viewTableValues(Integer targetVersion) {
        List<Pair<TableView<Shim>, TableView<Shim>>> tableList =
                Arrays.asList(new Pair<>(reboundTable1, compressionTable1),
                        new Pair<>(reboundTable2, compressionTable2),
                        new Pair<>(reboundTable3, compressionTable3),
                        new Pair<>(reboundTable4, compressionTable4)
                );
        List<Shim> reboundList;
        List<Shim> compressionList;
        for (int i = 0; i < observableRecord.getShimStackSetList().get(targetVersion - 1).getTypeNumber(); i++) {
            reboundList = observableRecord.getShimStackSetList().get(targetVersion - 1).getShimStackList().get(i).getReboundStack().getStack();
            compressionList = observableRecord.getShimStackSetList().get(targetVersion - 1).getShimStackList().get(i).getCompressionStack().getStack();

            tableList.get(i).getKey().setItems(FXCollections.observableArrayList(reboundList));
            tableList.get(i).getValue().setItems(FXCollections.observableArrayList(compressionList));
        }
    }
}
