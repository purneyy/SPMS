package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    public void onAddPatient() {
        try {
            Parent form = FXMLLoader.load(getClass().getResource("../views/add_patient.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Patient");
            stage.setScene(new Scene(form, 400, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
private void onViewPatients() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/views/view_patients.fxml"));
        Stage stage = new Stage();
        stage.setTitle("All Patients");
        stage.setScene(new Scene(root, 500, 400));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

