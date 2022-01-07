package ormdatabase.controller;

public class ViewController extends BaseViewController {

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
