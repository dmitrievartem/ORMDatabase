package ormdatabase.controller;

public class EditController {

    private BaseViewController baseViewController;

    public void setParentController(BaseViewController baseViewController) {
        this.baseViewController = baseViewController;
    }

    void start() {
        baseViewController.currentVersion = Controller.editableRecord.getShimStackSetList().size();
        baseViewController.enableInputs();
        baseViewController.previousVersion.setOnAction(event -> baseViewController.viewPreviousVersion(Controller.editableRecord));
        baseViewController.nextVersion.setOnAction(event -> baseViewController.viewNextVersion(Controller.editableRecord));
        baseViewController.editRecord.setDisable(true);
        baseViewController.deleteVersion.setOnAction(event -> baseViewController.deleteVersion(Controller.editableRecord));
        baseViewController.addVersion.setOnAction(event -> baseViewController.addVersion(Controller.editableRecord));
        baseViewController.save.setOnAction(event -> {
            saveRecord();
            Controller.staticView.fire();
        });
        baseViewController.viewRecord(Controller.editableRecord);
    }

    public void saveRecord() {
        baseViewController.saveObject(Controller.editableRecord);
        baseViewController.dataSource.update(Controller.editableRecord, Controller.observableRecord.getId());
        Controller.observableRecord = Controller.editableRecord.clone();
    }
}
