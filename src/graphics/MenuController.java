package graphics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



import static java.lang.System.exit;

public class MenuController {
    @FXML
    private Button startButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button exitButton;

    /**
     * start game.
     */
    @FXML
    protected void startGame() {
        Stage stage = (Stage)this.startButton.getScene().getWindow();
        try {
            HBox root = (HBox) FXMLLoader.load(getClass().getResource("GameGraphics.fxml"));
            stage.setTitle("Reversi");
            stage.setScene(new Scene(root, 500, 500));
            stage.show();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * settings.
     */
    @FXML
    protected void settings() {
        Stage stage = (Stage)this.settingsButton.getScene().getWindow();
        try {
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("SettingsGraphics.fxml"));
            stage.setTitle("Reversi Settings");
            stage.setScene(new Scene(root, 500, 500));
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * exit game.
     */
    @FXML
    protected void exitGame() {
        exit(0);
    }
}
