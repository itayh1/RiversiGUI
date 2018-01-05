import java.util.*;
public class Player implements IPlayer{
	private mark sign;
	
	public Player(mark sign) {
		this.sign = sign;
	}
	
	public mark getSign() {
		return this.sign;
	}
	
	public Point getPoint(List<Path> vector) {
		Point p;
		int row, col;
		String str;
		char playerMark = (this.sign == mark.Black) ? 'X' : 'O';
		Scanner scan = new Scanner(System.in);
		System.out.print(playerMark + ":It's your move\nyour possible moves: ");
		this.printOptions(vector);
		System.out.print("\nPlease enter your move: ");
		
		str = scan.nextLine();
		row = Integer.parseInt(str.trim().split(" ")[0]);
		col = Integer.parseInt(str.trim().split(" ")[1]);
		p = new Point(row - 1, col - 1);
		//scan.close(); make game crash
		return p;
	}
	
	private void printOptions(List<Path> paths) {
		Path p;
		
		for (int i = 0; i < paths.size() ; i++) {
			p = paths.get(i);
			if (i + 1 < paths.size()) {
				if (p.getSource().isEqual(paths.get(i + 1).getSource()))
					continue;
			}
			System.out.format("(%d, %d) ",
					p.src.getX() + 1, p.src.getY() + 1);			
		}
	}
}
