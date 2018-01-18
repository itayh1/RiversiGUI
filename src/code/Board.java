package code;

public class Board {
	mark board[][];
	int size;

	/**
	 * constructor.
	 * @param size int.
	 */
	public Board(int size) {
		this.size = size;
		this.board = new mark[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.board[i][j] = mark.Blank;
			}
		}

		setMark(size / 2 - 1, size / 2 - 1, mark.White);
		setMark(size / 2 - 1, size / 2, mark.Black);
		setMark(size / 2, size / 2, mark.White);
		setMark(size / 2, size / 2 - 1, mark.Black);
	}

	/**
	 * returns the mark.
	 * @param row int.
	 * @param column int.
	 * @return mark.
	 */
	public mark getMark(int row, int column) {
		return this.board[row][column];
	}

	/**
	 * sets mark.
	 * @param row int.
	 * @param column int.
	 * @param m mark.
	 */
	public void setMark(int row, int column, mark m) {
		this.board[row][column] = m;
	}

	/**
	 * return the size.
	 * @return int.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * counts the given sign.
	 * @param sign mark.
	 * @return int.
	 */
	public int countSign(mark sign) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (this.board[i][j] == sign)
					count++;
			}
		}
		return count;
	}

	/**
	 * print.
	 */
	public void print() {
//		System.out.println("board:");
//
//		for (int i = 1; i <= size; i++) {
//			System.out.print(" | " + i + "");
//			//cout << "| " << i << " ";
//		}
//		System.out.println("|");
//		//cout << "|" << endl;
//
//		for (int x = 0; x < size; x++) {
//			for (int y = 0; y < 34; y++) {
//				System.out.print("-");
//			}
//			System.out.println();
//			System.out.print((x + 1) + "|");
//			//cout << endl << x + 1 << "|";
//
//			for (int y = 0; y < size; y++) {
//				switch(board[x][y]) {
//					case Black:
//						System.out.print(" x |");
//						//cout << " x |";
//						break;
//					case White:
//						System.out.print(" o |");
//						//cout << " o |";
//						break;
//					case Blank:
//						System.out.print("   |");
//						//cout << "   |";
//						break;
//					default:
//						break;
//				}
//			}
//			System.out.println();
//			//cout << endl;
//		}
//		for (int i = 0; i < 34; i++) {
//			System.out.print("-");
//				//cout << "-";
//		}
//		System.out.println();
//		//cout << endl;
//	}
	}
}
