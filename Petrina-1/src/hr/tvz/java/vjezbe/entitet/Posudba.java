package hr.tvz.java.vjezbe.entitet;

import java.time.LocalDateTime;

public class Posudba {
	
	private Clan clan;
	private Knjiga knjiga;
	private LocalDateTime datetime;
	
	public Posudba(Clan clan, Knjiga knjiga, LocalDateTime datetime)
	{
		this.clan = clan;
		this.knjiga = knjiga;
		this.datetime = datetime;
	}
	
	public Clan getClan()
	{
		return this.clan;
	}

	public Knjiga getKnjiga()
	{
		return this.knjiga;
	}
	
	public LocalDateTime LocalDateTIme()
	{
		return this.datetime;
	}
}
