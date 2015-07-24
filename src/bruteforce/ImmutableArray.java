package bruteforce;

public class ImmutableArray 
{
	int[][] array;

	public ImmutableArray(int[][] array)
	{
		this.array = array;
	}
	
	public ImmutableArray add(int value, int x_index, int y_index)
	{
		int[][] new_array = new int[9][9];
		
		for(int i = 0 ; i < 9 ; i ++)
		{
			for(int j = 0 ; j < 9 ; j ++)
			{
				new_array[i][j] = array[i][j];
			}
		}
		new_array[x_index][y_index] = value;
		return new ImmutableArray(new_array);
	}
	/**
	 * pos[0] is x
	 * pos[1] is y
	 * @return
	 */
	public int[] firstEmpty()
	{
		int[] pos = new int[2];
		for(int i = 0 ; i < 9 ; i ++)
		{
			for(int j = 0 ; j < 9 ; j ++)
			{
				if(array[i][j] == 0)
				{
					pos[0] = i;pos[1] = j;
					return pos;
				}
			}
		}
		return null;
	}
	public int get(int x_index, int y_index)
	{
		return array[x_index][y_index];
	}
	
	public String toString()
	{
		String s = "";
		for(int i = 0 ; i < 9 ; i ++)
		{
			for(int j = 0 ; j < 9 ; j ++)
			{
				s += array[i][j] + " ";
			}
			s += "\n";
		}
		return s; 
	}
}
