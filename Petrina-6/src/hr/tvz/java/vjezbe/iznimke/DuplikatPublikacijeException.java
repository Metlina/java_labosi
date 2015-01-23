package hr.tvz.java.vjezbe.iznimke;

/**
 * Prestavlja greske duplikata publikacije
 * 
 * @author Tino
 *
 */
@SuppressWarnings("serial")
public class DuplikatPublikacijeException extends Exception {
	
	public DuplikatPublikacijeException(String poruka)
	{
		super(poruka);
	}
	
	public DuplikatPublikacijeException(Throwable uzrok)
	{
		super(uzrok);
	}
	
	public DuplikatPublikacijeException(String poruka, Throwable uzrok)
	{
		super(poruka, uzrok);
	}
	
}
