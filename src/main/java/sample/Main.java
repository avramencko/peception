package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.data_base.DataBaseHandler;

public class Main extends Application {
    public static Stage parentWindow;
    @Override
    public void start(Stage primaryStage) throws Exception{
        parentWindow = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("view/authorization.fxml"));
        parentWindow.setTitle("reception");
        parentWindow.setScene(new Scene(root));
        parentWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
