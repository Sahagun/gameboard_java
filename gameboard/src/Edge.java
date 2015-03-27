

public class Edge {
	public Coordinates x, y;

	public Edge(Coordinates x, Coordinates y) {
		this.x = x;
		this.y = y;
	}

	public boolean hasCoordinate(int n, int m) {
		if ((this.x.x == n) || (x.y == m))
			return true;
		else if ((this.y.x == n) || (this.y.y == m))
			return true;
		else
			return false;
	}
}
