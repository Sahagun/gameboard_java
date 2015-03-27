
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
	public static void createBoard( int seed ) {
		ArrayList<Coordinates> coords = new ArrayList<Coordinates>();
		Random rnd = new Random();
		seed = rnd.nextInt();
//		 System.out.println("Seed: " + seed);
//			rnd.setSeed(seed);

		System.out.println("Seed: -1248315122");
		rnd.setSeed(seed);
//		System.out.println("Seed: -1399926950");
//		rnd.setSeed(1399926950);
		// rnd.setSeed(123456789);
		// rnd.setSeed(1564210849101984245l);

		Coordinates[][] board = createPath(rnd, coords);
		addMoreTiles(board, rnd, coords);

		/*
		 * try { BufferedWriter output = new BufferedWriter(new FileWriter(new
		 * File("TestingInt.txt"), true)); output.append("Game Seed: " + seed);
		 * saveBoard(board); output.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// for (Coordinates c : coords) System.out.println(c.print());
		

		board = trimBoard(board);
		printBoard(board);
		
	}

	public static Coordinates[][] createPath( Random rnd, ArrayList<Coordinates> coords ) {
		int boardSize = rnd.nextInt(3) + 3;
		Coordinates[][] board = new Coordinates[boardSize][boardSize];
		Coordinates start = new Coordinates(rnd.nextInt(boardSize), rnd.nextInt(boardSize));
		start.start = true;
		coords.add(start);
		board[start.x][start.y] = start;

		// select a random number for the number of tiles
		int tilesNum = rnd.nextInt(14) + 2;

		Coordinates currentTile = start;
		int counter = 0;
		while ( counter < tilesNum && checkboard(board, currentTile) ) {
			int direction = rnd.nextInt(4);
			// North
			if (direction == 0 && currentTile.x > 0 && board[currentTile.x - 1][currentTile.y] == null) {
				Coordinates nextTile = new Coordinates(currentTile.x - 1, currentTile.y);
				nextTile.south = currentTile;
				currentTile.north = nextTile;
				board[nextTile.x][nextTile.y] = nextTile;
				currentTile = nextTile;
				coords.add(currentTile);
				counter++;
			}
			// East
			else if (direction == 1 && currentTile.y < (board.length - 1) && board[currentTile.x][currentTile.y + 1] == null) {
				Coordinates nextTile = new Coordinates(currentTile.x, currentTile.y + 1);
				nextTile.north = currentTile;
				currentTile.east = nextTile;
				board[nextTile.x][nextTile.y] = nextTile;
				currentTile = nextTile;
				coords.add(currentTile);
				counter++;
			}
			// South
			else if (direction == 2 && currentTile.x < (board.length - 1) && board[currentTile.x + 1][currentTile.y] == null) {
				Coordinates nextTile = new Coordinates(currentTile.x + 1, currentTile.y);
				nextTile.north = currentTile;
				currentTile.south = nextTile;
				board[nextTile.x][nextTile.y] = nextTile;
				currentTile = nextTile;
				coords.add(currentTile);
				counter++;
			}
			// West
			else if (direction == 3 && currentTile.y > 0 && board[currentTile.x][currentTile.y - 1] == null) {
				Coordinates nextTile = new Coordinates(currentTile.x, currentTile.y - 1);
				nextTile.east = currentTile;
				currentTile.west = nextTile;
				board[nextTile.x][nextTile.y] = nextTile;
				currentTile = nextTile;
				coords.add(currentTile);
				counter++;
			}

		}

		currentTile.finish = true;
		return board;
	}

	public static void addMoreTiles( Coordinates[][] board, Random rnd, ArrayList<Coordinates> coords ) {
		int tilesNum = rnd.nextInt(board.length - 1) + 1;
		for ( int i = 0; i < tilesNum; i++ ) {
			Coordinates currentTile = coords.get(rnd.nextInt(coords.size()));
			while ( currentTile.finish ) {
				currentTile = coords.get(rnd.nextInt(coords.size()));
			}

			int direction = rnd.nextInt(4);
			// North
			if (direction == 0 && currentTile.x > 0) {
				if (board[currentTile.x - 1][currentTile.y] != null) {
					if (!board[currentTile.x - 1][currentTile.y].finish) {
						Coordinates nextTile = board[currentTile.x - 1][currentTile.y];
						nextTile.south = currentTile;
						currentTile.north = nextTile;
					}
				}
				else {
					Coordinates nextTile = new Coordinates(currentTile.x - 1, currentTile.y);
					nextTile.south = currentTile;
					currentTile.north = nextTile;
					board[nextTile.x][nextTile.y] = nextTile;
					coords.add(nextTile);
				}

			}
			// West
			else if (direction == 3 && currentTile.y < (board.length - 1)) {
				if (board[currentTile.x][currentTile.y + 1] != null) {
					if (!board[currentTile.x][currentTile.y + 1].finish) {
						Coordinates nextTile = board[currentTile.x][currentTile.y + 1];
						nextTile.west = currentTile;
						currentTile.east = nextTile;
					}
				}
				else {
					Coordinates nextTile = new Coordinates(currentTile.x, currentTile.y + 1);
					nextTile.west = currentTile;
					currentTile.east = nextTile;
					board[nextTile.x][nextTile.y] = nextTile;
					coords.add(nextTile);

				}

			}
			// South
			else if (direction == 2 && currentTile.x < (board.length - 1)) {
				if (board[currentTile.x + 1][currentTile.y] != null) {
					if (!board[currentTile.x + 1][currentTile.y].finish) {
						Coordinates nextTile = board[currentTile.x + 1][currentTile.y];
						nextTile.north = currentTile;
						currentTile.south = nextTile;
					}
				}
				else {
					Coordinates nextTile = new Coordinates(currentTile.x + 1, currentTile.y);
					nextTile.north = currentTile;
					currentTile.south = nextTile;
					board[nextTile.x][nextTile.y] = nextTile;
					coords.add(nextTile);
				}

			}
			// West
			else if (direction == 3 && currentTile.y > 0) {
				if (board[currentTile.x][currentTile.y - 1] != null) {
					if (!board[currentTile.x][currentTile.y - 1].finish) {
						Coordinates nextTile = board[currentTile.x][currentTile.y - 1];
						nextTile.east = currentTile;
						currentTile.west = nextTile;
					}
				}
				else {
					Coordinates nextTile = new Coordinates(currentTile.x, currentTile.y - 1);
					nextTile.east = currentTile;
					currentTile.west = nextTile;
					board[nextTile.x][nextTile.y] = nextTile;
					coords.add(nextTile);

				}

			}

		}

	}

	public static boolean checkboard( Coordinates[][] board, Coordinates tile ) {
		int y = tile.y, x = tile.x;
		// Top Left
		if (x == 0 && y == 0) {
			if (board[x + 1][y] == null || board[x][y + 1] == null) return true;
			else return false;
		}
		// Bottom Right
		else if (x == board.length - 1 && y == board.length - 1) {
			if (board[x - 1][y] == null || board[x][y - 1] == null) return true;
			else return false;
		}
		// Top Right
		else if (x == 0 && y == board.length - 1) {
			if (board[x + 1][y] == null || board[x][y - 1] == null) return true;
			else return false;
		}
		// Bottom left
		else if (x == board.length - 1 && y == 0) {
			if (board[x - 1][y] == null || board[x][y + 1] == null) return true;
			else return false;
		}
		// Top Side
		else if (x == 0) {
			if (board[x][y + 1] == null || board[x][y - 1] == null || board[x + 1][y] == null) return true;
			else return false;
		}
		// Left Side
		else if (y == 0) {
			if (board[x][y + 1] == null || board[x - 1][y] == null || board[x + 1][y] == null) return true;
			else return false;
		}
		// Right Side
		else if (y == board.length - 1) {
			if (board[x][y - 1] == null || board[x - 1][y] == null || board[x + 1][y] == null) return true;
			else return false;
		}
		// Bottom Side
		else if (x == board.length - 1) {
			if (board[x][y + 1] == null || board[x][y - 1] == null || board[x - 1][y] == null) return true;
			else return false;
		}
		// Somewhere In the Middle
		else if (board[x - 1][y] == null || board[x][y + 1] == null || board[x][y - 1] == null || board[x + 1][y] == null) {
			return true;
		}
		else return false;
	}

	public static Coordinates[][] trimBoard( Coordinates[][] board ) {
		boolean[] rows = new boolean[board.length];
		int rowCount = 0;

		for ( int i = 0; i < board.length; i++ ) {
			int counter = 0;
			rows[i] = false;
			for ( int j = 0; j < board.length; j++ ) {
				if (board[i][j] == null) {
					counter++;
				}
			}
			if (counter == board.length){
				rows[i] = true;
				rowCount++;
			}
		}

		// X coords First
		Coordinates[][] trimBoardX = new Coordinates[board.length - rowCount][board.length];
		
		int x = 0;
		for ( int i = 0; i < board.length; i++ ) {
			if (!rows[i]) {
				for ( int j = 0; j < board.length; j++ ) {
					trimBoardX[x][j] = board[i][j];
				}
				x++;
			}
		}

		boolean[] cols = new boolean[board.length];
		int colCount = 0;
		
		for ( int i = 0; i < board.length; i++ ) {
			int counter = 0;
			cols[i] = false;
			for ( int j = 0; j < board.length; j++ ) {
				if (board[j][i] == null) {
					counter++;
				}
			}
			if (counter == board.length){
				cols[i] = true;
				colCount++;
			}
		}
		
		Coordinates[][] trimBoard = new Coordinates[trimBoardX.length][board.length - colCount];
		
		x = 0;
		for ( int i = 0; i < cols.length; i++ ) {
			if (!cols[i]) {
				for ( int j = 0; j < trimBoardX.length; j++ ) {
					trimBoard[j][x] = trimBoardX[j][i];
				}
				x++;
			}
		}

		
		return trimBoard;
	}

	public static void main( String[] args ) {
		System.out.print("Enter seed: ");
		// Scanner scanner = new Scanner(System.in);
		// String s = scanner.nextLine();
		createBoard(SimpleHash.hash("d"));
	}

	public static void saveBoard( Coordinates[][] board ) throws IOException {
		BufferedWriter output = new BufferedWriter(new FileWriter(new File("TestingInt.txt"), true));
		for ( int i = 0; i < board.length; i++ ) {

			for ( int j = 0; j < board[0].length; j++ ) {
				if (board[i][j] == null) {
					output.append("0 ");
				}
				else if (board[i][j].start) {
					if (board[i][j].east == null) {
						output.append("S ");
					}
					else {
						output.append("S-");
					}
				}
				else if (board[i][j].finish) {
					if (board[i][j].east == null) {
						output.append("F ");
					}
					else {
						output.append("F-");
					}
				}
				else {
					if (board[i][j].east == null) {
						output.append("x ");
					}
					else {
						output.append("x-");
					}
				}
			}
			output.newLine();
			for ( int j = 0; j < board[0].length; j++ ) {
				if (board[i][j] == null) {
					output.append("  ");
				}
				else if (board[i][j].south == null) {
					output.append("  ");
				}
				else output.append("| ");
			}
			output.newLine();
		}
		output.close();
	}

	public static void printBoard( Coordinates[][] board ) {
		for ( int i = 0; i < board.length; i++ ) {
			for ( int j = 0; j < board[0].length; j++ ) {
				if (board[i][j] == null) {
					System.out.print("0  ");
				}
				else if (board[i][j].start) {
					if (board[i][j].east == null) {
						System.out.print("S  ");
					}
					else {
						System.out.print("S--");
					}
				}
				else if (board[i][j].finish) {
					if (board[i][j].east == null) {
						System.out.print("F  ");
					}
					else {
						System.out.print("F--");
					}
				}
				else {
					if (board[i][j].east == null) {
						System.out.print("x  ");
					}
					else {
						System.out.print("x--");
					}
				}
			}
			System.out.println();
			for ( int j = 0; j < board[0].length; j++ ) {
				if (board[i][j] == null) {
					System.out.print("   ");
				}
				else if (board[i][j].south == null) {
					System.out.print("   ");
				}
				else System.out.print("|  ");
			}
			System.out.println();
		}
	}
}
