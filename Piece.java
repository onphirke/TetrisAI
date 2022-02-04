
public abstract class Piece {

	private int[][][] matrices;
	private int rotation;
	private int states;
	private int x = 8, y = 0;
	private int width, height;
	public final int type;

	public void update() {

		goDown();

	}

	public boolean turnRight(int[][] grid) {

		innerTurnRight();
		if (!check(grid)) {

			innerTurnLeft();
			return false;

		}
		return true;

	}

	public boolean turnLeft(int[][] grid) {

		innerTurnLeft();
		if (!check(grid)) {

			innerTurnRight();
			return false;

		}
		return true;

	}

	private void innerTurnRight() {

		rotation++;
		if (rotation >= states) {

			rotation -= states;

		}

	}

	private void innerTurnLeft() {

		rotation--;
		if (rotation < 0) {

			rotation += states;

		}

	}

	public boolean moveRight(int[][] grid) {

		innerMoveRight();
		if (!check(grid)) {

			innerMoveLeft();
			return false;

		}
		return true;

	}

	public boolean moveLeft(int[][] grid) {

		innerMoveLeft();
		if (!check(grid)) {

			innerMoveRight();
			return false;

		}
		return true;

	}

	public void innerMoveRight() {

		x++;

	}

	public void innerMoveLeft() {

		x--;

	}

	public boolean check(int[][] grid) {

		for (int r = 0; r < getMatrix().length; r++) {

			for (int c = 0; c < getMatrix()[r].length; c++) {

				if (getMatrix()[r][c] == 1) {

					if (r + y >= grid.length - 1 || r + y < 0 || c + x >= grid[0].length || c + x < 0) {

						return false;

					}

					if (grid[r + y][c + x] == 1) {

						return false;

					}

				}

			}

		}

		return true;

	}

	public int getRotation() {
		return rotation;
	}

	public int[][] getMatrix() {
		return matrices[rotation];
	}

	public void setMatrices(int[][][] matrices) {
		this.matrices = matrices;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void goDown() {

		y++;

	}

	public Piece(int states, int type) {

		this.states = states;
		this.type = type;

	}

	public static Piece generateRandomPiece() {

		int pieceType = (int) (Math.random() * 7);

		switch (pieceType) {

		case 0:
			return new IPiece();
			
		case 1:
			return new LPiece();

		case 2:
			return new JPiece();
			
		case 3:
			return new OPiece();

		case 4:
			return new SPiece();
			
		case 5:
			return new ZPiece();
			
		case 6:
			return new TPiece();
			
		}

		return null;

	}

}
