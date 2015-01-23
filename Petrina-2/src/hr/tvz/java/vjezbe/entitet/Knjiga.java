package hr.tvz.java.vjezbe.entitet;

public class Knjiga extends Publikacija implements ZaPosudbu{

	private String jezikKnjige;
	private Izdavac izdavac;
	
	private Boolean raspolozivost;
	
	public static final Double CIJENA_PO_STRANICI_HR = 0.50;
	public static final Double CIJENA_PO_STRANICI_STRANO = 2.5;
	
	public Knjiga(String nazivKnjige, String jezikKnjige, Izdavac izdavac, Integer godinaIzdavanjaKnjige, String vrstaPublikacije, double cijenaPoStraniciKnjige, Integer brojStranicaKnjige)
	{
		super(godinaIzdavanjaKnjige, brojStranicaKnjige, vrstaPublikacije, cijenaPoStraniciKnjige, nazivKnjige);
		this.jezikKnjige = jezikKnjige;
		this.izdavac = izdavac;
		this.raspolozivost = true;
	}
	
	public String getJezikKnjige()
	{
		return this.jezikKnjige;
	}
	
	public Izdavac getIzdavac()
	{
		return this.izdavac;
	}
	
	@Override
	public void posudba()
	{
		this.raspolozivost = false;
	}
	
	@Override
	public void vracanje()
	{
		this.raspolozivost = true;
	}
	
	public Boolean provjeriRaspolozivost()
	{
		return this.raspolozivost;
	}
}
