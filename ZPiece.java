
public class ZPiece extends Piece {
	
	private static int ID = 0;
	
	public ZPiece() {
		
		super(2, ID);
		
		super.setMatrices(new int[][][] {
			
			{	
				{0, 0, 0 ,0},
				{0, 1, 0, 0},
				{1, 1, 1, 0},
				{0, 0, 0, 0}
			},
			
			{
				
				{0, 1, 0 ,0},
				{1, 1, 0, 0},
				{1, 0, 0, 0},
				{0, 0, 0, 0}
				
			}
			
		});
		
	}
	
}
