import java.util.Arrays;

import javax.swing.JFrame;

public class SimulationViewer extends JFrame {

	public static final int PERGENCOUNT = 250;

	public final static int FPS = 100, SPACING = 200;
	public final static int WIDTH = 400, HEIGHT = 830;

	public TetrisGame[] games;
	public TetrisAI[] ais;

	TetrisPanel tp;

	public SimulationViewer() {

		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);

		games = new TetrisGame[PERGENCOUNT];
		ais = new TetrisAI[PERGENCOUNT];

		for (int i = 0; i < games.length; i++) {
			games[i] = new TetrisGame(i);
		}

		for (int i = 0; i < ais.length; i++) {
			ais[i] = new TetrisAI();
		}

		tp = new TetrisPanel(games[0]);
		add(tp);
		
		int counter = 0;
		while (true) {
			System.out.print("Gen " + counter + ": ");
			if (counter % 10 == 0) {
				showGeneration();
			} else {
				doGeneration();
			}
			counter++;
		}

	}

	public void showGeneration() {
		tp.tg = games[0];
		while (!checkForNewGen()) {
			tp.repaint();
			for (int i = 0; i < games.length; i++) {
				if (games[i].alive) {
					games[i].update();
					ais[i].updateAI(games[i].getCurrentPiece(), games[i].getGrid());
				}
			}
//			try {
//				Thread.sleep(1000 / TetrisPanel.FPS);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		newGen();
	}

	public void doGeneration() {
		while (!checkForNewGen()) {
			for (int i = 0; i < games.length; i++) {
				if (games[i].alive) {
					games[i].update();
					ais[i].updateAI(games[i].getCurrentPiece(), games[i].getGrid());
				}
			}
		}
		newGen();
	}

	public boolean checkForNewGen() {

		for (int i = 0; i < games.length; i++) {

			if (games[i].alive) {

				return false;

			}

		}

		return true;

	}

	public void newGen() {

		int total = 0;
		for (int i = 0; i < games.length; i++) {
			total += games[i].score;
		}
		System.out.println((double) total / PERGENCOUNT);

		Arrays.parallelSort(games, (a, b) -> b.score - a.score);

		int marker = PERGENCOUNT / 3;
		int marker2 = PERGENCOUNT * 2 / 3;
		TetrisAI[] newAIs = new TetrisAI[PERGENCOUNT];
		for (int i = 0; i < marker; i++) {
			newAIs[i] = ais[games[i].ID];
		}
		for (int i = marker; i < marker2; i++) {
			newAIs[i] = TetrisAI.randomFromAI(ais[games[0].ID]);
		}
		for (int i = marker2; i < newAIs.length; i++) {
			newAIs[i] = new TetrisAI();
		}

		Arrays.parallelSort(games, (a, b) -> a.ID - b.ID);

		for (int i = 0; i < games.length; i++) {
			games[i].reset();
		}

	}

	public static void main(String[] args) {

		new SimulationViewer();

	}

}
