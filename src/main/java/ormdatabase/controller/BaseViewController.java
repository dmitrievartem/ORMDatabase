package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import ormdatabase.model.*;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class BaseViewController extends Controller {

    protected int currentVersion = 1;
    protected final DataSource dataSource = new DataSource();

    @FXML
    protected Label id;

    @FXML
    protected TextField name;

    @FXML
    protected TextField car;

    @FXML
    protected DatePicker date;

    @FXML
    protected TextField phone;

    @FXML
    protected TextField city;

    @FXML
    protected CheckBox favorites;

    @FXML
    protected Label version;

    @FXML
    protected ComboBox<String> type;

    @FXML
    protected DatePicker versionDate;

    @FXML
    protected TextArea comment;

    @FXML
    protected TextField author;

    @FXML
    protected Button previousVersion;

    @FXML
    protected Button nextVersion;

    @FXML
    protected Button deleteVersion;

    @FXML
    protected Button addVersion;

    @FXML
    protected Button editRecord;

    @FXML
    protected Button save;

    protected final Label frontLeftLabel = new Label();
    protected final Label frontRightLabel = new Label();
    protected final Label rearLeftLabel = new Label();
    protected final Label rearRightLabel = new Label();

    protected final Label frontLabel = new Label();
    protected final Label rearLabel = new Label();

    protected final Label emptyLabel = new Label();

    @FXML
    protected VBox vBox1;

    @FXML
    protected VBox vBox2;

    @FXML
    protected VBox vBox3;

    @FXML
    protected VBox vBox4;

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
    private Button rt1resetButton;

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
    private Button rt2resetButton;

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
    private Button rt3resetButton;

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
    private Button rt4resetButton;

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
    private Button ct1resetButton;

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
    private Button ct2resetButton;

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
    private Button ct3resetButton;

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

    @FXML
    private Button ct4resetButton;

    protected void viewRecord(Record record) {
        id.setText(Objects.isNull(record.getId()) ? "Id" : String.valueOf(record.getId()));
        name.setText(record.getName());
        car.setText(record.getCar());
        LocalDate localDate = record.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        date.setValue(Objects.isNull(localDate) ? LocalDate.now() : localDate);
        phone.setText(record.getPhone());
        city.setText(record.getCity());
        favorites.setSelected(record.isFavorites());
        viewVersion(record, currentVersion);
    }

    protected void setType(String selectedType) {
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

    protected void viewVersion(Record record, int targetVersion) {
        if (record.getShimStackSetList().size() > 0) {
            ShimStackSet shimStackSet = record.getShimStackSetList().get(targetVersion - 1);
            String versionAmount = String.valueOf(record.getShimStackSetList().size());
            version.setText(String.valueOf(targetVersion).concat("/").concat(versionAmount));
            versionDate.setValue(shimStackSet.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            comment.setText(shimStackSet.getComment());
            author.setText(shimStackSet.getAuthor());
            type.getSelectionModel().select(shimStackSet.getType());
            setType(shimStackSet.getType());
            currentVersion = targetVersion;
        }

        viewTableValues(record, targetVersion);
    }

    public void viewPreviousVersion(Record record) {
        if (currentVersion - 1 > 0 && isTablesValid()) {
            saveVersion(record);
            int targetVersion = currentVersion - 1;
            viewVersion(record, targetVersion);
        }
    }

    public void viewNextVersion(Record record) {
        int versionAmount = record.getShimStackSetList().size();
        if (currentVersion + 1 <= versionAmount && isTablesValid()) {
            saveVersion(record);
            int targetVersion = currentVersion + 1;
            viewVersion(record, targetVersion);
        }
    }

    protected void viewTableValues(Record record, Integer targetVersion) {
        List<Pair<TableView<Shim>, TableView<Shim>>> tableList =
                Arrays.asList(new Pair<>(reboundTable1, compressionTable1),
                        new Pair<>(reboundTable2, compressionTable2),
                        new Pair<>(reboundTable3, compressionTable3),
                        new Pair<>(reboundTable4, compressionTable4)
                );
        ShimStackSet targetShimStackSet = record.getShimStackSetList().get(targetVersion - 1);
        List<Shim> reboundList = List.of(new Shim("0", "0", "0"));
        List<Shim> compressionList = List.of(new Shim("0", "0", "0"));
        for (int i = 0; i < targetShimStackSet.getTypeNumber(); i++) {
            if (targetShimStackSet.getShimStackList().size() > 0) {
                reboundList = targetShimStackSet.getShimStackList().get(i).getReboundStack().getStack();
                compressionList = targetShimStackSet.getShimStackList().get(i).getCompressionStack().getStack();
            }
            tableList.get(i).getKey().setItems(FXCollections.observableArrayList(reboundList));
            tableList.get(i).getValue().setItems(FXCollections.observableArrayList(compressionList));
        }
    }

    protected void disableInputs() {
        name.setEditable(false);
        car.setEditable(false);
        phone.setEditable(false);
        city.setEditable(false);
        favorites.setDisable(true);
        comment.setEditable(false);
        author.setEditable(false);
        for (TableView<Shim> tableView : getAllTables()) {
            tableView.setEditable(false);
        }

        date.setDisable(true);
        type.setDisable(true);
        versionDate.setDisable(true);

        deleteVersion.setDisable(true);
        addVersion.setDisable(true);
        save.setDisable(true);
        for (Button button : getAllTableButtons()) {
            button.setDisable(true);
        }
    }

    protected void setLabels() {
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
    }

    protected void deleteVersion(Record record) {
        int versionAmount = record.getShimStackSetList().size();
        int targetVersion;
        if (versionAmount > 1) {
            if (currentVersion == 1) {
                targetVersion = 1;
            } else if (currentVersion == versionAmount) {
                targetVersion = versionAmount - 1;
            } else {
                targetVersion = currentVersion - 1;
            }
            record.deleteVersion(currentVersion - 1);
            viewVersion(record, targetVersion);
        }
    }

    protected void addVersion(Record record) {
        if (isTablesValid()) {
            record.setVersion(currentVersion - 1, getCurrentShimStackSet());
            record.addVersion(new ShimStackSet());
            viewVersion(record, record.getShimStackSetList().size());
            resetTables();
        }
    }

    public void saveVersion(Record record) {
        record.setVersion(currentVersion - 1, getCurrentShimStackSet());
    }


    protected boolean isTablesValid() {

        for (TableView<Shim> table : getActualTables()) {
            if (table.getItems().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    protected void resetTables() {
        for (TableView<Shim> table : getActualTables()) {
            table.getItems().clear();
        }
    }

    protected ShimStackSet getCurrentShimStackSet() {
        ShimStackSet shimStackSet = new ShimStackSet();
        shimStackSet.setType(type.getSelectionModel().getSelectedItem());
        Date date = Date.from(versionDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        shimStackSet.setDate(date);
        shimStackSet.setComment(comment.getText());
        shimStackSet.setAuthor(author.getText());
        shimStackSet.setShimStackList(getCurrentShimStackPairList(getActualTables()));
        return shimStackSet;
    }

    protected List<StackPair> getCurrentShimStackPairList(List<TableView<Shim>> tableViewList) {
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

    protected void setColumnProperties() {
        for (TableView<Shim> tableView : getAllTables()) {
            tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("number"));
            tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("diameter"));
            tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("thickness"));
        }
    }

    protected void setTableCellsEditable() {
        List<TextField> onlyTextFields = List.of(name, car, city, author);
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
        phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue) && !newValue.matches("\\d*")) {
                phone.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (Objects.nonNull(newValue) && newValue.length() > 11) {
                phone.setText(newValue.substring(0, 11));
            }
        });
        comment.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue) && newValue.length() > 500) {
                comment.setText(newValue.substring(0, 500));
            }
        });
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
    }

    protected void setTypeComboBox() {
        List<String> typeList = FXCollections.observableArrayList(List.of("4 одинаковые", "4 разные", "перед-зад"));
        type.setItems(FXCollections.observableArrayList(typeList));
        type.getSelectionModel().select("4 разные");
        type.setOnAction(event -> setType(type.getSelectionModel().getSelectedItem()));
    }

    private List<TableView<Shim>> getAllTables() {
        return List.of(
                reboundTable1, compressionTable1,
                reboundTable2, compressionTable2,
                reboundTable3, compressionTable3,
                reboundTable4, compressionTable4
        );
    }

    protected List<TableView<Shim>> getActualTables() {
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

    protected int getTypeNumber() {
        switch (type.getSelectionModel().getSelectedItem()) {
            case "4 одинаковые":
                return 1;
            case "перед-зад":
                return 2;
            case "4 разные":
            default:
                return 4;
        }
    }

    private List<Button> getAllTableButtons() {
        return List.of(
                rt1addButton, rt2addButton, rt3addButton, rt4addButton,
                rt1deleteButton, rt2deleteButton, rt3deleteButton, rt4deleteButton,
                rt1resetButton, rt2resetButton, rt3resetButton, rt4resetButton,
                ct1addButton, ct2addButton, ct3addButton, ct4addButton,
                ct1deleteButton, ct2deleteButton, ct3deleteButton, ct4deleteButton,
                ct1resetButton, ct2resetButton, ct3resetButton, ct4resetButton
        );
    }

    @SuppressWarnings("unchecked")
    protected void addTableRow(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        ObservableList<Shim> shims = targetTable.getItems();
        shims.add(new Shim("0", "0", "0"));
        targetTable.setItems(shims);
    }

    @SuppressWarnings("unchecked")
    protected void deleteTableRow(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        ObservableList<Shim> shims = targetTable.getItems();
        shims.remove(targetTable.getItems().size() - 1);
        targetTable.setItems(shims);
    }

    @SuppressWarnings("unchecked")
    protected void resetTable(ActionEvent event) {
        VBox parentVBox = (VBox) ((Button) event.getSource()).getParent().getParent();
        TableView<Shim> targetTable = (TableView<Shim>) parentVBox.getChildren().get(0);
        targetTable.getItems().clear();
    }

    protected void setAddButtonAction() {
        List<Button> buttonList = List.of(
                rt1addButton, rt2addButton, rt3addButton, rt4addButton,
                ct1addButton, ct2addButton, ct3addButton, ct4addButton
        );
        for (Button button : buttonList) {
            button.setOnAction(this::addTableRow);
        }
    }

    protected void setDeleteButtonAction() {
        List<Button> buttonList = List.of(
                rt1deleteButton, rt2deleteButton, rt3deleteButton, rt4deleteButton,
                ct1deleteButton, ct2deleteButton, ct3deleteButton, ct4deleteButton
        );
        for (Button button : buttonList) {
            button.setOnAction(this::deleteTableRow);
        }
    }

    protected void setResetButtonAction() {
        List<Button> buttonList = List.of(
                rt1resetButton, rt2resetButton, rt3resetButton, rt4resetButton,
                ct1resetButton, ct2resetButton, ct3resetButton, ct4resetButton
        );
        for (Button button : buttonList) {
            button.setOnAction(this::resetTable);
            Image icon = new Image(new File("reset.png").toURI().toString());
            Objects.requireNonNull(icon);
            button.setGraphic(new ImageView(icon));
        }
    }

    protected void setEditButtonsAction() {
        setAddButtonAction();
        setDeleteButtonAction();
        setResetButtonAction();
    }

    protected void requiredFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setContentText("Заполняй все как надо блять");
        alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(Controller.class.getResource("light.css")).toExternalForm());
        alert.showAndWait();
    }

    public boolean saveObject(Record record) {
        if (!isTablesValid() || name.getText().isEmpty()) {
            requiredFieldsAlert();
            return false;
        } else {
            record.setName(name.getText());
            record.setUppercaseName(name.getText().toUpperCase(Locale.ROOT));
            record.setCar(car.getText());
            record.setUppercaseCar(car.getText().toUpperCase(Locale.ROOT));
            record.setDate(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            record.setPhone(phone.getText());
            record.setCity(city.getText());
            record.setFavorites(favorites.isSelected());
            saveVersion(record);
            return true;
        }
    }
}
