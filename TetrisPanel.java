import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TetrisPanel extends JPanel {

	public static final Font font = new Font("monospace", Font.BOLD, 25);
	public static final int FPS = 60;
	public static final int tileSize = 19;

	public TetrisGame tg;

	public TetrisPanel(TetrisGame tg) {

		this.tg = tg;

	}

	@Override
	public void paint(Graphics g) {

		int[][] grid = tg.getGrid();
		Piece currentPiece = tg.getCurrentPiece();
		int score = tg.score;
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, grid[0].length * tileSize, grid.length * tileSize);

		g.setColor(Color.DARK_GRAY);

		for (int r = 0; r < grid.length; r++) {

			for (int c = 0; c < grid[r].length; c++) {

				if (grid[r][c] == 1) {

					g.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);

				}

			}

		}

		g.setColor(Color.BLUE);

		int[][] pieceMatrix = currentPiece.getMatrix();

		for (int r = 0; r < pieceMatrix.length; r++) {

			for (int c = 0; c < pieceMatrix[r].length; c++) {

				if (pieceMatrix[r][c] == 1) {

					g.fillRect((currentPiece.getX() + c) * tileSize, (currentPiece.getY() + r) * tileSize, tileSize,
							tileSize);

				}

			}

		}

		if (true) {

			g.setFont(font);
			g.setColor(Color.GREEN);
			g.drawString("" + score, 5, 50);

		}

	}

}
