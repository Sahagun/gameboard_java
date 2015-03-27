
public class Coordinates {
	public int x, y;
	public boolean start, finish;
	public Coordinates north, south, east, west;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinates() {
		this.x = 0;
		this.y = 0;
	}

	@Override
	public String toString() {
		String c = "x: " + x + " y: " + y;
		return c;
	}

	public String print() {
		String c = "x: " + x + " y: " + y;
		String n = (north == null) ? " North is null" : " North x: " + north.x
				+ " y: " + north.y;
		String e = (east == null) ? " East is null" : " East x: " + east.x
				+ " y: " + east.y;
		String s = (south == null) ? " South is null" : " South x: " + south.x
				+ " y: " + south.y;
		String w = (west == null) ? " West is null" : " West x: " + west.x
				+ " y: " + west.y;

		return c + n + e + s + w;
	}

	boolean hasOpenAdjacent() {
		if (x == 0 && y == 0) {
			if (east == null || south == null) {
				return true;
			} else
				return false;
		} else if (x == 0 && y == 3) {
			if (east == null || north == null) {
				return true;
			} else
				return false;
		} else if (x == 0) {
			if (east == null || north == null || south == null) {
				return true;
			} else
				return false;
		} else if (x == 3 && y == 3) {
			if (west == null || north == null) {
				return true;
			} else
				return false;
		} else if (x == 3 && y == 0) {
			if (east == null || south == null) {
				return true;
			} else
				return false;
		} else if (y == 0) {
			if (east == null || south == null) {
				return true;
			} else
				return false;
		} else if (x == 3) {
			if (west == null || north == null || east == null) {
				return true;
			} else
				return false;
		} else if (y == 3) {
			if (west == null || north == null || south == null) {
				return true;
			} else
				return false;
		} else if (north == null || south == null || east == null
				|| west == null) {
			return true;
		} else {
			return false;
		}
	}
}
