package heuristic;

public class FilledSquare extends Square
{
	int value;
	boolean unUsed = true;
	public boolean filed() 
	{
		return true;
	}
	public FilledSquare(int value)
	{
		this.value = value;
	}
	public String toString()
	{
		return value + "";
	}
}
