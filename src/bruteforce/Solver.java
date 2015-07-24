package bruteforce;

import java.util.Date;

public class Solver {
	static int counter = 0;
	@SuppressWarnings("deprecation")
	public static void main(String args[])
	{
		ImmutableArray board = null;
		try
		{
			board = BoardGenerator.readBoard("sudoku3.txt"); //for sudoku3.txt took: 2min 137ms (total 2137ms)
		}catch(Exception e){e.printStackTrace();}
		System.out.println("Given Board:");
		System.out.println(board);
		
		Date d = new Date();
		System.out.println(d.getMinutes() + ":" + d.getSeconds() + ":" + (System.currentTimeMillis() % 1000));

		board = solveBoard(board);
		
		d = new Date();
		System.out.println(d.getMinutes() + ":" + d.getSeconds()+ ":" + (System.currentTimeMillis() % 1000));
		System.out.println(board);
	}

	public static ImmutableArray solveBoard(ImmutableArray board) {
		int[] pos = board.firstEmpty();
		if(pos == null)
		{
			if(validBoard(board))
			{
				return board;
			}
			
			return null;
		}
		else
		{
			//System.out.println(pos[0] + " " + pos[1]);
			for(int k = 1 ; k <= 9 ; k ++)
			{
				ImmutableArray newBoard = board.add(k, pos[0], pos[1]);
				newBoard = solveBoard(newBoard);
				if(newBoard != null)
				{
					return newBoard;
				}
			}
		}
		return null;
	}
	
	public static boolean validBoard(ImmutableArray board)
	{
		//counter ++;
		//System.out.println(counter);
		for(int k = 0 ; k < 9 ; k ++)
		{
			int sum = 0;
			for(int i = 0 ; i < 9 ; i ++)
			{
				sum += Math.pow(10 , board.get(i , k) - 1);
			}
			if(sum != 111111111)
			{
				return false;
			}
		}
		
		for(int k = 0 ; k < 9 ; k ++)
		{
			int sum = 0;
			for(int j = 0 ; j < 9 ; j ++)
			{
				sum += Math.pow(10 , board.get(k , j) - 1);
			}
			if(sum != 111111111)
			{
				return false;
			}
		}
		
		for(int q = 0 ; q < 9 ; q += 3)
		{
			for(int w = 0 ; w < 9 ; w += 3)
			{
				int sum = 0;
				for(int e = 0 ; e < 3 ; e ++)
				{
					for(int r = 0 ; r < 3 ; r ++)
					{
						sum += Math.pow(10 , board.get(q + e , w + r) - 1);
					}
				}
				if(sum != 111111111)
				{
					return false;
				}
			}
		}
		return true;
	}
}
