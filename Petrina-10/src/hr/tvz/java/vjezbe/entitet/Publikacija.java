package hr.tvz.java.vjezbe.entitet;

import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;

import java.math.BigDecimal;

/**
 * Predstavlja entitet Publikaciju koji implementira sucelje ZaProdaju i definiran je jos godinom izdavanja publikacije, brojem stranica publikacije,
 * vrstom publikacije, cijenom publikacije i nazivom publikacije
 * 
 * @author Tino
 *
 */
public abstract class Publikacija implements ZaProdaju {

	protected Integer id;
	protected Integer godinaIzdanjaPublikacije;
	protected Integer brojStranicaPublikacije;
	//protected String vrstaPublikacije;
	protected VrstaPublikacije vrstaPublikacije;
	protected String naziv;
	
	protected BigDecimal cijenaPublikacije;
	
	public static final String VRSTA_PUBLIKACIJE_ELEKTRONICKA = "VRSTA_PUBLIKACIJE_ELEKTRONICKA";
	public static final String VRSTA_PUBLIKACIJE_PAPIRNATA = "VRSTA_PUBLIKACIJE_PAPIRNATA";
	
	/**
	 * Inicijalizira podatke entiteta Publikacije
	 * 
	 * @param godinaIzdanjaPublikacije podatak o godini izdanja publikacije
	 * @param brojStranicaPublikacije podatak o broju stranica publikacije
	 * @param vrstaPublikacije podatak o vrsti publikacije
	 * @param cijenaPoStranici podatak o cijeni publikacije
	 * @param naziv podatak o nazivu publikacije
	 */
	public Publikacija(Integer id, Integer godinaIzdanjaPublikacije, Integer brojStranicaPublikacije, VrstaPublikacije vrstaPublikacije, Double cijenaPoStranici, String naziv)
	{
		this.id = id;
		this.godinaIzdanjaPublikacije = godinaIzdanjaPublikacije;
		this.brojStranicaPublikacije = brojStranicaPublikacije;
		this.vrstaPublikacije = vrstaPublikacije;
		this.cijenaPublikacije = izracunajCijenu(new BigDecimal(brojStranicaPublikacije), vrstaPublikacije, new BigDecimal(cijenaPoStranici));
		this.naziv = naziv;
	}
	
	public Publikacija(Integer godinaIzdanjaPublikacije, Integer brojStranicaPublikacije, VrstaPublikacije vrstaPublikacije, Double cijenaPoStranici, String naziv)
	{
		this.godinaIzdanjaPublikacije = godinaIzdanjaPublikacije;
		this.brojStranicaPublikacije = brojStranicaPublikacije;
		this.vrstaPublikacije = vrstaPublikacije;
		this.cijenaPublikacije = izracunajCijenu(new BigDecimal(brojStranicaPublikacije), vrstaPublikacije, new BigDecimal(cijenaPoStranici));
		this.naziv = naziv;
	}
	
	public Integer getId()
	{
		return this.id;
	}
	
	/**
	 * Dohvaca godinu izdanja publikaije
	 * 
	 * @return godina izdanja
	 */
	public Integer getGodinaIzdanjaPublikacije()
	{
		return this.godinaIzdanjaPublikacije;
	}
	
	public void setGodinaIzdanjaPublikacije(Integer godina)
	{
		this.godinaIzdanjaPublikacije = godina;
	}
	
	/**
	 * Dohvaca broj stranica publikacije
	 * 
	 * @return broj stranica
	 */
	public Integer getBrojStranicaPublikacije()
	{
		return this.brojStranicaPublikacije;
	}
	
	/**
	 * Dohvaca vrstu publikacije
	 * 
	 * @return vrsta
	 */
	public VrstaPublikacije getVrstaPublikacije()
	{
		return this.vrstaPublikacije;
	}
	
	/**
	 * Dohvaca naziv publikacije
	 * 
	 * @return naziv
	 */
	public String getNaziv()
	{
		return this.naziv;
	}
	
	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}
	
	/**
	 * Dohvaca cijenu publikacije
	 * 
	 * @return cijena
	 */
	public BigDecimal getCijenaPublikacije()
	{
		return cijenaPublikacije;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((brojStranicaPublikacije == null) ? 0
						: brojStranicaPublikacije.hashCode());
		result = prime
				* result
				+ ((cijenaPublikacije == null) ? 0 : cijenaPublikacije
						.hashCode());
		result = prime
				* result
				+ ((godinaIzdanjaPublikacije == null) ? 0
						: godinaIzdanjaPublikacije.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime
				* result
				+ ((vrstaPublikacije == null) ? 0 : vrstaPublikacije.hashCode());
		return result;
	}

	/** 
	 * Usporeduje entitet Publikacija po svim kriterijima
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Publikacija other = (Publikacija) obj;
		if (brojStranicaPublikacije == null) {
			if (other.brojStranicaPublikacije != null) {
				return false;
			}
		} else if (!brojStranicaPublikacije
				.equals(other.brojStranicaPublikacije)) {
			return false;
		}
		if (cijenaPublikacije == null) {
			if (other.cijenaPublikacije != null) {
				return false;
			}
		} else if (!cijenaPublikacije.equals(other.cijenaPublikacije)) {
			return false;
		}
		if (godinaIzdanjaPublikacije == null) {
			if (other.godinaIzdanjaPublikacije != null) {
				return false;
			}
		} else if (!godinaIzdanjaPublikacije
				.equals(other.godinaIzdanjaPublikacije)) {
			return false;
		}
		if (naziv == null) {
			if (other.naziv != null) {
				return false;
			}
		} else if (!naziv.equals(other.naziv)) {
			return false;
		}
		if (vrstaPublikacije == null) {
			if (other.vrstaPublikacije != null) {
				return false;
			}
		} else if (!vrstaPublikacije.equals(other.vrstaPublikacije)) {
			return false;
		}
		return true;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}	
	
}
