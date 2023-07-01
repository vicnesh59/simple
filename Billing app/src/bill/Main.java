package bill;

import java.util.*;

public class Main {
	static Scanner ob=new Scanner(System.in);
	static ArrayList<Product> products=new ArrayList<>();
	static ArrayList<People> peoples=new ArrayList<>();
	static String na;
	
	private static void printproducts()
	{
		System.out.println("Products"+"\n"
				+"Name\t"+"Ava.stock\t"+"Price"
				);
		for(Product p : products)
		{
			System.out.println(""+p.name+"\t"+p.quantity+"\t"+p.price);
		}
	}
	
	private static int addpeople(String name)
	{
		int flg=0;
		for(People o:peoples)
		{
			if(o.name.equals(name))
			{
				flg=1;
				System.out.println("People exists");
				return flg;
			}
		}
		return flg;
	}
	
	
	private static int addproduct(String name)
	{
		int flg=0,op;
		for(Product o: products)
		{
			if(o.name.equals(name))
			{
				flg=1;
				System.out.println("Product exists");
				System.out.println("1.Update quantity\n2.Remove product\n3.Change price\n4.Exit");
				op=ob.nextInt();
				switch(op)
				{
					case 1:
							System.out.println("Enter number to add:");
							o.quantity+=ob.nextInt();
							break;
					case 2:
							products.remove(o);
							break;
					case 3:
							System.out.println("Enter the new price:");
							o.price=ob.nextFloat();
					case 4:
							break;
				}
				return flg;
			}
		}
		return flg;
	}

	public static void main(String[] args) {
		
		//1.add products(update,delete,initialize)
		//2.Add consumer details
		//3.buy product
		//4.generate bill
		boolean choice=true;
		int option;

		
		System.out.println("Welcome");
		while(choice)
		{
			details();
			printproducts();
			option=ob.nextInt();
			switch(option)
			{
				case 1:
						add();
						break;
				case 2:
						addconsumer();
						break;
				case 3:
						buyproduct();
						break;
				case 4:
						generatebill();
						break;
				case 5:
						choice=false;
						break;
			}
			
		}

	}
	
	public static void details()
	{
		System.out.println("\n1.Add Products \n2.Add Consumer \n3.Buy Product \n4.Generate Bill\n5.Exit");
		System.out.println("Enter Your choice:");
	}
	
	public static void add()
	{
		int quan,price,flg;
		String name;
		System.out.println("Enter the quantities:");
		quan=ob.nextInt();
		System.out.println("Enter the Price:");
		price=ob.nextInt();
		System.out.println("Enter the name:");
		name=ob.next();
		flg=addproduct(name);
		if(flg==0) {
		Product o=new Product(quan,price,name);
		products.add(o);
		}
	}
	
	public static void addconsumer()
	{
		String name,address;
		int flg,gp;
		System.out.println("Enter the name:");
		name=ob.next();
		System.out.println("Enter the Address:");
		address=ob.next();
		System.out.println("1.Set Gold Premium");
		gp=ob.nextInt();
		flg=addpeople(name);
		if(flg==0)
		{
			People o=new People(name,address);
			if(gp==1)
				o.setGoldpremium(true);
			peoples.add(o);
		}
		
	}
	
	public static void buyproduct()
	{
		String name,pname;
		boolean ifpremium=false;
		float discount=0,price;
		int q,n,flg=0,lg=0;
		System.out.println("Enter your name:");
		name=ob.next();
		na=name;
		for(People o:peoples)
		{
			if(o.name.equals(name))
			{
				lg=1;
				ifpremium=o.isGoldpremium();
				if(ifpremium)
					discount=(float) 0.1;
				System.out.println("How Many products:");
				n=ob.nextInt();
				while(n>0)
				{
					flg=0;
					System.out.println("Enter the product name:");
					pname=ob.next();
					for(Product p:products)
					{
						if(p.name.equals(pname))
						{
							flg=1;
							System.out.println("Enter the quantity:");
							q=ob.nextInt();
							if(p.quantity<q)
							{
								System.out.println("Quantity is less");
								n++;
							}
							else
							{
								p.quantity-=q;
								price=q*p.price;
								price=price-(price*discount);
								o.add(pname, q, price);
							}
						}
					}
					if(flg==0) {
						System.out.print("Product is not available\n");
						n++;
					}
						
					n--;
				}
				
			}
		}
		if(lg==0)
		{
			int op;
			System.out.println("No User Found"+"\n"
					+"Press 1 to Add user?" 
					);
			op=ob.nextInt();
			if(op==1)
			{
				System.out.print("Enter address:");
				People o=new People(name,ob.next());
				peoples.add(o);
			}
			
		}
	
	}
	
	public static void generatebill()
	{
		System.out.println("Hello "+na+"  Your Bill");
		for(People o:peoples)
		{
			if(o.name.equals(na))
			{
				o.display();
			}
		}
	}

}
