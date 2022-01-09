package ormdatabase.controller;

import javafx.scene.control.Button;

public class ViewController {

    private BaseViewController baseViewController;

    private Button edit;

    public void setParentController(BaseViewController baseViewController) {
        this.baseViewController = baseViewController;
    }

    void start() {
        BaseViewController.currentVersion = Controller.observableRecord.getShimStackSetList().size();
        baseViewController.disableInputs();
        baseViewController.previousVersion.setOnAction(event -> baseViewController.viewPreviousVersion(Controller.observableRecord));
        baseViewController.nextVersion.setOnAction(event -> baseViewController.viewNextVersion(Controller.observableRecord));
        baseViewController.editRecord.setDisable(false);
        baseViewController.editRecord.setOnAction(event -> edit.fire());
        baseViewController.viewRecord(Controller.observableRecord);
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }
}
