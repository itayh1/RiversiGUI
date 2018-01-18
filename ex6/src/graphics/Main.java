package graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * start the stage.
     * @param primaryStage stage.
     * @throws Exception exception.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = FXMLLoader.load(getClass().getResource("MenuGraphics.fxml"));
        primaryStage.setTitle("Reversi");
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * main.
     * @param args string[].
     */
    public static void main(String[] args) {
        launch(args);
    }
}