import java.util.*;

public class Test
{
	public Test()
	{
		System.out.println("Test started!");
	}
	
	public static void main(String[] args)
	{
		Test test = new Test();
		Scanner scan = new Scanner(System.in);
		int input;
		
		ArrayList<Subject> subjList = new ArrayList<Subject>();
		
		subjList.add(new Subject("Calculus"));
		subjList.add(new Subject("Discreet Structure"));
		subjList.add(new Subject("Programming Fundamentals"));
		subjList.add(new Subject("Prof Development"));
		subjList.add(new Subject("Computational Methods"));
		
		for (Subject s : subjList)
		{
			for(int i = 1; i <= 5; i++)
				s.addDiscussion(new Discussion("Topic " + i + " of " + s.getSubjName()));
		}
		
		System.out.println("=======================");
		System.out.println("=  List of subjects   =");
		System.out.println("=======================");
		
		for(Subject s : subjList)
			System.out.println(subjList.indexOf(s) + ". " + s);
		//System.out.println(subjList);
		
		input = scan.nextInt();
		
		System.out.println("List of " + subjList.get(input) + " discussions:");
		for(int i = 0; i < 5; i++)
			System.out.println(i + ". " + subjList.get(input).getDiscussion(i) + ".");
	}
}
