package hr.tvz.java.vjezbe.glavna;

import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.entitet.CitanjeIPisanjeUDatoteku;
import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.entitet.Izdavac;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.entitet.Knjiznica;
import hr.tvz.java.vjezbe.entitet.Posudba;
import hr.tvz.java.vjezbe.entitet.Publikacija;
import hr.tvz.java.vjezbe.enumeracija.Jezik;
import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.tvz.java.vjezbe.iznimke.DuplikatPublikacijeException;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prestavlja klasu u kojoj se izvrsava program
 * 
 * @author Tino
 *
 */
public class Glavna {
	
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);
	
	/**
	 * Unos podataka u memoriju iz "knjiga.txt"
	 * 
	 * @return knjiga
	 * @throws NeisplativoObjavljivanjeException ako je knjiga neisplativa
	 */
	public static Knjiga unosPodatakaIzDatotekeKnjiga(Integer broj) throws NeisplativoObjavljivanjeException
	{
		String nazivKnjige = null, nazivIzdavaca = null, drzavaIzdavaca = null;
		Integer odabir, odabirJezika;
		Integer godinaKnjige = null, brojStranicaKnjige = null;
		Double cijenaPoStranici = null;
		Jezik jezik = Jezik.HRVATSKI;
		VrstaPublikacije vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		
		CitanjeIPisanjeUDatoteku citanjeKnjige = new CitanjeIPisanjeUDatoteku();
		
		List<String> listaPodatakaKnjige = new ArrayList<>();
		
		citanjeKnjige.citanjeKnjige();
		
		listaPodatakaKnjige = citanjeKnjige.getLinijeDatotekeKnjiga();
		
		if (broj ==1) {
			nazivKnjige = listaPodatakaKnjige.get(0);
			odabir = Integer.parseInt(listaPodatakaKnjige.get(1));

			if (odabir == 1) {
				vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
			} else if (odabir == 2) {
				vrstaPublikacije = VrstaPublikacije.PAPIRNATA;
			}

			nazivIzdavaca = listaPodatakaKnjige.get(2);
			drzavaIzdavaca = listaPodatakaKnjige.get(3);
			godinaKnjige = Integer.parseInt(listaPodatakaKnjige.get(4));
			odabirJezika = Integer.parseInt(listaPodatakaKnjige.get(5));

			switch (odabirJezika) {
			case (1):
				jezik = Jezik.HRVATSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_HR;
				break;
			case (2):
				jezik = Jezik.ENGLESKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (3):
				jezik = Jezik.NJEMACKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (4):
				jezik = Jezik.FRANCUSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (5):
				jezik = Jezik.TALIJANSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (6):
				jezik = Jezik.RUSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (7):
				jezik = Jezik.KINESKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			}

			brojStranicaKnjige = Integer.parseInt(listaPodatakaKnjige.get(6));

		}
		
		else if (broj == 2)
		{
			nazivKnjige = listaPodatakaKnjige.get(7);
			odabir = Integer.parseInt(listaPodatakaKnjige.get(8));

			if (odabir == 1) {
				vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
			} else if (odabir == 2) {
				vrstaPublikacije = VrstaPublikacije.PAPIRNATA;
			}

			nazivIzdavaca = listaPodatakaKnjige.get(9);
			drzavaIzdavaca = listaPodatakaKnjige.get(10);
			godinaKnjige = Integer.parseInt(listaPodatakaKnjige.get(11));
			odabirJezika = Integer.parseInt(listaPodatakaKnjige.get(12));

			switch (odabirJezika) {
			case (1):
				jezik = Jezik.HRVATSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_HR;
				break;
			case (2):
				jezik = Jezik.ENGLESKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (3):
				jezik = Jezik.NJEMACKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (4):
				jezik = Jezik.FRANCUSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (5):
				jezik = Jezik.TALIJANSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (6):
				jezik = Jezik.RUSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (7):
				jezik = Jezik.KINESKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			}

			brojStranicaKnjige = Integer.parseInt(listaPodatakaKnjige.get(13));
		}
		Izdavac izdavac = new Izdavac(nazivIzdavaca, drzavaIzdavaca);
		Knjiga knjiga = new Knjiga(nazivKnjige, jezik, izdavac, godinaKnjige, vrstaPublikacije, cijenaPoStranici, brojStranicaKnjige);
		
		return knjiga;
	}
	
	/**
	 * Unos poodataka u memoriju iz "casopis.txt"
	 * 
	 * @return casopis
	 * @throws NeisplativoObjavljivanjeException ako je casopis neisplativ
	 */
	public static Casopis unosPodatakaIzDatotekeCasopis(Integer broj) throws NeisplativoObjavljivanjeException
	{
		String nazivCasopisa = null;
		Integer godinaCasopisa = null, brojstranicaCasopisa = null, mjesecCasopisa = null;
		Integer odabir;
		VrstaPublikacije vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		
		CitanjeIPisanjeUDatoteku citanjeCasopisa = new CitanjeIPisanjeUDatoteku();
		
		List<String> listaPodatakaCasopis = new ArrayList<>();
		
		citanjeCasopisa.citanjeCasopisa();
		
		listaPodatakaCasopis = citanjeCasopisa.getLinijeDatotetekeCasopisa();
		
		
		if (broj == 1)
		{
			nazivCasopisa = listaPodatakaCasopis.get(0);
			godinaCasopisa = Integer.parseInt(listaPodatakaCasopis.get(1));
			mjesecCasopisa = Integer.parseInt(listaPodatakaCasopis.get(2));
			odabir = Integer.parseInt(listaPodatakaCasopis.get(3));

			if (odabir == 1) {
				vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
			} else if (odabir == 2) {
				vrstaPublikacije = VrstaPublikacije.PAPIRNATA;
			}

			brojstranicaCasopisa = Integer.parseInt(listaPodatakaCasopis.get(4));
		}
		
		if (broj == 2)
		{
			nazivCasopisa = listaPodatakaCasopis.get(5);
			godinaCasopisa = Integer.parseInt(listaPodatakaCasopis.get(6));
			mjesecCasopisa = Integer.parseInt(listaPodatakaCasopis.get(7));
			odabir = Integer.parseInt(listaPodatakaCasopis.get(8));

			if (odabir == 1) {
				vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
			} else if (odabir == 2) {
				vrstaPublikacije = VrstaPublikacije.PAPIRNATA;
			}

			brojstranicaCasopisa = Integer.parseInt(listaPodatakaCasopis.get(9));
		}
		
		Casopis casopis = new Casopis(mjesecCasopisa, godinaCasopisa, brojstranicaCasopisa, vrstaPublikacije, nazivCasopisa);
		return casopis;
	}
	
	/**
	 * Unos podataka u memoriju iz "clan.txt"
	 * 
	 * @return clan
	 */
	public static Clan unosPodatakaIzDatotekeClan()
	{
		String ime, prezime, OIB;
		
		CitanjeIPisanjeUDatoteku citanjeClana = new CitanjeIPisanjeUDatoteku();
		
		List<String> listaPodatakaClana = new ArrayList<>();
		
		citanjeClana.citanjeClana();
		
		listaPodatakaClana = citanjeClana.getLinijeDatotekeClana();
		
		OIB = listaPodatakaClana.get(0);
		prezime = listaPodatakaClana.get(1);
		ime = listaPodatakaClana.get(2);
		
		Clan clan = new Clan(ime, prezime, OIB);
		return clan;
	}
	
	/**
	 * Ispisuje publikacije
	 * 
	 * @param publikacija vrsta publikacije
	 */
	public static void ispisiPublikaciju(Publikacija publikacija)
	{
		if (publikacija instanceof Knjiga)
		{
			System.out.print("Naziv publikacije : " + publikacija.getNaziv() + "\n");
			System.out.print("Vrsta : " + publikacija.getVrstaPublikacije() + "\n");
			System.out.print("Broj stranica : " + publikacija.getBrojStranicaPublikacije() + "\n");
			System.out.print("Cijena : " + publikacija.getCijenaPublikacije().setScale(2, BigDecimal.ROUND_HALF_UP) + "\n");
			System.out.print("Jezik knjige : " + ((Knjiga) publikacija).getJezikKnjige() +"\n");
			System.out.print("Izdavac : " + ((Knjiga) publikacija).getIzdavac().getNazivIzdavaca() +"\n");
			System.out.print("Drzava izdavaca: " + ((Knjiga) publikacija).getIzdavac().getDrzavaIzdavaca() +"\n");
			System.out.print("Raspolozivo za posudbu : " + ((Knjiga) publikacija).provjeriRaspolozivost() + "\n");
		}
		else if (publikacija instanceof Casopis)
		{
			System.out.print("Naziv publikacije : " + publikacija.getNaziv() + "\n");
			System.out.print("Vrsta : " + publikacija.getVrstaPublikacije() + "\n");
			System.out.print("Broj stranica : " + publikacija.getBrojStranicaPublikacije() + "\n");
			System.out.print("Cijena : " + publikacija.getCijenaPublikacije() + "\n");
			System.out.print("Mjesec : " + ((Casopis) publikacija).getMjesecIzdavanjaCasopisa() + "\n");
		}
		else
			System.out.println("Publikacija nije ni tip Knjiga ni tip Casopis!");
	}
	
	/**
	 * Provjerava duplikate knjiga
	 * 
	 * @param knjiga1 podatak o knjizi1
	 * @param knjiga2 podatak o knjizi2
	 * @throws DuplikatPublikacijeException baca gresku ako postoji duplikat knjige
	 */
	public static void provjeraDuplikataKnjige(Knjiga knjiga1, Knjiga knjiga2) throws DuplikatPublikacijeException
	{
		if (knjiga2.equals(knjiga1))
		{
			throw new DuplikatPublikacijeException("Pogreska! Unijeli ste duplikat knjige, molimo ponovite upis!");
		}
	}
	
	/**
	 * Provjerava duplikate casopisa
	 * 
	 * @param casopis1 podatak o casopisu1
	 * @param casopis2 podatak o casopisu2
	 * @throws DuplikatPublikacijeException baca gresku ako postoji duplikat casopisa
	 */
	public static void provjeraDuplikataCasopis(Casopis casopis1, Casopis casopis2) throws DuplikatPublikacijeException
	{
		if (casopis2.equals(casopis1))
		{
			throw new DuplikatPublikacijeException("Pogreska! Unijeli ste duplikat casopisa, molimo ponovite upis!");
		}
	}
	
	/**
	 * Obavljanje programa
	 * 
	 * @param args vanjski stringovi
	 */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String odabir, pretraziPublikacije;
		Knjiga knjiga1 = null, knjiga2 = null;
		Casopis casopis1 = null, casopis2 = null;
		Clan clan;
		Boolean pogodio = true;
		Knjiznica<Publikacija> knjiznica = new Knjiznica<Publikacija>();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		CitanjeIPisanjeUDatoteku pisanje = new CitanjeIPisanjeUDatoteku();
		
		System.out.println("Ucitavnje knjiga...");
		
		do {
			try {
				System.out.println("Ucitavam 1. knjigu.");
				logger.info("Ucitavanje 1. knjige!");
				knjiga1 = unosPodatakaIzDatotekeKnjiga(1);
				pogodio = false;
			} catch (NeisplativoObjavljivanjeException e) {
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}
		} while (pogodio);
		
		knjiznica.dodajPublikaciju(knjiga1);
		
		do{
			try {
				System.out.println("Ucitavam 2. knjigu.");
				logger.info("Ucitavanje 2. knjige!");
				knjiga2 = unosPodatakaIzDatotekeKnjiga(2);
				provjeraDuplikataKnjige(knjiga1, knjiga2);
				pogodio = false;
			}catch (NeisplativoObjavljivanjeException e) {
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}catch (DuplikatPublikacijeException e)
			{
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}
		}while(pogodio);
		
		knjiznica.dodajPublikaciju(knjiga2);
		
		System.out.println("Knjige ucitane.");
		System.out.println("Ucitavanje casopisa...");
		
		do{
			try {
				System.out.println("Ucitavam 1. casopis.");
				logger.info("Ucitavanje 1. casopisa!");
				casopis1 = unosPodatakaIzDatotekeCasopis(1);
				pogodio = false;
			}catch (NeisplativoObjavljivanjeException e) {
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}
		}while(pogodio);
		
		knjiznica.dodajPublikaciju(casopis1);
		
		do{
			try {
				System.out.println("Ucitavam 2.casopis.");
				logger.info("Ucitavanje 2. casopisa!");
				casopis2 = unosPodatakaIzDatotekeCasopis(2);
				provjeraDuplikataCasopis(casopis1, casopis2);
				pogodio = false;
			}catch (NeisplativoObjavljivanjeException e) {
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}catch (DuplikatPublikacijeException e)
			{
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}
		}while(pogodio);
		
		knjiznica.dodajPublikaciju(casopis2);
		
		System.out.println("Casopisi ucitani. \n");
		
		List<Publikacija> sortiranaLista = new ArrayList<>();

		sortiranaLista = knjiznica.dohvatiSvePublikacije().stream().sorted((Publikacija p1, Publikacija p2) -> (p1.getCijenaPublikacije().compareTo(p2.getCijenaPublikacije()))).collect(Collectors.toList());
		
		Publikacija najmanja = sortiranaLista.stream().filter(p -> (p.getCijenaPublikacije().doubleValue() > 0)).findFirst().get();
		Publikacija najveca = sortiranaLista.get(sortiranaLista.size() - 1);
		
		System.out.println("Najskuplja publikacija : ");
		ispisiPublikaciju(najveca);
		
		System.out.println();
		
		System.out.println("Najjeftinija publikacija : ");
		ispisiPublikaciju(najmanja);

		System.out.println();
		
		System.out.println("Ucitavanje clana...");
		logger.info("Ucitavam clan!");
		clan = unosPodatakaIzDatotekeClan();
		System.out.println("Clan ucitan. \n");
		
		System.out.println("Publikacije za posudbu : ");		
		
		for (Publikacija p : knjiznica.publikacijeKojeSeMoguPosuditi())
		{
			ispisiPublikaciju(p);
			System.out.println();
		}
		
		System.out.println();
		
		System.out.println("Odaberite publikaciju :");
		System.out.println("1)" + knjiga1.getNaziv());
		System.out.println("2)" + knjiga2.getNaziv());
		System.out.println("3)" + casopis1.getNaziv());
		System.out.println("4)" + casopis2.getNaziv());
		odabir = scan.nextLine();
		
		do
		{
			if (!odabir.equals(knjiga1.getNaziv()) && !odabir.equals(knjiga2.getNaziv()) && !odabir.equals(casopis1.getNaziv()) && !odabir.equals(casopis2.getNaziv()))
			{
				System.out.println("Unesen je krivi naslov, molimo unesite ponovno!");
				odabir = scan.nextLine();
			}
			
		}while(!odabir.equals(knjiga1.getNaziv()) && !odabir.equals(knjiga2.getNaziv()) && !odabir.equals(casopis1.getNaziv()) && !odabir.equals(casopis2.getNaziv()));
				
		final String odabrana = odabir;
		
		Publikacija posudena = knjiznica.dohvatiSvePublikacije().stream().filter(p -> p.getNaziv().equals(odabrana)).findFirst().get();

		Posudba<Publikacija> posudba = new Posudba<Publikacija>(clan, posudena, localDateTime);
		
		System.out.println();
		
		System.out.println("Stanje posudbe : ");
		ispisiPublikaciju(posudena);
		System.out.println("Podaci korisnika :");
		System.out.println("Prezime : " + posudba.getClan().getPrezime());
		System.out.println("Ime : " + posudba.getClan().getIme());
		System.out.println("OIB : " + posudba.getClan().getOIB());
		System.out.println("Datum posudbe : " + localDateTime.format(DateTimeFormatter.ofPattern("dd:MM:yyyy. HH:mm:ss" + "\n")));
		
		System.out.println("Pisem posudbu...");
		pisanje.pisanjePosudbe(posudba);
		System.out.println("Posudba zapisana.");
		
		pisanje.citanjePosudbe(posudba);
		
		System.out.println("Pretrazi publikacije : ");
		pretraziPublikacije = scan.nextLine();
		
		List<Publikacija> novaLista = new ArrayList<>();
		novaLista = knjiznica.dohvatiSvePublikacije().stream().filter(p -> p.getNaziv().contains(pretraziPublikacije)).collect(Collectors.toList());
		
		System.out.println("Pronadene publikacije : ");
		if (novaLista.isEmpty())
		{
			System.out.println("Ne postoji publikacija s tom rijecju!");
		}
		else 
		{
			for (Publikacija publikacija : novaLista)
			{
				ispisiPublikaciju(publikacija);
				System.out.println();
			}
		}
		scan.close();
	}
}