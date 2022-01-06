package ormdatabase.controller;

import javafx.fxml.FXML;
import ormdatabase.model.Record;

import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class EditController extends BaseViewController {

    @FXML
    void initialize() {
        currentVersion = observableRecord.getShimStackSetList().size();
        setTypeComboBox();
        setLabels();
        setColumnProperties();
        setTableCellsEditable();
        setEditButtonsAction();

        editableRecord = new Record(observableRecord);

        staticView.setOnAction(event -> {
            if (!isTablesValid() || name.getText().isEmpty()){
                requiredFieldsAlert();
            } else {
                saveVersion(editableRecord);
                switchPane(event);
            }
        });

        previousVersion.setOnAction(event -> viewPreviousVersion(editableRecord));
        nextVersion.setOnAction(event -> viewNextVersion(editableRecord));
        editRecord.setDisable(true);
        deleteVersion.setOnAction(event -> deleteVersion(editableRecord));
        addVersion.setOnAction(event -> addVersion(editableRecord));
        save.setOnAction(event -> save());

        viewRecord(editableRecord);
    }

    public void save() {
        if (!isTablesValid() || name.getText().isEmpty()){
            requiredFieldsAlert();
        } else {
            saveVersion(editableRecord);
            observableRecord = editableRecord;
            editableRecord.setName(name.getText());
            editableRecord.setUppercaseName(name.getText().toUpperCase(Locale.ROOT));
            editableRecord.setCar(car.getText());
            editableRecord.setUppercaseCar(car.getText().toUpperCase(Locale.ROOT));
            editableRecord.setDate(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            editableRecord.setPhone(phone.getText());
            editableRecord.setCity(city.getText());
            editableRecord.setFavorites(favorites.isSelected());
            dataSource.update(editableRecord, observableRecord.getId());
        }
    }
}
