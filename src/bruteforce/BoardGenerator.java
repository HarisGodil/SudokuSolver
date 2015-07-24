package bruteforce;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoardGenerator {
	public static ImmutableArray readBoard(String filepath) throws FileNotFoundException
	{
		@SuppressWarnings("resource")
		Scanner file = new Scanner(new File(filepath));
		int[][] array = new int[9][9];
		for(int i = 0 ; i < 9 ; i ++)
		{
			String s = file.nextLine();
			String[] ints = s.split(" ");
			for(int j = 0 ; j < 9 ; j++)
			{
				array[i][j] = Integer.parseInt(ints[j]);
			}
		}
		
		return new ImmutableArray(array);
	}
}
