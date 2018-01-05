import java.util.*;
public class Game {
	
	private Logic logic;
	private Board board;
	private Player player1;
	private Player player2;
	
	public Game(int boardSize) {
		this.board = new Board(boardSize);
		this.logic = new Logic(this.board);
		this.player1 = new Player(mark.Black);
		this.player2 = new Player(mark.White);
	}
	
	public void play() {
		Player player = this.player1;
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
	
	public boolean makeTurn(Player player) {
		Point point;
		Path temp;
		List<Path> pathVector = new ArrayList();
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
	
	private Player switchPlayer(Player current) {
		if (this.player1.getSign() == current.getSign()) {
			return this.player2;
		}
		return this.player1;
	}
}
