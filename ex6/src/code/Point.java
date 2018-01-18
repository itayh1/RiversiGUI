package code;


public class Point {
	private int x;
	private int y;

	/**
	 * constructor.
	 * @param x int.
	 * @param y int.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

    /**
     * get x.
     * @return int.
     */
	public int getX() {
		return this.x;
	}

    /**
     * get y.
     * @return int.
     */
	public int getY() {
		return this.y;
	}

    /**
     * set x.
     * @param x int.
     */
	public void setX(int x) {
		this.x = x;
	}

    /**
     * set y.
     * @param y int.
     */
	public void setY(int y) {
		this.y = y;
	}

    /**
     * is equal.
     * @param other point.
     * @return boolean.
     */
	public boolean isEqual(Point other) {
		return (this.x == other.getX()) &&
				(this.y == other.getY());
	}
}