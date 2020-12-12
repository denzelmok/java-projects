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

public class AddStudentController extends Controller<University> {
    @FXML
    private ToggleGroup attendTg;
    @FXML
    private TextField numTf;
    @FXML
    private TextField nameTf;
    @FXML
    private CheckBox scholarCb;
    @FXML
    private Button addBtn;
    @FXML
    private Text errorText;
    
    @FXML
    private void handleClose(ActionEvent event) {
        stage.close();
    }
    
    @FXML
    private void handleAdd(ActionEvent event) throws Exception {
        try {
            getUniversity().addStudent(getNumber(), getName(), getAttend(), isScholar());
            stage.close();
        } catch (Exception e) {
            errorText.setText(e.getMessage());
        }
    }
    
    @FXML
    private void initialize() {
        numTf.textProperty().addListener((o, oldText, newText) -> {
            if (!getName().isEmpty() && getAttend() != null)
            addBtn.setDisable(!newText.matches("[0-9]+"));
        });
        nameTf.textProperty().addListener((o, oldText, newText) -> {
            if (!getNumber().isEmpty() && getAttend() != null)
            addBtn.setDisable(newText.isEmpty());
        });
        attendTg.selectedToggleProperty().addListener((o, oldText, newText) -> {
            if (!getName().isEmpty() && !getNumber().isEmpty())
            addBtn.setDisable(newText == null);
        });
    }
    
    private String getNumber() {
        return numTf.getText();
    }
    
    private String getName() {
        return nameTf.getText();
    }
    
    private boolean isScholar() {
        return scholarCb.isSelected();
    }
    
    public final University getUniversity() { return model; }
    
    private String getAttend() {
        if (attendTg.getSelectedToggle() != null)
            return attendTg.getSelectedToggle().getUserData().toString();
        else
            return null;
    }
}
