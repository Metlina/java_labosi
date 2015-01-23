package hr.tvz.java.vjezbe.entitet;

import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

public class Casopis extends Publikacija {

	private Integer mjesecIzdavanjaCasopisa;
	
	public static final Double CIJENA_PO_PRIMJERKU = 12.50; 
	
	public Casopis(Integer mjesecIzdavanjaCasopisa, Integer godinaIzdanjaPublikacije, Integer brojStranicaPublikacije, String vrstaPublikacije, String nazivCasopisa) throws NeisplativoObjavljivanjeException
	{
		super(godinaIzdanjaPublikacije, brojStranicaPublikacije, vrstaPublikacije, CIJENA_PO_PRIMJERKU/brojStranicaPublikacije, nazivCasopisa);
		this.mjesecIzdavanjaCasopisa = mjesecIzdavanjaCasopisa;
		
		if ((brojStranicaPublikacije/CIJENA_PO_PRIMJERKU) <= 1.00)
		{
			throw new NeisplativoObjavljivanjeException("Pogreska! Unijeli ste neisplativi casopis za objavljivanje, molimo ponovite upis!");
		}
		
	}
	
	public Integer getMjesecIzdavanjaCasopisa()
	{
		return this.mjesecIzdavanjaCasopisa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((mjesecIzdavanjaCasopisa == null) ? 0
						: mjesecIzdavanjaCasopisa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Casopis other = (Casopis) obj;
		if (mjesecIzdavanjaCasopisa == null) {
			if (other.mjesecIzdavanjaCasopisa != null) {
				return false;
			}
		} else if (!mjesecIzdavanjaCasopisa
				.equals(other.mjesecIzdavanjaCasopisa)) {
			return false;
		}
		return true;
	}

	
	
}
