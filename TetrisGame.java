import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

public class TetrisGame implements Comparable<TetrisGame> {

	public static boolean doCheck = true;
	public static final int gridRows = 40, gridCols = 20;
	private Piece currentPiece;
	private int[][] grid = new int[gridRows][gridCols];
	public final int ID;

	int score = 0;

	boolean alive = true;

	public TetrisGame(int ID) {
		this.ID = ID;
		currentPiece = Piece.generateRandomPiece();
		update();
	}
	
	public void reset() {
		for (int i = 0; i < grid.length; i++) {
			Arrays.fill(grid[i], 0);
		}
		score = 0;
		alive = true;
		currentPiece = Piece.generateRandomPiece();
		update();
	}
	
	public void addWidthScore() {
		int maxWidth = 0;
		for (int r = 0; r < grid.length; r++) {
			int width = 0;
			for (int c = 0; c < grid[r].length; c++) {
				width += grid[r][c];
			}
			maxWidth = Math.max(width, maxWidth);
		}
		score += maxWidth * 3;
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public Piece getCurrentPiece() {
		return currentPiece;
	}

	public void update() {
		
		for (int c = 0; c < grid[0].length; c++) {

			if (grid[2][c] == 1) {

				alive = false;
				addWidthScore();
				return;

			}

		}

		int[][] pieceMatrix = currentPiece.getMatrix();

		for (int r = 0; r < pieceMatrix.length; r++) {

			for (int c = 0; c < pieceMatrix[r].length; c++) {

				if (pieceMatrix[r][c] == 1) {

					if (currentPiece.getY() + r >= grid.length - 1) {

						changePiece(pieceMatrix);
						break;

					}

					if (grid[currentPiece.getY() + r + 1][currentPiece.getX() + c] == 1) {

						changePiece(pieceMatrix);
						break;

					}

				}

			}

		}

		currentPiece.update();

	}

	private void changePiece(int[][] pieceMatrix) {

		for (int r = 0; r < pieceMatrix.length; r++) {

			for (int c = 0; c < pieceMatrix[r].length; c++) {

				if (pieceMatrix[r][c] == 1) {

					grid[currentPiece.getY() + r][currentPiece.getX() + c] = 1;

				}

			}

		}

		score++;

		currentPiece = Piece.generateRandomPiece();

		checkShiftGrid();

	}

	private void checkShiftGrid() {

		for (int r = 0; r < grid.length; r++) {

			for (int c = 0; c < grid[r].length; c++) {

				if (grid[r][c] != 1) {

					break;

				}

				if (grid[r][c] == 1 && c == grid[r].length - 1) {

					score += 4;
					eraseRow(r);

				}

			}

		}

	}

	private void eraseRow(int rowIndex) {

		int[] tmp = grid[rowIndex];
		
		for (int r = rowIndex; r > 0; r++) {

			for (int c = 0; c < grid[0].length; c++) {

				grid[r][c] = grid[r - 1][c];

			}

		}

		grid[0] = tmp;
		for (int c = 0; c < grid[0].length; c++) {

			grid[0][c] = 0;

		}

	}

	@Override
	public int compareTo(TetrisGame o) {

		return score - o.score;

	}

}
