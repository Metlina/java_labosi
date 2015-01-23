package hr.tvz.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface ZaProdaju {
	
	public default BigDecimal izracunajCijenu(BigDecimal brojStranica, String vrstaPublikacije, BigDecimal cijenaPoStranici)
	{
		BigDecimal pom1;
		BigDecimal pom2 = new BigDecimal(3.14);
		if (vrstaPublikacije.equals("VRSTA_PUBLIKACIJE_ELEKTRONICKA"))
		{
			pom1 = (brojStranica.multiply(cijenaPoStranici));
			return pom1.divide(pom2, 2, RoundingMode.HALF_UP);
		}
		else if (vrstaPublikacije.equals("VRSTA_PUBLIKACIJE_PAPIRNATA"))
		{
			return (brojStranica.multiply(cijenaPoStranici));
		}
		else
			return null;
	}
	
}
