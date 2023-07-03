package ticket;

public class Train {
	
	private static int no=0;
	private int Slc=1,Fc=0,Sec=0,Rac=1,Wl=1;
	private String from,to;
	
	Train()
	{
		
	}
	
	
	public Train(String from, String to) {
		super();
		this.no=this.no+1;
		this.from = from;
		this.to = to;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public int getSlc() {
		return Slc;
	}


	public void setSlc(int slc) {
		Slc = slc;
	}


	public int getFc() {
		return Fc;
	}


	public void setFc(int fc) {
		Fc = fc;
	}


	public int getSec() {
		return Sec;
	}


	public void setSec(int sec) {
		Sec = sec;
	}


	public int getRac() {
		return Rac;
	}


	public void setRac(int rac) {
		Rac = rac;
	}


	public int getWl() {
		return Wl;
	}


	public void setWl(int wl) {
		Wl = wl;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}
	
	public void printDetails()
	{
		if(this.Wl>0)
		{
		System.out.println("Train no "+this.no+"\n1.First Class tickets - "+this.Fc
				+ "\n2.Second Class tickets - "+this.Sec+"\n3.Sleeper tickets - "+this.Slc
				+"\nRAC tickets - "+this.Rac+"\nWaiting List - "+this.Wl);
		}
		else
		{
			System.out.println("No Tickets Available\nPress 0 to exit");
		}
	}
	
	

}
