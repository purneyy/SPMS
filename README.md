The Smart Patient Management System(SPMS) is a basic JavaFX-based desktop application designed to streamline the management of patient information in clinics or small healthcare setups. It enables medical staff to easily add, view, edit, and manage patient data through a user-friendly graphical interface.

🔧 Key Features:
1.Add Patient Details

Capture patient's name, age, gender, and symptoms via a simple form

Save data securely to a real SQLite database

2.View All Patients

Display all patient records in a structured TableView

Automatically fetch and display the data from the database on load

3.Edit Patient Information

Includes an “Edit” button beside each record

Opens a pre-filled form to update existing patient details

Automatically updates the database after editing

4.Modern UI with JavaFX

Built using JavaFX with FXML layout files

Clean, interactive GUI with modular design (MVC pattern).

| Component      | Technology                     |
| -------------- | ------------------------------ |
| Frontend (GUI) | JavaFX + FXML                  |
| Backend Logic  | Java (Controllers + Models)    |
| Database       | SQLite (local DB)              |
| IDE            | Visual Studio Code             |
| Java Version   | JDK 24 (with OpenJFX 24)       |
| Build Method   | Manual CLI / VS Code Java Pack |

