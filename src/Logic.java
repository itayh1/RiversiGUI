import java.util.*;

public class Logic {
	
	private Board board;
	
	public Logic(Board b) {
		this.board = b;
	}
	
	public void avaliblePoints(List<Path> pathVector, mark player) {
		int row, col, size = this.board.getSize();
		mark opponent = (player == mark.Black) ? mark.White : mark.Black;
		for (int x = 0; x < size; x++) {
	        for (int y = 0; y < size; y++) {
	            if (board.getMark(x, y) != mark.Blank) continue;
	            for (int xDelta = -1; xDelta <= 1; xDelta++) {
	                for (int yDelta = -1; yDelta <= 1; yDelta++) {
	                    /* Don't check outside the array, or the current square */
	                    if (x + xDelta < 0 || x + xDelta >= size || y + yDelta < 0 ||
	                        y + yDelta >= size || (xDelta == 0 && yDelta == 0))
	                        continue;
	                    if (board.getMark(x + xDelta, y + yDelta) == opponent) {
	                        row = x + xDelta;
	                        col = y + yDelta;

	                        for (;;) {
	                            row += xDelta;
	                            col += yDelta;
	                            if (row < 0 || row >= size || col < 0 || col >= size)
	                                break;
	                            if (board.getMark(row, col) == mark.Blank)
	                                break;
	                            if (board.getMark(row, col) == player) {
	                                Point src = new Point(x, y);
	                                Point dst = new Point(row, col);
	                                Path path = new Path(src, dst);
	                                pathVector.add(path);
	                                break;
	                            }
	                        } //end path creator for
	                    }
	                } // end y-Delta for
	            } // end x-Delta for
	        } // end column for
	    } // end row for
	}
	
	public void reverseCells(Path path, mark player) {
		int xDelta = path.getDestination().getX() - path.getSource().getX();
	    int yDelta = path.getDestination().getY() - path.getSource().getY();

	    // if bigger than 0, put 1, if smaller than zero put -1, else put 0.
	    xDelta = (xDelta > 0) ? 1 : ((xDelta < 0) ? -1 : 0 );
	    yDelta = (yDelta > 0) ? 1 : ((yDelta < 0) ? -1 : 0 );
	    
	    int row = path.getSource().getX(), col = path.getSource().getY();
	    do {
	        board.setMark(row, col, player);
	        row += xDelta;
	        col += yDelta;
	    } while (board.getMark(row, col) != player);
	}
	
	public mark switchSign(mark sign) {
	    if (sign == mark.Black)
	        return mark.White;
	    return mark.Black;
	}
}
