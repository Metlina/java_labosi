package hr.tvz.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

public class Knjiga extends Publikacija implements ZaPosudbu{

	private String jezikKnjige;
	private Izdavac izdavac;
	
	private Boolean raspolozivost;
	
	public static final Double CIJENA_PO_STRANICI_HR = 0.50;
	public static final Double CIJENA_PO_STRANICI_STRANO = 2.5;
	
	public Knjiga(String nazivKnjige, String jezikKnjige, Izdavac izdavac, Integer godinaIzdavanjaKnjige, String vrstaPublikacije, double cijenaPoStraniciKnjige, Integer brojStranicaKnjige) throws NeisplativoObjavljivanjeException
	{
		super(godinaIzdavanjaKnjige, brojStranicaKnjige, vrstaPublikacije, cijenaPoStraniciKnjige, nazivKnjige);
		this.jezikKnjige = jezikKnjige;
		this.izdavac = izdavac;
		this.raspolozivost = true;
		
		if (super.getCijenaPublikacije().intValue() <= 100)
		{
			throw new NeisplativoObjavljivanjeException("Pogreska! Unijeli ste neisplativu knjigu za objavljivanje, molimo ponovite upis!");
		}
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((izdavac == null) ? 0 : izdavac.hashCode());
		result = prime * result
				+ ((jezikKnjige == null) ? 0 : jezikKnjige.hashCode());
		result = prime * result
				+ ((raspolozivost == null) ? 0 : raspolozivost.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Knjiga other = (Knjiga) obj;
		if (izdavac == null) {
			if (other.izdavac != null) {
				return false;
			}
		} else if (!izdavac.equals(other.izdavac)) {
			return false;
		}
		if (jezikKnjige == null) {
			if (other.jezikKnjige != null) {
				return false;
			}
		} else if (!jezikKnjige.equals(other.jezikKnjige)) {
			return false;
		}
		if (raspolozivost == null) {
			if (other.raspolozivost != null) {
				return false;
			}
		} else if (!raspolozivost.equals(other.raspolozivost)) {
			return false;
		}
		return true;
	}

	
	
}
