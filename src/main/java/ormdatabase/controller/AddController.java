package ormdatabase.controller;

import javafx.fxml.FXML;
import ormdatabase.model.DataSource;
import ormdatabase.model.Record;
import ormdatabase.model.ShimStackSet;

import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AddController extends BaseViewController {

    @FXML
    void initialize() {
        setTypeComboBox();
        setLabels();
        setColumnProperties();
        setTableCellsEditable();
        setEditButtonsAction();

        previousVersion.setOnAction(event -> viewPreviousVersion(newRecord));
        nextVersion.setOnAction(event -> viewNextVersion(newRecord));
        editRecord.setDisable(true);
        deleteVersion.setOnAction(event -> deleteVersion(newRecord));
        addVersion.setOnAction(event -> addVersion(newRecord));
        save.setOnAction(event -> saveRecord());

        if (Objects.isNull(newRecord)) {
            System.out.println("newRecord IS NULL");
            resetNewRecord();
        } else {
            viewRecord(newRecord);
        }
    }

    public void resetNewRecord() {
        newRecord = new Record();
        newRecord.addVersion(new ShimStackSet());
        int currentVersion = 1;
        viewVersion(newRecord, currentVersion);
    }

    @Override
    public boolean saveObject(Record record) {
        if (!isTablesValid()) {
            requiredFieldsAlert();
            return false;
        } else {
            record.setName(name.getText());
            record.setUppercaseName(name.getText().toUpperCase(Locale.ROOT));
            record.setCar(car.getText());
            record.setUppercaseCar(car.getText().toUpperCase(Locale.ROOT));
//            Date recordDate = Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            record.setDate(Objects.isNull(date.getValue()) ? new Date() : Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//            record.setDate(recordDate);
            record.setPhone(phone.getText());
            record.setCity(city.getText());
            record.setFavorites(favorites.isSelected());
            saveVersion(record);
            return true;
        }
    }

    public void saveRecord() {
        dataSource.insert(newRecord);
        resetNewRecord();
    }
}
