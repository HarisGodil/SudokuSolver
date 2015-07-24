package heuristic;
import java.util.ArrayList;
import java.util.Arrays;

public class EmptySquare extends Square
{
	final static Integer[] start = {1,2,3,4,5,6,7,8,9};
	ArrayList<Integer> possibilities;
	public boolean filed() 
	{
		return false;
	}
	public EmptySquare()
	{
		this.possibilities = new ArrayList<Integer>();
		this.possibilities.addAll(Arrays.asList(start));
	}
	public String toString()
	{
		return "0";
	}
	public void remove(int value)
	{
		int index = possibilities.indexOf(value);
		if(index != -1)
		{
			this.possibilities.remove(index); 
		}
		/*
		System.out.print(value + "!:");
		for(Integer i : possibilities)
		{
			System.out.print(i + " ");
		}
		System.out.println();
		*/
	}
}