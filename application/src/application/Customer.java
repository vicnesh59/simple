package application;

import java.io.*;

public class Customer implements Serializable {
	
	private static int accno=100;
	private int acno,balance,pinno,trnum=1000;
	private String accName,Desc;
	private boolean credit;
	
	
	
	public Customer(int balance, int pinno, String accName) {
		super();
		this.setAcno(++accno);
		this.balance = balance;
		this.pinno = pinno;
		this.accName = accName;
	}
	public String getDesc() {
		return Desc;
	}
	public void setTransactionid(int value)
	{
		this.trnum=value;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public boolean isCredit() {
		return credit;
	}

	public void setCredit(boolean credit) {
		this.credit = credit;
	}
	public int getTrnum() {
		return trnum;
	}

	public void setTrnum() {
		this.trnum = ++this.trnum;
	}

	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	
	public int getPinno() {
		return pinno;
	}
	public void setPinno(int pinno) {
		this.pinno = pinno;
	}
	public int getAcno() {
		return acno;
	}
	public void setAcno(int acno) {
		this.acno = acno;
	}
	public void deductBalance(int value)
	{
		this.balance-=value;
	}
	public void creditBalance(int value)
	{
		this.balance+=value;
	}
	
	

}
