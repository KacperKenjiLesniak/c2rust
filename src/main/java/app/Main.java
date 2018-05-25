package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import transpiler.Transpiler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("c2RustTranspiler");
        primaryStage.setScene(new Scene(root, 1400, 1000));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
