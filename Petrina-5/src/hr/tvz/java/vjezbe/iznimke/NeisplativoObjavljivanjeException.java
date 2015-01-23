package hr.tvz.java.vjezbe.iznimke;

/**
 * Prestavlja greske neisplativosti objavljivanja publikacije
 * 
 * @author Tino
 *
 */
public class NeisplativoObjavljivanjeException extends Exception{

	public NeisplativoObjavljivanjeException(String poruka)
	{
		super(poruka);
	}
	
	public NeisplativoObjavljivanjeException(Throwable uzrok)
	{
		super(uzrok);
	}
	
	public NeisplativoObjavljivanjeException(String poruka, Throwable uzrok)
	{
		super(poruka, uzrok);
	}
	
}
