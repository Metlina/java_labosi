package hr.tvz.java.vjezbe.glavna;

import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.entitet.Izdavac;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.entitet.Posudba;
import hr.tvz.java.vjezbe.entitet.Publikacija;
import hr.tvz.java.vjezbe.iznimke.DuplikatPublikacijeException;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Glavna {
	
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static Knjiga upisiKnjigu() throws NeisplativoObjavljivanjeException
	{
		Scanner scan = new Scanner(System.in);
		
		String nazivKnjige, jezikKnjige, nazivIzdavaca, drzavaIzdavaca;
		Integer odabir;
		String vrstaPublikacije = null;
		Integer godinaKnjige, brojStranicaKnjige;
		Double cijenaPoStranici;
		
		System.out.print("Unesite naziv knjige : \n");
		nazivKnjige = scan.nextLine();
		System.out.print("Unesite jezik knjige : \n");
		jezikKnjige = scan.nextLine();
		
		if (jezikKnjige.equals("hrvatski") || jezikKnjige.equals("Hrvatski"))
		{
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_HR;
		}
		else
		{
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
		}		
		
		System.out.print("Unesite godinu knjige : \n");
		godinaKnjige = scan.nextInt();
		System.out.print("Unesite broj stranica knjige : \n");
		brojStranicaKnjige = scan.nextInt();
		System.out.print("Unesite vrstu : \n");
		System.out.print("1. Elektronicka publikacija \n");
		System.out.print("2. Papirnata publikacija \n");
		odabir = scan.nextInt();
		scan.nextLine();
		
		do
		{
			if (odabir != 1 && odabir != 2)
			{
				System.out.print("Unijeli ste krivi odabir, molim unesite ponovno! \n");
				odabir = scan.nextInt();
			}
		}while (odabir != 1 && odabir != 2);
		
		if (odabir == 1)
		{
			vrstaPublikacije = Publikacija.VRSTA_PUBLIKACIJE_ELEKTRONICKA;
		}
		else if (odabir == 2)
		{
			vrstaPublikacije = Publikacija.VRSTA_PUBLIKACIJE_PAPIRNATA;
		}
		
		System.out.print("Unesite izdavaca : \n");
		nazivIzdavaca = scan.nextLine();
		System.out.print("Unesite drzavu izdavaca : \n");
		drzavaIzdavaca = scan.nextLine();
		
		Izdavac izdavac = new Izdavac(nazivIzdavaca, drzavaIzdavaca);
		Knjiga knjiga = new Knjiga(nazivKnjige, jezikKnjige, izdavac, godinaKnjige, vrstaPublikacije, cijenaPoStranici, brojStranicaKnjige);
		
		//scan.close();
		
		return knjiga;
	}
	
	public static Casopis upisCasopisa() throws NeisplativoObjavljivanjeException
	{
		Scanner scan = new Scanner(System.in);
		
		String nazivCasopisa;
		Integer godinaCasopisa, brojstranicaCasopisa, mjesecCasopisa;
		Integer odabir;
		String vrstaPublikacije = null;
		
		System.out.print("Unesite naziv casopisa : " + "\n");
		nazivCasopisa = scan.nextLine();
		System.out.print("Unesite godinu casopisa : " + "\n");
		godinaCasopisa = scan.nextInt();
		System.out.print("Unesite broj stranica casopisa : " + "\n");
		brojstranicaCasopisa = scan.nextInt();
		System.out.print("Unesite mjesec casopisa" + "\n");
		mjesecCasopisa = scan.nextInt();
		System.out.print("Unesite vrstu : \n");
		System.out.print("1. Elektronicka publikacija \n");
		System.out.print("2. Papirnata publikacija \n");
		odabir = scan.nextInt();
		
		do
		{
			if (odabir != 1 && odabir != 2)
			{
				System.out.print("Unijeli ste krivi odabir, molim unesite ponovno! \n");
				odabir = scan.nextInt();
			}
		}while (odabir != 1 && odabir != 2);
		
		if (odabir == 1)
		{
			vrstaPublikacije = Publikacija.VRSTA_PUBLIKACIJE_ELEKTRONICKA;
		}
		else if (odabir == 2)
		{
			vrstaPublikacije = Publikacija.VRSTA_PUBLIKACIJE_PAPIRNATA;
		}
		
		Casopis casopis = new Casopis(mjesecCasopisa, godinaCasopisa, brojstranicaCasopisa, vrstaPublikacije, nazivCasopisa);
		
		return casopis;
		
	}
	
	public static Clan upisiClana()
	{
		Scanner scan = new Scanner(System.in);
		
		String ime, prezime, OIB;
		
		System.out.print("Unesite OIB clana : \n");
		OIB = scan.nextLine();
		System.out.print("Unesite ime clana : \n");
		ime = scan.nextLine();
		System.out.print("Unesite prezime clana : \n");
		prezime = scan.nextLine();
		System.out.print("\n");
		
		Clan clan = new Clan(ime, prezime, OIB);
		
		//scan.close();
		
		return clan;
	}
	
	public static void ispisiKnjiga(Publikacija publikacija)
	{
		System.out.print("Naziv publikacije :" + publikacija.getNaziv() + "\n");
		System.out.print("Vrsta : " + publikacija.getVrstaPublikacije() + "\n");
		System.out.print("Broj stranica : " + publikacija.getBrojStranicaPublikacije() + "\n");
		System.out.print("Cijena : " + publikacija.getCijenaPublikacije().setScale(2, BigDecimal.ROUND_HALF_UP) + "\n");
		System.out.print("Jezik knjige : " + ((Knjiga) publikacija).getJezikKnjige() +"\n");
		System.out.print("Izdavac : " + ((Knjiga) publikacija).getIzdavac().getNazivIzdavaca() +"\n");
		System.out.print("Drzava izdavaca: " + ((Knjiga) publikacija).getIzdavac().getDrzavaIzdavaca() +"\n");
		System.out.print("Raspolozivo za posudbu : " + ((Knjiga) publikacija).provjeriRaspolozivost() + "\n");
		
	}
	
	public static void ispisiCasopis(Publikacija publikacija)
	{
		System.out.print("Naziv publikacije :" + publikacija.getNaziv() + "\n");
		System.out.print("Vrsta : " + publikacija.getVrstaPublikacije() + "\n");
		System.out.print("Broj stranica : " + publikacija.getBrojStranicaPublikacije() + "\n");
		System.out.print("Cijena : " + publikacija.getCijenaPublikacije() + "\n");
		System.out.print("Mjesec : " + ((Casopis) publikacija).getMjesecIzdavanjaCasopisa() + "\n");
	}
	
	public static void provjeraDuplikataKnjige(Knjiga knjiga1, Knjiga knjiga2) throws DuplikatPublikacijeException
	{
		if (knjiga2.equals(knjiga1))
		{
			throw new DuplikatPublikacijeException("Pogreska! Unijeli ste duplikat knjige, molimo ponovite upis!");
		}
	}
	
	public static void provjeraDuplikataCasopis(Casopis casopis1, Casopis casopis2) throws DuplikatPublikacijeException
	{
		if (casopis2.equals(casopis1))
		{
			throw new DuplikatPublikacijeException("Pogreska! Unijeli ste duplikat casopisa, molimo ponovite upis!");
		}
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
	
		String odabir;
		Knjiga knjiga1 = null, knjiga2 = null;
		Casopis casopis1 = null, casopis2 = null;
		Clan clan;
		Publikacija pub;
		Boolean pogodio = true;
		Publikacija[] publikacije = new Publikacija[4];
		
		do {
			try {
				System.out.print("Unos 1. knjige : \n");
				logger.info("Unos 1. knjige!");
				knjiga1 = upisiKnjigu();
				pogodio = false;
			} catch (NeisplativoObjavljivanjeException e) {
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}
		} while (pogodio);
		
		do{
			try {
				System.out.print("Unos 2. knjige : \n");
				logger.info("Unos 2. knjige!");
				knjiga2 = upisiKnjigu();
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
		
		do{
			try {
				System.out.print("Unos 1. casopisa : \n");
				logger.info("Unos 1. casopisa!");
				casopis1 = upisCasopisa();
				pogodio = false;
			}catch (NeisplativoObjavljivanjeException e) {
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}
		}while(pogodio);
		
		do{
			try {
				System.out.print("Unos 2. casopisa : \n");
				logger.info("Unos 2. casopisa!");
				casopis2 = upisCasopisa();
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
		
		publikacije[0] = knjiga1;
		publikacije[1] = knjiga2;
		publikacije[2] = casopis1;
		publikacije[3] = casopis2;
		
		Arrays.sort(publikacije, (Publikacija p1, Publikacija p2) -> (p1.getCijenaPublikacije().compareTo(p2.getCijenaPublikacije())));
		
		Publikacija najmanja = publikacije[0];
		Publikacija najveca = publikacije[3];
			
		System.out.println("Najskuplja publikacija : ");
		
		if (najveca instanceof Knjiga)
		{
			ispisiKnjiga(najveca);
		}
		else if (najveca instanceof Casopis)
		{
			ispisiCasopis(najveca);
		}
		
		System.out.println("Najjeftinija publikacija : ");
		
		if (najmanja instanceof Knjiga)
		{
			ispisiKnjiga(najmanja);
		}
		else if (najmanja instanceof Casopis)
		{
			ispisiCasopis(najmanja);
		}
		
		System.out.print("Unos clana : \n");
		clan = upisiClana();
		System.out.print("Odaberite publikaciju : \n");
		System.out.print("1)" + knjiga1.getNaziv() + "\n");
		System.out.print("2)" + knjiga2.getNaziv() + "\n");
		System.out.print("3)" + casopis1.getNaziv() + "\n");
		System.out.print("4)" + casopis2.getNaziv() + "\n");
		odabir = scan.nextLine();
		
		do
		{
			if (!odabir.equals(knjiga1.getNaziv()) && !odabir.equals(knjiga2.getNaziv()) && !odabir.equals(casopis1.getNaziv()) && !odabir.equals(casopis2.getNaziv()))
			{
				System.out.print("Unesen je krivi naslov, molimo unesite ponovno! \n");
				odabir = scan.nextLine();
			}
			
		}while(!odabir.equals(knjiga1.getNaziv()) && !odabir.equals(knjiga2.getNaziv()) && !odabir.equals(casopis1.getNaziv()) && !odabir.equals(casopis2.getNaziv()));
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		System.out.print("Stanje posudbe : \n");
		
		if (odabir.equals(knjiga1.getNaziv()))
		{
			Posudba posudba = new Posudba(clan, knjiga1, localDateTime);
			ispisiKnjiga(knjiga1);
		}
		else if (odabir.equals(knjiga2.getNaziv()))
		{
			Posudba posudba = new Posudba(clan, knjiga2, localDateTime);
			ispisiKnjiga(knjiga2);
		}
		else if (odabir.equals(casopis1.getNaziv()))
		{
			Posudba posudba = new Posudba(clan, casopis1, localDateTime);
			ispisiCasopis(casopis1);
		}
		else if (odabir.equals(casopis2.getNaziv()))
		{
			Posudba posudba = new Posudba(clan, casopis2, localDateTime);
			ispisiCasopis(casopis2);
		}
		
		System.out.print("Podaci korisnika : \n");
		System.out.print("Prezime : " + clan.getPrezime() +"\n");
		System.out.print("Ime : " + clan.getIme() +"\n");
		System.out.print("OIB : " + clan.getOIB() +"\n");
		System.out.print("Datum posudbe : " + localDateTime.format(DateTimeFormatter.ofPattern("dd:MM:yyyy. HH:mm:ss")) +"\n");
		
		scan.close();

	}

}