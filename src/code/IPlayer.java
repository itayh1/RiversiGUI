package code;

import java.util.List;

public interface IPlayer {

    /**
     * return point.
     * @param vector list.
     * @return point.
     */
	public Point getPoint(List<Path> vector);

    /**
     * return sign.
     * @return mark.
     */
	public mark getSign();

    /**
     * sets sign.
     * @param m mark.
     */
	public void setSign(mark m);
}
