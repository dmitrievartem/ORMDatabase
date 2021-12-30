package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import ormdatabase.SceneSwitcher;
import ormdatabase.model.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class AddRecordController extends SceneSwitcher {

    private Record newRecord;
    private int currentVersion;

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
    private CheckBox favoritesCheckBox;

    @FXML
    private ComboBox<String> versionTypeField;

    @FXML
    private Label versionLabel;

    @FXML
    private DatePicker versionDateField;

    @FXML
    private TextField commentField;

    @FXML
    private TextField authorField;

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
    private TableColumn<Shim, String> rt1numberColumn;

    @FXML
    private TableColumn<Shim, String> rt1diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt1thicknessColumn;

    @FXML
    private TableView<Shim> reboundTable2;

    @FXML
    private TableColumn<Shim, String> rt2numberColumn;

    @FXML
    private TableColumn<Shim, String> rt2diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt2thicknessColumn;

    @FXML
    private TableView<Shim> reboundTable3;

    @FXML
    private TableColumn<Shim, String> rt3numberColumn;

    @FXML
    private TableColumn<Shim, String> rt3diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt3thicknessColumn;

    @FXML
    private TableView<Shim> reboundTable4;

    @FXML
    private TableColumn<Shim, String> rt4numberColumn;

    @FXML
    private TableColumn<Shim, String> rt4diameterColumn;

    @FXML
    private TableColumn<Shim, String> rt4thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable1;

    @FXML
    private TableColumn<Shim, String> ct1numberColumn;

    @FXML
    private TableColumn<Shim, String> ct1diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct1thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable2;

    @FXML
    private TableColumn<Shim, String> ct2numberColumn;

    @FXML
    private TableColumn<Shim, String> ct2diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct2thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable3;

    @FXML
    private TableColumn<Shim, String> ct3numberColumn;

    @FXML
    private TableColumn<Shim, String> ct3diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct3thicknessColumn;

    @FXML
    private TableView<Shim> compressionTable4;

    @FXML
    private TableColumn<Shim, String> ct4numberColumn;

    @FXML
    private TableColumn<Shim, String> ct4diameterColumn;

    @FXML
    private TableColumn<Shim, String> ct4thicknessColumn;

    @FXML
    void initialize() {
        List<TextField> onlyTextFields = List.of(nameField, carField, cityField, authorField);
        for (TextField textField : onlyTextFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (Objects.nonNull(newValue) && !newValue.matches("[(][)]\\s\\p{L}*")) {
                    textField.setText(newValue.replaceAll("[^[(][)]\\s\\p{L}*]", ""));
                }
                if (Objects.nonNull(newValue) && newValue.length() > 50) {
                    textField.setText(newValue.substring(0, 50));
                }
            });
        }
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue) && !newValue.matches("\\d*")) {
                phoneField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (Objects.nonNull(newValue) && newValue.length() > 11) {
                phoneField.setText(newValue.substring(0, 11));
            }
        });
        commentField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue) && newValue.length() > 500) {
                commentField.setText(newValue.substring(0, 500));
            }
        });

        List<String> typeList = FXCollections.observableArrayList(List.of("4 одинаковые", "4 разные", "перед-зад"));
        versionTypeField.setItems(FXCollections.observableArrayList(typeList));
        versionTypeField.getSelectionModel().select("4 разные");

        versionDateField.setValue(LocalDate.now());
        dateField.setValue(LocalDate.now());

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
                    event -> {
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
            );
        }

        resetNewRecord();
    }

    public void resetNewRecord() {
        newRecord = new Record();
        newRecord.addVersion(new ShimStackSet());
        currentVersion = 1;
        viewVersion(currentVersion);
    }

    void viewVersion(int targetVersion) {
        currentVersion = targetVersion;
        ShimStackSet targetShimStackSet = newRecord.getShimStackSetList().get(targetVersion - 1);
        String versionAmount = String.valueOf(newRecord.getShimStackSetList().size());
        versionLabel.setText(String.valueOf(targetVersion).concat("/").concat(versionAmount));
        versionTypeField.getSelectionModel().select(targetShimStackSet.getType());
        setType(targetShimStackSet.getType());
        Date date = targetShimStackSet.getDate();
        versionDateField.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        commentField.setText(targetShimStackSet.getComment());
        authorField.setText(targetShimStackSet.getAuthor());
        viewTableValues(targetVersion);
    }

    void viewTableValues(Integer targetVersion) {
        List<Pair<TableView<Shim>, TableView<Shim>>> tableList =
                Arrays.asList(new Pair<>(reboundTable1, compressionTable1),
                        new Pair<>(reboundTable2, compressionTable2),
                        new Pair<>(reboundTable3, compressionTable3),
                        new Pair<>(reboundTable4, compressionTable4)
                );
        ShimStackSet targetShimStackSet = newRecord.getShimStackSetList().get(targetVersion - 1);
        if (targetShimStackSet.getShimStackList().size() > 0) {
            List<Shim> reboundList;
            List<Shim> compressionList;
            for (int i = 0; i < newRecord.getShimStackSetList().get(targetVersion - 1).getTypeNumber(); i++) {
                reboundList = targetShimStackSet.getShimStackList().get(i).getReboundStack().getStack();
                compressionList = targetShimStackSet.getShimStackList().get(i).getCompressionStack().getStack();
                tableList.get(i).getKey().setItems(FXCollections.observableArrayList(reboundList));
                tableList.get(i).getValue().setItems(FXCollections.observableArrayList(compressionList));
            }
        }
    }

    public void setTypeFromEvent(ActionEvent event) {
        ComboBox<?> typeComboBox = (ComboBox<?>) event.getSource();
        String selectedType = (String) typeComboBox.getValue();
        setType(selectedType);
    }

    public void setType(String selectedType) {
        List<ShimStackSet> shimStackSetList = newRecord.getShimStackSetList();
        ShimStackSet shimStackSet = shimStackSetList.get(currentVersion - 1);
        shimStackSet.setType(selectedType);
        shimStackSetList.set(currentVersion - 1, shimStackSet);
        newRecord.setShimStackSetList(shimStackSetList);

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

    public void viewPreviousVersion() {
        if (currentVersion - 1 > 0 && isTablesValid()) {
            saveVersion();
            int targetVersion = currentVersion - 1;
            viewVersion(targetVersion);
        }
    }

    public void viewNextVersion() {
        int versionAmount = newRecord.getShimStackSetList().size();
        if (currentVersion + 1 <= versionAmount && isTablesValid()) {
            saveVersion();
            int targetVersion = currentVersion + 1;
            viewVersion(targetVersion);
        }
    }

    public boolean isTablesValid() {

        for (TableView<Shim> table : getActualTables()) {
            if (table.getItems().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public List<TableView<Shim>> getActualTables() {
        List<TableView<Shim>> tableList = List.of(
                reboundTable1, compressionTable1,
                reboundTable2, compressionTable2,
                reboundTable3, compressionTable3,
                reboundTable4, compressionTable4
        );
        List<TableView<Shim>> actualTableList = new ArrayList<>();
        for (int i = 0; i < (getTypeNumber() * 2); i++) {
            actualTableList.add(tableList.get(i));
        }
        return actualTableList;
    }

    public void saveVersion() {
        newRecord.setVersion(currentVersion - 1, getCurrentShimStackSet());
    }

    public ShimStackSet getCurrentShimStackSet() {
        ShimStackSet shimStackSet = new ShimStackSet();
        shimStackSet.setType(versionTypeField.getSelectionModel().getSelectedItem());
        Date date = Date.from(versionDateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        shimStackSet.setDate(date);
        shimStackSet.setComment(commentField.getText());
        shimStackSet.setAuthor(authorField.getText());
        shimStackSet.setShimStackList(getCurrentShimStackPairList(getActualTables()));
        return shimStackSet;
    }

    public List<StackPair> getCurrentShimStackPairList(List<TableView<Shim>> tableViewList) {
        List<StackPair> stackPairList = new ArrayList<>();
        for (int i = 0; i < tableViewList.size(); i += 2) {
            stackPairList.add(
                    new StackPair(
                            new ReboundStack(new ArrayList<>(tableViewList.get(i).getItems())),
                            new CompressionStack(new ArrayList<>(tableViewList.get(i + 1).getItems())))
            );
        }
        return stackPairList;
    }

    public int getTypeNumber() {
        switch (versionTypeField.getSelectionModel().getSelectedItem()) {
            case "4 одинаковые":
                return 1;
            case "перед-зад":
                return 2;
            case "4 разные":
            default:
                return 4;
        }
    }

    public void deleteVersion() {
        int versionAmount = newRecord.getShimStackSetList().size();
        int targetVersion;
        if (versionAmount > 1) {
            if (currentVersion == 1) {
                targetVersion = 1;
            } else if (currentVersion == versionAmount) {
                targetVersion = versionAmount - 1;
            } else {
                targetVersion = currentVersion - 1;
            }
            newRecord.deleteVersion(currentVersion - 1);
            viewVersion(targetVersion);
        }
    }

    public void addVersion() {
        if (isTablesValid()) {
            newRecord.setVersion(currentVersion - 1, getCurrentShimStackSet());
            newRecord.addVersion(new ShimStackSet());
            viewVersion(newRecord.getShimStackSetList().size());
            resetTables();
        }
    }

    public void resetTables() {
        for (TableView<Shim> table : getActualTables()) {
            table.getItems().clear();
        }
    }

    public void addDeleteRow(ActionEvent event) {
        TableView<Shim> targetTable;
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

    public void saveNewRecord() {
        if (isTablesValid() && !nameField.getText().isEmpty()) {
            saveVersion();
            newRecord.setName(nameField.getText());
            newRecord.setUppercaseName(nameField.getText().toUpperCase(Locale.ROOT));
            newRecord.setCar(carField.getText());
            newRecord.setUppercaseCar(carField.getText().toUpperCase(Locale.ROOT));
            Date date = Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            newRecord.setDate(date);
            newRecord.setPhone(phoneField.getText());
            newRecord.setCity(cityField.getText());
            newRecord.setFavorites(favoritesCheckBox.isSelected());
            DataSource dataSource = new DataSource();
            dataSource.insert(newRecord);
            resetNewRecord();
        }
    }
}