package hr.tvz.java.vjezbe.enumeracija;

import java.util.ArrayList;
import java.util.List;

/**
 * Prestavlja enumeraciju jezika
 * 
 * @author Tino
 *
 */
public enum Jezik {
	
	HRVATSKI, ENGLESKI, NJEMACKI, FRANCUSKI, TALIJANSKI, RUSKI, KINESKI;
	
	public static List<String> vrijednosti()
	{
		List<String> listaJezika = new ArrayList<String>();
		
		for (Jezik j : Jezik.values())
		{
			listaJezika.add(j.name());
		}
		
		return listaJezika;
	}
}
