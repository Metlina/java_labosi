package hr.tvz.java.vjezbe.entitet;

public class Knjiga {

	private String nazivKnjige;
	private String jezikKnjige;
	private Izdavac izdavac;
	
	public Knjiga(String nazivKnjige, String jezikKnjige, Izdavac izdavac)
	{
		this.nazivKnjige = nazivKnjige;
		this.jezikKnjige = jezikKnjige;
		this.izdavac = izdavac;
	}
	
	public String getNazivKnjige()
	{
		return this.nazivKnjige;
	}
	
	public String getJezikKnjige()
	{
		return this.jezikKnjige;
	}
	
	public Izdavac getIzdavac()
	{
		return this.izdavac;
	}
}
