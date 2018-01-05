
public class Path {
	
	Point src;
	Point dst;
	
	public Path(Point src, Point dst) {
		this.src = src;
		this.dst = dst;
	}
	
	public Point getSource() {
		return this.src;
	}
	
	public Point getDestination() {
		return this.dst;
	}
	
	public void setSource(Point src) {
		this.src = src;
	}
	
	public void setDestination(Point dst) {
		this.dst = dst;
	}
	
	public boolean isEqual(Path other) {
		return this.src.isEqual(other.getSource()) &&
			this.dst.isEqual(other.getDestination());
	}
}
