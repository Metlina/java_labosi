package hr.tvz.java.vjezbe.entitet;

import java.io.Serializable;

import hr.tvz.java.vjezbe.enumeracija.Jezik;
import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

/**
 * Predstavlja entitet Knjiga koji nasljeduje entitet Publikacija i implementira ZaPosudbu i definiran je jos izdavacem, jezikom i raspolozivoscu
 * 
 * @author Tino
 *
 */
@SuppressWarnings("serial")
public class Knjiga extends Publikacija implements ZaPosudbu, Serializable{

	//private String jezikKnjige;
	private Izdavac izdavac;
	private Jezik jezik;
	
	private Boolean raspolozivost;
	
	public static final Double CIJENA_PO_STRANICI_HR = 0.50;
	public static final Double CIJENA_PO_STRANICI_STRANO = 2.5;
	
	/**
	 * Inicijalizira podatke entiteta Knjiga
	 * 
	 * @param nazivKnjige podatak o nazivu knjige
	 * @param jezik podatak o jeziku knjige
	 * @param izdavac podatak o izdavacu
	 * @param godinaIzdavanjaKnjige podatak o godini izdavanja knjige
	 * @param vrstaPublikacije podatak o vrsti publikacije
	 * @param cijenaPoStraniciKnjige podatak o cijeni po stranici
	 * @param brojStranicaKnjige podatak o broju stranica
	 * @throws NeisplativoObjavljivanjeException baca gresku ako je premala cijena
	 */
	public Knjiga(String nazivKnjige, Jezik jezik, Izdavac izdavac, Integer godinaIzdavanjaKnjige,  VrstaPublikacije vrstaPublikacije, double cijenaPoStraniciKnjige, Integer brojStranicaKnjige) throws NeisplativoObjavljivanjeException
	{
		super(godinaIzdavanjaKnjige, brojStranicaKnjige, vrstaPublikacije, cijenaPoStraniciKnjige, nazivKnjige);
		this.jezik = jezik;
		this.izdavac = izdavac;
		this.raspolozivost = true;
		
		if (super.getCijenaPublikacije().intValue() <= 100)
		{
			throw new NeisplativoObjavljivanjeException("Pogreska! Unijeli ste neisplativu knjigu za objavljivanje, molimo ponovite upis!");
		}
	}
	/**
	 * Dohvaca jezik knjige
	 * 
	 * @return jezik
	 */
	public Jezik getJezikKnjige()
	{
		return this.jezik;
	}
	
	/**
	 * Dohvaca izdavaca knjige
	 * 
	 * @return izdavac
	 */
	public Izdavac getIzdavac()
	{
		return this.izdavac;
	}
	
	/**
	 * Postavlja parametar posudbe
	 */
	@Override
	public void posudba()
	{
		this.raspolozivost = false;
	}
	
	/**
	 * Postavlja parametar vracanja
	 */
	@Override
	public void vracanje()
	{
		this.raspolozivost = true;
	}
	
	/**
	 * Provjerava raspolozivost knjige
	 */
	public Boolean provjeriRaspolozivost()
	{
		return this.raspolozivost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((izdavac == null) ? 0 : izdavac.hashCode());
		result = prime * result + ((jezik == null) ? 0 : jezik.hashCode());
		result = prime * result
				+ ((raspolozivost == null) ? 0 : raspolozivost.hashCode());
		return result;
	}
	
	/**
	 * Usporeduje entitet po svim kriterijima
	 */
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
		if (jezik != other.jezik) {
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
