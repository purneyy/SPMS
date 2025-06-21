import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {
    @Override
public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
loader.setLocation(Main.class.getResource("/views/home.fxml"));
Parent root = loader.load();

    Scene scene = new Scene(root);
    primaryStage.setTitle("Smart Patient Manager");
    primaryStage.setScene(scene);
    primaryStage.show();
}
public static void main(String[] args) {
        launch(args);
    }
}
