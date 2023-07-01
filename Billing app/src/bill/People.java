package bill;

import java.util.*;

public class People {
	
	String name;
	String address;
	boolean goldpremium=false;
	
	
	public boolean isGoldpremium() {
		return goldpremium;
	}

	public void setGoldpremium(boolean goldpremium) {
		this.goldpremium = goldpremium;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public ArrayList<Product> getBought() {
		return bought;
	}

	ArrayList<Product> bought=new ArrayList<>();
	
	People()
	{
		
	}
	
	People(String name,String address)
	{
		this.name=name;
		this.address=address;
	}
	
	public void add(String name,int quan,float price)
	{
		Product o=new Product(quan,price,name);
		bought.add(o);
	}
	
	public void display()
	{
		int p=0;
		System.out.println("Name    Quantity    Price");
		for(Product b:bought)
		{
			p+=b.price;
			System.out.println(""+b.name+"--------"+b.quantity+"----"+b.price);
		}
		System.out.print("Total:----------------------"+p);
	}

}
