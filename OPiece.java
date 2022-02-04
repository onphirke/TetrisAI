
public class OPiece extends Piece {
	
	private static int ID = 0;
	
	public OPiece() {
		
		super(1, ID);
		
		super.setMatrices(new int[][][] {
			
			{	
				{0, 0, 0 ,0},
				{0, 1, 1, 0},
				{0, 1, 1, 0},
				{0, 0, 0, 0}
			}
			
		});
		
	}
	
}
