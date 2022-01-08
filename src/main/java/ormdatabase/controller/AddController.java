package ormdatabase.controller;

import ormdatabase.model.Record;
import ormdatabase.model.ShimStackSet;

import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class AddController {

    private BaseViewController baseViewController;

    public void setParentController(BaseViewController baseViewController) {
        this.baseViewController = baseViewController;
    }

    void start() {
        baseViewController.currentVersion = Controller.newRecord.getShimStackSetList().size();
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
        Controller.newRecord.addVersion(new ShimStackSet());
        baseViewController.currentVersion = 1;
        baseViewController.viewRecord(Controller.newRecord);
    }

    public boolean saveObject(Record record) {
        if (!baseViewController.isTablesValid()) {
            baseViewController.requiredFieldsAlert();
            return false;
        } else {
            System.out.println("saveObject(add) -------------- ");
            record.setName(baseViewController.name.getText());
            record.setCar(baseViewController.car.getText());
            record.setDate(Objects.isNull(baseViewController.date.getValue()) ? new Date() : Date.from(baseViewController.date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            record.setPhone(baseViewController.phone.getText());
            record.setCity(baseViewController.city.getText());
            record.setFavorites(baseViewController.favorites.isSelected());
            baseViewController.saveVersion(record);
            return true;
        }
    }

    public void saveRecord() {
        saveObject(Controller.newRecord);
        baseViewController.dataSource.insert(Controller.newRecord);
        initNewRecord();
    }
}
