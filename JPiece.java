
public class JPiece extends Piece {
	
	private static int ID = 0;
	
	public JPiece() {
		
		super(4, ID);
		
		super.setMatrices(new int[][][] {
			
			{	
				{0, 0, 1 ,0},
				{1, 1, 1, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			},
			
			{
				
				{0, 1, 0 ,0},
				{0, 1, 0, 0},
				{0, 1, 1, 0},
				{0, 0, 0, 0}
				
			}, 
			
			{	
				{0, 0, 0 ,0},
				{1, 1, 1, 0},
				{1, 0, 0, 0},
				{0, 0, 0, 0}
			},
			
			{
				
				{1, 1, 0 ,0},
				{0, 1, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 0, 0}
				
			}
			
		});
		
	}
	
}
