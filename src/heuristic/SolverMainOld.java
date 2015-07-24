package heuristic;

public class SolverMainOld 
{
	public static void main(String args[])
	{
		Square[][] board = null;
		try
		{
			board = BoardReader.boardGenerator("sudoku1.txt");
		}catch(Exception e){e.printStackTrace();}
		
		System.out.println("Given Board:");
		printBoard(board);
		
		solve(board);
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
		boolean done = false;
		while(!done)
		{
			done = true;
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
							//System.out.println("______________________________________________\n");
							//printBoard(board);
						}
						else
						{
							done = false;
						}
					}
				}
			}
		}
	}
}
/*
for(Integer poss:((EmptySquare)board[i][j]).possibilities)
{
	int count = 0;
	for(int k = 0 ; k < 9 ; k ++)
	{
		if(board[i][k].filed())
		{
			if(((FilledSquare)board[i][k]).value == poss)
			{
				count += 1;
			}
		}
		else
		{
			if(((EmptySquare)board[i][k]).possibilities.indexOf(poss) == -1)
			{
				count += 1;
			}
		}
	}
	if(count == 1)
	{
		board[i][j] = new FilledSquare(poss);
	}
}
if(!board[i][j].filed())
{
	for(Integer poss:((EmptySquare)board[i][j]).possibilities)
	{
		int count = 0;
		for(int k = 0 ; k < 9 ; k ++)
		{
			if(board[k][j].filed())
			{
				if(((FilledSquare)board[k][j]).value == poss)
				{
					count += 1;
				}
			}
			else
			{
				if(((EmptySquare)board[k][j]).possibilities.indexOf(poss) == -1)
				{
					count += 1;
				}
			}
		}
		if(count == 1)
		{
			board[i][j] = new FilledSquare(poss);
		}
	}
}
if(!board[i][j].filed())
{
	for(Integer poss:((EmptySquare)board[i][j]).possibilities)
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
					if(((FilledSquare)board[sq_x+k][sq_y+m]).value == poss)
					{
						count += 1;
					}
				}
				else
				{
					if(((EmptySquare)board[sq_x+k][sq_y+m]).possibilities.indexOf(poss) == -1)
					{
						count += 1;
					}
				}
			}
		}
		if(count == 1)
		{
			board[i][j] = new FilledSquare(poss);
		}
	}
}
*/