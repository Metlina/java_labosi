package hr.tvz.java.vjezbe.entitet;

public class Izdavac {

	private String nazivIzdavaca;
	private String drzavaIzdavaca;
	
	public Izdavac(String nazivIzdavaca, String drzavaIzdavaca)
	{
		this.nazivIzdavaca = nazivIzdavaca;
		this.drzavaIzdavaca = drzavaIzdavaca;
	}
	
	public String getNazivIzdavaca()
	{
		return this.nazivIzdavaca;
	}
	
	public String getDrzavaIzdavaca()
	{
		return this.drzavaIzdavaca;
	}
	
}
