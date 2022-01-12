package ormdatabase.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import ormdatabase.DataSource;
import ormdatabase.entity.Record;

public class EditController extends BaseViewController {

    private DataSource dataSource;

    @FXML
    public void initialize() {
        initMainFieldsAndButtons();
        setFocusListener(MainController.editableRecord);
        enableInputs();
        editRecord.setDisable(true);
        previousVersion.setOnAction(event -> viewPreviousVersion(MainController.editableRecord));
        nextVersion.setOnAction(event -> viewNextVersion(MainController.editableRecord));
        deleteVersion.setOnAction(event -> deleteVersion(MainController.editableRecord));
        addVersion.setOnAction(event -> addVersion(MainController.editableRecord));
        save.setOnAction(event -> saveRecord());
    }

    public void saveRecord() {
        saveObject(MainController.editableRecord);
        dataSource.update(MainController.editableRecord, MainController.observableRecord.getId());
        MainController.observableRecord = new Record(MainController.editableRecord);
        Notifications.create()
                .owner(save.getScene().getWindow())
                .position(Pos.BOTTOM_RIGHT)
                .hideCloseButton()
                .text("Изменения сохранены")
                .hideAfter(Duration.seconds(3))
                .show();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
