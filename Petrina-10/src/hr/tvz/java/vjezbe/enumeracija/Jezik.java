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
	
	HRVATSKI(1), ENGLESKI(2), NJEMACKI(3), FRANCUSKI(4), TALIJANSKI(5), RUSKI(6), KINESKI(7);
	
	private Integer value;
	
	private Jezik(Integer value)
	{
		this.value = value;
	}
	
	public static List<String> vrijednosti()
	{
		List<String> listaJezika = new ArrayList<String>();
		
		for (Jezik j : Jezik.values())
		{
			listaJezika.add(j.name());
		}
		
		return listaJezika;
	}

	public Integer getKod() {
		return value;
	}
}
