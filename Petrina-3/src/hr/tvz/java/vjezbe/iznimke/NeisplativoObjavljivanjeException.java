package hr.tvz.java.vjezbe.iznimke;

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
