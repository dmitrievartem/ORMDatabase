package ormdatabase.controller;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import ormdatabase.model.DataSource;
import ormdatabase.model.Record;

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
        Notifications.create()
                .owner(baseViewController.save.getScene().getWindow())
                .position(Pos.BOTTOM_RIGHT)
                .hideCloseButton()
                .text("Новая запись сохранена")
                .hideAfter(Duration.seconds(3))
                .show();
        initNewRecord();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
