package ormdatabase.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import ormdatabase.DataSource;
import ormdatabase.entity.Record;

public class AddController extends BaseViewController{

    private DataSource dataSource;

    @FXML
    public void initialize() {
        initMainFieldsAndButtons();
        setFocusListener(MainController.newRecord);
        enableInputs();
        editRecord.setDisable(true);
        previousVersion.setOnAction(event -> viewPreviousVersion(MainController.newRecord));
        nextVersion.setOnAction(event -> viewNextVersion(MainController.newRecord));
        deleteVersion.setOnAction(event -> deleteVersion(MainController.newRecord));
        addVersion.setOnAction(event -> addVersion(MainController.newRecord));
        save.setOnAction(event -> saveRecord());
        viewRecord(MainController.newRecord);
    }

    public void initNewRecord() {
        MainController.newRecord = new Record();
        viewRecord(MainController.newRecord);
    }

    public void saveRecord() {
        saveObject(MainController.newRecord);
        dataSource.insert(MainController.newRecord);
        Notifications.create()
                .owner(save.getScene().getWindow())
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
