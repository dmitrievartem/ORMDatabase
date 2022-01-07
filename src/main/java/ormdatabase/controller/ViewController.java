package ormdatabase.controller;

import javafx.fxml.FXML;

public class ViewController extends BaseViewController {

    @FXML
    void initialize() {
        currentVersion = observableRecord.getShimStackSetList().size();
        setLabels();
        setColumnProperties();
        previousVersion.setOnAction(event -> viewPreviousVersion(observableRecord));
        nextVersion.setOnAction(event -> viewNextVersion(observableRecord));
        editRecord.setOnAction(event -> staticEdit.fire());
        disableInputs();
        viewRecord(observableRecord);
    }
}
