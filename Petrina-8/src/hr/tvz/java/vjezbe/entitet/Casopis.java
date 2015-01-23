package hr.tvz.java.vjezbe.entitet;

import java.io.Serializable;

import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

/**
 * Predstavlja entitet Casopisa koji nasljeduje entitet Publikacija i definiran je jos i mjesecom izdanja casopisa
 * 
 * @author Tino
 *
 */
@SuppressWarnings("serial")
public class Casopis extends Publikacija implements Serializable{

	private Integer mjesecIzdavanjaCasopisa;
	
	public static final Double CIJENA_PO_PRIMJERKU = 12.50; 
	
	/**
	 * Inicijalizira podatke entiteta casopisa
	 * 
	 * @param mjesecIzdavanjaCasopisa podatak o mjesecu casopisa
	 * @param godinaIzdanjaPublikacije podatak o godini izdanja
	 * @param brojStranicaPublikacije podatak o broju stranica
	 * @param vrstaPublikacije podatak o vrsti publikacije
	 * @param nazivCasopisa podatak o nazivu casopisa
	 * @throws NeisplativoObjavljivanjeException baca gresku ako je premala cijena
	 */
	public Casopis(Integer mjesecIzdavanjaCasopisa, Integer godinaIzdanjaPublikacije, Integer brojStranicaPublikacije,  VrstaPublikacije vrstaPublikacije, String nazivCasopisa) throws NeisplativoObjavljivanjeException
	{
		super(godinaIzdanjaPublikacije, brojStranicaPublikacije, vrstaPublikacije, CIJENA_PO_PRIMJERKU/brojStranicaPublikacije, nazivCasopisa);
		this.mjesecIzdavanjaCasopisa = mjesecIzdavanjaCasopisa;
		
		if ((brojStranicaPublikacije/CIJENA_PO_PRIMJERKU) <= 1.00)
		{
			throw new NeisplativoObjavljivanjeException("Pogreska! Unijeli ste neisplativi casopis za objavljivanje, molimo ponovite upis!");
		}
		
	}
	
	/**
	 * Dohvaca mjesec izdavanja casopisa
	 * 
	 * @return mjesec izdavanja
	 */
	public Integer getMjesecIzdavanjaCasopisa()
	{
		return this.mjesecIzdavanjaCasopisa;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((mjesecIzdavanjaCasopisa == null) ? 0
						: mjesecIzdavanjaCasopisa.hashCode());
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
		Casopis other = (Casopis) obj;
		if (mjesecIzdavanjaCasopisa == null) {
			if (other.mjesecIzdavanjaCasopisa != null) {
				return false;
			}
		} else if (!mjesecIzdavanjaCasopisa
				.equals(other.mjesecIzdavanjaCasopisa)) {
			return false;
		}
		return true;
	}

	
	
}
