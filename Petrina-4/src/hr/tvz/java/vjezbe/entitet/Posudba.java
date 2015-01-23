package hr.tvz.java.vjezbe.entitet;

import java.time.LocalDateTime;

public class Posudba {
	
	private Clan clan;
	private Publikacija publikacija;
	private LocalDateTime datetime;
	
	public Posudba(Clan clan, Publikacija publikacija, LocalDateTime datetime)
	{
		this.clan = clan;
		this.publikacija = publikacija;
		this.datetime = datetime;
	}
	
	public Clan getClan()
	{
		return this.clan;
	}

	public Publikacija getPublikacija()
	{
		return this.publikacija;
	}
	
	public LocalDateTime LocalDateTIme()
	{
		return this.datetime;
	}
}
