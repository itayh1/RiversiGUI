package code;

import graphics.BoardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import sun.print.CUPSPrinter;

import java.io.File;
import java.util.*;

import static java.lang.System.exit;

public class Game {
	
	private Logic logic;
	private Board board;
	private IPlayer player1;
	private IPlayer player2;
	private IPlayer currentPlayer;
	private boolean gameJustStarted = true;

    /**
     * constructor.
     * @param boardSize int.
     * @param s string.
     */
	public Game(int boardSize, String s) {
		this.board = new Board(boardSize);
		this.logic = new Logic(this.board);
		if (s.equals("1")) {
            this.player1 = new Player(mark.Black);
            this.player2 = new Player(mark.White);
        } else {
		    this.player2 = new Player(mark.Black);
		    this.player1 = new Player(mark.White);
        }
	}
	/**
	 * run the game flow.
	 */
	public void play() {
		currentPlayer = this.player1;
		boolean running = true;
		do {
			running = makeTurn(1, 1, "Player 1");
			currentPlayer = switchPlayer(currentPlayer);
		}
		while (running) ;

		int black = this.board.countSign(mark.Black);
		int white = this.board.countSign(mark.White);
		int blank = this.board.countSign(mark.Blank);
		System.out.format("black: %d\nwhite: %d\nblank: %d", black, white, blank);
	}
	/**
	 * play one turn
	 * the player that plays right now
	 * @return true if game still running, else, return false if
	 * game over (both players have no move).
	 */
	public boolean makeTurn(int x, int y, String s) {
	    if(this.gameJustStarted) {
            if (s.equals("Player 1")) {
                currentPlayer = this.player1;
            } else {
                currentPlayer = this.player2;
            }
            this.gameJustStarted = false;
        }
		Point point = new Point(x, y);
		List<Path> pathVector = new ArrayList<Path>();
		// put in vector available points to play
		this.logic.avaliblePoints(pathVector, currentPlayer.getSign());
		if (pathVector.isEmpty()) {
		    // current player has no moves
            currentPlayer = this.switchPlayer(currentPlayer);
            pathVector.clear();
            this.logic.avaliblePoints(pathVector, currentPlayer.getSign());
            if (pathVector.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game ended");
                alert.setHeaderText("Notice: Both players don't have options, game ended!");
                alert.setContentText("Press 'ok' to exit");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("The Winner");
                        alert1.setHeaderText("And the winner is...");
                        String winner = this.Winner();
                        alert1.setContentText(winner);
                        alert1.showAndWait().ifPresent(rs1 -> {
                            if (rs1 == ButtonType.OK) {
                                exit(0);
                            }
                        });
                    }
                });
                return false;
            }
            return true;
        } else {
		    if (isInList(pathVector, point)) {
                for (Path p : pathVector) {
                    if (p.getSource().isEqual(point)) {
                        this.logic.reverseCells(p, currentPlayer.getSign());
                    }
                }
                currentPlayer = switchPlayer(currentPlayer);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid option!");
                alert.setHeaderText("Oops! something went wrong");
                alert.setContentText("You clicked on invalid cell, please select a new one!");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                    }
                });
                return false;
            }
        }
		return true;
	}
	/**
	 * switch between the players
	 * current player playing
	 * @return the other player
	 */
	public IPlayer switchPlayer(IPlayer current) {
	    if (current.getSign() == this.player1.getSign()) {
	        return this.player2;
        }
        return this.player1;
	}

    /**
     * return the board.
     * @return board.
     */
	public Board getBoard() {
		return this.board;
	}

    /**
     * return player 1.
     * @return player.
     */
	public IPlayer getPlayer1() {
	    return this.player1;
    }

    /**
     * return player 2.
     * @return player.
     */
    public IPlayer getPlayer2() {
	    return this.player2;
    }

    /**
     * draw game
     * @param b board controller.
     */
    public void drawGame(BoardController b) {
	    b.draw();
    }

    /**
     * return the logic.
     * @return logic.
     */
    public Logic getLogic() {
	    return this.logic;
    }

    /**
     * return if is in list.
     * @param l list.
     * @param p point.
     * @return boolean.
     */
    public boolean isInList(List<Path> l, Point p) {
	    for (int i = 0; i < l.size(); i++) {
	        if (l.get(i).getSource().getX() == p.getX() && l.get(i).getSource().getY() == p.getY()) {
	            return true;
            }
        }
        return false;
	}

    /**
     * return the winner.
     * @return string.
     */
	public String Winner() {
	    int p1 = this.board.countSign(mark.Black);
	    int p2 = this.board.countSign(mark.White);
	    if (p1 > p2) {
	        return "Player 1";
        } else if (p2 > p1) {
	        return "Player 2";
        } else {
	        return "Ohh wait, it's a draw!";
        }
	}
}