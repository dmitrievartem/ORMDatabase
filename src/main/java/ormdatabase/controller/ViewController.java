package ormdatabase.controller;

public class ViewController {

    private BaseViewController baseViewController;

    public void setParentController(BaseViewController baseViewController) {
        this.baseViewController = baseViewController;
    }

    void start() {
        baseViewController.currentVersion = Controller.observableRecord.getShimStackSetList().size();
        baseViewController.disableInputs();
        baseViewController.previousVersion.setOnAction(event -> baseViewController.viewPreviousVersion(Controller.observableRecord));
        baseViewController.nextVersion.setOnAction(event -> baseViewController.viewNextVersion(Controller.observableRecord));
        baseViewController.editRecord.setDisable(false);
        baseViewController.editRecord.setOnAction(event -> Controller.staticEdit.fire());
        baseViewController.viewRecord(Controller.observableRecord);
    }
}
