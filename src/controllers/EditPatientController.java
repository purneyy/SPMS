package controllers;

import data.PatientData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Patient;

public class EditPatientController {

    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private ComboBox<String> genderCombo;
    @FXML private TextField symptomsField;

    private Patient currentPatient;

    // Called automatically after FXML is loaded
    @FXML
    public void initialize() {
        genderCombo.getItems().addAll("Male", "Female", "Other");
    }

    // Called by parent controller to pass selected patient data
    public void setPatient(Patient patient) {
        this.currentPatient = patient;
        nameField.setText(patient.getName());
        ageField.setText(String.valueOf(patient.getAge()));
        genderCombo.setValue(patient.getGender());
        symptomsField.setText(patient.getSymptoms());
    }

    @FXML
    private void handleSave() {
        try {
            // Update patient object from form fields
            currentPatient.setName(nameField.getText().trim());
            currentPatient.setAge(Integer.parseInt(ageField.getText().trim()));
            currentPatient.setGender(genderCombo.getValue());
            currentPatient.setSymptoms(symptomsField.getText().trim());

            // Update in database
            PatientData.updatePatient(currentPatient);

            // Close the window
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Please enter a valid number for age.");
            alert.setContentText("Example: 25");
            alert.showAndWait();
        }
    }
}
