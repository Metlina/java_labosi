package hr.tvz.java.vjezbe.entitet;

import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Prestavlja sucelje ZaProdaju
 * @author Tino
 *
 */
public interface ZaProdaju {
	
	/**
	 * Racuna cijenu publikacije u ovisnosti u vrsti publikacije
	 * @param brojStranica podatak o broju stranica
	 * @param vrstaPublikacije podatak o vrsti publikacije
	 * @param cijenaPoStranici podatak o cijeni po stranici
	 * @return
	 */
	public default BigDecimal izracunajCijenu(BigDecimal brojStranica,  VrstaPublikacije vrstaPublikacije, BigDecimal cijenaPoStranici)
	{
		BigDecimal pom1;
		BigDecimal pom2 = new BigDecimal(3.14);
		if (vrstaPublikacije == VrstaPublikacije.ELEKTRONICKA)
		{
			pom1 = (brojStranica.multiply(cijenaPoStranici));
			return pom1.divide(pom2, 2, RoundingMode.HALF_UP);
		}
		else if (vrstaPublikacije == VrstaPublikacije.PAPIRNATA)
		{
			return (brojStranica.multiply(cijenaPoStranici));
		}
		else
			return null;
	}
	
}
