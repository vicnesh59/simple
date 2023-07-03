package ticket;

import java.util.*;

public class Main {

	static Scanner ob=new Scanner(System.in);
	static ArrayList<People> people=new ArrayList<>();
	static ArrayList<Train> train=new ArrayList<>();
	static HashMap<People,Train> BookedList=new HashMap<>();
	static HashMap<Train,People> RacList=new HashMap<>();
	static HashMap<Train,People> WaitingList=new HashMap<>();
	
	
	public static void showbookedList()
	{
		Iterator <People> iterator=BookedList.keySet().iterator();
		while(iterator.hasNext())
		{
			People o=iterator.next();
			System.out.println("Name:"+o.getName()+"\n"
					+ "Age:"+o.getAge()+"\nSource:"+o.getFrom()
					+ "\nDestination:"+o.getTo()+"\nLocation:"+o.getChoice()+"\nStatus:");
			if(o.isBooked())
				System.out.print("Booked...");
			System.out.println("\n");
		}
	}
	
	public static void checkList(String name,String from,String to)
	{
		String choice="";
		int check=0;
		People ob1=new People();
		Train obj=new Train();
		Iterator <People> it=BookedList.keySet().iterator();
		while(it.hasNext())
		{
			People o=it.next();
			if(o.getName().equals(name) && o.getFrom().equals(from) && o.getTo().equals(to))
			{
				ob1=o;
				if(o.getChoice().equals("FirstClass"))
				{
					choice="FirstClass";
					 obj=BookedList.get(o);
					int flg=obj.getFc();
					flg++;
					obj.setFc(flg);
					check=1;
				}
				else if(o.getChoice().equals("SecondClass"))
				{
					choice="SecondClass";
					 obj=BookedList.get(o);
					int flg=obj.getSec();
					flg++;
					obj.setSec(flg);
					check=2;
				}
				else if(o.getChoice().equals("SleeperClass"))
				{
					choice="SleeperClass";
					 obj=BookedList.get(o);
					int flg=obj.getSlc();
					flg++;
					obj.setSlc(flg);
					check=3;
				}
				change(obj,choice,check);
			}
			
		}
		BookedList.remove(ob1, obj);
		
	}
	
	public static void change(Train o,String choice,int check)
	{
		Iterator<Train> iterator=RacList.keySet().iterator();
		Iterator<Train> wliterator=WaitingList.keySet().iterator();
		while(iterator.hasNext())
		{
			Train ob=iterator.next();
			if(ob.getNo()==o.getNo())
			{
				People object=RacList.get(ob);
				object.setChoice(choice);
				BookedList.put(object, ob);
				if(check==1)
				{
					int a=ob.getFc();
					a--;
					ob.setFc(a);
				}
				else if(check==2)
				{
					int a=ob.getSec();
					a--;
					ob.setSec(a);
				}
				else
				{
					int a=ob.getSlc();
					a--;
					ob.setSlc(a);
				}
				int a=ob.getRac();
				a++;
				ob.setRac(a);
				RacList.remove(ob, object);
				
			}
		}
		while(wliterator.hasNext())
		{
			Train ob=wliterator.next();
			if(ob.getNo()==o.getNo())
			{
				People object=WaitingList.get(ob);
				object.setChoice("RAC");
				int b=ob.getRac();
				b--;
				ob.setRac(b);
				int a=ob.getWl();
				a++;
				ob.setWl(a);
				RacList.put(ob, object);
				WaitingList.remove(ob, object);
			}
		}
		
	}

	
	public static void main(String[] args) {
		
		boolean start=true;
		int choice;
		while(start)
		{
			details();
			choice=ob.nextInt();
			switch(choice)
			{
			case 1:
					createAccount();
					break;
			case 2:
					bookTicket();
					break;
			case 3:
					showbookedList();
					break;
			case 4:
					addTrain();
					break;
			case 5:
					cancelticket();
					break;
			case 6:
					start=false;
					break;
					
			}
			
		}

	}
	
