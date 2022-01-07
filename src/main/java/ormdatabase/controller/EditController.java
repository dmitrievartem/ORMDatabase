package ormdatabase.controller;

import java.util.Objects;

public class EditController {

    private BaseViewController baseViewController;

    public void setParentController(BaseViewController baseViewController) {
        this.baseViewController = baseViewController;
    }

    void start() {
        baseViewController.currentVersion = Controller.observableRecord.getShimStackSetList().size();
        baseViewController.setTypeComboBox();
        baseViewController.setLabels();
        baseViewController.setColumnProperties();
        baseViewController.setTableCellsEditable();
        baseViewController.setEditButtonsAction();

        if (Objects.isNull(Controller.editableRecord)) {
            Controller.editableRecord = Controller.observableRecord.clone();
        }

        baseViewController.previousVersion.setOnAction(event -> baseViewController.viewPreviousVersion(Controller.editableRecord));
        baseViewController.nextVersion.setOnAction(event -> baseViewController.viewNextVersion(Controller.editableRecord));
        baseViewController.editRecord.setDisable(true);
        baseViewController.deleteVersion.setOnAction(event -> baseViewController.deleteVersion(Controller.editableRecord));
        baseViewController.addVersion.setOnAction(event -> baseViewController.addVersion(Controller.editableRecord));
        baseViewController.save.setOnAction(event -> saveRecord());

        baseViewController.viewRecord(Controller.editableRecord);
    }

    public void saveRecord() {
        baseViewController.saveObject(Controller.editableRecord);
        baseViewController.dataSource.update(Controller.editableRecord, Controller.observableRecord.getId());
        Controller.observableRecord = Controller.editableRecord.clone();
    }
}
