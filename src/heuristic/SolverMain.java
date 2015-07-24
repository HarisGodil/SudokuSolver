package heuristic;

import java.util.Date;

public class SolverMain
{
	@SuppressWarnings("deprecation")
	public static void main(String args[])
	{
		Square[][] board = null;
		try
		{
			board = BoardReader.boardGenerator("sudoku1.txt"); //for sudoku3.txt took: <1 ms
		}catch(Exception e){e.printStackTrace();}
		
		System.out.println("Given Board:");
		printBoard(board);

		Date d = new Date();
		System.out.println(d.getMinutes() + ":" + d.getSeconds() + ":" + (System.currentTimeMillis() % 1000));

		solve(board);
		
		d = new Date();
		System.out.println(d.getMinutes() + ":" + d.getSeconds() + ":" + (System.currentTimeMillis() % 1000));

		System.out.println("Done:");
		printBoard(board);
	}
	public static void printBoard(Square[][] board)
	{
		for(int i = 0 ; i < 9 ; i ++)
		{
			for(int j = 0 ; j < 9 ; j ++)
			{
				System.out.print(board[i][j]);
				System.out.print(" ");
				if(j % 3 == 2)
				{
					System.out.print("  ");
				}
			}
			System.out.println();
			if(i % 3 == 2)
			{
				System.out.println();
			}
		}
	}
	
	public static void solve(Square[][] board)
	{
		while(!updatePossibilities(board))
		{
			checkGroups(board);
		}
	}
	
	public static boolean updatePossibilities(Square[][] board)
	{
		boolean done = true;
		for(int i = 0 ; i < 9 ; i ++)
		{
			for(int j = 0 ; j < 9 ; j ++)
			{
				if(board[i][j].filed() && ((FilledSquare)board[i][j]).unUsed)
				{
					int value = ((FilledSquare) board[i][j]).value;
					for(int k = 0 ; k < 9 ; k ++)
					{
						if(!board[i][k].filed())
						{
							((EmptySquare)board[i][k]).remove(value);
						}
					}
					for(int k = 0 ; k < 9 ; k ++)
					{
						if(!board[k][j].filed())
						{
							((EmptySquare)board[k][j]).remove(value);
						}
					}
					int sq_x = i / 3; sq_x *= 3;
					int sq_y = j / 3; sq_y *= 3;
					for(int k = 0 ; k < 3 ; k ++)
					{
						for(int m = 0 ; m < 3 ; m ++)
						{
							if(!board[sq_x+k][sq_y+m].filed())
							{
								((EmptySquare)board[sq_x+k][sq_y+m]).remove(value);
							}
						}
					}
					((FilledSquare)board[i][j]).unUsed = false;
				}
				else if(!board[i][j].filed())
				{
					if(((EmptySquare)board[i][j]).possibilities.size() == 1)
					{
						int value = ((EmptySquare)board[i][j]).possibilities.get(0);
						board[i][j] = new FilledSquare(value);
						//System.out.println("___________\n");
						//printBoard(board);
					}
					else
					{
						done = false;
					}
				}
			}
		}
		return done;
	}
	
	public static void checkGroups(Square[][] board)
	{
		for(int i = 0 ; i < 9 ; i ++)
		{
			for(int j = 0 ; j < 9 ; j ++)
			{
				if(!board[i][j].filed())
				{
					for(Integer pos : ((EmptySquare)board[i][j]).possibilities)
					{
						int count = 0;
						int sq_x = i / 3; sq_x *= 3;
						int sq_y = j / 3; sq_y *= 3;
						for(int k = 0 ; k < 3 ; k ++)
						{
							for(int m = 0 ; m < 3 ; m ++)
							{
								if(board[sq_x+k][sq_y+m].filed())
								{
									if(((FilledSquare)board[sq_x+k][sq_y+m]).value == pos)
									{
										count += 3;
									}
								}
								else
								{
									if(((EmptySquare)board[sq_x+k][sq_y+m]).possibilities.indexOf(pos) != -1)
									{
										count ++;
									}
								}
							}
						}
						if(count == 1)
						{
							if(!board[i][j].filed())
							{
								board[i][j] = new FilledSquare(pos);
								//System.out.println("______________________________________________\n");
								//printBoard(board);
							}
						}
					}
				}
				/////////////////////////////////////////
				if(!board[i][j].filed())
				{
					for(Integer pos : ((EmptySquare)board[i][j]).possibilities)
					{
						int count = 0;
						for(int k = 0 ; k < 9 ; k ++)
						{
							if(board[k][j].filed())
							{
								if(((FilledSquare)board[k][j]).value == pos)
								{
									count += 3;
								}
							}
							else
							{
								if(((EmptySquare)board[k][j]).possibilities.indexOf(pos) != -1)
								{
									count ++;
								}
							}
						}
						if(count == 1)
						{
							if(!board[i][j].filed())
							{
								board[i][j] = new FilledSquare(pos);
								//System.out.println("______________________________________________\n");
								//printBoard(board);
							}
						}
					}
				}
				////////////////////////////////////////////
				if(!board[i][j].filed())
				{
					for(Integer pos : ((EmptySquare)board[i][j]).possibilities)
					{
						int count = 0;
						for(int k = 0 ; k < 9 ; k ++)
						{
							if(board[i][k].filed())
							{
								if(((FilledSquare)board[i][k]).value == pos)
								{
									count += 3;
								}
							}
							else
							{
								if(((EmptySquare)board[i][k]).possibilities.indexOf(pos) != -1)
								{
									count ++;
								}
							}
						}
						if(count == 1)
						{
							if(!board[i][j].filed())
							{
								board[i][j] = new FilledSquare(pos);
								//System.out.println("______________________________________________\n");
								//printBoard(board);
							}
						}
					}
				}
			}
		}
	}
}