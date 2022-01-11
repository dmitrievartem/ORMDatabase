package ormdatabase.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import ormdatabase.model.DataSource;
import ormdatabase.model.Record;

public class EditController {

    private BaseViewController baseViewController;

    private DataSource dataSource;

    private Button view;

    public void setParentController(BaseViewController baseViewController) {
        this.baseViewController = baseViewController;
    }

    void start() {
        BaseViewController.currentVersion = Controller.editableRecord.getShimStackSetList().size();
        baseViewController.enableInputs();
        baseViewController.previousVersion.setOnAction(event -> baseViewController.viewPreviousVersion(Controller.editableRecord));
        baseViewController.nextVersion.setOnAction(event -> baseViewController.viewNextVersion(Controller.editableRecord));
        baseViewController.editRecord.setDisable(true);
        baseViewController.deleteVersion.setOnAction(event -> baseViewController.deleteVersion(Controller.editableRecord));
        baseViewController.addVersion.setOnAction(event -> baseViewController.addVersion(Controller.editableRecord));
        baseViewController.save.setOnAction(event -> {
            saveRecord();
            view.fire();
        });
        baseViewController.viewRecord(Controller.editableRecord);
    }

    public void saveRecord() {
        baseViewController.saveObject(Controller.editableRecord);
        dataSource.update(Controller.editableRecord, Controller.observableRecord.getId());
        Controller.observableRecord = new Record(Controller.editableRecord);
        Notifications.create()
                .owner(baseViewController.save.getScene().getWindow())
                .position(Pos.BOTTOM_RIGHT)
                .hideCloseButton()
                .text("Изменения сохранены")
                .hideAfter(Duration.seconds(3))
                .show();
    }

    public void setView(Button view) {
        this.view = view;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
