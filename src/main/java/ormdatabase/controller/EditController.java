package ormdatabase.controller;

import javafx.fxml.FXML;
import ormdatabase.model.Record;

import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class EditController extends BaseViewController {

    @FXML
    void initialize() {
        currentVersion = observableRecord.getShimStackSetList().size();
        setTypeComboBox();
        setLabels();
        setColumnProperties();
        setTableCellsEditable();
        setEditButtonsAction();

        if (Objects.isNull(editableRecord)) {
            System.out.println("editableRecord IS NULL");
//            editableRecord = new Record(observableRecord);
            editableRecord = observableRecord.clone();
//            editableRecord = observableRecord;
            System.out.println("observable == observable: ".concat(String.valueOf(observableRecord.getName()==observableRecord.getName())));
            System.out.println("editable == observable: ".concat(String.valueOf(editableRecord.getName()==observableRecord.getName())));
        }


//        staticView.setOnAction(event -> {
//            if (!isTablesValid() || name.getText().isEmpty()) {
//                requiredFieldsAlert();
//            } else {
//                saveVersion(editableRecord);
//                switchPane(event);
//            }
//        });

        previousVersion.setOnAction(event -> viewPreviousVersion(editableRecord));
        nextVersion.setOnAction(event -> viewNextVersion(editableRecord));
        editRecord.setDisable(true);
        deleteVersion.setOnAction(event -> deleteVersion(editableRecord));
        addVersion.setOnAction(event -> addVersion(editableRecord));
        save.setOnAction(event -> saveRecord());

        viewRecord(editableRecord);
    }

    public void saveObject() {
        if (!isTablesValid() || name.getText().isEmpty()) {
            requiredFieldsAlert();
        } else {
            editableRecord.setName(name.getText());
            editableRecord.setUppercaseName(name.getText().toUpperCase(Locale.ROOT));
            editableRecord.setCar(car.getText());
            editableRecord.setUppercaseCar(car.getText().toUpperCase(Locale.ROOT));
            editableRecord.setDate(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            editableRecord.setPhone(phone.getText());
            editableRecord.setCity(city.getText());
            editableRecord.setFavorites(favorites.isSelected());
            saveVersion(editableRecord);
        }
    }

    public void saveRecord() {
        saveObject();
        dataSource.update(editableRecord, observableRecord.getId());
//        observableRecord = new Record(editableRecord);
        observableRecord = editableRecord.clone();
    }
}
