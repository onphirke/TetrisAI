public class TetrisAI {

	private static final double MUTATIONS = 0.01;
	private static final double VARIANCE = 0.01;
	private static final int SIGHT = 4;

	private AINode[] inputNodes = new AINode[4 + TetrisGame.gridCols * SIGHT];
	private AINode[][] hiddenNodes = new AINode[6][8];
	private AINode[] outputNodes = new AINode[4];

	public TetrisAI() {

		setup();

	}
	
	public static TetrisAI randomFromAI(TetrisAI aiO) {

		TetrisAI newAI = new TetrisAI();
		newAI.copyAI(aiO);
		
		for (int i = 0; i < newAI.hiddenNodes.length; i++) {
			
			for (int j = 0; j < newAI.hiddenNodes[0].length; j++) {
				
				if(Math.random() < MUTATIONS) {
					
					newAI.hiddenNodes[i][j].randomizeFromBase(MUTATIONS, VARIANCE);
					
				}
				
			}
			
		}
		
		for (int i = 0; i < newAI.outputNodes.length; i++) {
			
			if(Math.random() < MUTATIONS) {
				
				newAI.outputNodes[i].randomizeFromBase(MUTATIONS, VARIANCE);
				
			}
			
		}
		
		return newAI;
		
	}
	
	public void updateAI(Piece p, int[][] grid) {

		updateIns(p, grid);
		compute();
		updatePieceWithAI(p, grid);

	}

	private void updatePieceWithAI(Piece p, int[][] grid) {

		double cutOff = 0.5;
		
		if (outputNodes[0].value > cutOff) {

			p.turnRight(grid);

		}

		if (outputNodes[1].value > cutOff) {

			p.turnLeft(grid);

		}

		if (outputNodes[2].value > cutOff) {

			p.moveRight(grid);

		}

		if (outputNodes[3].value > cutOff) {

			p.moveLeft(grid);

		}

	}

	private void updateIns(Piece p, int[][] g) {

		inputNodes[0].value = p.type;
		inputNodes[1].value = p.getRotation();
		inputNodes[2].value = p.getX();
		inputNodes[3].value = p.getY();

		if (p.getY() + SIGHT >= TetrisGame.gridRows) {

			return;

		}

		for (int i = 0; i < SIGHT; i++) {

			for (int j = 0; j < g[0].length; j++) {

				inputNodes[4 + i * TetrisGame.gridCols + j].value = g[p.getY() + i][j];

			}

		}

	}

	public void setup() {

		for (int i = 0; i < inputNodes.length; i++) {

			inputNodes[i] = new AINode(0);

		}

		for (int l = 0; l < hiddenNodes.length; l++) {
			for (int i = 0; i < hiddenNodes[l].length; i++) {

				hiddenNodes[l][i] = new AINode((l == 0) ? inputNodes.length : hiddenNodes[l - 1].length);

			}
		}

		for (int i = 0; i < outputNodes.length; i++) {

			outputNodes[i] = new AINode(hiddenNodes[hiddenNodes.length - 1].length);

		}

	}

	public double compute() {

		for (int j = 0; j < hiddenNodes[0].length; j++) {

			hiddenNodes[0][j].activation(inputNodes);

		}

		for (int i = 1; i < hiddenNodes.length; i++) {

			for (int j = 0; j < hiddenNodes[i].length; j++) {

				hiddenNodes[i][j].activation(hiddenNodes[i - 1]);

			}

		}

		for (int k = 0; k < outputNodes.length; k++) {

			outputNodes[k].activation(hiddenNodes[hiddenNodes.length - 1]);

		}

		return outputNodes[0].value;

	}

	private void copyAI(TetrisAI aiO) {

		for (int i = 0; i < hiddenNodes.length; i++) {

			for (int j = 0; j < hiddenNodes[i].length; j++) {

				double[] other = aiO.hiddenNodes[i][j].inWeights;
				double[] self = hiddenNodes[i][j].inWeights;

				for (int k = 0; k < other.length; k++) {

					self[k] = other[k];

				}

			}

		}

		for (int j = 0; j < outputNodes.length; j++) {

			double[] other = aiO.outputNodes[j].inWeights;
			double[] self = outputNodes[j].inWeights;

			for (int k = 0; k < other.length; k++) {

				self[k] = other[k];

			}

		}

	}
	
	public String getSingleOutput() {
		
		/*double sum = 0.0;
		
		for (int i = 0; i < outputNodes.length; i++) {
			
			sum += outputNodes[i].value;
			
		}
		
		return sum;*/
		
		return outputNodes[0].value + " " + outputNodes[1].value + " " + outputNodes[2].value + " " + outputNodes[3].value;
		
	}

}

/*
 * 
 * INPUT rotation pieceID pieceX pieceY
 * 
 * OUTPUT turnleft turnright moveleft moveright
 * 
 */