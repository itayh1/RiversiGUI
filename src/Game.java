import java.util.*;
public class Game {
	
	private Logic logic;
	private Board board;
	private IPlayer player1;
	private IPlayer player2;
	
	public Game(int boardSize) {
		this.board = new Board(boardSize);
		this.logic = new Logic(this.board);
		this.player1 = new Player(mark.Black);
		this.player2 = new Player(mark.White);
	}
	/**
	 * run the game flow.
	 */
	public void play() {
		IPlayer player = this.player1;
		boolean running = true;
		
		do {
			running = makeTurn(player);
			player = switchPlayer(player);
		}
		while (running) ;
		
		int black = this.board.countSign(mark.Black);
		int white = this.board.countSign(mark.White);
		int blank = this.board.countSign(mark.Blank);
		System.out.format("black: %d\nwhite: %d\nblank: %d", black, white, blank);
	}
	/**
	 * play one turn
	 * @param player : the player that plays right now
	 * @return true if game still running, else, return false if
	 * game over (both players have no move).
	 */
	public boolean makeTurn(IPlayer player) {
		Point point;
		//Path temp;
		List<Path> pathVector = new ArrayList<Path>();
		// put in vector available points to play
		this.logic.avaliblePoints(pathVector, player.getSign());
		if (pathVector.isEmpty()) {
			// current player has no moves
			player = this.switchPlayer(player);
			pathVector.clear();
			this.logic.avaliblePoints(pathVector, player.getSign());
			if (pathVector.isEmpty()) {
				this.board.print();
				return false;
			}
			return true;
		}
		this.board.print();
		point = player.getPoint(pathVector);
		for (Path p : pathVector) {
			if (p.getSource().isEqual(point)) {
				this.logic.reverseCells(p, player.getSign());
			}
		}
		return true;
	}
	/**
	 * switch between the players
	 * @param current : current player playing
	 * @return the other player
	 */
	private IPlayer switchPlayer(IPlayer current) {
		if (this.player1.getSign() == current.getSign()) {
			return this.player2;
		}
		return this.player1;
	}
}
