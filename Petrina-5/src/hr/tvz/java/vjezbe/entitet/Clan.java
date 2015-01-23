package hr.tvz.java.vjezbe.entitet;

/**
 * Predstavlja entitet Clan koji je definiran je imenom, prezimenom i OIB-om
 * 
 * @author Tino
 *
 */
public class Clan {
	
	private String ime;
	private String prezime;
	private String OIB;
	
	/**
	 * Inicijalizira podatke entiteta Clan
	 * 
	 * @param ime podatak o imenu
	 * @param prezime podatak o prezimenu
	 * @param OIB podatak o OIB-u
	 */
	public Clan(String ime, String prezime, String OIB)
	{
		this.ime = ime;
		this.prezime = prezime;
		this.OIB = OIB;
	}
	
	/**
	 * Dohvaca ime clana
	 * 
	 * @return ime
	 */
	public String getIme()
	{
		return this.ime;
	}
	
	/**
	 * Dohvaca prezime clana
	 * 
	 * @return prezime
	 */
	public String getPrezime()
	{
		return this.prezime;
	}
	
	/**
	 * Dohvaca OIB clana
	 * 
	 * @return OIB
	 */
	public String getOIB()
	{
		return this.OIB;
	}
	
}
