package graphics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private ComboBox startPlayer;
    @FXML
    private ColorPicker playerColor1;
    @FXML
    private ColorPicker playerColor2;
    @FXML
    private ComboBox<String> boardSize;
    @FXML
    private Button backToMenu;

    /**
     * initialaizer.
     * @param location url.
     * @param resources resource bundle.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.startPlayer.getItems().add("Player 1");
        this.startPlayer.getItems().add("Player 2");

        for (int i = 4; i <= 20; i+= 2) {
            this.boardSize.getItems().add(i + " X " + i);
        }

        File settingsF = new File("GameSettings.txt");
        BufferedReader buffer = null;

        try {
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(settingsF)));
            String startPlayer = buffer.readLine();
            String color1 = buffer.readLine();
            String color2 = buffer.readLine();
            String boardSize = buffer.readLine();
            boardSize = boardSize + " X " + boardSize;
            this.startPlayer.setValue(startPlayer);
            this.playerColor1.setValue(Color.valueOf(color1));
            this.playerColor2.setValue(Color.valueOf(color2));
            this.boardSize.setValue(boardSize);
            buffer.close();
        } catch (Exception e) {
            this.boardSize.setValue("8 X 8");
            this.startPlayer.setValue("Player 1");
            this.playerColor1.setValue(Color.valueOf("BLACK"));
            this.playerColor2.setValue(Color.valueOf("WHITE"));
        }
    }

    /**
     * return to the menu.
     */
    @FXML
    protected void backToMenu() {
        Stage stage = (Stage)this.backToMenu.getScene().getWindow();
        try {
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("MenuGraphics.fxml"));
            Scene scene = new Scene(root, 500, 500);
            stage.setTitle("Reversi");
            stage.setScene(scene);
            stage.show();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }


    /**
     * save the settings.
     */
    @FXML
    protected void saveSettings() {
        File settingsFile = new File("GameSettings.txt");
        Stage stage = (Stage)this.backToMenu.getScene().getWindow();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(settingsFile)));
            writer.println(this.startPlayer.getValue());
            writer.println(this.playerColor1.getValue());
            writer.println(this.playerColor2.getValue());
            writer.println(this.boardSize.getValue().toString().split(" ")[0]);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SAVING SETTINGS...");
            alert.setHeaderText("Saving settings, please stand by...");
            alert.setContentText("Press 'OK' to save the settings!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    try {
                        GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("MenuGraphics.fxml"));
                        Scene scene = new Scene(root, 500, 500);
                        stage.setTitle("Reversi");
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            writer.close();
        }
    }
}