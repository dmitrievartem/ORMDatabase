package ormdatabase.controller;

import javafx.fxml.FXML;
import ormdatabase.model.Record;

import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class EditController extends BaseViewController {

    @FXML
    void initialize() {
        setTypeComboBox();
        setLabels();
        setColumnProperties();
        setTableCellsEditable();
        setEditButtonsAction();

        staticView.setOnAction(event -> {
            if (!isTablesValid() || name.getText().isEmpty()){
                requiredFieldsAlert();
            } else {
                saveVersion(observableRecord);
                switchPane(event);
            }
        });

        previousVersion.setOnAction(event -> viewPreviousVersion(observableRecord));
        nextVersion.setOnAction(event -> viewNextVersion(observableRecord));
        editRecord.setDisable(true);
        deleteVersion.setOnAction(event -> deleteVersion(observableRecord));
        addVersion.setOnAction(event -> addVersion(observableRecord));
        save.setOnAction(event -> save(observableRecord));

        viewRecord(observableRecord);
    }

    public void save(Record record) {
        if (!isTablesValid() || name.getText().isEmpty()){
            requiredFieldsAlert();
        } else {
            saveVersion(observableRecord);
            record.setName(name.getText());
            record.setUppercaseName(name.getText().toUpperCase(Locale.ROOT));
            record.setCar(car.getText());
            record.setUppercaseCar(car.getText().toUpperCase(Locale.ROOT));
            record.setDate(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            record.setPhone(phone.getText());
            record.setCity(city.getText());
            record.setFavorites(favorites.isSelected());
            dataSource.update(record, observableRecord.getId());
        }
    }
}
