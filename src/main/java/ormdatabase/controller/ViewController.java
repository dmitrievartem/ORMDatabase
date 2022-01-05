package ormdatabase.controller;

import javafx.fxml.FXML;

public class ViewController extends BaseViewController {

    @FXML
    void initialize() {
        setLabels();
        setColumnProperties();
        previousVersion.setOnAction(event -> viewPreviousVersion(observableRecord));
        nextVersion.setOnAction(event -> viewNextVersion(observableRecord));
        editRecord.setOnAction(event -> {
            switchPane("edit");
            switchButton(staticView, staticEdit);
        });
        disableInputs();
        viewRecord(observableRecord);
    }
}
