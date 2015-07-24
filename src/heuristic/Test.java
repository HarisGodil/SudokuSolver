package heuristic;
import java.util.ArrayList;

public class Test
{
	public static void main(String arg[])
	{
		ArrayList<Integer> p = new ArrayList<Integer>();
		p.add(1); p.add(2); p.add(3);
		p.remove(1);
		System.out.println(p.get(0));
	}
}
