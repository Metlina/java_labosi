package hr.tvz.java.vjezbe.entitet;

public class Clan {
	
	private String ime;
	private String prezime;
	private String OIB;
	
	public Clan(String ime, String prezime, String OIB)
	{
		this.ime = ime;
		this.prezime = prezime;
		this.OIB = OIB;
	}
	
	public String getIme()
	{
		return this.ime;
	}
	
	public String getPrezime()
	{
		return this.prezime;
	}
	
	public String getOIB()
	{
		return this.OIB;
	}
	
}
