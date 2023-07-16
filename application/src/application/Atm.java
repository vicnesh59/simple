package application;

import java.io.*;

public class Atm implements Serializable {
	
	private final static int twsand=2000, fivehundred=500, hundred=100;
	private  int twdeno=0,fivehundeno=0,hundeno=0,total=0;
	
	
	Atm()
	{
		
	}
	
	Atm(int twdeno, int fivehundeno, int hundeno)
	{
		this.twdeno += twdeno;
		this.fivehundeno += fivehundeno;
		this.hundeno += hundeno;
		this.total=(twdeno*twsand)+(fivehundeno*fivehundred)+(hundeno*hundred);
	}
	
	public  void updateFile(int twdeno, int fivehundeno, int hundeno)
	{
		this.setTwdeno(twdeno);	
		this.setFivehundeno(fivehundeno);
		this.setHundeno(hundeno);
		this.setTotal();
	}

	public  int getTwdeno() {
		return twdeno;
	}

	public  void setTwdeno(int twdeno) {
		this.twdeno+= twdeno;
	}

	public  int getFivehundeno() {
		return fivehundeno;
	}

	public  void setFivehundeno(int fivehundeno) {
		this.fivehundeno+= fivehundeno;
	}

	public  int getHundeno() {
		return hundeno;
	}

	public void setHundeno(int hundeno) {
		this.hundeno+= hundeno;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal() {
		this.total =(this.getFivehundeno()*this.fivehundred)+
				(this.getHundeno()*this.hundred)+(this.twdeno*this.twsand);
	}
	
	public void setTwo(int value)
	{
		this.twdeno-=value;
	}
	public void setFive(int value)
	{
		this.fivehundeno-=value;
	}
	public void setOne(int value)
	{
		this.hundeno-=value;
	}
	
	public void display()
	{
		System.out.println("_________________________________");
		System.out.println("Denominations\tNumber\tValue");
		System.out.println("2000\t\t"+this.getTwdeno()+"\t"+2000*this.getTwdeno());
		System.out.println("500\t\t"+this.getFivehundeno()+"\t"+500*this.getFivehundeno());
		System.out.println("100\t\t"+this.getHundeno()+"\t"+100*this.getHundeno());
		System.out.println("Total\t\t\t"+this.getTotal());
		System.out.println("_________________________________");
	}
	

}
