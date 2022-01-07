package ormdatabase.controller;

import javafx.fxml.FXML;

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
            editableRecord = observableRecord.clone();
        }

        previousVersion.setOnAction(event -> viewPreviousVersion(editableRecord));
        nextVersion.setOnAction(event -> viewNextVersion(editableRecord));
        editRecord.setDisable(true);
        deleteVersion.setOnAction(event -> deleteVersion(editableRecord));
        addVersion.setOnAction(event -> addVersion(editableRecord));
        save.setOnAction(event -> saveRecord());

        viewRecord(editableRecord);
    }

    public void saveRecord() {
        saveObject(editableRecord);
        dataSource.update(editableRecord, observableRecord.getId());
        observableRecord = editableRecord.clone();
    }
}
