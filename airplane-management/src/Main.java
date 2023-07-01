import java.util.*;

public class Main {
	
	static Scanner ob=new Scanner(System.in);
	static ArrayList<Airplane> airplanes=new ArrayList<>();
	static ArrayList<Booked> bookedlist=new ArrayList<>();
	static ArrayList<People> peoples=new ArrayList<>();
	
	public static void main(String[] args)
	{
		boolean start=true;
		int choice;
		while(start)
		{
			details();
			choice=ob.nextInt();
			switch(choice)
			{
			case 1:
					newPlanes();
					break;
			case 2:
					bookticket();
					break;
			case 3:
					start=false;
					break;
			}
		}
		
		
	}
	
	public static void details()
	{
		System.out.println("1.New Airplanes\n 2.Book Tickets\n3.Exit");
	}
	
	public static void newPlanes()
	{
		String name,from,desti;
		System.out.println("Enter the name:");
		name=ob.next();
		System.out.println("Enter its origin:");
		from=ob.next();
		System.out.println("Enter its destination:");
		desti=ob.next();
		Airplane o=new Airplane(name,from,desti);
		airplanes.add(o);
		System.out.println("Aeroplane added");
	}
	
	public static void bookticket()
	{
		String name,from,desti;
		int age;
		System.out.println("Enter your name:");
		name=ob.next();
		System.out.println("Enter your age:");
		age=ob.nextInt();
		System.out.println("Enter your orgination:");
		from=ob.next();
		System.out.println("Enter your destination:");
		desti=ob.next();
		int choice=check(from,desti,name);
		if(choice!=0)
		{
			String Class="";
			if(choice==1)Class="First Class";
			if(choice==2)Class="Second Class";
			if(choice==3)Class="Economic Class";
			System.out.println("Ticket Booked");
			People obj=new People(name,Class,from,desti,age);
			peoples.add(obj);
		}
		
	}
	
	public static int check(String from, String desti,String name)
	{
		int choice,flg=0;
		for(Airplane o: airplanes)
		{
			if(o.getFrom().equals(from)&&o.getDestination().equals(desti))
			{
				flg++;
				if(o.getEcticket()>0 || o.getFcticket()>0 || o.getScticket()>0)
				{
					System.out.println("Enter your choice:\n1.First Class rs.250 "
								+o.getFcticket()+"\n2.Second Class rs.150 "
							+o.getScticket()+"\n3.Economic Class rs.100 "
							+o.getEcticket()
							);
					choice=ob.nextInt();
					if(choice==1 && o.getFcticket()>0)
					{
						int ticket=o.getFcticket();
						Booked object=new Booked(o.getId(),o.getName(),name);
						bookedlist.add(object);
						ticket--;
						o.setFcticket(ticket);
						return 1;
					}
					else if(choice==2 && o.getScticket()>0)
					{
						int ticket=o.getScticket();
						Booked object=new Booked(o.getId(),o.getName(),name);
						bookedlist.add(object);
						ticket--;
						o.setScticket(ticket);
						return 2;
					}
					else if(choice==3 && o.getEcticket()>0)
					{
						int ticket=o.getEcticket();
						Booked object=new Booked(o.getId(),o.getName(),name);
						bookedlist.add(object);
						ticket--;
						o.setEcticket(ticket);
						return 3;
					}
					else
					{
						System.out.println("No Ticket Available...");
						return 0;
					}
					
					
				}
				else
				{
					System.out.println("Ticket Unavailable");
					return 0;
				}
			}
		}
		if(flg==0)
			System.out.println("No Flights Found");
		return 0;
	}

}
