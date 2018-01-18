package code;

import java.util.*;

public class Player implements IPlayer{
	private mark sign;

    /**
     * constructor.
     * @param sign mark.
     */
	public Player(mark sign) {
		this.sign = sign;
	}

    /**
     * get sign.
     * @return mark.
     */
	public mark getSign() {
		return this.sign;
	}

    /**
     * get point.
     * @param vector list.
     * @return point.
     */
	public Point getPoint(List<Path> vector) {
		Point p;
		int row, col;
		String str;
		char playerMark = (this.sign == mark.Black) ? 'X' : 'O';
		Scanner scan = new Scanner(System.in);
		//System.out.print(playerMark + ":It's your move\nyour possible moves: ");
		this.printOptions(vector);
		//System.out.print("\nPlease enter your move: ");

		str = scan.nextLine();
		row = Integer.parseInt(str.trim().split(" ")[0]);
		col = Integer.parseInt(str.trim().split(" ")[1]);
		p = new Point(row - 1, col - 1);
		//scan.close(); make game crash
		return p;
	}

    /**
     * prints options.
     * @param paths list.
     * @return list.
     */
	private List<Path> printOptions(List<Path> paths) {
		return paths;
	}

    /**
     * set sign.
     * @param m mark.
     */
	public void setSign(mark m) {
		this.sign = m;
	}
}