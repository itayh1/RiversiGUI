package graphics;

import code.Board;
import code.mark;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class BoardController extends GridPane {

    private Board board;
    private Color player1;
    private Color player2;
    public int cellLength;

    /**
     * constructor.
     * @param b board.
     */
    public BoardController(Board b) {
        this.board = b;
        this.player1 = Color.WHITE;
        this.player2 = Color.BLACK;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoardGraphics.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
           fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * draw board.
     */
    public void draw() {
        this.getChildren().clear();
        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();

        int heightC = height / this.board.getSize();
        int widthC = width / this.board.getSize();
        this.cellLength = heightC;

        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                double d = 0;
                if (this.board.getMark(i, j) == mark.Black) {
                    StackPane stackPane = new StackPane();
                    Rectangle r = new Rectangle(heightC, widthC, Color.color(0.5, 0.9, 0.92));
                    r.setStroke(Color.WHITE);
                    stackPane.getChildren().add(r);
                    if (widthC >= heightC) {
                        d = heightC;
                    } else {
                        d = widthC;
                    }
                    Circle player1Circle = new Circle(0, 0, (d - 10) / 2, this.player1);
                    player1Circle.setStroke(Color.WHITE);
                    stackPane.getChildren().add(player1Circle);
                    this.add(stackPane, j, i);
                }else if (this.board.getMark(i, j) == mark.White) {
                    StackPane stackPane = new StackPane();
                    Rectangle r = new Rectangle(heightC, widthC, Color.color(0.5, 0.9, 0.92));
                    r.setStroke(Color.WHITE);
                    stackPane.getChildren().add(r);
                    if (widthC >= heightC) {
                        d = heightC;
                    } else {
                        d= widthC;
                    }
                    Circle player2Circle = new Circle(0, 0, (d - 10) / 2, this.player2);
                    player2Circle.setStroke(Color.WHITE);
                    stackPane.getChildren().add(player2Circle);
                    this.add(stackPane, j, i);
                } else {
                    Rectangle r = new Rectangle(heightC, widthC, Color.color(0.5, 0.9, 0.92));
                    r.setStroke(Color.WHITE);
                    this.add(r, j ,i);
                }
            }
        }
    }

    /**
     * sets player1 color.
     * @param c color.
     */
    public void setPlayer1(Color c) {
        this.player1 = c;
    }

    /**
     * sets player2 color.
     * @param c color.
     */
    public void setPlayer2(Color c) {
        this.player2 = c;
    }

    /**
     * return the board.
     * @return board.
     */
    public Board getBoard() {
        return this.board;
    }
}
