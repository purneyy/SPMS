package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Patient;

import java.io.IOException;

import data.PatientData;

public class ViewPatientsController {

    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, String> nameColumn;

    @FXML
    private TableColumn<Patient, Integer> ageColumn;

    @FXML
    private TableColumn<Patient, String> genderColumn;

    @FXML
    private TableColumn<Patient, String> symptomsColumn;
    @FXML
    public void initialize() {
        // Step 1: Set up column-to-model property bindings
        nameColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        ageColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAge()).asObject());
        genderColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getGender()));
        symptomsColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSymptoms()));

        // Step 2: Fetch data from database
        ObservableList<Patient> patients = FXCollections.observableArrayList(PatientData.getAllPatients());

        // Debug output
        System.out.println("✅ Total patients fetched: " + patients.size());
        for (Patient p : patients) {
            System.out.println(" - " + p.getName() + " | " + p.getAge() + " | " + p.getGender() + " | " + p.getSymptoms());
        }
          System.out.println("✅ ViewPatientsController initialized");

        // Step 3: Populate TableView
        patientTable.setItems(patients);

        // Add Edit column
        TableColumn<Patient, Void> editCol = new TableColumn<>("Edit");

        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((event) -> {
                            Patient selectedPatient = getTableView().getItems().get(getIndex());
                            showEditDialog(selectedPatient);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        editCol.setCellFactory(cellFactory);
        patientTable.getColumns().add(editCol);
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) patientTable.getScene().getWindow();
        stage.close();
    }

   private void showEditDialog(Patient patient) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/edit_patient.fxml"));
        Parent root = loader.load();

        // Pass patient to edit controller
        EditPatientController controller = loader.getController();
        controller.setPatient(patient);

        // Show as modal dialog
        Stage stage = new Stage();
        stage.setTitle("Edit Patient");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);  // Block interaction with other windows
        stage.showAndWait();

        // Refresh the table after editing
        refreshTable();

    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
private void refreshTable() {
    ObservableList<Patient> refreshed = FXCollections.observableArrayList(PatientData.getAllPatients());
    patientTable.setItems(refreshed);
}

    
}
