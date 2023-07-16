package application;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main implements Serializable {
	
	static ArrayList<Customer> clist=new ArrayList<>();
	static Atm object=new Atm();
	static Thread t;
	
	static Scanner ob=new Scanner(System.in);
	public static void info()															// Displays the Choices
	{
		System.out.println("______________________________");
		System.out.println("\n1.Load Cash to ATM\n2.Show Customer details\n3.Show ATM Operations\n4.Exit");
		System.out.println("______________________________");
		System.out.println("Enter Your Choice:");
	}
	
	public static void updateDenominations()									//Update Denominations
	{
		int twode,fivede,hunde;
		System.out.println("Enter the denominations for 2000:");
		twode=ob.nextInt();
		System.out.println("Enter the denominations for 500:");
		fivede=ob.nextInt();
		System.out.println("Enter the denominations for 100:");
		hunde=ob.nextInt();
		object.updateFile(twode,fivede,hunde);
		object.display();
			
	}
	public static void getCustomer()										// Gets Customer details from the text if no details
	{																		// is available then it creates a file and allows
		File file=new File("F:\\programs\\zoho\\customer.txt");				// the user to create new Customer
		try {
			boolean result=file.createNewFile();
			if(result)
				return;
			BufferedReader br=new BufferedReader(new FileReader("F:\\programs\\zoho\\customer.txt"));    // Address To save the file
			String s="";
			int pin,acno,bal;
			String name="";
			while((s=br.readLine())!=null)
			{
				String data[]=new String[4];
				data=s.split("-");
				name=data[1];
				pin=Integer.parseInt(data[2]);
				bal=Integer.parseInt(data[3]);
				Customer obj=new Customer(bal,pin,name);
				clist.add(obj);			
			}
		}
		catch(Exception e)
		{
			System.out.println(""+e);
		}
	}
	public static void getTransaction(Customer o)											// Update Transaction number and gets
	{																						// transaction details from the file
			int flg=0,cpflg=0;																// if no file exists it simply creates
			File file=new File("F:\\programs\\zoho\\"+o.getAcno()+"_transaction.txt");		// the file
			try {
				boolean result=file.createNewFile();
				if(result)
				{
					System.out.println("No Transaction exist");
					return;
				}
				LineNumberReader lr=new LineNumberReader(new FileReader(file));
				lr.skip(Long.MAX_VALUE);
				int lines=lr.getLineNumber();
				if(lines>10)
					flg=lines-10;
				BufferedReader br=new BufferedReader(new FileReader(file));
				String s="";
				System.out.println("_________________________________________________________________________\n");
				System.out.printf("%5s %15s %20s %10s %10s","T.Id","Description","Credit/Debit","Amount","Closing Balance\n");
				while((s=br.readLine())!=null)
				{
					cpflg++;
					String data[]=new String[5];
					data=s.split("-");
					if(cpflg>flg) {
						System.out.format("%5s %20s %15s %10s %10s",data[0],data[1],data[2],data[3],data[4]);
						System.out.println();
					}
				}
				System.out.println("___________________________________________________________________________");
			}
			catch(Exception e)
			{
				System.out.println(""+e);
			}
		
	}
	public static void createCustomer() 										  // Adds new Customer  OR  Displays the Customer's 
	{																			 // Details
		int cho;
		System.out.println("1.Add customers\n2.Show customer list");
		cho=ob.nextInt();
		File file=new File("F:\\programs\\zoho\\customer.txt");					// Address To save the file
		FileWriter writer;
		if(cho==1)
		{
			String name;
			int pinno,balance;
			System.out.println("Enter the name:");
			name=ob.next();
			System.out.println("Create the 4-digit pin:");
			pinno=ob.nextInt();
			System.out.println("Enter the balance:");
			balance=ob.nextInt();
			Customer obj=new Customer(balance,pinno,name);
			clist.add(obj);
			System.out.println("Account Added...");
			try {
				writer=new FileWriter(file,true);
				writer.write(""+obj.getAcno()+"-"+obj.getAccName()+"-"+obj.getPinno()+"-"+obj.getBalance()+"\n");
				writer.close();
			}
			catch(Exception e)
			{
				System.out.println(""+e);
			}
		}
		else
		{
			int flg=0;
			System.out.println("________________________________________________");
			System.out.println("Accno\tAccount holder\tPinno\tAccount balance");
			for(Customer o:clist)
			{
				flg=1;
				System.out.println(""+o.getAcno()+"\t\t"+o.getAccName()+"\t"+o.getPinno()+"\t"+o.getBalance());
			}
			if(flg==0)
				System.out.println("No Account added");
			System.out.println("_________________________________________________");			
		}
	}
	public static void updateCustomer()											// Updates the existing Customer details from the file
	{
		File file=new File("F:\\programs\\zoho\\customer.txt");                // Address To retrieve the file
		PrintWriter writer;
		try {
			writer=new PrintWriter(file);
			writer.print("");
			for(Customer o:clist)
			{
				writer.append(""+o.getAcno()+"-"+o.getAccName()+"-"+o.getPinno()+"-"+o.getBalance()+"\n");
			}
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println(""+e);
		}
	}
	public static void atmProcess()														// Displays the ATM Process
	{
		int choice;
		System.out.println("____________________________");
		System.out.println("1.Check balance\n2.Withdraw money\n3.Transfer Money\n4.check ATM Balance\n5.Mini Statement");
		System.out.println("____________________________");
		System.out.println("Enter your Choice:");
		choice=ob.nextInt();
		if(choice==1)
		{
			checkBalance();
		}
		else if(choice==2)
		{
			withdrawMoney();
		}
		else if(choice==3)
		{
			findaccount();
		}
		else if(choice==4)
		{
			object.display();
		}
		else if(choice==5)
		{
			System.out.println("Enter your pin:");
			int pin=ob.nextInt();
			for(Customer o:clist)
			{
				if(o.getPinno()==pin)
				{
					getTransaction(o);
				}
			}
		}
	}
	public static void extractatm()														// Extracts the ATM denominations and 
	{																					// Updates the Object
		File file=new File("F:\\programs\\zoho\\atm.txt");		// Address To retrieve the file
		try {
			boolean result=file.createNewFile();
			if(result)
				return;
			BufferedReader br=new BufferedReader(new FileReader("F:\\programs\\zoho\\atm.txt"));
			String s="";
			int x=0;
			int a[]=new int[3];
			while((s=br.readLine())!=null)
			{
				String data[]=new String[2];
				data=s.split("-");
				a[x]= Integer.parseInt(data[1]);
				x++;
			}
			object=new Atm(a[0],a[1],a[2]);
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println(""+e);
		}
		
	}
	public static void updateatm() 														// Updates the ATM Denominations to the text file
	{
		File file=new File("F:\\programs\\zoho\\atm.txt");    // Address To save the file 
		PrintWriter writer;
		try {
			file.createNewFile();
			writer = new PrintWriter("F:\\programs\\zoho\\atm.txt");
			writer.print("");
			writer.append("2000-"+object.getTwdeno()+"\n");
			writer.append("500-"+object.getFivehundeno()+"\n");
			writer.append("100-"+object.getHundeno()+"\n");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public static void setid()														// Creates the Transaction.txt file OR 
	{																				//Retrieves the Transaction id for every Customer from previous Updated text 
		String data[]=new String[5];
		int flg=0;
		for(Customer o:clist)
		{
			File file=new File("F:\\programs\\zoho\\"+o.getAcno()+"_transaction.txt");      // Address To retrieve the file
			try {
				boolean result=file.createNewFile();
				if(result)
				{
					return;
				}
				BufferedReader br=new BufferedReader(new FileReader(file));
				String s="";
				while((s=br.readLine())!=null)
				{
					flg=1;
					data=s.split("-");
				}
				if(flg==1)
				{
					int value=Integer.parseInt(data[0]);
					o.setTransactionid(value);
				}
			}
			catch(Exception e)
			{
				System.out.println(""+e);
			}
		}
	}
	
	public static void main(String[] args)  {						// MAIN FUNCTION...
		
		int choice;
		extractatm();													// Extract ATM amount information from file
		getCustomer();													// Extract Customer Information from file
		setid();														// Set the Transaction Id from the Transaction file if exist before
		System.out.println("Welcome to Our ATM Service");
		boolean start=true;
		while(start)
		{
			info();														// Display details 
			choice=ob.nextInt();
			switch(choice)
			{
			case 1:
				updateDenominations();									// Update Denomination to the ATM
				break;
			case 2:
				createCustomer();										// Create and View Customer Details
				break;
			case 3:
				atmProcess();											// Process The ATM Functions
				break;
			case 4:
				start=false;
				break;
				
			}
		}
		updateatm();													// Update ATM denominations to the text file
		updateCustomer();												// Update Customer changes to the text file
	}
	public static void checkBalance()
	{
		int pinno,flg=0;														// Check Balance for every Customer
		System.out.println("Enter your Pin no:");
		pinno=ob.nextInt();
		for(Customer o:clist)
		{
			if( o.getPinno()==pinno )
			{
				flg=1;
				System.out.println("Your Balance is: "+o.getBalance());
			}
		}
		if(flg==0)
			System.out.println("No Account found recheck your Pin");
		
	}
	public static void withdrawMoney() 											// Withdraws Money 
	{
		int pinno,flg=0,withamt;
		int two=0,five=0,one=0,cpwithdraw;
		System.out.println("Maximum 10,000  and minimum 100 in a single transaction");
		System.out.println("Enter your Pin no:");
		pinno=ob.nextInt();
		for(Customer o:clist)
		{
			if( o.getPinno()==pinno )
			{
				flg=1;
				System.out.println("Enter the amount to withdraw:");
				withamt=ob.nextInt();
				cpwithdraw=withamt;
				if(withamt<100 || withamt>10000) {
					System.out.println("Please enter valid range");
					return;
				}
				if(o.getBalance()<withamt)
				{
					System.out.println("Insufficient Balance in your account");
					return;
				}
				if(object.getTotal()<withamt)
				{
					System.out.println("Insufficient Balance in the ATM");
					return;
				}
				while(withamt!=0)
				{
					if(withamt>3000)
					{
						two++;
						withamt-=2000;
					}
					else if(withamt>1000)
					{
						five++;
						withamt-=500;
					}
					else {
						one++;
						withamt-=100;
					}
				}
				deductAmount(two,five,one,o,cpwithdraw);
				return;
			}
		}
		
	}
	public static void deductAmount(int two,int five,int one,Customer o,int total) 			    // Detects the Money from the ATM or 
	{																							// from the Customer and
		if(object.getTwdeno()<two || object.getFivehundeno()<five || object.getHundeno()<one )	// Updates the File
		{
			System.out.println("Lesser Denominations try with different amount");
			return;
		}
		object.setTwo(two);
		object.setFive(five);
		object.setOne(one);
		object.setTotal();
		o.deductBalance(total);
		System.out.println("Amount:"+total+"is withdrawn");
		o.setTrnum();
		o.setDesc("Cash Withdraw");
		o.setCredit(false);
		 t=new Thread(new Runnable() {
			public void run()
			{
				updateFile(o,total);
			}
		});
		t.start();
		
	}
	public static void findaccount()										// Finds the Account to Transfer Money
	{
		int flg=0;
		System.out.println("Enter your pin:");
		int pin=ob.nextInt();
		for(Customer o:clist)
		{
			if(o.getPinno()==pin)
			{
				flg=1;
				transferMoney(o);
				return;
			}
		}
		if(flg==0)
			System.out.println("Invalid pin");
	}
	public static void transferMoney(Customer sender)						// Transfers the Money and Updates the Text File
	{
		System.out.println("Enter the Receiver's Account no: ");
		int acno=ob.nextInt();
		System.out.println("Enter the amount to be Transferred:");
		int amt=ob.nextInt();
		if(sender.getBalance()<amt)
		{
			System.out.println("Insufficient balance in your account\n");
			return;
		}
		for(Customer o:clist)
		{
			if(o.getAcno()==acno)
			{
				o.creditBalance(amt);
				sender.deductBalance(amt);
				System.out.println("Amount transferred successfully...\n");
				sender.setTrnum();
				sender.setDesc("Transfer to "+o.getAcno());
				sender.setCredit(false);
				o.setTrnum();
				o.setDesc("Transfer from "+sender.getAcno());
				o.setCredit(true);
				 t=new Thread(new Runnable() {
					public void run()
					{
						updateFile(sender,amt);
						updateFile(o,amt);
					}
				});
				t.start();
				return;
			}
		}
	}
	public static void updateFile(Customer o,int amt)								// Updates the Transaction File
	{
		File file=new File("F:\\programs\\zoho\\"+o.getAcno()+"_transaction.txt");	// Address To save the file
		StringBuffer sb=new StringBuffer();
		String ts="Debit";
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			file.createNewFile();
			BufferedReader br=new BufferedReader(new FileReader(file));
			String s="";
			while((s=br.readLine())!=null)
			{
				sb.append(s);
				sb.append("\n");
			}
			if(o.isCredit())
				ts="Credit";
			sb.append(""+o.getTrnum()+"-"+o.getDesc()+"-"+ts+"-"+amt+"-"+o.getBalance());
			PrintWriter pw=new PrintWriter(new FileOutputStream(file));
			pw.print(sb.toString());
			pw.close();
			System.out.println("File Updated");
		}
		catch(Exception e)
		{
			System.out.println(""+e);
		}
	}

}
