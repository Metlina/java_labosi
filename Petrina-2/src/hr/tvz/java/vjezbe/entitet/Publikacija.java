package hr.tvz.java.vjezbe.entitet;

import java.math.BigDecimal;

public abstract class Publikacija implements ZaProdaju {

	protected Integer godinaIzdanjaPublikacije;
	protected Integer brojStranicaPublikacije;
	protected String vrstaPublikacije;
	protected String naziv;
	
	protected BigDecimal cijenaPublikacije;
	
	public static final String VRSTA_PUBLIKACIJE_ELEKTRONICKA = "VRSTA_PUBLIKACIJE_ELEKTRONICKA";
	public static final String VRSTA_PUBLIKACIJE_PAPIRNATA = "VRSTA_PUBLIKACIJE_PAPIRNATA";
	
	public Publikacija(Integer godinaIzdanjaPublikacije, Integer brojStranicaPublikacije, String vrstaPublikacije, Double cijenaPoStranici, String naziv)
	{
		this.godinaIzdanjaPublikacije = godinaIzdanjaPublikacije;
		this.brojStranicaPublikacije = brojStranicaPublikacije;
		this.vrstaPublikacije = vrstaPublikacije;
		this.cijenaPublikacije = izracunajCijenu(new BigDecimal(brojStranicaPublikacije), vrstaPublikacije, new BigDecimal(cijenaPoStranici));
		this.naziv = naziv;
	}
	
	public Integer getGodinaIzdanjaPublikacije()
	{
		return this.godinaIzdanjaPublikacije;
	}
	
	public Integer getBrojStranicaPublikacije()
	{
		return this.brojStranicaPublikacije;
	}
	
	public String getVrstaPublikacije()
	{
		return this.vrstaPublikacije;
	}
	
	public String getNaziv()
	{
		return this.naziv;
	}
	
	public BigDecimal getCijenaPublikacije()
	{
		return cijenaPublikacije;
	}
}
