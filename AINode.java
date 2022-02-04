
public class AINode {

	public final static int ACTIVATIONWIDTH = 12;

	double bias;
	double[] inWeights;

	double value;

	public AINode(int inNodeCount) {

		inWeights = new double[inNodeCount];

		for (int i = 0; i < inWeights.length; i++) {

			inWeights[i] = Math.random();

		}

		if (inNodeCount > 0) {

			bias = Math.random();

		}

		else {

			bias = -1;

		}

	}

	public void activation(AINode[] prevLayer) {

		for (int i = 0; i < prevLayer.length; i++) {

			value += prevLayer[i].value * inWeights[i];

		}

		value += bias;

		value = (Math.tanh(value / ACTIVATIONWIDTH) + 1) / 2;

	}

	public void randomizeFromBase(double mutationRate, double variance) {

		for (int i = 0; i < inWeights.length; i++) {
			if (Math.random() < mutationRate) {
				inWeights[i] += (Math.random() * 2 - 1) * variance;
			}
		}

		if (Math.random() < mutationRate) {
			bias += (Math.random() * 2 - 1) * variance;
		}
	}

}
