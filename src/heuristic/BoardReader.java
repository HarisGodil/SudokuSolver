package heuristic;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class BoardReader 
{
	public static Square[][] boardGenerator(String filepath) throws FileNotFoundException
	{
		Square board[][] = new Square[9][9];
		@SuppressWarnings("resource")
		Scanner file = new Scanner(new File(filepath));
		String[] values;
		for(int i = 0 ; i < 9 ; i ++)
		{
			values = file.nextLine().split(" ");
			for(int j = 0 ; j < 9 ; j ++)
			{
				int value = Integer.parseInt(values[j]);
				if(value == 0)
				{
					board[i][j] = new EmptySquare();
				}
				else
				{
					board[i][j] = new FilledSquare(value);
				}
			}
		}
		return board;
	}
}
