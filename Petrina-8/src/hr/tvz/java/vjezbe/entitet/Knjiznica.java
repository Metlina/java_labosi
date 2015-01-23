package hr.tvz.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Prestavlja entitet knjiznice koja je definirana publikacijama
 * 
 * @author Tino
 *
 * @param <T>
 */
public class Knjiznica <T extends Publikacija>{

	private List<T> listaPublikacija;
	
	/**
	 * Inicijalizira listu klase
	 */
	public Knjiznica()
	{
		listaPublikacija = new ArrayList<T>();
	}
	
	/**
	 * Dodaje publikaciju u listu
	 * 
	 * @param publikacija tip publikacije
	 */
	public void dodajPublikaciju(T publikacija)
	{
		listaPublikacija.add(publikacija);
	}
	
	/**
	 * Dohvaca sve publikacije u listi
	 * @return lista publikacija
	 */
	public List<T> dohvatiSvePublikacije()
	{
		return listaPublikacija;
	}
	
	/**
	 * Dohvaca publikacije koje se mogu posuditi
	 * 
	 * @return lista publikacija koje se mogu posuditi
	 */
	public List<T> publikacijeKojeSeMoguPosuditi()
	{
		List<T> listaPosudbe = new ArrayList<>();
		
		for(@SuppressWarnings("unused") T t : listaPublikacija)
		{
			listaPosudbe = this.listaPublikacija.stream().filter(p -> (p instanceof Knjiga && ((Knjiga)p).provjeriRaspolozivost() == true)).collect(Collectors.toList());
		}	
		
		return listaPosudbe;
	}
	
}
