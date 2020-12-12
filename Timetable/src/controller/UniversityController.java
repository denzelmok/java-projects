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

public class UniversityController extends Controller<University> {
    @FXML private ListView<Student> studentsLv;
    @FXML private Button removeStudentBtn;
    @FXML private Button loginBtn;
    
    @FXML
    private void handleAdd(ActionEvent event) throws Exception {
        ViewLoader.showStage(getUniversity(), "/view/add_student.fxml", "Add Student", new Stage());
    }
    
    @FXML
    private void handleLogin(ActionEvent event) throws Exception {
        ViewLoader.showStage(getCurrentStudent(), "/view/student.fxml", "Student", new Stage());
    }
    
    @FXML
    private void handleRemove(ActionEvent event) {
        try {
            getUniversity().remove(getCurrentStudent());
        } catch (Exception e) { throw new RuntimeException(e); }
    }
    
    @FXML
    public void initialize() {
        studentsLv.getSelectionModel().selectedItemProperty().addListener((observable, oldStudent, newStudent) -> {
            removeStudentBtn.setDisable(newStudent == null);
            loginBtn.setDisable(newStudent == null);
        });
    }
    
    public Student getCurrentStudent() {
        return studentsLv.getSelectionModel().getSelectedItem();
    }
    
    public final University getUniversity() { return model; }
}
