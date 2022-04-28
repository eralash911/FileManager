import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("firstPanel.fxml"));
        primaryStage.setTitle("Java file Manager");
        primaryStage.setScene(new Scene(parent, 1000, 360));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
