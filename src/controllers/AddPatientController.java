package controllers;

import java.io.IOException;

import data.PatientData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Patient;

public class AddPatientController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

   @FXML
private ComboBox<String> genderBox;


    @FXML
    private TextArea symptomsArea;

    @FXML
    public void initialize() {
        genderBox.getItems().addAll("Male", "Female", "Other");
    }

   @FXML
public void handleSubmit() {
    String name = nameField.getText();
    String ageText = ageField.getText();
    String gender = genderBox.getValue();
    String symptoms = symptomsArea.getText();

    if (name.isEmpty() || ageText.isEmpty() || gender == null || symptoms.isEmpty()) {
        System.out.println("Please fill all fields.");
        return;
    }

    try {
        int age = Integer.parseInt(ageText);
        Patient newPatient = new Patient(name, age, gender, symptoms);
        PatientData.addPatient(newPatient);

        System.out.println("Patient saved successfully!");
        System.out.println(newPatient);

        // Clear the form
        nameField.clear();
        ageField.clear();
        genderBox.setValue(null);
        symptomsArea.clear();

        // ✅ Open "View Patients" window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/view_patients.fxml"));
        Parent viewRoot = loader.load();

        Stage stage = new Stage();
        stage.setTitle("All Patients");
        stage.setScene(new Scene(viewRoot));
        stage.show();

    } catch (NumberFormatException e) {
        System.out.println("Age must be a number.");
    } catch (IOException e) {
        e.printStackTrace();
    }
     System.out.println("Working directory: " + System.getProperty("user.dir"));
}
    @FXML
    private void handleClose() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
   

}
