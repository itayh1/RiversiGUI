package code;

public class Path {
	
	Point src;
	Point dst;

	/**
	 * constructor.
	 * @param src point.
	 * @param dst point.
	 */
	public Path(Point src, Point dst) {
		this.src = src;
		this.dst = dst;
	}

	/**
	 * return src.
	 * @return point.
	 */
	public Point getSource() {
		return this.src;
	}

	/**
	 * return dst.
	 * @return point.
	 */
	public Point getDestination() {
		return this.dst;
	}

	/**
	 * sets source.
	 * @param src point.
	 */
	public void setSource(Point src) {
		this.src = src;
	}

	/**
	 * set destination.
	 * @param dst point.
	 */
	public void setDestination(Point dst) {
		this.dst = dst;
	}

	/**
	 * is equal.
	 * @param other path.
	 * @return boolean.
	 */
	public boolean isEqual(Path other) {
		return this.src.isEqual(other.getSource()) &&
			this.dst.isEqual(other.getDestination());
	}
}
