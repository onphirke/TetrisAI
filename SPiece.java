
public class SPiece extends Piece {
	
	private static int ID = 0;
	
	public SPiece() {
		
		super(2, ID);
		
		super.setMatrices(new int[][][] {
			
			{	
				{0, 0, 0 ,0},
				{0, 1, 1, 0},
				{1, 1, 0, 0},
				{0, 0, 0, 0}
			},
			
			{
				
				{1, 0, 0 ,0},
				{1, 1, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 0, 0}
				
			}
			
		});
		
	}
	
}
