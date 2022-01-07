package ormdatabase.controller;

import ormdatabase.model.Record;

public class ViewController {
//public class ViewController extends BaseViewController {

//    private Record record;
//
//    public ViewController(Record record) {
//        this.record = record;
//    }

    private BaseViewController baseViewController;

    public void setParentController(BaseViewController baseViewController) {
        this.baseViewController = baseViewController;
    }

    void start() {
        baseViewController.currentVersion = Controller.observableRecord.getShimStackSetList().size();
        baseViewController.previousVersion.setOnAction(event -> baseViewController.viewPreviousVersion(Controller.observableRecord));
        baseViewController.nextVersion.setOnAction(event -> baseViewController.viewNextVersion(Controller.observableRecord));
        baseViewController.editRecord.setOnAction(event -> Controller.staticEdit.fire());
        baseViewController.disableInputs();
        baseViewController.editRecord.setDisable(false);
        baseViewController.viewRecord(Controller.observableRecord);
    }
}
