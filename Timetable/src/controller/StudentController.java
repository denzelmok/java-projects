package controller;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.beans.property.*;
import java.io.*;
import java.text.*;
import au.edu.uts.ap.javafx.*;
import model.*;

public class StudentController extends Controller<Student> {
    @FXML private Text scholarTxt;
    @FXML private Text attendTxt;
    @FXML private ComboBox<Subject> subjectsCmb;
    @FXML private TableView<Activity> subjectActTv;
    @FXML private TableView<Activity> activityTv;
    @FXML private TableColumn<Activity, String> subjectClm;
    @FXML private TableColumn<Activity, String> subjectActClm;
    @FXML private Button enrolBtn;
    @FXML private Button withdrawBtn;
    @FXML private Button logoutBtn;
    
    @FXML
    private void handleEnrol(ActionEvent event) {
        try {
            getStudent().enrol(getCurrentActivity(), getCurrentActivity().getSubjectNumber(), getCurrentActivity().getGroup());
            subjectActTv.refresh();
            enrolBtn.setDisable(true);
        } catch (Exception e) { throw new RuntimeException(e); }
    }
    
    @FXML
    private void handleWithdraw(ActionEvent event) {
        try {
            getStudent().withdraw(getCurrentEnrolledActivity());
            subjectActTv.refresh();
            enrolBtn.setDisable(!getCurrentActivity().canEnrol());
        } catch (Exception e) { throw new RuntimeException(e); }
    }
    
    @FXML
    private void handleLogout(ActionEvent event) {
        stage.close();
    }
    
    @FXML
    private void initialize() {
        // set format of subject number
        subjectClm.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getSubject().toString().split(" ")[0]));
        subjectActClm.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getSubject().toString().split(" ")[0]));
        // set items in tableview
        subjectsCmb.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> {
            subjectActTv.setItems(newSubject.getActivities());
        });
        // set state of buttons
        subjectActTv.getSelectionModel().selectedItemProperty().addListener((observable, oldActivity, newActivity) -> {
            // if enrolled in subject and group, disable
            enrolBtn.setDisable(newActivity == null || !getCurrentActivity().canEnrol());
        });
        activityTv.getSelectionModel().selectedItemProperty().addListener((observable, oldActivity, newActivity) -> {
            withdrawBtn.setDisable(newActivity == null);
        });
        // attendance format
        if (getStudent().getAttendance().equals("ft"))
            attendTxt.setText("Full Time");
        else
            attendTxt.setText("Part Time");
        // scholarship format
        if (getStudent().getScholarship())
            scholarTxt.setText("Yes");
        else
            scholarTxt.setText("No");
        
    }
    
    public Subject getCurrentSubject() {
        return subjectsCmb.getSelectionModel().getSelectedItem();
    }
    
    public Activity getCurrentActivity() {
        return subjectActTv.getSelectionModel().getSelectedItem();
    }
    
    public Activity getCurrentEnrolledActivity() {
        return activityTv.getSelectionModel().getSelectedItem();
    }
    
    public final Student getStudent() { return model; }
}
