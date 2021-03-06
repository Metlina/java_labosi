package hr.tvz.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Prestavlja entitet posudbe koji je definiran clanom, publikacijom i datumom
 * 
 * @author Tino
 */
@SuppressWarnings("serial")
public class Posudba<T> implements Serializable {
	
	private Integer id;
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
	
	public Posudba(Integer id, Clan clan, T publikacija, LocalDateTime datetime)
	{
		this.id = id;
		this.clan = clan;
		this.publikacija = publikacija;
		this.datetime = datetime;
	}
	
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
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
	public LocalDateTime getLocalDateTIme()
	{
		return this.datetime;
	}
}
