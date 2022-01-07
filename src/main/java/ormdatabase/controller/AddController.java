package ormdatabase.controller;

import javafx.fxml.FXML;
import ormdatabase.model.Record;
import ormdatabase.model.ShimStackSet;

import java.time.ZoneId;
import java.util.Date;
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
            resetNewRecord();
        } else {
            viewRecord(newRecord);
        }
    }

    public void resetNewRecord() {
        newRecord = new Record();
        newRecord.addVersion(new ShimStackSet());
        viewRecord(newRecord);
    }

    @Override
    public boolean saveObject(Record record) {
        if (!isTablesValid()) {
            requiredFieldsAlert();
            return false;
        } else {
            record.setName(name.getText());
            record.setCar(car.getText());
            record.setDate(Objects.isNull(date.getValue()) ? new Date() : Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            record.setPhone(phone.getText());
            record.setCity(city.getText());
            record.setFavorites(favorites.isSelected());
            saveVersion(record);
            return true;
        }
    }

    public void saveRecord() {
        saveObject(newRecord);
        dataSource.insert(newRecord);
        resetNewRecord();
    }
}
