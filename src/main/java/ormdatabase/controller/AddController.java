package ormdatabase.controller;

import ormdatabase.model.DataSource;
import ormdatabase.model.Record;
import ormdatabase.model.ShimStackSet;

import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class AddController {

    private DataSource dataSource;

    private BaseViewController baseViewController;

    public void setParentController(BaseViewController baseViewController) {
        this.baseViewController = baseViewController;
    }

    void start() {
        BaseViewController.currentVersion = Controller.newRecord.getShimStackSetList().size();
        baseViewController.enableInputs();
        baseViewController.previousVersion.setOnAction(event -> baseViewController.viewPreviousVersion(Controller.newRecord));
        baseViewController.nextVersion.setOnAction(event -> baseViewController.viewNextVersion(Controller.newRecord));
        baseViewController.editRecord.setDisable(true);
        baseViewController.deleteVersion.setOnAction(event -> baseViewController.deleteVersion(Controller.newRecord));
        baseViewController.addVersion.setOnAction(event -> baseViewController.addVersion(Controller.newRecord));
        baseViewController.save.setOnAction(event -> saveRecord());
        baseViewController.viewRecord(Controller.newRecord);
    }

    public void initNewRecord() {
        Controller.newRecord = new Record();
        BaseViewController.currentVersion = 1;
        baseViewController.viewRecord(Controller.newRecord);
    }

    public void saveRecord() {
        baseViewController.saveObject(Controller.newRecord);
        dataSource.insert(Controller.newRecord);
        initNewRecord();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
