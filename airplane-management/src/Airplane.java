
public class Airplane {
	
	private String name,from,destination;
	private int id=0;
	private static int fcticket=2,scticket=2,ecticket=1;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getFcticket() {
		return fcticket;
	}

	public static void setFcticket(int fcticket) {
		Airplane.fcticket = fcticket;
	}

	public static int getScticket() {
		return scticket;
	}

	public static void setScticket(int scticket) {
		Airplane.scticket = scticket;
	}

	public static int getEcticket() {
		return ecticket;
	}

	public static void setEcticket(int ecticket) {
		Airplane.ecticket = ecticket;
	}

	Airplane()
	{
		
	}

	 Airplane(String name, String from, String destination) {
		this.name = name;
		this.from = from;
		this.destination = destination;
		this.id++;
	}
	
	

}
