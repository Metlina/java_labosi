package hr.tvz.java.vjezbe.entitet;

import java.io.Serializable;

/**
 * Predstavlja entitet Izdavac koji je definiran nazivom izdavaca i drzavom izdavaca
 * 
 * @author Tino
 *
 */
@SuppressWarnings("serial")
public class Izdavac implements Serializable{
	
	private Integer id;
	private String nazivIzdavaca;
	private String drzavaIzdavaca;
	
	/**
	 * Inicijalizira podatke entiteta Izdavaca
	 * 
	 * @param nazivIzdavaca podatak o nazivu izdavaca
	 * @param drzavaIzdavaca podatak o drzavi izdavaca
	 */
	public Izdavac(Integer id, String nazivIzdavaca, String drzavaIzdavaca)
	{
		this.id = id;
		this.nazivIzdavaca = nazivIzdavaca;
		this.drzavaIzdavaca = drzavaIzdavaca;
	}
	
	public Izdavac(String nazivIzdavaca, String drzavaIzdavaca)
	{
		this.nazivIzdavaca = nazivIzdavaca;
		this.drzavaIzdavaca = drzavaIzdavaca;
	}
	
	public Integer getId()
	{
		return this.id;
	}
	
	/**
	 * Dohvaca naziv izdavaca
	 * 
	 * @return naziv izdavaca
	 */
	public String getNazivIzdavaca()
	{
		return this.nazivIzdavaca;
	}
	
	/**
	 * Dohvaca drzavu izdavaca
	 * 
	 * @return drzava izdavaca
	 */
	public String getDrzavaIzdavaca()
	{
		return this.drzavaIzdavaca;
	}
	
	@Override
	public String toString()
	{
		return this.nazivIzdavaca;
	}

	public void setId(int int1) {
		this.id = int1;
	}
	
}
