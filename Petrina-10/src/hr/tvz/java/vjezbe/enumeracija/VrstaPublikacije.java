package hr.tvz.java.vjezbe.enumeracija;

import java.util.ArrayList;
import java.util.List;

/**
 * Prestavlja enumeraciju vrste publikacije
 * 
 * @author Tino
 *
 */
public enum VrstaPublikacije {

	ELEKTRONICKA(1), PAPIRNATA(2);

	private Integer value;
	
	private VrstaPublikacije(Integer value)
	{
		this.value = value;
	}
	
	public static List<String> vrijednosti()
	{
		List<String> listaPublikacija = new ArrayList<String>();
		
		for (VrstaPublikacije j : VrstaPublikacije.values())
		{
			listaPublikacija.add(j.name());
		}
		
		return listaPublikacija;
	}

	public Integer getKod() {
		return value;
	}	
}