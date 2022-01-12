package ormdatabase.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ViewController extends BaseViewController{

    private Button edit;

    @FXML
    public void initialize() {
        initMainFieldsAndButtons();
        disableInputs();
        previousVersion.setOnAction(event -> viewPreviousVersion(MainController.observableRecord));
        nextVersion.setOnAction(event -> viewNextVersion(MainController.observableRecord));
        editRecord.setDisable(false);
        editRecord.setOnAction(event -> edit.fire());
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }
}
