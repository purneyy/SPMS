package data;

import models.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PatientData {
    private static final String DB_URL = "jdbc:sqlite:patients.db";

    static {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS patients (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "name TEXT NOT NULL," +
                         "age INTEGER NOT NULL," +
                         "gender TEXT NOT NULL," +
                         "symptoms TEXT)";
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Add patient to the DB
    public static void addPatient(Patient patient) {
        String sql = "INSERT INTO patients(name, age, gender, symptoms) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getGender());
            pstmt.setString(4, patient.getSymptoms());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Saving to DB at: " + new java.io.File("patients.db").getAbsolutePath());

    }
static {
    try {
        Class.forName("org.sqlite.JDBC"); // force load driver
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}

// ✅ Retrieve all patients from the DB
public static List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        String query = "SELECT * FROM patients";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String symptoms = rs.getString("symptoms");

                Patient p = new Patient(name, age, gender, symptoms);
                patientList.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Reading from DB at: " + new java.io.File("patients.db").getAbsolutePath());
        return patientList;

    }
    public static void updatePatient(Patient patient) {
    String sql = "UPDATE patients SET name = ?, age = ?, gender = ?, symptoms = ? WHERE name = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, patient.getName());
        pstmt.setInt(2, patient.getAge());
        pstmt.setString(3, patient.getGender());
        pstmt.setString(4, patient.getSymptoms());
        pstmt.setString(5, patient.getName()); // Assuming name is unique ID

        pstmt.executeUpdate();
        System.out.println("✅ Patient updated: " + patient.getName());

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
