package hr.tvz.java.vjezbe.entitet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * Prestavlja citanje i zapisivanje u datoteke
 * 
 * 
 * @author Tino
 */

public class CitanjeIPisanjeUDatoteku {
	
	public static final String KNJIGA = "knjiga.txt";
	public static final String CASOPIS = "casopis.txt";
	public static final String CLAN = "clan.txt";
	public static final String POSUDBA = "posudba.dat";
	
	private List<String> linijeDatotekeKnjiga;
	private List<String> linijeDatotekeCasopisa;
	private List<String> linijeDatotekeClana;
	

	public CitanjeIPisanjeUDatoteku()
	{
		linijeDatotekeKnjiga = new ArrayList<String>();
		linijeDatotekeCasopisa = new ArrayList<String>();
		linijeDatotekeClana = new ArrayList<String>();
	}
	
	/**
	 * Citanje datoteke "knjiga.txt"
	 */
	public void citanjeKnjige(){
		try (BufferedReader knjiga = new BufferedReader(new FileReader( KNJIGA ))) {
			linijeDatotekeKnjiga = IOUtils. readLines(knjiga);
		} catch (IOException e1) {
			System. exit(0);
		}
	}
	
	/**
	 * Citanje datoteke "casopis.txt"
	 */
	public void citanjeCasopisa(){
		try (BufferedReader casopis = new BufferedReader(new FileReader( CASOPIS ))) {
			linijeDatotekeCasopisa = IOUtils. readLines(casopis);
		} catch (IOException e2) {
			System. exit(0);
		}
	}
	
	/**
	 * Citanje datoteke "clan.txt"
	 */
	public void citanjeClana(){
		try (BufferedReader clan = new BufferedReader(new FileReader( CLAN ))) {
			linijeDatotekeClana = IOUtils. readLines(clan);
		} catch (IOException e3) {
			System. exit(0);
		}
	}
	
	/**
	 * Zapisivanje posudbe u datotekeu "posudba.dat"
	 * 
	 * @param posudba objekt koji zapisujemo
	 */
	public void pisanjePosudbe(Posudba<Publikacija> posudba)
	{
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream( POSUDBA )))
		{
				out.writeObject(posudba);
				out.close();
		}
		catch (IOException e4)
		{
			System.err.println(e4);
			System.exit(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void citanjePosudbe(Posudba<Publikacija> posudba)
	{
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream( POSUDBA )))
		{
			posudba = (Posudba<Publikacija>) in.readObject();
			
			/*System.out.println("Podaci posudbe : ");
			System.out.println("Naziv publikacije :" + posudba.getPublikacija().getNaziv());
			System.out.println("Vrsta : " + posudba.getPublikacija().getVrstaPublikacije());
			System.out.println("Broj stranica : " + posudba.getPublikacija().getBrojStranicaPublikacije());
			System.out.println("Cijena : " + posudba.getPublikacija().getCijenaPublikacije());
			System.out.println("Jezik knjige : ");
			System.out.println("Prezime : " + posudba.getClan().getPrezime());
			System.out.println("Ime : " + posudba.getClan().getIme());
			System.out.println("OIB : " + posudba.getClan().getOIB());*/
			in.close();
		}
		catch (IOException e5)
		{
			System.err.println(e5);
			System.exit(0);
		}
		catch (ClassNotFoundException e6)
		{
			System.err.println(e6);
			System.exit(0);
		}
	}
	
	public List<String> getLinijeDatotekeKnjiga()
	{
		return linijeDatotekeKnjiga;
	}
	
	public List<String> getLinijeDatotetekeCasopisa()
	{
		return linijeDatotekeCasopisa;
	}
	
	public List<String> getLinijeDatotekeClana()
	{
		return linijeDatotekeClana;
	}
}