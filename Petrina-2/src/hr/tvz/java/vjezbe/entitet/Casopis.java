package hr.tvz.java.vjezbe.entitet;

public class Casopis extends Publikacija {

	private Integer mjesecIzdavanjaCasopisa;
	
	public static final Double CIJENA_PO_PRIMJERKU = 12.50; 
	
	public Casopis(Integer mjesecIzdavanjaCasopisa, Integer godinaIzdanjaPublikacije, Integer brojStranicaPublikacije, String vrstaPublikacije, String nazivCasopisa)
	{
		super(godinaIzdanjaPublikacije, brojStranicaPublikacije, vrstaPublikacije, CIJENA_PO_PRIMJERKU/brojStranicaPublikacije, nazivCasopisa);
		this.mjesecIzdavanjaCasopisa = mjesecIzdavanjaCasopisa;
	}
	
	public Integer getMjesecIzdavanjaCasopisa()
	{
		return this.mjesecIzdavanjaCasopisa;
	}
}
