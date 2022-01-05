package ormdatabase.controller;

import javafx.fxml.FXML;
import ormdatabase.model.DataSource;
import ormdatabase.model.Record;
import ormdatabase.model.ShimStackSet;

import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class AddController extends BaseViewController {

    private Record newRecord;

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
        save.setOnAction(event -> save(newRecord));

        resetNewRecord();
    }

    public void resetNewRecord() {
        newRecord = new Record();
        newRecord.addVersion(new ShimStackSet());
        int currentVersion = 1;
        viewVersion(newRecord, currentVersion);
    }

    public void save(Record record) {
        if (isTablesValid() && !name.getText().isEmpty()) {
            saveVersion(newRecord);
            record.setName(name.getText());
            record.setUppercaseName(name.getText().toUpperCase(Locale.ROOT));
            record.setCar(car.getText());
            record.setUppercaseCar(car.getText().toUpperCase(Locale.ROOT));
            record.setDate(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            record.setPhone(phone.getText());
            record.setCity(city.getText());
            record.setFavorites(favorites.isSelected());
            DataSource dataSource = new DataSource();
            dataSource.insert(record);
            resetNewRecord();
        }
    }
}
