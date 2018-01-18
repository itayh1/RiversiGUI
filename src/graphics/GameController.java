package graphics;

import code.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.stage.Stage;

public class GameController implements Initializable {
    @FXML
    private HBox root;
    @FXML
    private Text currentPlayer;
    @FXML
    private Text player1Score;
    @FXML
    private Text player2Score;
    @FXML
    private Button backToMenu;

    private BoardController boardController;
    private mark currentColor;
    private int xMouse;
    private int yMouse;
    private Color player1;
    private Color player2;
    private String firstPlayer;


    /**
     * initializer.
     * @param location url.
     * @param resources resource bundle.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.currentColor = mark.Black;

        Game game = createGame(new File("GameSettings.txt"));

        this.boardController = new BoardController(game.getBoard());
        this.boardController.setPlayer1(this.player1);
        this.boardController.setPlayer2(this.player2);
        this.boardController.setPrefHeight(450);
        this.boardController.setPrefWidth(450);
        root.getChildren().add(0, this.boardController);

        this.boardController.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xMouse = (int)event.getY();
                yMouse = (int)event.getX();
                int x = (int)(xMouse / boardController.cellLength);
                int y = (int)(yMouse / boardController.cellLength);
                game.makeTurn(x, y, firstPlayer);
                draw();
            }
        });
        this.draw();
    }

    /**
     * draw the game.
     */
    public void draw() {
        if (this.currentColor == mark.Black) {
            this.currentPlayer.setText("Player 1");
        } else if (this.currentColor == mark.White) {
            this.currentPlayer.setText("Player 2");
        }
        this.player1Score.setText("" + this.boardController.getBoard().countSign(mark.Black));
        this.player2Score.setText("" + this.boardController.getBoard().countSign(mark.White));
        this.boardController.draw();
    }

    /**
     * return tp the menu function.
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
     * creates the game from file.
     * @param file file.
     * @return game.
     */
    public Game createGame(File file) {
        BufferedReader buffer = null;
        int size = 8;
        this.firstPlayer = "Player 1";
        this.player1 = Color.BLACK;
        this.player2 = Color.WHITE;
        try {
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String firstPlayer = buffer.readLine();
            String firstPlayerColor = buffer.readLine();
            String secondPlayerColor = buffer.readLine();
            String boardSize = buffer.readLine();
            this.firstPlayer = firstPlayer;
            if (this.firstPlayer.equals("Player 1")) {
                this.player1 = Color.valueOf(firstPlayerColor);
                this.player2 = Color.valueOf(secondPlayerColor);
            } else {
                this.player2 = Color.valueOf(firstPlayerColor);
                this.player1 = Color.valueOf(secondPlayerColor);
            }
            size = Integer.parseInt(boardSize);
            buffer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Game g = new Game(size, this.firstPlayer);
        return g;
    }


    /**
     * checks if a point is in list.
     * @param l list of points.
     * @param p point.
     * @return boolean.
     */
    public boolean isInList(List<Point> l, Point p) {
        int x = p.getX();
        int y = p.getY();
        for (int i = 0; i < l.size(); i++) {
            if (x == l.get(i).getX() && y == l.get(i).getY()) {
                return true;
            }
        }
        return false;
    }
}