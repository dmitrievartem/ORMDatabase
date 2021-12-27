package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import ormdatabase.SceneSwitcher;
import ormdatabase.model.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class AddViewController extends SceneSwitcher {

    Record record = new Record();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button favorites;

    @FXML
    private Button search;

    @FXML
    private Button settings;

    @FXML
    private Button view;

    @FXML
    private TextField nameField;

    @FXML
    private TextField carField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField cityField;

    @FXML
    private ComboBox<String> typeField;

    @FXML
    private Label versionLabel;

    @FXML
    private DatePicker versionDateField;

    @FXML
    private TextField commentField;

    @FXML
    private TextField authorField;

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
    private TableColumn<Shim, String> rt1numberColumn;

    @FXML
    private TableColumn<Shim, String> rt1diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt1thicknessColumn;

    @FXML
    private Button rt1addButton;

    @FXML
    private Button rt1deleteButton;

    @FXML
    private TableView<Shim> reboundTable2;

    @FXML
    private TableColumn<Shim, String> rt2numberColumn;

    @FXML
    private TableColumn<Shim, String> rt2diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt2thicknessColumn;

    @FXML
    private Button rt2addButton;

    @FXML
    private Button rt2deleteButton;

    @FXML
    private TableView<Shim> reboundTable3;

    @FXML
    private TableColumn<Shim, String> rt3numberColumn;

    @FXML
    private TableColumn<Shim, String> rt3diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt3thicknessColumn;

    @FXML
    private Button rt3addButton;

    @FXML
    private Button rt3deleteButton;

    @FXML
    private TableView<Shim> reboundTable4;

    @FXML
    private TableColumn<Shim, String> rt4numberColumn;

    @FXML
    private TableColumn<Shim, String> rt4diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt4thicknessColumn;

    @FXML
    private Button rt4addButton;

    @FXML
    private Button rt4deleteButton;

    @FXML
    private TableView<Shim> compressionTable1;

    @FXML
    private TableColumn<Shim, String> ct1numberColumn;

    @FXML
    private TableColumn<Shim, String> ct1diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct1thicknessColumn;

    @FXML
    private Button ct1addButton;

    @FXML
    private Button ct1deleteButton;

    @FXML
    private TableView<Shim> compressionTable2;

    @FXML
    private TableColumn<Shim, String> ct2numberColumn;

    @FXML
    private TableColumn<Shim, String> ct2diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct2thicknessColumn;

    @FXML
    private Button ct2addButton;

    @FXML
    private Button ct2deleteButton;

    @FXML
    private TableView<Shim> compressionTable3;

    @FXML
    private TableColumn<Shim, String> ct3numberColumn;

    @FXML
    private TableColumn<Shim, String> ct3diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct3thicknessColumn;

    @FXML
    private Button ct3addButton;

    @FXML
    private Button ct3deleteButton;

    @FXML
    private TableView<Shim> compressionTable4;

    @FXML
    private TableColumn<Shim, String> ct4numberColumn;

    @FXML
    private TableColumn<Shim, String> ct4diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct4thicknessColumn;

    @FXML
    private Button ct4addButton;

    @FXML
    private Button ct4deleteButton;

    private Label frontLeftLabel = new Label();
    private Label frontRightLabel = new Label();
    private Label rearLeftLabel = new Label();
    private Label rearRightLabel = new Label();

    private Label frontLabel = new Label();
    private Label rearLabel = new Label();

    private Label emptyLabel = new Label();

    @FXML
    void initialize() {
        dateField.setValue(LocalDate.now());
        versionDateField.setValue(LocalDate.now());

        List<String> typeList = FXCollections.observableArrayList(List.of("4 одинаковые", "4 разные", "перед-зад"));
        typeField.setItems(FXCollections.observableArrayList(typeList));

//        versionLabel.setText(String.valueOf(record.getShimStackSetList().size()).concat("/"));

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

//        List<Shim> shimList = FXCollections.observableArrayList();
//        Shim shim = new Shim("1", "0.34", "0.2");
//        shimList.add(shim);
//        shimList.add(shim);
//        shimList.add(shim);
//        shimList.add(shim);
//        reboundTable1.setItems(FXCollections.observableArrayList(shimList));

        List<TableColumn<Shim, String>> columnList = List.of(
                rt1numberColumn, rt1diameterColumn, rt1thicknessColumn,
                rt2numberColumn, rt2diameterColumn, rt2thicknessColumn,
                rt3numberColumn, rt3diameterColumn, rt3thicknessColumn,
                rt4numberColumn, rt4diameterColumn, rt4thicknessColumn,
                ct1numberColumn, ct1diameterColumn, ct1thicknessColumn,
                ct2numberColumn, ct2diameterColumn, ct2thicknessColumn,
                ct3numberColumn, ct3diameterColumn, ct3thicknessColumn,
                ct4numberColumn, ct4diameterColumn, ct4thicknessColumn
        );

        for (TableColumn<Shim, String> column : columnList) {
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Shim, String>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Shim, String> event) {
                            Shim shim = event.getTableView().getItems().get(event.getTablePosition().getRow());
                            switch (event.getTablePosition().getColumn()) {
                                case 0:
                                    shim.setNumber(event.getNewValue());
                                    break;
                                case 1:
                                    shim.setDiameter(event.getNewValue());
                                    break;
                                case 2:
                                    shim.setThickness(event.getNewValue());
                                    break;
                            }
                        }
                    }
            );
        }
    }

    @FXML
    public void setType(ActionEvent event) {
        ComboBox<?> typeComboBox = (ComboBox<?>) event.getSource();
        String selectedType = (String) typeComboBox.getValue();

        record.setType(selectedType);

        if (selectedType.equals("4 одинаковые")) {
            vBox1.getChildren().remove(0);
            vBox1.getChildren().add(0, emptyLabel);
            vBox2.getChildren().remove(0);
            vBox2.getChildren().add(0, frontRightLabel);
            vBox1.setDisable(false);
            vBox2.setDisable(true);
            vBox3.setDisable(true);
            vBox4.setDisable(true);
        } else if (selectedType.equals("перед-зад")) {
            vBox1.getChildren().remove(0);
            vBox1.getChildren().add(0, frontLabel);
            vBox2.getChildren().remove(0);
            vBox2.getChildren().add(0, rearLabel);
            vBox1.setDisable(false);
            vBox2.setDisable(false);
            vBox3.setDisable(true);
            vBox4.setDisable(true);
        } else if (selectedType.equals("4 разные")) {
            vBox1.getChildren().remove(0);
            vBox1.getChildren().add(0, frontLeftLabel);
            vBox2.getChildren().remove(0);
            vBox2.getChildren().add(0, frontRightLabel);
            vBox1.setDisable(false);
            vBox2.setDisable(false);
            vBox3.setDisable(false);
            vBox4.setDisable(false);
        }
    }

    @FXML
    public void addDeleteRow(ActionEvent event) {
        TableView<Shim> targetTable = null;
        Button btn = (Button) event.getSource();
        String id = btn.getId();

        switch (id) {
            default:
            case "rt1addButton":
            case "rt1deleteButton":
                targetTable = reboundTable1;
                break;
            case "ct1addButton":
            case "ct1deleteButton":
                targetTable = compressionTable1;
                break;
            case "rt2addButton":
            case "rt2deleteButton":
                targetTable = reboundTable2;
                break;
            case "ct2addButton":
            case "ct2deleteButton":
                targetTable = compressionTable2;
                break;
            case "rt3addButton":
            case "rt3deleteButton":
                targetTable = reboundTable3;
                break;
            case "ct3addButton":
            case "ct3deleteButton":
                targetTable = compressionTable3;
                break;
            case "rt4addButton":
            case "rt4deleteButton":
                targetTable = reboundTable4;
                break;
            case "ct4addButton":
            case "ct4deleteButton":
                targetTable = compressionTable4;
                break;
        }
        Shim newShim = new Shim("0", "0", "0");
        ObservableList<Shim> shims = targetTable.getItems();

        if (id.contains("add")) {
            shims.add(newShim);
        } else if (id.contains("delete") && targetTable.getItems().size() > 0) {
            shims.remove(targetTable.getItems().size() - 1);
        }
        targetTable.setItems(shims);
    }

    public boolean isTablesValid() {
//        if (nameField.getCharacters().toString().isBlank() || typeField.getSelectionModel().isEmpty()) {
//            return false;
//        }

        for (TableView<Shim> table : getActualTables()) {
            if (table.getItems().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void resetTables() {
        for (TableView<Shim> table : getActualTables()) {
            table.getItems().clear();
        }
    }

    public void addVersion() {
        if (isTablesValid()) {
            record.addShimStackSet(getCurrentShimStackSet());
        }
    }

    public ShimStackSet getCurrentShimStackSet() {
        ShimStackSet shimStackSet = new ShimStackSet();
        shimStackSet.setVersion(record.getShimStackSetList().size());
        Date date = Date.from(versionDateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        shimStackSet.setDate(date);
        shimStackSet.setComment(commentField.getText());
        shimStackSet.setAuthor(authorField.getText());
        shimStackSet.setShimStackList(getCurrentShimStackList(getActualTables()));
        return shimStackSet;
    }

    public List<TableView<Shim>> getActualTables() {
        List<TableView<Shim>> tableList = List.of(
                reboundTable1, compressionTable1,
                reboundTable2, compressionTable2,
                reboundTable3, compressionTable3,
                reboundTable4, compressionTable4
        );
        List<TableView<Shim>> actualTableList = new ArrayList<>();
        for (int i = 0; i < (record.getTypeNumber() * 2); i++) {
            actualTableList.add(tableList.get(i));
        }
        return actualTableList;
    }

    public List<StackPair> getCurrentShimStackList(List<TableView<Shim>> tableViewList) {
        List<StackPair> stackPairList = new ArrayList<>();
        StackPair stackPair = new StackPair();
        ReboundStack reboundStack = new ReboundStack();
        CompressionStack compressionStack = new CompressionStack();
        for (int i = 0; i < tableViewList.size(); i += 2) {
            reboundStack.setStack(new ArrayList<>(tableViewList.get(i).getItems()));
            compressionStack.setStack(new ArrayList<>(tableViewList.get(i + 1).getItems()));
            stackPair.setReboundStack(reboundStack);
            stackPair.setCompressionStack(compressionStack);
            stackPairList.add(stackPair);
        }
        return stackPairList;
    }
}