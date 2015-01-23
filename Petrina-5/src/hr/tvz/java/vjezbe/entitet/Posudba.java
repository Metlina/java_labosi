package hr.tvz.java.vjezbe.entitet;

import java.time.LocalDateTime;

/**
 * Prestavlja entitet posudbe koji je definiran clanom, publikacijom i datumom
 * 
 * @author Tino
 */
public class Posudba<T> {
	
	private Clan clan;
	private T publikacija;
	private LocalDateTime datetime;
	
	/**
	 * Inicijalizira podatak o clanu, publikaciji i datumu
	 * 
	 * @param clan podatak o clanu
	 * @param publikacija podatak o publikaciji
	 * @param datetime podatak o datumu
	 */
	public Posudba(Clan clan, T publikacija, LocalDateTime datetime)
	{
		this.clan = clan;
		this.publikacija = publikacija;
		this.datetime = datetime;
	}
	
	/**
	 * Dohvaca clan
	 * 
	 * @return clan
	 */
	public Clan getClan()
	{
		return this.clan;
	}
	
	/**
	 * Dohvaca publikaciju
	 * 
	 * @return publikacija
	 */
	public T getPublikacija()
	{
		return this.publikacija;
	}
	
	/**
	 * Dohvaca datum
	 * 
	 * @return datum
	 */
	public LocalDateTime LocalDateTIme()
	{
		return this.datetime;
	}
}