	public static void details()
	{
		System.out.println("1.Create A/C\n2.Book Ticket\n3.Show booked list\n4.Add Train\n5.Cancel ticket\n6.Exit\nEnter your choice");
	}
	
	public static People createAccount()
	{
		String name,address;
		int age,flg=0;
		System.out.println("Enter your name:");
		name=ob.next();
		System.out.println("Enter your age:");
		age=ob.nextInt();
		System.out.println("Enter your address:");
		address=ob.next();
		for(People obj:people)
		{
			if(obj.getName().equals(name)&& obj.getAge()==age && obj.getAddress().equals(address))
			{
				flg=1;
				System.out.println("Account Exist");
				return obj;
			}
		}
		if(flg==0)
		{
			People o=new People(name,address,age);
			people.add(o);
			return o;
		}
		return null;
	}
	
	public static void bookTicket()
	{
		String from,to,choice;
		People o=createAccount();
		System.out.println("Enter your source station:");
		from=ob.next();
		System.out.println("Enter your destination station:");
		to=ob.next();
		checkTrain(from,to,o);
	}
	
	public static void addTrain()
	{
		String from,to;
		System.out.println("Enter its Source:");
		from=ob.next();
		System.out.println("Enter its Destination:");
		to=ob.next();
		Train o=new Train(from,to);
		train.add(o);
		System.out.println("Train Added....");
	}
	
	public static void checkTrain(String from,String to,People obj)
	{
		int choice,setter,flag=0;
		for(Train o:train)
		{
			if(o.getFrom().equals(from) && o.getTo().equals(to))
			{
				flag=1;
				o.printDetails();
				choice=ob.nextInt();
				if(choice==1 || choice==2 || choice==3)
				{
					if(o.getFc()>0 && choice==1) {
						System.out.println("Preferred Berth Booked..");
						obj.setChoice("FirstClass");
						obj.setFrom(from);
						obj.setTo(to);
						obj.setBooked(true);
						setter=o.getFc();
						setter--;
						o.setFc(setter);
						BookedList.put(obj, o);
					}
					else if(o.getSec()>0 && choice==2 || o.getSec()>0 && choice==1)
					{
						System.out.println("...Second Class Booked..");
						obj.setChoice("SecondClass");
						obj.setFrom(from);
						obj.setTo(to);
						obj.setBooked(true);
						setter=o.getSec();
						setter--;
						o.setSec(setter);
						BookedList.put(obj, o);
					}
					else if(o.getSlc()>0 && choice==3 || o.getSlc()>0 && choice==2 || o.getSlc()>0 && choice==1)
					{
						System.out.println("...Sleeper Class Booked..");
						obj.setChoice("SleeperClass");
						obj.setFrom(from);
						obj.setTo(to);
						obj.setBooked(true);
						setter=o.getSlc();
						setter--;
						o.setSlc(setter);
						BookedList.put(obj, o);
					}
					else if(o.getRac()>0)
					{
						System.out.println("Preferred Berth not available...RAC Booked..");
						obj.setChoice("RAC");
						obj.setFrom(from);
						obj.setTo(to);
						obj.setBooked(true);
						setter=o.getRac();
						setter--;
						o.setRac(setter);
						RacList.put(o, obj);
					}
					else if(o.getWl()>0)
					{
						System.out.println("Preferred Berth not available...Added to Waiting List..");
						obj.setChoice("WaitingList");
						obj.setFrom(from);
						obj.setTo(to);
						obj.setBooked(true);
						setter=o.getWl();
						setter--;
						o.setWl(setter);
						WaitingList.put(o, obj);
					}
								
				}
				else
				{
					System.out.println("Booking Cancelled..");
					break;
				}
			}
		}
		if(flag==0)
			System.out.println("No Train Available");
	}
	
	public static void cancelticket()
	{
		String name,from,to;
		System.out.println("Enter your name:");
		name=ob.next();
		System.out.println("Enter your Source:");
		from=ob.next();
		System.out.println("Enter your Destination:");
		to=ob.next();
		checkList(name,from,to);
	}
	
	
}


